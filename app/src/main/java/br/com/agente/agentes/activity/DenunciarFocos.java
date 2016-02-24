package br.com.agente.agentes.activity;

import android.location.Location;
import android.support.design.widget.Snackbar;
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

import br.com.agente.agentes.R;
import br.com.agente.agentes.gps.ListenerDeLocalizacao;
import br.com.agente.agentes.util.ControleDeMapa;
import br.com.agente.agentes.util.Fonte;

public class DenunciarFocos extends AppCompatActivity {

    GoogleMap mapa;
    TextView textoExplicativo, titulo, voltar;
    MarkerOptions markerOptions;
    Marker marcadorUsuario;
    LinearLayout botaoVoltar;

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
        if (marcadorUsuario == null) {
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
        }
        ControleDeMapa.moverCamera(mapa, location);
    }

}
