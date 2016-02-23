package br.com.agente.agentes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Agente;
import br.com.agente.agentes.util.AreaDeTransferencia;
import br.com.agente.agentes.util.Download;

public class DetalheAgente extends AppCompatActivity {

    private ImageView imageViewFoto;
    private TextView textViewNome;
    private TextView textViewMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_agente);
        iniciarComponentes();
        mostrarInformacoes();
    }

    private void mostrarInformacoes() {
        Agente agente = AreaDeTransferencia.agente;
        AreaDeTransferencia.agente=null;
        textViewNome.setText(agente.getNome());
        textViewMatricula.setText(agente.getMatricula());
        Download.baixarImagem(agente.getFoto(),imageViewFoto);
    }

    private void iniciarComponentes() {
        imageViewFoto = (ImageView) findViewById(R.id.image_view_foto);
        textViewMatricula = (TextView) findViewById(R.id.text_view_matricula_agente);
        textViewNome = (TextView) findViewById(R.id.text_view_nome_agente);
    }
}
