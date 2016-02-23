package br.com.agente.agentes.tarefaAssincrona;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.agente.agentes.R;
import br.com.agente.agentes.activity.DetalheAgente;
import br.com.agente.agentes.bean.Agente;
import br.com.agente.agentes.util.Alerta;
import br.com.agente.agentes.util.AreaDeTransferencia;

/**
 * Created by messias on 2/19/16.
 *
 * @author Messias Lima
 * @version 1.0
 * @since 19 feb 2016
 */
public class ValidarAgente extends AsyncTask<String, Void, Agente> {

    private Context context;
    private ProgressDialog progressDialog;

    public ValidarAgente(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.obtendo_dados));
        progressDialog.show();
    }

    @Override
    protected Agente doInBackground(String... params) {
        String json = Servidor.fazerGet(context, "validaragente.php?id=" + params[0]);
        try {
            JSONObject jsonObject = new JSONObject(json);
            Agente agente = new Agente();
            agente.setId(jsonObject.getInt("id"));
            agente.setNome(jsonObject.getString("nome"));
            agente.setMatricula(jsonObject.getString("matricula"));
            agente.setLatitude(jsonObject.getDouble("latitude"));
            agente.setLongitude(jsonObject.getDouble("longitude"));
            agente.setFoto(jsonObject.getString("foto"));
            return agente;
        } catch (JSONException e) {
            e.printStackTrace();
            if (json.trim().length()>0){
                return new Agente();
            }
        }
        return null;
    }
//.replace("\\","")
    @Override
    protected void onPostExecute(Agente agente) {
        progressDialog.dismiss();
        if (agente!=null){
            if (agente.getId()==0){
                Alerta.mandarAlerta(context,context.getString(R.string.agente_não_encontrado));
            }else{
                Intent intent = new Intent(context, DetalheAgente.class);
                AreaDeTransferencia.agente = agente;
                context.startActivity(intent);
            }
        }else {
            Alerta.mandarAlerta(context,context.getString(R.string.falha_de_conexao));
        }
    }


}
