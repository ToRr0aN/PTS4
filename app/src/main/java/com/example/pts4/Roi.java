package com.example.pts4;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece {

    boolean rocking = false;

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
                            } else if (rocking && uneCase.nomCaseX == 2) {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        rocking = false;
                                        deplacement(uneCase);
                                        cases[0][0].piece.deplacementRocking(cases[3][0]);
                                        firstMove = false;
                                        isOnClick = false;
                                    }
                                });


                            }else if (rocking && uneCase.nomCaseX == 6) {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        rocking = false;
                                        deplacement(uneCase);
                                        cases[7][0].piece.deplacementRocking(cases[5][0]);
                                        firstMove = false;
                                        isOnClick = false;
                                    }
                                });
                            } else {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        deplacement(uneCase);
                                        firstMove = false;
                                        isOnClick = false;
                                    }

                                });
                            }
                        } else {
                            if (uneCase.hasBlackPiece()) {
                                firstMove = false;
                                prise(uneCase);
                            } else if (rocking && uneCase.nomCaseX == 6) {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        rocking = false;
                                        deplacement(uneCase);
                                        cases[7][7].piece.deplacementRocking(cases[5][7]);
                                        firstMove = false;
                                        isOnClick = false;
                                    }
                                });


                            }else if (rocking && uneCase.nomCaseX == 2) {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        rocking = false;
                                        deplacement(uneCase);
                                        cases[0][7].piece.deplacementRocking(cases[3][7]);
                                        firstMove = false;
                                        isOnClick = false;
                                    }
                                });

                            } else {
                                uneCase.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        firstMove = false;
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
            if (firstMove && cases[1][7].piece == null && cases[2][7].piece == null && cases[3][7].piece == null && cases[0][7].piece instanceof Tour && !(cases[0][7].piece.isBlack) && cases[0][7].piece.firstMove && canMove(cases[2][7])) {
                list.add(cases[2][7]);
                rocking = true;
            }

            if (firstMove && cases[5][7].piece == null && cases[6][7].piece == null && cases[7][7].piece instanceof Tour && !(cases[7][7].piece.isBlack) && cases[7][7].piece.firstMove && canMove(cases[6][7])) {
                list.add(cases[6][7]);
                rocking = true;
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

            if (firstMove && cases[1][0].piece == null && cases[2][0].piece == null && cases[3][7].piece == null && cases[0][0].piece instanceof Tour && cases[0][0].piece.isBlack && cases[0][0].piece.firstMove && canMove(cases[2][0])) {
                list.add(cases[6][0]);
                rocking = true;
            }

            if (firstMove && cases[6][0].piece == null && cases[5][0].piece == null && cases[7][0].piece instanceof Tour && cases[7][0].piece.isBlack && cases[7][0].piece.firstMove && canMove(cases[6][0])) {
                list.add(cases[2][0]);
                rocking = true;
            }


        }
        return list;
    }

    @Override
    public List<Case> getListOfPossibleTaken() {
        List list = new ArrayList<>();
        if (!isBlack) {
            if (aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX][aCase.nomCaseY + 1].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX + 1][aCase.nomCaseY]);
            }
            if (aCase.nomCaseX > 0 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY - 1]);
            }

            if (aCase.nomCaseX > 0 && aCase.nomCaseY < 7 && !(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1].hasWhitePiece())) {
                list.add(cases[aCase.nomCaseX - 1][aCase.nomCaseY + 1]);
            }
            if (aCase.nomCaseX < 7 && aCase.nomCaseY > 0 && !(cases[aCase.nomCaseX + 1][aCase.nomCaseY - 1].hasWhitePiece())) {
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



   /* public boolean isMat() {
        List<Case> maListe = new ArrayList<>();
        if (!isBlack) {
            if (getListOfPossibleTaken().isEmpty() && echiquier.echecNoir(aCase)) {
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
            if (getListOfPossibleTaken().isEmpty() && echiquier.echecBlanc(aCase)) {
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
    }*/

    public boolean isMat() {
        List<Case> maListe = new ArrayList<>();
        if (!isBlack) {
            if (getListOfPossibleCases().isEmpty() && echiquier.echecNoir(getCase())) {
                for (Piece piece : echiquier.blancs) {
                    if (!(piece.getListOfPossibleCases().isEmpty())) {
                        Log.e("nom piece", piece.getClass().toString());
                        return false;

                    }
                }
                Log.e("mat", "mat");
                return true;
            }
        } else {
            if (getListOfPossibleCases().isEmpty() && echiquier.echecBlanc(getCase())) {
                for (Piece piece : echiquier.noirs) {
                    if (!(piece.getListOfPossibleCases().isEmpty())) {
                        Log.e("nom piece", piece.getClass().toString());
                        return false;

                    }
                }
                Log.e("mat", "mat");
                return true;
            }
        }
        return false;
    }

    public boolean isPat() {
        List<Case> maListe = new ArrayList<>();
        if (!isBlack) {
            if (getListOfPossibleCases().isEmpty() && !echiquier.echecNoir(getCase())) {
                for (Piece piece : echiquier.blancs) {
                    if (!(piece.getListOfPossibleCases().isEmpty())) {
                        Log.e("nom piece", piece.getClass().toString());
                        return false;

                    }
                }
                Log.e("mat", "mat");
                return true;
            }
        } else {
            if (getListOfPossibleCases().isEmpty() && !echiquier.echecBlanc(getCase())) {
                for (Piece piece : echiquier.noirs) {
                    if (!(piece.getListOfPossibleCases().isEmpty())) {
                        Log.e("nom piece", piece.getClass().toString());
                        return false;

                    }
                }
                Log.e("mat", "mat");
                return true;
            }
        }
        return false;
    }
   /* @Override
    public boolean canMove(Case maCase) {
        Case original = getCase();
        aCase.piece = null;
        aCase = maCase;
        Piece pOriginal = null;
        if (maCase.piece != null) pOriginal = maCase.piece;
        maCase.piece = this;
        if (isBlack) {
            if (echiquier.echecBlanc(getCase())) {
                aCase = original;
                maCase.piece = pOriginal;
                aCase.piece = this;

                return false;
            }
        } else {
            if (echiquier.echecNoir(getCase())) {
                aCase = original;
                maCase.piece = pOriginal;
                aCase.piece = this;

                return false;
            }
        }
        aCase.piece = this;
        maCase.piece = pOriginal;
        aCase = original;
        return true;
    }*/

}