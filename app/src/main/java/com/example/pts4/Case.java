package com.example.pts4;

import android.content.Context;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;


public class Case {

    int taille, coordPixelX, coordPixelY, nomCaseX, nomCaseY;
    Context context;
    ImageView imageView;
    Piece piece;

    public Case(int taille, int coordPixelX, int coordPixelY, Context context, int nomCaseX, int nomCaseY, int color, ConstraintLayout layout) {
        this.taille = taille;
        this.coordPixelX = coordPixelX;
        this.coordPixelY = coordPixelY;
        this.nomCaseX = nomCaseX;
        this.nomCaseY = nomCaseY;
        this.context = context;

        imageView = new ImageView(context);
        imageView.setBackgroundColor(color);
        imageView.setX(coordPixelX);
        imageView.setY(coordPixelY);
        layout.addView(imageView);
        imageView.getLayoutParams().width = taille;
        imageView.getLayoutParams().height = taille;

    }

    public void putPion(Piece piece){
        this.piece = piece;
    }
}
