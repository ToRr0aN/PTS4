package com.example.pts4;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Echiquier {

    Case cases[][] = new Case[8][8];
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public Echiquier(Context context, ConstraintLayout layout) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int taille = width / 8;
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

            Case aCase = new Case(taille, incrémenteurX * taille, incrémenteurY * taille, context, i % 4, i % 8, couleur, layout);
            Log.e("test", Integer.toString(incrémenteurX));
            Log.e("test", Integer.toString(incrémenteurY));
            cases[incrémenteurX][incrémenteurY] = aCase;
            incrémenteurX++;
        }
        //Pion pion = new Pion(0,1);
        //cases[0][0].putPion(pion);
    }

}
