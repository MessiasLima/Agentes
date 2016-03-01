package br.com.agente.agentes.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Foco;
import br.com.agente.agentes.gps.CustomInfoWindow;
import br.com.agente.agentes.gps.ListenerDeLocalizacao;
import br.com.agente.agentes.tarefaAssincrona.RecuperarFocos;
import br.com.agente.agentes.util.AreaDeTransferencia;
import br.com.agente.agentes.util.ControleDeMapa;
import br.com.agente.agentes.util.Fonte;

public class DenunciarFocos extends AppCompatActivity {

    GoogleMap mapa;
    TextView textoExplicativo, titulo, voltar;
    MarkerOptions markerOptions, markerOptionsNovoFoco, markerOptionsFoco;
    Marker marcadorUsuario, marcadorFoco;
    LinearLayout botaoVoltar;
    RecuperarFocos recuperarFocos;
    Map<String, Marker> hashMarcadoresDeFoco = new HashMap<>();
    public static Map<String, Foco> hashFocos = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denunciar_foco_externa);
        iniciarComponentes();
        registrarSeNoListenerDeLocalizacao();
    }

    private void registrarSeNoListenerDeLocalizacao() {
        MenuPrincipal.listenerDeLocalizacao.setAgentesDaArea(this);
    }

    private void iniciarComponentes() {

        textoExplicativo = (TextView) findViewById(R.id.botao_denunciar_foco_texto_explicativo);
        Fonte.setarFonteTextos(textoExplicativo);

        titulo = (TextView) findViewById(R.id.titulo_denunciar_foco);
        Fonte.setarFonteTitulo(titulo);

        botaoVoltar = (LinearLayout) findViewById(R.id.botao_voltar_denunciar_foco);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        voltar = (TextView) findViewById(R.id.voltar_denunciar_foco);
        Fonte.setarFonteTitulo(voltar);

        configurarMapa();
    }

    private void configurarMapa() {
        if (mapa == null) {
            mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.denunciar_foco_mapa)).getMap();
            mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    adicionarMarcadorNovoFoco(latLng);
                }
            });
            mapa.setInfoWindowAdapter(new CustomInfoWindow(this));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mapa.setMyLocationEnabled(true);
            mapa.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    String nome = marker.getTitle();
                    if (nome != null && nome.equals("Eu")) {
                        startActivity(new Intent(DenunciarFocos.this, NovoFoco.class));
                        NovoFoco.marcadorNovoFoco = marker;
                    } else {
                        AreaDeTransferencia.foco = hashFocos.get(nome);
                        startActivity(new Intent(DenunciarFocos.this, DetalheFoco.class));
                    }
                }
            });
        }
        if (ListenerDeLocalizacao.localizacaoAtual != null) {
            adicionarMarcadorUsuario(ListenerDeLocalizacao.localizacaoAtual);
        } else {
            Snackbar.make(textoExplicativo, R.string.sem_localizacao, Snackbar.LENGTH_LONG).show();
        }
    }

    //Adicinar marcador do jogador
    public void adicionarMarcadorUsuario(Location location) {
        //Se o marcador do jogador ainda não foi colocado
        /*if (marcadorUsuario == null) {
            if (markerOptions == null) {
                markerOptions = new MarkerOptions();
            }
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marcador_usuario))
                    .title(getString(R.string.eu))
                    .position(new LatLng(location.getLatitude(), location.getLongitude()));

            marcadorUsuario = mapa.addMarker(markerOptions);

        } else {
            //Se o marcador já existe, ele apenas atualiza a posição
            marcadorUsuario.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
        }*/
        ControleDeMapa.moverCamera(mapa, location);
        colocarFocosNoMapa(location);
    }

    private void colocarFocosNoMapa(Location location) {
        if (recuperarFocos == null) {
            recuperarFocos = new RecuperarFocos(this);
            recuperarFocos.execute(location);
        } else {
            if (recuperarFocos.localUltimaConsulta == null) {
                if (!recuperarFocos.isExecutando()) {
                    recuperarFocos.execute(location);
                }
            } else if (recuperarFocos.localUltimaConsulta.distanceTo(location) > 50) {
                if (!recuperarFocos.isExecutando()) {
                    recuperarFocos.execute(location);
                }
            }
        }
    }

    //Adicinar marcador do Foco
    public void adicionarMarcadorNovoFoco(LatLng location) {
        //Se o marcador do foco ainda não foi colocado
        if (marcadorFoco == null) {
            if (markerOptionsNovoFoco == null) {
                markerOptionsNovoFoco = new MarkerOptions();
            }
            markerOptionsNovoFoco//.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marcador_usuario))
                    .title(getString(R.string.eu))
                    .draggable(true)
                    .position(location);

            marcadorFoco = mapa.addMarker(markerOptionsNovoFoco);

        } else {
            //Se o marcador já existe, ele apenas atualiza a posição
            marcadorFoco.setPosition(location);
        }
        marcadorFoco.showInfoWindow();
    }

    public void manipularMarcadoresDeFoco(List<Foco> focos) {
        for (Foco f : focos) {

            if (!hashFocos.containsKey(f.getNome())) {
                hashFocos.put(f.getNome(), f);
            }

            if (!hashMarcadoresDeFoco.containsKey(f.getNome())) {
                if (markerOptionsFoco == null) {
                    markerOptionsFoco = new MarkerOptions();
                }
                markerOptionsFoco.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_mosquito))
                        .title(f.getNome())
                        .snippet(f.getClasse())
                        .draggable(false)
                        .position(new LatLng(f.getLatitude(), f.getLongitude()));

                Marker mkr = mapa.addMarker(markerOptionsFoco);

                hashMarcadoresDeFoco.put(f.getNome(), mkr);
            }
        }
    }

    @Override
    protected void onDestroy() {
        hashFocos.clear();
        hashMarcadoresDeFoco.clear();
        super.onDestroy();
    }
}
