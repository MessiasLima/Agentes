package br.com.agente.agentes.tarefaAssincrona;

import android.content.Context;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by messias on 2/19/16.
 */
public class Servidor {

    public static final String HOST ="50.116.112.164";
    public static final int PORTA =80;

    public static String fazerGet(String uri) {

        String urlString =   "/~aplicativo/agentes_sistema/" + uri;
        String resultado = "";
        try {
            URL url = new URL("http",HOST,PORTA,urlString);
            Log.i("Debug", urlString);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(1000 * 10);
            DataInputStream input = new DataInputStream(connection.getInputStream());
            String linha;
            while ((linha = input.readLine()) != null) {
                resultado = resultado + linha;
            }
            input.close();
            return resultado;
        } catch (MalformedURLException mue) {
            System.err.println("__URL mal formada");
            mue.printStackTrace();

        } catch (SocketTimeoutException stoe) {
            System.err.println("__Timeout de conexao");
            stoe.printStackTrace();
        } catch (IOException ioe) {
            System.err.println("__Erro de IO");
            ioe.printStackTrace();
        }

        Log.i("Debug", "Resposta do servidor: " + resultado);
        return null;
    }
}
