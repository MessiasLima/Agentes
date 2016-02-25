package br.com.agente.agentes.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import br.com.agente.agentes.R;

/**
 * Created by messias on 2/19/16.
 * @author Messias Lima
 * @since 19/02/2016
 * @version 1.0
 */
public class Alerta {
    public static void mandarAlerta(Context context, String mensagem){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(mensagem);
        builder.setNeutralButton(context.getText(R.string.entendi),null);
        builder.create().show();
    }
}
