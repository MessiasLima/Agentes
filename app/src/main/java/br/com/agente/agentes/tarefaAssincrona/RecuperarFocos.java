package br.com.agente.agentes.tarefaAssincrona;

import android.location.Location;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.agente.agentes.R;
import br.com.agente.agentes.activity.DenunciarFocos;
import br.com.agente.agentes.bean.Foco;
import br.com.agente.agentes.util.Alerta;

/**
 * Created by messias on 2/25/16.
 *
 * @author Messias Lima
 * @version 1.0
 * @since 25/02/2016
 */
public class RecuperarFocos extends AsyncTask<Location, Void, List<Foco>> {

    public static DenunciarFocos denunciarFocos;
    public static Location localUltimaConsulta;
    public static boolean executando;

    public boolean isExecutando() {
        return executando;
    }

    public void setExecutando(boolean executando) {
        this.executando = executando;
    }

    public RecuperarFocos(DenunciarFocos denunciarFocos) {
        this.denunciarFocos = denunciarFocos;
    }

    @Override
    protected void onPreExecute() {
        setExecutando(true);
    }

    @Override
    protected List<Foco> doInBackground(Location... params) {
        String json = Servidor.fazerGet("resgatarlocaisfoco.php?lat=" + params[0].getLatitude() + "&lon=" + params[0].getLongitude());
        List<Foco> focos = new ArrayList<Foco>();
        if (json == null) {
            return null;
        } else {
            try {
                JSONArray jsonArrayFocos = new JSONArray(json);
                for (int i = 0; i < jsonArrayFocos.length(); i++) {
                    JSONObject jsonObject = jsonArrayFocos.getJSONObject(i);
                    Foco foco = new Foco();
                    foco.setId(jsonObject.getInt("id"));
                    foco.setNome(jsonObject.getString("nome"));
                    foco.setLatitude(jsonObject.getDouble("latitude"));
                    foco.setLongitude(jsonObject.getDouble("longitude"));
                    foco.setFoto(jsonObject.getString("foto"));
                    foco.setDescricao(jsonObject.getString("classe"));
                    focos.add(foco);
                }
                localUltimaConsulta = params[0];
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return focos;
        }
    }

    @Override
    protected void onPostExecute(List<Foco> focos) {
        if (focos==null){
            Alerta.mandarAlerta(denunciarFocos,denunciarFocos.getString(R.string.falha_de_conexao));
        }else if (focos.size()<1){
            Alerta.mandarAlerta(denunciarFocos,denunciarFocos.getString(R.string.nao_existem_focos_na_area));
        }else{
            denunciarFocos.manipularMarcadoresDeFoco(focos);
        }
        setExecutando(false);
    }
}
