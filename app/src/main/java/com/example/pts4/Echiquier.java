package com.example.pts4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Method;

public class Echiquier {

    Case cases[][] = new Case[8][8];
    Context context;



    @RequiresApi(api = Build.VERSION_CODES.R)
    public Echiquier(Context context, ConstraintLayout layout) {



        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getRealMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int taille = width / 8;
        int centrage = (height - (8 * taille))/2;
        int incrémenteurX = 0;
        int incrémenteurY = 0;
        boolean alternance = false;
        int couleur;
        for (int i = 0; i < 64; i++) {

            if (i % 8 == 0 && i!=0) {
                incrémenteurX = 0;
                incrémenteurY++;
                alternance = !alternance;
            }
            alternance = !alternance;
            if (alternance) couleur = Color.WHITE;
            else couleur = Color.BLACK;

            Case aCase = new Case(taille, incrémenteurX * taille, incrémenteurY * taille + centrage, context,incrémenteurX, incrémenteurY, couleur, layout);
            cases[incrémenteurX][incrémenteurY] = aCase;


            incrémenteurX++;
            Log.e("test", Integer.toString(i));

        }
        Pion pion = new Pion(cases[0][7], context, layout, false, this);


    }


    public Case[][] getCases() {
        return cases;
    }

}
