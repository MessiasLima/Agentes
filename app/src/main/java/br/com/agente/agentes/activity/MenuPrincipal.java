package br.com.agente.agentes.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.gps.ListenerDeLocalizacao;
import br.com.agente.agentes.util.Alerta;
import br.com.agente.agentes.util.Fonte;

public class MenuPrincipal extends AppCompatActivity {

    TextView tituloAplicativo;

    TextView botaoConsultarTexto;
    LinearLayout botaoConsultar;

    TextView botaoAgentesAreaTexto;
    LinearLayout botaoAgentesArea;

    TextView botaoDenuciarFocosTexto;
    LinearLayout botaoDenunciarFocos;

    TextView botaoTelefonesTexto;
    LinearLayout botaoTelefones;

    LocationManager locationManager;
    static ListenerDeLocalizacao listenerDeLocalizacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_externo);
        iniciarComponentes();
    }

    @Override
    protected void onResume() {
        ativarGPS();
        super.onResume();
    }

    private void ativarGPS() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listenerDeLocalizacao = new ListenerDeLocalizacao(this);


        // Se não possui permissão
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Verifica se já mostramos o alerta e o usuário negou na 1ª vez.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Caso o usuário tenha negado a permissão anteriormente, e não tenha marcado o check "nunca mais mostre este alerta"
                // Podemos mostrar um alerta explicando para o usuário porque a permissão é importante.
                //Alerta.mandarAlerta(this, getString(R.string.falta_permissao_location));
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

            } else {
                // Solicita a permissão
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

            }
        } else {
            // Tudo OK, podemos prosseguir.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30 * 1000, 50, listenerDeLocalizacao);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation!=null){
                ListenerDeLocalizacao.ultimaLocalizacaoConhecida=lastKnownLocation;
            }
            try {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30 * 1000, 50, listenerDeLocalizacao);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void iniciarComponentes() {
        tituloAplicativo = (TextView) findViewById(R.id.textView_titulo_app);
        Fonte.setarFonteTitulo(tituloAplicativo);

        botaoConsultarTexto = (TextView) findViewById(R.id.botao_consultar_texto);
        Fonte.setarFonteTitulo(botaoConsultarTexto);
        botaoConsultar = (LinearLayout) findViewById(R.id.botao_consultar);
        botaoConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, ConsultarAgente.class));
            }
        });

       /* botaoAgentesAreaTexto = (TextView) findViewById(R.id.botao_agentes_area_texto);
        Fonte.setarFonteTitulo(botaoAgentesAreaTexto);
        botaoAgentesArea = (LinearLayout) findViewById(R.id.botao_agentes_area);
        botaoAgentesArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, AgentesDaArea.class));
            }
        });*/

        botaoDenuciarFocosTexto = (TextView) findViewById(R.id.botao_denunciar_focos_texto);
        Fonte.setarFonteTitulo(botaoDenuciarFocosTexto);
        botaoDenunciarFocos = (LinearLayout) findViewById(R.id.botao_denunciar_focos);
        botaoDenunciarFocos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, DenunciarFocos.class));
            }
        });

        botaoTelefonesTexto = (TextView) findViewById(R.id.botao_telefones_texto);
        Fonte.setarFonteTitulo(botaoTelefonesTexto);
        botaoTelefones = (LinearLayout) findViewById(R.id.botao_telefones);
        botaoTelefones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, Telefones.class));
            }
        });

        AdView adView = (AdView) findViewById(R.id.adView_menu_principal);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Alerta.mandarAlerta(this, getString(R.string.falta_permissao_location));
            return;
        }
        locationManager.removeUpdates(listenerDeLocalizacao);

    }
}
