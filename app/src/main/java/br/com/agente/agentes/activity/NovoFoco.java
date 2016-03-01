package br.com.agente.agentes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.Marker;

import br.com.agente.agentes.R;

public class NovoFoco extends AppCompatActivity {

    public static Marker marcadorNovoFoco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_foco);
    }
}
