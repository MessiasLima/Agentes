package br.com.agente.agentes.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.util.Fonte;

public class Splash extends AppCompatActivity {

    TextView textView;

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
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
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
