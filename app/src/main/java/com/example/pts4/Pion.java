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

public class Pion extends Piece {

    boolean isOnClick = false;
    boolean firstMoove = true;


    public Pion(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context, layout, isBlack, echiquier);
        if (isBlack)
            imageView.setImageResource(R.drawable.pionn2);
        else
            imageView.setImageResource(R.drawable.pionb2);

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

                list = new ArrayList<>();
                if (!isBlack) {
                    if (getCase().nomCaseY > 0 && !(cases[getCase().nomCaseX][getCase().nomCaseY - 1].hasPiece()))
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - 1]);
                    if (getCase().nomCaseY - 1 > 0 && !cases[getCase().nomCaseX][getCase().nomCaseY - 2].hasPiece() && !(cases[getCase().nomCaseX][getCase().nomCaseY - 1].hasPiece()) && firstMoove)
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - 2]);
                    if (getCase().nomCaseX > 0 && getCase().nomCaseY > 0)
                        if (cases[getCase().nomCaseX - 1][getCase().nomCaseY - 1].hasBlackPiece())
                            list.add(cases[getCase().nomCaseX - 1][getCase().nomCaseY - 1]);
                    if (getCase().nomCaseX < 7 && getCase().nomCaseY > 0)
                        if (cases[getCase().nomCaseX + 1][getCase().nomCaseY - 1].hasBlackPiece())
                            list.add(cases[getCase().nomCaseX + 1][getCase().nomCaseY - 1]);

                } else {
                    if (getCase().nomCaseY < 7 && !(cases[getCase().nomCaseX][getCase().nomCaseY + 1].hasPiece()))
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + 1]);
                    if (getCase().nomCaseY + 1 < 7 && !(cases[getCase().nomCaseX][getCase().nomCaseY + 2].hasPiece()) && !(cases[getCase().nomCaseX][getCase().nomCaseY + 1].hasPiece()) && firstMoove)
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + 2]);
                    if (getCase().nomCaseX < 7 && getCase().nomCaseY < 7)
                        if (cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1].hasWhitePiece())
                            list.add(cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1]);
                    if (getCase().nomCaseX > 0 && getCase().nomCaseY < 7)
                        if (cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1].hasWhitePiece())
                            list.add(cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1]);

                }


                if (!isOnClick) {
                    isOnClick = true;
                    for (Case uneCase : list) {
                        uneCase.clickable(false);
                        if (isBlack) {
                            if (uneCase.hasWhitePiece() && uneCase.nomCaseX != aCase.nomCaseX) {
                                prise(uneCase);
                                firstMoove = false;

                            } else {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        deplacement(uneCase);
                                        firstMoove = false;
                                        isOnClick = false;
                                    }

                                });
                            }
                        } else {
                            if (uneCase.hasBlackPiece() && uneCase.nomCaseX != aCase.nomCaseX) {
                                prise(uneCase);
                                firstMoove = false;

                            } else {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        deplacement(uneCase);
                                        firstMoove = false;

                                        isOnClick = false;
                                    }
                                });
                            }
                        }
                    }
                } else {
                    for (Case maCase : list) {
                        maCase.getImageView().setOnClickListener(null);
                        maCase.clickable(true);
                        if (isBlack && maCase.hasWhitePiece()) {
                            maCase.piece.imageView.setOnClickListener(null);
                        } else {
                            if (!isBlack && maCase.hasBlackPiece())
                                maCase.piece.imageView.setOnClickListener(null);
                        }
                    }
                    isOnClick = !isOnClick;
                }
            }
        });


    }


}
