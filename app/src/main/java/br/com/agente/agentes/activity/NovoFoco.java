package br.com.agente.agentes.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.maps.model.Marker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Foco;
import br.com.agente.agentes.tarefaAssincrona.EnviarNovoFoco;
import br.com.agente.agentes.util.Fonte;

public class NovoFoco extends AppCompatActivity {

    public static Marker marcadorNovoFoco;
    TextView labelRua, labelDescricao, botaoConfirmarLabel, botaoVoltarLabel;
    EditText fieldRua, fieldDescricao;
    ImageView imageView;
    LinearLayout botaoConfirmar, botaoVoltar;
    Bitmap bitmapFoto;
    File fileFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_foco_externa);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        labelRua = (TextView) findViewById(R.id.novo_foco_label_rua);
        Fonte.setarFonteTitulo(labelRua);

        labelDescricao = (TextView) findViewById(R.id.novo_foco_label_descricao);
        Fonte.setarFonteTitulo(labelDescricao);

        fieldRua = (EditText) findViewById(R.id.novo_foco_field_rua);
        //Fonte.setarFonteTitulo(fieldRua);

        fieldDescricao = (EditText) findViewById(R.id.novo_foco_field_descricao);

        imageView = (ImageView) findViewById(R.id.novo_foco_image_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    fileFoto = File.createTempFile("agentes"+System.currentTimeMillis(),".jpg",Environment.getExternalStorageDirectory());
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileFoto));
                    startActivityForResult(intent, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        botaoConfirmarLabel = (TextView) findViewById(R.id.novo_foco_botao_confirmar_label);
        Fonte.setarFonteTitulo(botaoConfirmarLabel);

        botaoVoltarLabel = (TextView) findViewById(R.id.novo_foco_botao_voltar_label);
        Fonte.setarFonteTitulo(botaoVoltarLabel);

        botaoVoltar = (LinearLayout) findViewById(R.id.novo_foco_botao_voltar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        botaoConfirmar = (LinearLayout) findViewById(R.id.novo_foco_botao_confirmar);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNovoFoco();
            }
        });
    }

    private void enviarNovoFoco() {
        String rua = fieldRua.getEditableText().toString();
        String descrição = fieldDescricao.getEditableText().toString();

        if (rua.trim().length() == 0 || descrição.trim().length() == 0 || bitmapFoto == null) {
            Snackbar.make(imageView, R.string.preencha_tudo, Snackbar.LENGTH_LONG).show();
        } else {
            Foco foco = new Foco();
            foco.setNome(rua);
            foco.setDescricao(descrição);
            foco.setLatitude(marcadorNovoFoco.getPosition().latitude);
            foco.setLongitude(marcadorNovoFoco.getPosition().longitude);
            foco.setFotoBitmap(bitmapFoto);
            foco.setFotoFile(fileFoto);
            new EnviarNovoFoco(this, foco).execute();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {

                try {

                    bitmapFoto = BitmapFactory.decodeFile(fileFoto.getAbsolutePath());
                    FileOutputStream fileOutputStream =  new FileOutputStream(fileFoto);
                    bitmapFoto.compress(Bitmap.CompressFormat.JPEG,50,fileOutputStream);

                    fileOutputStream.flush();
                    fileOutputStream.close();

                    imageView.setImageBitmap(bitmapFoto);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
