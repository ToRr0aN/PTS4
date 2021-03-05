package com.example.pts4;

import android.content.Context;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class Reine extends Piece {

    public Reine(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context, layout, isBlack, echiquier);
        if (isBlack)
            imageView.setImageResource(R.drawable.reinen2);
        else
            imageView.setImageResource(R.drawable.reineb2);

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

                list = getListOfPossibleCases();

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
                                        isOnClick = false;
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

    @Override
    public List<Case> getListOfPossibleCases() {
        List list = new ArrayList<>();
        boolean hasPiece1 = true;
        boolean hasPiece2 = true;
        boolean hasPiece3 = true;
        boolean hasPiece4 = true;
        boolean hasPiece5 = true;
        boolean hasPiece6 = true;
        boolean hasPiece7 = true;
        boolean hasPiece8 = true;


        if (!isBlack) {
            for (int i = 1; i < 8; i++) {
                if (getCase().nomCaseY > 0 && getCase().nomCaseX > 0) {
                    if (getCase().nomCaseY - i > -1 && getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasWhitePiece()) && hasPiece1 && canMove(cases[getCase().nomCaseX - i][getCase().nomCaseY - i])) { //haut gauche
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasBlackPiece()) {
                            hasPiece1 = false;
                        }
                    } else hasPiece1 = false;
                }
                if (getCase().nomCaseY < 8 && getCase().nomCaseX < 8) { // bas droit
                    if (getCase().nomCaseY + i < 8 && getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasWhitePiece()) && hasPiece2 && canMove(cases[getCase().nomCaseX + i][getCase().nomCaseY + i])) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasBlackPiece()) {
                            hasPiece2 = false;
                        }
                    } else hasPiece2 = false;

                }
                if (getCase().nomCaseX > 0 && getCase().nomCaseY < 8) {
                    if (getCase().nomCaseX - i > -1 && getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasWhitePiece()) && (hasPiece3) && canMove(cases[getCase().nomCaseX - i][getCase().nomCaseY + i])) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasBlackPiece()) {
                            hasPiece3 = false;
                        }
                    } else hasPiece3 = false;
                }
                if (getCase().nomCaseX < 8 && getCase().nomCaseY > 0) {
                    if (getCase().nomCaseX + i < 8 && getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasWhitePiece()) && hasPiece4 && canMove(cases[getCase().nomCaseX + i][getCase().nomCaseY - i])) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasBlackPiece()) {
                            hasPiece4 = false;
                        }
                    } else hasPiece4 = false;
                }
                if (getCase().nomCaseY > 0) {
                    if (getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX][getCase().nomCaseY - i].hasWhitePiece()) && hasPiece5 && canMove(cases[getCase().nomCaseX][getCase().nomCaseY - i])) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY - i].hasBlackPiece()) {
                            hasPiece5 = false;
                        }
                    } else hasPiece5 = false;
                }
                if (getCase().nomCaseY < 8) {
                    if (getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX][getCase().nomCaseY + i].hasWhitePiece()) && hasPiece6 && canMove(cases[getCase().nomCaseX][getCase().nomCaseY + i])) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY + i].hasBlackPiece()) {
                            hasPiece6 = false;
                        }
                    } else hasPiece6 = false;

                }
                if (getCase().nomCaseX > 0) {
                    if (getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY].hasWhitePiece()) && (hasPiece7) && canMove(cases[getCase().nomCaseX - i][getCase().nomCaseY])) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY].hasBlackPiece()) {
                            hasPiece7 = false;
                        }
                    } else hasPiece7 = false;
                }
                if (getCase().nomCaseX < 8) {
                    if (getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY].hasWhitePiece()) && hasPiece8 && canMove(cases[getCase().nomCaseX + i][getCase().nomCaseY])) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY].hasBlackPiece()) {
                            hasPiece8 = false;
                        }
                    } else hasPiece8 = false;
                }

            }

        } else {
            for (int i = 1; i < 8; i++) {
                if (getCase().nomCaseY > 0 && getCase().nomCaseX > 0) {
                    if (getCase().nomCaseY - i > -1 && getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasBlackPiece()) && hasPiece1 && canMove(cases[getCase().nomCaseX - i][getCase().nomCaseY - i])) { //haut gauche
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasWhitePiece()) {
                            hasPiece1 = false;
                        }
                    } else hasPiece1 = false;
                }
                if (getCase().nomCaseY < 8 && getCase().nomCaseX < 8) { // bas droit
                    if (getCase().nomCaseY + i < 8 && getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasBlackPiece()) && hasPiece2 && canMove(cases[getCase().nomCaseX + i][getCase().nomCaseY + i])) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasWhitePiece()) {
                            hasPiece2 = false;
                        }
                    } else hasPiece2 = false;

                }
                if (getCase().nomCaseX > 0 && getCase().nomCaseY < 8) {
                    if (getCase().nomCaseX - i > -1 && getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasBlackPiece()) && (hasPiece3) && canMove(cases[getCase().nomCaseX - i][getCase().nomCaseY + i])) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasWhitePiece()) {
                            hasPiece3 = false;
                        }
                    } else hasPiece3 = false;
                }
                if (getCase().nomCaseX < 8 && getCase().nomCaseY > 0) {
                    if (getCase().nomCaseX + i < 8 && getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasBlackPiece()) && hasPiece4 && canMove(cases[getCase().nomCaseX + i][getCase().nomCaseY - i])) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasWhitePiece()) {
                            hasPiece4 = false;
                        }
                    } else hasPiece4 = false;
                }
                if (getCase().nomCaseY > 0) {
                    if (getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX][getCase().nomCaseY - i].hasBlackPiece()) && hasPiece5 && canMove(cases[getCase().nomCaseX][getCase().nomCaseY - i])) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY - i].hasWhitePiece()) {
                            hasPiece5 = false;
                        }
                    } else hasPiece5 = false;
                }
                if (getCase().nomCaseY < 8) {
                    if (getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX][getCase().nomCaseY + i].hasBlackPiece()) && hasPiece6 && canMove(cases[getCase().nomCaseX][getCase().nomCaseY + i])) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY + i].hasWhitePiece()) {
                            hasPiece6 = false;
                        }
                    } else hasPiece6 = false;
                }
                if (getCase().nomCaseX > 0) {
                    if (getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY].hasBlackPiece()) && (hasPiece7) && canMove(cases[getCase().nomCaseX - i][getCase().nomCaseY])) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY].hasWhitePiece()) {
                            hasPiece7 = false;
                        }
                    } else hasPiece7 = false;
                }
                if (getCase().nomCaseX < 8) {
                    if (getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY].hasBlackPiece()) && hasPiece8 && canMove(cases[getCase().nomCaseX + i][getCase().nomCaseY])) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY].hasWhitePiece()) {
                            hasPiece8 = false;
                        }
                    } else hasPiece8 = false;
                }
            }
        }
        return list;
    }

    @Override
    public List<Case> getListOfPossibleTaken() {
        List list = new ArrayList<>();
        boolean hasPiece1 = true;
        boolean hasPiece2 = true;
        boolean hasPiece3 = true;
        boolean hasPiece4 = true;
        boolean hasPiece5 = true;
        boolean hasPiece6 = true;
        boolean hasPiece7 = true;
        boolean hasPiece8 = true;


        if (!isBlack) {
            for (int i = 1; i < 8; i++) {
                if (getCase().nomCaseY > 0 && getCase().nomCaseX > 0) {
                    if (getCase().nomCaseY - i > -1 && getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasWhitePiece()) && hasPiece1) { //haut gauche
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasBlackPiece()) {
                            hasPiece1 = false;
                        }
                    } else hasPiece1 = false;
                }
                if (getCase().nomCaseY < 8 && getCase().nomCaseX < 8) { // bas droit
                    if (getCase().nomCaseY + i < 8 && getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasWhitePiece()) && hasPiece2) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasBlackPiece()) {
                            hasPiece2 = false;
                        }
                    } else hasPiece2 = false;

                }
                if (getCase().nomCaseX > 0 && getCase().nomCaseY < 8) {
                    if (getCase().nomCaseX - i > -1 && getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasWhitePiece()) && (hasPiece3)) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasBlackPiece()) {
                            hasPiece3 = false;
                        }
                    } else hasPiece3 = false;
                }
                if (getCase().nomCaseX < 8 && getCase().nomCaseY > 0) {
                    if (getCase().nomCaseX + i < 8 && getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasWhitePiece()) && hasPiece4) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasBlackPiece()) {
                            hasPiece4 = false;
                        }
                    } else hasPiece4 = false;
                }
                if (getCase().nomCaseY > 0) {
                    if (getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX][getCase().nomCaseY - i].hasWhitePiece()) && hasPiece5) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY - i].hasBlackPiece()) {
                            hasPiece5 = false;
                        }
                    } else hasPiece5 = false;
                }
                if (getCase().nomCaseY < 8) {
                    if (getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX][getCase().nomCaseY + i].hasWhitePiece()) && hasPiece6) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY + i].hasBlackPiece()) {
                            hasPiece6 = false;
                        }
                    } else hasPiece6 = false;

                }
                if (getCase().nomCaseX > 0) {
                    if (getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY].hasWhitePiece()) && (hasPiece7)) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY].hasBlackPiece()) {
                            hasPiece7 = false;
                        }
                    } else hasPiece7 = false;
                }
                if (getCase().nomCaseX < 8) {
                    if (getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY].hasWhitePiece()) && hasPiece8) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY].hasBlackPiece()) {
                            hasPiece8 = false;
                        }
                    } else hasPiece8 = false;
                }

            }

        } else {
            for (int i = 1; i < 8; i++) {
                if (getCase().nomCaseY > 0 && getCase().nomCaseX > 0) {
                    if (getCase().nomCaseY - i > -1 && getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasBlackPiece()) && hasPiece1) { //haut gauche
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY - i].hasWhitePiece()) {
                            hasPiece1 = false;
                        }
                    } else hasPiece1 = false;
                }
                if (getCase().nomCaseY < 8 && getCase().nomCaseX < 8) { // bas droit
                    if (getCase().nomCaseY + i < 8 && getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasBlackPiece()) && hasPiece2) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY + i].hasWhitePiece()) {
                            hasPiece2 = false;
                        }
                    } else hasPiece2 = false;

                }
                if (getCase().nomCaseX > 0 && getCase().nomCaseY < 8) {
                    if (getCase().nomCaseX - i > -1 && getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasBlackPiece()) && (hasPiece3)) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY + i].hasWhitePiece()) {
                            hasPiece3 = false;
                        }
                    } else hasPiece3 = false;
                }
                if (getCase().nomCaseX < 8 && getCase().nomCaseY > 0) {
                    if (getCase().nomCaseX + i < 8 && getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasBlackPiece()) && hasPiece4) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY - i].hasWhitePiece()) {
                            hasPiece4 = false;
                        }
                    } else hasPiece4 = false;
                }
                if (getCase().nomCaseY > 0) {
                    if (getCase().nomCaseY - i > -1 && !(cases[getCase().nomCaseX][getCase().nomCaseY - i].hasBlackPiece()) && hasPiece5) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY - i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY - i].hasWhitePiece()) {
                            hasPiece5 = false;
                        }
                    } else hasPiece5 = false;
                }
                if (getCase().nomCaseY < 8) {
                    if (getCase().nomCaseY + i < 8 && !(cases[getCase().nomCaseX][getCase().nomCaseY + i].hasBlackPiece()) && hasPiece6) {
                        list.add(cases[getCase().nomCaseX][getCase().nomCaseY + i]);
                        if (cases[getCase().nomCaseX][getCase().nomCaseY + i].hasWhitePiece()) {
                            hasPiece6 = false;
                        }
                    } else hasPiece6 = false;
                }
                if (getCase().nomCaseX > 0) {
                    if (getCase().nomCaseX - i > -1 && !(cases[getCase().nomCaseX - i][getCase().nomCaseY].hasBlackPiece()) && (hasPiece7)) {
                        list.add(cases[getCase().nomCaseX - i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX - i][getCase().nomCaseY].hasWhitePiece()) {
                            hasPiece7 = false;
                        }
                    } else hasPiece7 = false;
                }
                if (getCase().nomCaseX < 8) {
                    if (getCase().nomCaseX + i < 8 && !(cases[getCase().nomCaseX + i][getCase().nomCaseY].hasBlackPiece()) && hasPiece8) {
                        list.add(cases[getCase().nomCaseX + i][getCase().nomCaseY]);
                        if (cases[getCase().nomCaseX + i][getCase().nomCaseY].hasWhitePiece()) {
                            hasPiece8 = false;
                        }
                    } else hasPiece8 = false;
                }
            }
        }
        return list;
    }

}
