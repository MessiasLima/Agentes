package br.com.agente.agentes.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.agente.agentes.R;

/**
 * Created by messias on 2/22/16.
 * @author Messias Lima
 */
public class Download {
    public static Bitmap baixarImagem(final String url, final ImageView imageView, final Context context) {

        new AsyncTask<Void,Void,Bitmap>(){
            @Override
            protected Bitmap doInBackground(Void... params) {
               return baixarImagemSincrona(url,context);
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

    public static Bitmap baixarImagemSincrona(String url, Context context) {
        try {

           /* URL urlImagem = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlImagem.openConnection();
            InputStream input = connection.getInputStream();
            Bitmap img = BitmapFactory.decodeStream(input);
            input.close();
            connection.disconnect();
            Log.i("Download imagem", url);*/

            return Picasso.with(context).load(url).get();

            //return img;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
