package com.example.pts4;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class Cheval extends Piece {


    public Cheval(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context, layout, isBlack, echiquier);
        if (isBlack)
            imageView.setImageResource(R.drawable.chevaln2);
        else
            imageView.setImageResource(R.drawable.chevalb2);

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
                echiquier.resetCase(getPiece());


                list = new ArrayList<>();
                if (!isBlack) {
                    if (aCase.nomCaseX < 6 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX + 2][aCase.nomCaseY + 1].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX + 2][aCase.nomCaseY + 1]);
                    }
                    if (aCase.nomCaseX < 6 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX + 2][aCase.nomCaseY - 1].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX + 2][aCase.nomCaseY - 1]);
                    }
                    if (aCase.nomCaseX > 1 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX - 2][aCase.nomCaseY + 1].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX - 2][aCase.nomCaseY + 1]);
                    }
                    if (aCase.nomCaseX > 1 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX - 2][aCase.nomCaseY - 1].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX - 2][aCase.nomCaseY - 1]);
                    }

                    if (aCase.nomCaseX < 7 && aCase.nomCaseY < 6 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 2].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 2]);
                    }
                    if (aCase.nomCaseX < 7 && aCase.nomCaseY > 1 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 2].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 2]);
                    }
                    if (aCase.nomCaseX > 0 && aCase.nomCaseY < 6 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 2].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 2]);
                    }
                    if (aCase.nomCaseX > 0 && aCase.nomCaseY > 1 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 2].hasWhitePiece())) {
                        list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 2]);
                    }
                } else {
                    if (aCase.nomCaseX < 6 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX + 2][aCase.nomCaseY + 1].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX + 2][aCase.nomCaseY + 1]);
                    }
                    if (aCase.nomCaseX < 6 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX + 2][aCase.nomCaseY - 1].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX + 2][aCase.nomCaseY - 1]);
                    }
                    if (aCase.nomCaseX > 1 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX - 2][aCase.nomCaseY + 1].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX - 2][aCase.nomCaseY + 1]);
                    }
                    if (aCase.nomCaseX > 1 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX - 2][aCase.nomCaseY - 1].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX - 2][aCase.nomCaseY - 1]);
                    }

                    if (aCase.nomCaseX < 7 && aCase.nomCaseY < 6 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 2].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 2]);
                    }
                    if (aCase.nomCaseX < 7 && aCase.nomCaseY > 1 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 2].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 2]);
                    }
                    if (aCase.nomCaseX > 0 && aCase.nomCaseY < 6 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 2].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 2]);
                    }
                    if (aCase.nomCaseX > 0 && aCase.nomCaseY > 1 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 2].hasBlackPiece())) {
                        list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 2]);
                    }
                }


                if (!isOnClick) {
                    isOnClick = true;
                    for (Case uneCase : list) {
                        uneCase.clickable(false);
                        if (isBlack) {
                            if (uneCase.hasWhitePiece()) {
                                prise(uneCase);
                            } else {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        deplacement(uneCase);
                                        for (Piece blancs : echiquier.noirs) {
                                            isOnClick = false;
                                        }
                                    }
                                });
                            }
                        } else {
                            if (uneCase.hasBlackPiece()) {
                                prise(uneCase);
                            } else {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        deplacement(uneCase);
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
