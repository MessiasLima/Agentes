package br.com.agente.agentes.bean;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by messias on 2/19/16.
 *
 * @author Messias Lima
 * @version 1.0
 * @since 19/2/2016
 */
public class Agente implements Serializable {
    private int id = 0;
    private String matricula;
    private String nome;
    private double latitude;
    private double longitude;
    private String foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
}
