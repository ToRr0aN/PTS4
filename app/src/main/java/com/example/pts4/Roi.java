package com.example.pts4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece {

    public Roi(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context, layout, isBlack, echiquier);
        if (isBlack)
            imageView.setImageResource(R.drawable.tourn2);
        else
            imageView.setImageResource(R.drawable.tourb2);

        layout.addView(imageView);
        imageView.getLayoutParams().height = (int) (getCase().getTaille());
        imageView.getLayoutParams().width = (int) (getCase().getTaille());
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);
    }
    @Override
    public void showDeplacement() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                echiquier.resetCase();

                list = new ArrayList<>();

                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX][getCase().nomCaseY - 1].hasPiece())) {
                            list.add(cases[getCase().nomCaseX][getCase().nomCaseY - 1]);
                        }
                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX - 1][getCase().nomCaseY - 1].hasPiece())) {
                            list.add(cases[getCase().nomCaseX - 1][getCase().nomCaseY - 1]);
                        }
                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX - 1][getCase().nomCaseY].hasPiece())) {
                            list.add(cases[getCase().nomCaseX - 1][getCase().nomCaseY]);
                        }
                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1].hasPiece())) {
                            list.add(cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1]);
                        }
                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX][getCase().nomCaseY + 1].hasPiece())) {
                            list.add(cases[getCase().nomCaseX][getCase().nomCaseY + 1]);
                        }
                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1].hasPiece())) {
                            list.add(cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1]);
                        }
                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX + 1][getCase().nomCaseY].hasPiece())) {
                            list.add(cases[getCase().nomCaseX + 1][getCase().nomCaseY]);
                        }
                if (getCase().nomCaseY > 0 && getCase().nomCaseY < 8)
                    if(getCase().nomCaseX > 0 && getCase.nomCaseW < 8)
                        if(!(cases[getCase().nomCaseX + 1][getCase().nomCaseY - 1].hasPiece())) {
                            list.add(cases[getCase().nomCaseX + 1][getCase().nomCaseY - 1]);
                        }
            }
        }
    }
}