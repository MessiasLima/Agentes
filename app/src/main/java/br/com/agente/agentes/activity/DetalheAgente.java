package br.com.agente.agentes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Agente;
import br.com.agente.agentes.util.AreaDeTransferencia;
import br.com.agente.agentes.util.Download;
import br.com.agente.agentes.util.Fonte;

public class DetalheAgente extends AppCompatActivity {

    private ImageView imageViewFoto;
    private TextView textViewNome, textViewMatricula, nomeAgente, matriculaAgente;

    //TODO consertar layout do detalhe do agente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_agente_externo);
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
        Fonte.setarFonteTextos(textViewMatricula);

        textViewNome = (TextView) findViewById(R.id.text_view_nome_agente);
        Fonte.setarFonteTextos(textViewNome);

        nomeAgente = (TextView) findViewById(R.id.nome_agente);
        Fonte.setarFonteTitulo(nomeAgente);

        matriculaAgente = (TextView) findViewById(R.id.matricula_agente);
        Fonte.setarFonteTitulo(matriculaAgente);

        AdView adView = (AdView) findViewById(R.id.adView_detalhe_agente);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
