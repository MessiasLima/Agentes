package br.com.agente.agentes.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import br.com.agente.agentes.R;
import br.com.agente.agentes.util.Fonte;

public class Splash extends AppCompatActivity {

    TextView textView;
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        textView = (TextView) findViewById(R.id.titulo_logo);
        Fonte.setarFonteTitulo(textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (verificarPlayServices()){
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    startActivity(new Intent(Splash.this,MenuPrincipal.class));
                    finish();
                }
            }.execute();
        }
    }

    private boolean verificarPlayServices() {
        final int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Play services sem suporte", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        return true;
    }
}
