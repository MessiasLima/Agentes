package br.com.agente.agentes.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
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

    public static void alertaGPS(final Context context, String provider){
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.gps_desativado).setMessage(context.getString(R.string.app_name) + " " + context.getString(R.string.nao_funciona_sem_gps));
            builder.setNegativeButton(R.string.ignorar, null);
            builder.setPositiveButton("Ativar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            builder.setCancelable(true).create().show();

        }
    }
}
