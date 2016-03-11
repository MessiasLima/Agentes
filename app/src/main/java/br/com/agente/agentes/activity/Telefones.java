package br.com.agente.agentes.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.agente.agentes.R;
import br.com.agente.agentes.adapter.TelefoneAdapter;
import br.com.agente.agentes.bean.Telefone;

public class Telefones extends AppCompatActivity {

    List<Telefone> listTelefones = new ArrayList<>();
    Map<String,String> hashTelefones = new HashMap<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefones);
        preencherMapDeNomes();
        iniciarComponentes();
    }

    private void preencherMapDeNomes() {
        hashTelefones.put("Denuncia de Pontos de lixo de Fortaleza", "156");
        hashTelefones.put("Centro de controle de Zoonoses","3105-1026");

        Set<String> keys = hashTelefones.keySet();

        for (String key: keys){
            Telefone t = new Telefone();
            t.setNome(key);
            t.setTelefone(hashTelefones.get(key));
            listTelefones.add(t);
        }
    }

    private void iniciarComponentes() {
        AdView adView = (AdView) findViewById(R.id.adView_telefones);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        listView = (ListView) findViewById(R.id.listView_telefone);
        TelefoneAdapter adapter = new TelefoneAdapter(this,R.layout.telefone_list_item,listTelefones);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Telefone telefone = (Telefone)parent.getItemAtPosition(position);

                Intent chamada = new Intent(Intent.ACTION_DIAL);
                chamada.setData(Uri.parse("tel:" + telefone.getTelefone() ));
                startActivity(chamada);

            }
        });
    }
}
