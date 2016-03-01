package br.com.agente.agentes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Foco;
import br.com.agente.agentes.util.AreaDeTransferencia;
import br.com.agente.agentes.util.Download;
import br.com.agente.agentes.util.Fonte;

public class DetalheFoco extends AppCompatActivity {

    private Foco foco;
    ImageView imagem;
    private TextView nome, nomeFoco, tipo, tipoFoco;

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

        nome = (TextView) findViewById(R.id.nome_foco);
        Fonte.setarFonteTitulo(nome);

        nomeFoco =  (TextView) findViewById(R.id.nome_foco_dinamico);
        Fonte.setarFonteTextos(nomeFoco);
        nomeFoco.setText(foco.getNome());

        tipo =  (TextView) findViewById(R.id.tipo_foco);
        Fonte.setarFonteTitulo(tipo);

        tipoFoco =  (TextView) findViewById(R.id.tipo_foco_dinamico);
        Fonte.setarFonteTextos(tipoFoco);
        tipoFoco.setText(foco.getClasse());
    }

}
