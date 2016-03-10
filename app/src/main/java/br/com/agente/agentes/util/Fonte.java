package br.com.agente.agentes.util;

import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by messias on 2/22/16.
 * @author Messias Lima
 * @since 22/02/2016
 * @version 1.0
 */
public class Fonte {
    public static void setarFonteTitulo(TextView view){
        Typeface myTypeface = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/antonio-light.ttf");
        view.setTypeface(myTypeface);
    }

    public static void setarFonteTitulo(EditText view){
        Typeface myTypeface = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/antonio-light.ttf");
        view.setTypeface(myTypeface);
    }

    public static void setarFonteTextos(TextView view){
        Typeface myTypeface = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/mentone-semibol.otf");
        view.setTypeface(myTypeface);
    }

    public static void setarFonteTextos(EditText view){
        Typeface myTypeface = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/mentone-semibol.otf");
        view.setTypeface(myTypeface);
    }
}
