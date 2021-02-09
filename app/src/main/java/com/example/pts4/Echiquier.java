package com.example.pts4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Echiquier {

    Case cases[][] = new Case[8][8];
    List<Piece> blancs;
    List<Piece> noirs;


    @RequiresApi(api = Build.VERSION_CODES.R)
    public Echiquier(Context context, ConstraintLayout layout) {


        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getRealMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int taille = width / 8;
        int centrage = (height - (8 * taille)) / 2;
        int incrémenteurX = 0;
        int incrémenteurY = 0;
        boolean alternance = false;
        int couleur;
        for (int i = 0; i < 64; i++) {

            if (i % 8 == 0 && i != 0) {
                incrémenteurX = 0;
                incrémenteurY++;
                alternance = !alternance;
            }
            alternance = !alternance;
            if (alternance) couleur = Color.WHITE;
            else couleur = Color.BLUE;

            Case aCase = new Case(taille, incrémenteurX * taille, incrémenteurY * taille + centrage, context, incrémenteurX, incrémenteurY, couleur, layout);
            cases[incrémenteurX][incrémenteurY] = aCase;


            incrémenteurX++;
            Log.e("test", Integer.toString(i));

        }
        blancs = new ArrayList<>();
        noirs = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            noirs.add(new Pion(cases[i][1], context, layout, true, this));
            blancs.add(new Pion(cases[i][6], context, layout, false, this));
        }
        blancs.add(new Tour(cases[7][7], context, layout, false, this));
        blancs.add(new Tour(cases[0][7], context, layout, false, this));

        noirs.add(new Tour(cases[0][0], context, layout, true, this));
        noirs.add(new Tour(cases[7][0], context, layout, true, this));

        blancs.add(new Cheval(cases[6][7], context, layout, false, this));
        blancs.add(new Cheval(cases[1][7], context, layout, false, this));

        noirs.add(new Cheval(cases[1][0], context, layout, true, this));
        noirs.add(new Cheval(cases[6][0], context, layout, true, this));



        manche(true);

    }


    public Case[][] getCases() {
        return cases;
    }

    public void reset() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j].imageView.setOnClickListener(null);
                if (cases[i][j].hasPiece())
                    cases[i][j].piece.imageView.setOnClickListener(null);
            }
        }
    }

    public void resetCase(Piece maPiece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j].imageView.setOnClickListener(null);
                cases[i][j].clickable(true);
            }
        }
        for (Piece piece : blancs) {
            if (!(piece.equals(maPiece)))
                piece.isOnClick = false;
        }
        for (Piece piece : noirs) {
            if (!(piece.equals(maPiece)))
                piece.isOnClick = false;
        }
    }

    public void manche(boolean tour) {
        if (tour) {
            for (Piece piece : blancs) {
                piece.showDeplacement();
            }
        } else {
            for (Piece piece : noirs) {
                piece.showDeplacement();
            }
        }
    }


}


