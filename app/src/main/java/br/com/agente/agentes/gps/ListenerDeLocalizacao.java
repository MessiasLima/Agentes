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
import br.com.agente.agentes.util.Alerta;

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
        Alerta.alertaGPS(context,provider);
    }
}
