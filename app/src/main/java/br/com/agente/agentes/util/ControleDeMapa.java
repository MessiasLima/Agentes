package br.com.agente.agentes.util;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by messias on 2/24/16.
 * @author Messias Lima
 */
public class ControleDeMapa {
    //Mover camera
    public static void moverCamera(GoogleMap mapa , Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15)
                .bearing(mapa.getCameraPosition().bearing)
                        //.tilt(70)
                .build();
        mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
