package com.example.pts4;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public abstract class Piece {

    ImageView imageView;
    Case aCase;
    Context context;
    boolean isBlack;
    Echiquier echiquier;
    List<Case> list;
    Case cases[][];
    boolean isOnClick = false;


    public Piece(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        aCase.setPiece(this);
        this.aCase = aCase;
        cases = echiquier.getCases();
        this.echiquier = echiquier;
        imageView = new ImageView(context);
        this.isBlack = isBlack;
    }

    public abstract void showDeplacement();

    public void deplacement(Case moovingCase) {
        aCase.setPiece(null);
        moovingCase.setPiece(this);
        aCase = moovingCase;
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);
        echiquier.reset();
        for (Case laCase : list) {
            laCase.clickable(true);
        }
        echiquier.manche(isBlack);
    }

    public void prise(Case priseCase) {
        priseCase.piece.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priseCase.piece.imageView.setVisibility(View.INVISIBLE);
                deplacement(priseCase);

            }
        });
    }

    public Case getCase() {
        return aCase;
    }
}
