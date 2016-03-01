package br.com.agente.agentes.gps;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import br.com.agente.agentes.R;
import br.com.agente.agentes.activity.DenunciarFocos;
import br.com.agente.agentes.bean.Foco;
import br.com.agente.agentes.util.Download;

/**
 * Created by messias on 2/25/16.
 * @author Messias Lima
 * @since 25/02/2016
 * @version 1.0
 */
public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

    View view;
    Activity context;

    public CustomInfoWindow(Activity context) {
        this.context = context;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        if (marker.getTitle().equals("Eu")){
            view = context.getLayoutInflater().inflate(R.layout.info_window_novo_foco, null);
            return view;
        }

        view = context.getLayoutInflater().inflate(R.layout.info_window_foco, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.info_window_img);
        imageView.setImageResource(R.mipmap.ic_mosquito);

        TextView textViewNome = (TextView) view.findViewById(R.id.info_window_tit);
        textViewNome.setText(marker.getTitle());

        TextView textViewClasse = (TextView) view.findViewById(R.id.info_window_sub);
        textViewClasse.setText(marker.getSnippet());

        if (DenunciarFocos.hashFocos.containsKey(marker.getTitle())) {
            Foco foco = DenunciarFocos.hashFocos.get(marker.getTitle());
            if (foco.getFotoBitmap() == null) {
                foco.atualizarMarcador(marker);
            } else {
                imageView.setImageBitmap(foco.getFotoBitmap());
            }
        }

        return view;
    }


}
