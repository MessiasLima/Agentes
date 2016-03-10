package br.com.agente.agentes.tarefaAssincrona;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import br.com.agente.agentes.R;
import br.com.agente.agentes.bean.Foco;
import br.com.agente.agentes.util.Alerta;
import br.com.agente.agentes.util.MultipartUtility;


/**
 * Created by messias on 3/9/16.
 *
 * @author Messias Lima
 * @version 1.0
 */
public class EnviarNovoFoco extends AsyncTask<Void, Void, Boolean> {
    Foco foco;
    Activity context;
    ProgressDialog progressDialog;

    public EnviarNovoFoco(Activity context, Foco foco) {
        this.foco = foco;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.enviando_dados));
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            MultipartUtility multipartUtility = new MultipartUtility(Servidor.HOST + Servidor.CAMINHO_DO_SISTEMA + "recebeUpload.php", "utf-8");
            multipartUtility.addFormField("rua", foco.getNome());
            multipartUtility.addFormField("descricao", foco.getDescricao());
            multipartUtility.addFormField("latitude", Double.toString(foco.getLatitude()));
            multipartUtility.addFormField("longitude", Double.toString(foco.getLongitude()));
            multipartUtility.addFilePart("arquivo", foco.getFotoFile());
            String resposta = multipartUtility.finish();
            if (resposta == null) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alerta.mandarAlerta(context, "Erro desconhecido, verifique stacktrace");
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean sucesso) {
        progressDialog.dismiss();
        if (sucesso) {
            Toast.makeText(context.getApplicationContext(), R.string.envio_sucesso, Toast.LENGTH_LONG).show();
            context.finish();
        } else {
            Alerta.mandarAlerta(context, context.getString(R.string.falha_de_conexao));

        }
    }
}
