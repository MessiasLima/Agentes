package br.com.agente.agentes.gps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import br.com.agente.agentes.R;
import br.com.agente.agentes.activity.AgentesDaArea;
import br.com.agente.agentes.activity.DenunciarFocos;

/**
 * Created by messias on 2/22/16.
 *
 * @author Messias Lima
 * @version 1.0
 * @since 22/02/2016
 */
public class ListenerDeLocalizacao implements LocationListener {

    private Context context;
    public static Location localizacaoAtual = null;
    private AgentesDaArea agentesDaArea;
    private DenunciarFocos denunciarFocos;

    public ListenerDeLocalizacao(Context context) {
        this.context = context;
    }


    public void setAgentesDaArea(AgentesDaArea agentesDaArea) {
        this.agentesDaArea = agentesDaArea;
    }

    public void setAgentesDaArea(DenunciarFocos denunciarFocos) {
        this.denunciarFocos = denunciarFocos;
    }

    @Override
    public void onLocationChanged(Location location) {
        localizacaoAtual = location;
        Log.i("GPS", "Localizacao atual: " + location.getLatitude() + ", " + location.getLongitude());
        if (agentesDaArea != null) {
            agentesDaArea.adicionarMarcadorUsuario(location);
        }
        if (denunciarFocos != null) {
            denunciarFocos.adicionarMarcadorUsuario(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("GPS", "GPS Habilitado");
    }

    @Override
    public void onProviderDisabled(String provider) {

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
