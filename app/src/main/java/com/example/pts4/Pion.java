package com.example.pts4;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece {

    ImageView imageView;
    boolean isOnClick = false;
    Case cases[][] = new Case[8][8];
    boolean isBlack;

    public Pion(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context);
        cases = echiquier.getCases();
        imageView = new ImageView(context);
        this.isBlack = isBlack;
        /*if (isBlack)*/
        imageView.setImageResource(R.drawable.pion);
        //else mettre pion blanc

        layout.addView(imageView);
        imageView.getLayoutParams().height = (int) (getCase().getTaille() / 1.5);
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX - getCase().taille);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Case> list = new ArrayList<>();
                if (!isBlack) {
                    if (getCase().nomCaseY > 0)
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - 1]);
                    if (getCase().nomCaseY - 1 > 0)
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - 2]);
                } else {
                    if (getCase().nomCaseY < 7)
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + 1]);
                    if (getCase().nomCaseY + 1 < 7)
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + 2]);

                }

                Log.e("test", Boolean.toString(isOnClick));

                if (!isOnClick) {
                    isOnClick = !isOnClick;
                    Log.e("nom :", Integer.toString(getCase().nomCaseY + 1));
                    for (Case uneCase : list) {
                        uneCase.clickable(false);
                        uneCase.getImageView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deplacement(uneCase);
                                for (Case maCase : list) {
                                    maCase.getImageView().setOnClickListener(null);
                                    maCase.clickable(true);
                                }
                                isOnClick = !isOnClick;
                            }
                        });
                    }
                } else {
                    for (Case maCase : list) {
                        maCase.getImageView().setOnClickListener(null);
                        maCase.clickable(true);
                    }
                    isOnClick = !isOnClick;
                }
            }
        });

    }

    //@Override
    public void deplacement(Case moovingCase) {
        aCase = moovingCase;
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX - getCase().taille);
    }

    public void showDeplacement() {

    }

}
