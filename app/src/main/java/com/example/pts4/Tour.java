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

public class Tour extends Piece {

    public Pion(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context, layout, isBlack, echiquier);
        if (isBlack)
            imageView.setImageResource(R.drawable.tourn2);
        else
            imageView.setImageResource(R.drawable.tourb2);
    }
        layout.addView(imageView);
        imageView.getLayoutParams().height = (int) (getCase().getTaille());
        imageView.getLayoutParams().width = (int) (getCase().getTaille());
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);

    @Override
    public void showDeplacement() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list = new ArrayList<>();

                jmax=8-(getCase().nomCaseY);
                    for(int j=1;j<jmax;j++) {
                        if (getCase().nomCaseY > 0 && !(cases[getCase().nomCaseX][getCase().nomCaseY + j].hasPiece()))
                            list.add(cases[getCase().nomCaseX][getCase().nomCaseY + j]);
                    }
                imax=8-(getCase().nomCaseX);
                    for(int i=1;i<imax;i++) {
                    if (getCase().nomCaseX > 0 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY].hasPiece()))
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY]);
                }
                    jmin=(-1)+(getCase().nomCaseY);
                for(int j=7;j>jmin;j--) {
                    if (getCase().nomCaseY < 8 && !(cases[getCase().nomCaseX][getCase().nomCaseY - j].hasPiece()))
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - j]);
                }
                imin=(-1)+(getCase().nomCaseX);
                for(int i=7;i>imin;i--) {
                    if (getCase().nomCaseX < 8 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY].hasPiece()))
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY]);
                }
            }
        }
}