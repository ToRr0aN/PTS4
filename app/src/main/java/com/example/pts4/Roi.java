package com.example.pts4;

import android.content.Context;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece {

    public Roi(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context, layout, isBlack, echiquier);
        if (isBlack)
            imageView.setImageResource(R.drawable.roin2);
        else
            imageView.setImageResource(R.drawable.roib2);

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
        if (!isBlack) {
            if (aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX][aCase.nomCaseY + 1].hasWhitePiece()) && canMove(cases[aCase.nomCaseX][aCase.nomCaseY + 1])) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1].hasWhitePiece()) && canMove(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1])) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY].hasWhitePiece()) && canMove(cases[aCase.nomCaseX + 1][aCase.nomCaseY])) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseX > 0 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1].hasWhitePiece()) && canMove(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1])) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1]);
            }

            if (aCase.nomCaseX > 0 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1].hasWhitePiece()) && canMove(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1])) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1].hasWhitePiece()) && canMove(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1])) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1]);
            }
            if (aCase.nomCaseX > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY].hasWhitePiece()) && canMove(cases[aCase.nomCaseX - 1][aCase.nomCaseY])) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX][aCase.nomCaseY - 1].hasWhitePiece()) && canMove(cases[aCase.nomCaseX][aCase.nomCaseY - 1])) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY - 1]);
            }
        } else {
            if (aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX][aCase.nomCaseY + 1].hasBlackPiece()) && canMove(cases[aCase.nomCaseX][aCase.nomCaseY + 1])) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1].hasBlackPiece()) && canMove(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1])) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY].hasBlackPiece()) && canMove(cases[aCase.nomCaseX + 1][aCase.nomCaseY])) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseX > 0 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1].hasBlackPiece()) && canMove(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1])) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1]);
            }

            if (aCase.nomCaseX > 0 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1].hasBlackPiece()) && canMove(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1])) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1].hasBlackPiece()) && canMove(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1])) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1]);
            }
            if (aCase.nomCaseX > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY].hasBlackPiece()) && canMove(cases[aCase.nomCaseX - 1][aCase.nomCaseY])) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX][aCase.nomCaseY - 1].hasBlackPiece()) && canMove(cases[aCase.nomCaseX][aCase.nomCaseY - 1])) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY - 1]);
            }
        }
        return list;
    }

    @Override
    public List<Case> getListOfPossibleTaken() {
        List list = new ArrayList<>();
        if (!isBlack) {
            if (aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX][aCase.nomCaseY + 1].hasWhitePiece()) ) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1].hasWhitePiece()) ) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY].hasWhitePiece()) ) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseX > 0 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1].hasWhitePiece()) ) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1]);
            }

            if (aCase.nomCaseX > 0 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1].hasWhitePiece()) ) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1]);
            }
            if (aCase.nomCaseX > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX][aCase.nomCaseY - 1].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY - 1]);
            }
        } else {
            if (aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX][aCase.nomCaseY + 1].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseX > 0 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1]);
            }

            if (aCase.nomCaseX > 0 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1]);
            }
            if (aCase.nomCaseX > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX][aCase.nomCaseY - 1].hasBlackPiece())) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY - 1]);
            }
        }
        return list;
    }

    public boolean canMove(Case maCase) {
        Case original = getCase();
        aCase.piece = null;
        aCase = maCase;
        maCase.piece = this;
        if (isBlack) {
            if (echiquier.echecBlanc(getCase())) {
                aCase = original;
                maCase.piece = null;
                aCase.piece = this;

                return false;
            }
        } else {
            if (echiquier.echecNoir(getCase())) {
                aCase = original;
                maCase.piece = null;
                aCase.piece = this;

                return false;
            }
        }
        aCase.piece = this;
        maCase.piece = null;
        aCase = original;
        return true;
    }

    public boolean isMat() {
        List<Case> maListe = new ArrayList<>();
        if (!isBlack) {
            if (getListOfPossibleCases().isEmpty() && echiquier.echecNoir(aCase)) {
                for (Piece piece : echiquier.blancs) {
                    Case original = piece.getCase();
                    list = piece.getListOfPossibleCases();
                    for (Case maCase : maListe) {
                        piece.aCase = maCase;
                        maCase.piece = piece;
                        if (!echiquier.echecNoir(aCase)) {
                            maCase.piece = null;
                            piece.aCase = original;
                            piece.getCase().piece = piece;
                            return false;
                        }
                        maCase.piece = null;
                    }
                    piece.aCase = original;
                    piece.getCase().piece = piece;
                }
                return true;
            }
        } else {
            if (getListOfPossibleCases().isEmpty() && echiquier.echecBlanc(aCase)) {
                for (Piece piece : echiquier.noirs) {
                    Case original = piece.getCase();
                    list = piece.getListOfPossibleCases();
                    for (Case maCase : maListe) {
                        piece.aCase = maCase;
                        maCase.piece = piece;
                        if (!echiquier.echecBlanc(aCase)) {
                            maCase.piece = null;
                            piece.aCase = original;
                            piece.getCase().piece = piece;
                            return false;
                        }
                        maCase.piece = null;
                    }
                    piece.aCase = original;
                    piece.getCase().piece = piece;
                }
                return true;
            }
        }
        return false;
    }

}