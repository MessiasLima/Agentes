package br.com.agente.agentes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.agente.agentes.R;
import br.com.agente.agentes.tarefaAssincrona.ValidarAgente;
import br.com.agente.agentes.util.Fonte;

public class ConsultarAgente extends AppCompatActivity {

    private EditText editTextMatricula;
    private TextView textoExplicativo, botaoConsultarTexto;
    private LinearLayout botaoConsultar;
    private TextView botaoTelefonesTexto;
    private LinearLayout botaoTelefones;
    private TextView botaoVoltarTexto;
    private LinearLayout botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_agente);
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        textoExplicativo = (TextView) findViewById(R.id.texto_explicativo_consultar_agente);
        Fonte.setarFonteTextos(textoExplicativo);

        editTextMatricula = (EditText) findViewById(R.id.edit_text_matricula);

        botaoConsultarTexto= (TextView) findViewById(R.id.botao_consultar_main_texto);
        Fonte.setarFonteTitulo(botaoConsultarTexto);
        botaoConsultar = (LinearLayout) findViewById(R.id.botao_consultar_main);
        botaoConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarAgente();
            }
        });

        botaoTelefonesTexto = (TextView) findViewById(R.id.botao_telefones_texto);
        Fonte.setarFonteTitulo(botaoTelefonesTexto);
        botaoTelefones = (LinearLayout) findViewById(R.id.botao_telefones);
        botaoTelefones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConsultarAgente.this, Telefones.class));
            }
        });

        botaoVoltarTexto= (TextView) findViewById(R.id.botao_voltar_texto);
        Fonte.setarFonteTitulo(botaoVoltarTexto);
        botaoVoltar = (LinearLayout) findViewById(R.id.botao_voltar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void validarAgente(){
        ValidarAgente validarAgente = new ValidarAgente(this);
        validarAgente.execute(editTextMatricula.getEditableText().toString());
    }

}
