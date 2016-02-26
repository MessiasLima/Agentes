package br.com.agente.agentes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Foco;
import br.com.agente.agentes.util.AreaDeTransferencia;
import br.com.agente.agentes.util.Download;

public class DetalheFoco extends AppCompatActivity {

    private Foco foco;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_foco);
        foco = AreaDeTransferencia.foco;
        AreaDeTransferencia.foco = null;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        imagem = (ImageView) findViewById(R.id.img_detalhe_foco);
        if (foco.getFotoBitmap() == null) {
            Download.baixarImagem(foco.getFoto(),imagem);
        } else {
            imagem.setImageBitmap(foco.getFotoBitmap());
        }
    }

}
