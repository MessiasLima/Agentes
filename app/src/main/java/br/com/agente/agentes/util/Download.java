package br.com.agente.agentes.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.agente.agentes.R;

/**
 * Created by messias on 2/22/16.
 */
public class Download {
    public static Bitmap baixarImagem(final String url, final ImageView imageView) {

        new AsyncTask<Void,Void,Bitmap>(){
            @Override
            protected Bitmap doInBackground(Void... params) {
               return baixarImagemSincrona(url);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap!=null){
                    imageView.setImageBitmap(bitmap);
                }else{
                    Snackbar.make(imageView, R.string.falha_imagem,Snackbar.LENGTH_INDEFINITE).show();
                }
            }
        }.execute();


        return null;
    }

    public static Bitmap baixarImagemSincrona(String url) {
        try {

            URL urlImagem = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlImagem.openConnection();
            InputStream input = connection.getInputStream();
            Bitmap img = BitmapFactory.decodeStream(input);
            input.close();
            connection.disconnect();
            Log.i("Download imagem", url);
            return img;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
