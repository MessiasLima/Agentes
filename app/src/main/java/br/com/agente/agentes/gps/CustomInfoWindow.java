package br.com.agente.agentes.gps;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import br.com.agente.agentes.R;

/**
 * Created by messias on 2/25/16.
 */
public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

    View view;
    Activity context;

    public CustomInfoWindow(Activity context){
        this.context = context;
        view = context.getLayoutInflater().inflate(R.layout.info_window_foco,null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView textViewNome = (TextView) view.findViewById(R.id.info_window_tit);
        textViewNome.setText(marker.getTitle());

        TextView textViewClasse= (TextView) view.findViewById(R.id.info_window_sub);
        textViewClasse.setText(marker.getSnippet());

        return view;
    }
}
