package br.com.agente.agentes.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.gps.ListenerDeLocalizacao;
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
        setContentView(R.layout.activity_menu_principal);
        iniciarComponentes();
        ativarGPS();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void ativarGPS() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listenerDeLocalizacao = new ListenerDeLocalizacao(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30 * 1000, 5, listenerDeLocalizacao);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30 * 1000, 5, listenerDeLocalizacao);
        } catch (Exception ex){
            ex.printStackTrace();
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

        botaoAgentesAreaTexto = (TextView) findViewById(R.id.botao_agentes_area_texto);
        Fonte.setarFonteTitulo(botaoAgentesAreaTexto);
        botaoAgentesArea = (LinearLayout) findViewById(R.id.botao_agentes_area);
        botaoAgentesArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, AgentesDaArea.class));
            }
        });

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
    }

    @Override
    protected void onDestroy() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(listenerDeLocalizacao);
        super.onDestroy();
    }
}
