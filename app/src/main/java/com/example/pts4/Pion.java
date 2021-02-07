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
    Case cases[][];
    List<Case> list;

    public Pion(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        aCase.setPiece(this);
        this.aCase = aCase;
        cases = echiquier.getCases();
        imageView = new ImageView(context);
        this.isBlack = isBlack;
        if (isBlack)
            imageView.setImageResource(R.drawable.pion);
        else
            imageView.setImageResource(R.drawable.pion);

        layout.addView(imageView);
        imageView.getLayoutParams().height = (int) (getCase().getTaille());
        imageView.getLayoutParams().width = (int) (getCase().getTaille());
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);

        showDeplacement(this);
        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list = new ArrayList<>();
                if (!isBlack) {
                    if (getCase().nomCaseY > 0 && !(cases[getCase().nomCaseX][getCase().nomCaseY - 1].hasPiece()))
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - 1]);
                    if (getCase().nomCaseY - 1 > 0 && !cases[getCase().nomCaseX][getCase().nomCaseY - 2].hasPiece() && !(cases[getCase().nomCaseX][getCase().nomCaseY - 1].hasPiece()))
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
                    if (getCase().nomCaseY + 1 < 7 && !(cases[getCase().nomCaseX][getCase().nomCaseY + 2].hasPiece()) && !(cases[getCase().nomCaseX][getCase().nomCaseY + 1].hasPiece()))
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + 2]);
                    if (getCase().nomCaseX < 7 && getCase().nomCaseY < 7)
                        if (cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1].hasWhitePiece())
                            prise(cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1]);
                    if (getCase().nomCaseX > 0 && getCase().nomCaseY < 7)
                        if (cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1].hasWhitePiece())
                            prise(cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1]);
                }


                if (!isOnClick) {
                    isOnClick = !isOnClick;
                    for (Case uneCase : list) {
                        uneCase.clickable(false);
                        uneCase.getImageView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (Case maCase : list) {
                                    if (maCase.hasPiece()){
                                        maCase.clickable(true);
                                        maCase.piece.imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Log.e("test","eee0");
                                            }
                                        });
                                    }else{
                                    maCase.getImageView().setOnClickListener(null);
                                    maCase.clickable(true);
                                }}
                                isOnClick = !isOnClick;
                                deplacement(uneCase);
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
        });*/

    }

    //@Override
    public void deplacement(Case moovingCase) {
        aCase.setPiece(null);
        moovingCase.setPiece(this);
        aCase = moovingCase;
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);
    }

    public void showDeplacement(Piece piece) {
        piece.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list = new ArrayList<>();
                if (!isBlack) {
                    if (getCase().nomCaseY > 0 && !(cases[getCase().nomCaseX][getCase().nomCaseY - 1].hasPiece()))
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - 1]);
                    if (getCase().nomCaseY - 1 > 0 && !cases[getCase().nomCaseX][getCase().nomCaseY - 2].hasPiece() && !(cases[getCase().nomCaseX][getCase().nomCaseY - 1].hasPiece()))
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
                    if (getCase().nomCaseY + 1 < 7 && !(cases[getCase().nomCaseX][getCase().nomCaseY + 2].hasPiece()) && !(cases[getCase().nomCaseX][getCase().nomCaseY + 1].hasPiece()))
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + 2]);
                    if (getCase().nomCaseX < 7 && getCase().nomCaseY < 7)
                        if (cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1].hasWhitePiece())
                            prise(cases[getCase().nomCaseX + 1][getCase().nomCaseY + 1]);
                    if (getCase().nomCaseX > 0 && getCase().nomCaseY < 7)
                        if (cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1].hasWhitePiece())
                            prise(cases[getCase().nomCaseX - 1][getCase().nomCaseY + 1]);
                }


                if (!isOnClick) { //ya rien qui marche
                    isOnClick = !isOnClick;
                    for (Case uneCase : list) {
                        uneCase.clickable(false);
                        uneCase.getImageView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (Case maCase : list) {
                                    if (maCase.hasPiece()){
                                        maCase.clickable(true);
                                        Log.e("test","eeee");

                                        maCase.piece.imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showDeplacement(maCase.piece);
                                            }
                                        });
                                    }else{
                                        Log.e("test2","eeee");
                                        maCase.getImageView().setOnClickListener(null);
                                        maCase.clickable(true);
                                    }}
                                isOnClick = !isOnClick;
                                deplacement(uneCase);
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

    public void prise(Case priseCase) {
        list.add(priseCase);
        priseCase.piece.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deplacement(priseCase);
                for (Case maCase : list) {
                    maCase.getImageView().setOnClickListener(null);
                    maCase.clickable(true);
                }
                isOnClick = !isOnClick;
            }
        });
    }


}
