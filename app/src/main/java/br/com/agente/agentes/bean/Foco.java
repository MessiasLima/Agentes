package br.com.agente.agentes.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.Marker;

import java.io.File;

import br.com.agente.agentes.util.Download;

/**
 * Created by messias on 2/25/16.
 * @author Messias Lima
 * @since 25/02/2016
 * @version 1.0
 */
public class Foco {
    private int id;
    private String nome;
    private double latitude;
    private double longitude;
    private String foto;
    private String descricao;
    private Bitmap fotoBitmap;
    private File fotoFile;

    public File getFotoFile() {
        return fotoFile;
    }

    public void setFotoFile(File fotoFile) {
        this.fotoFile = fotoFile;
    }

    public Bitmap getFotoBitmap() {
        return fotoBitmap;
    }

    public void setFotoBitmap(Bitmap fotoBitmap) {
        this.fotoBitmap = fotoBitmap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void atualizarMarcador(final Marker marker, final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                setFotoBitmap(Download.baixarImagemSincrona(getFoto(),context));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                marker.showInfoWindow();
            }
        }.execute();
    }
}
