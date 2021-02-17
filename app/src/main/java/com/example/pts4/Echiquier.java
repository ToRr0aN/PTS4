package com.example.pts4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Echiquier {

    Case cases[][] = new Case[8][8];
    List<Piece> blancs;
    List<Piece> noirs;
    Roi roiN, roiB;
    ConstraintLayout layout;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public Echiquier(Context context, ConstraintLayout layout) {
        this.context = context;
        this.layout = layout;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getRealMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int taille = width / 8;
        int centrage = (height - (8 * taille)) / 2;
        int incrémenteurX = 0;
        int incrémenteurY = 0;
        boolean alternance = false;
        int couleur;
        for (int i = 0; i < 64; i++) {

            if (i % 8 == 0 && i != 0) {
                incrémenteurX = 0;
                incrémenteurY++;
                alternance = !alternance;
            }
            alternance = !alternance;
            if (alternance) couleur = Color.rgb(223, 175, 44);
            else couleur = Color.rgb(181, 101, 29);

            Case aCase = new Case(taille, incrémenteurX * taille, incrémenteurY * taille + centrage, context, incrémenteurX, incrémenteurY, couleur, layout);
            cases[incrémenteurX][incrémenteurY] = aCase;


            incrémenteurX++;
            Log.e("test", Integer.toString(i));

        }
        blancs = new ArrayList<>();
        noirs = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            noirs.add(new Pion(cases[i][1], context, layout, true, this));
            blancs.add(new Pion(cases[i][6], context, layout, false, this));
        }
        blancs.add(new Tour(cases[7][7], context, layout, false, this));
        blancs.add(new Tour(cases[0][7], context, layout, false, this));

        noirs.add(new Tour(cases[0][0], context, layout, true, this));
        noirs.add(new Tour(cases[7][0], context, layout, true, this));

        blancs.add(new Cheval(cases[6][7], context, layout, false, this));
        blancs.add(new Cheval(cases[1][7], context, layout, false, this));

        noirs.add(new Cheval(cases[1][0], context, layout, true, this));
        noirs.add(new Cheval(cases[6][0], context, layout, true, this));

        blancs.add(new Fou(cases[2][7], context, layout, false, this));
        blancs.add(new Fou(cases[5][7], context, layout, false, this));

        noirs.add(new Fou(cases[2][0], context, layout, true, this));
        noirs.add(new Fou(cases[5][0], context, layout, true, this));

        roiN = new Roi(cases[4][0], context, layout, true, this);
        noirs.add(roiN);

        roiB = new Roi(cases[4][7], context, layout, false, this);
        blancs.add(roiB);


        noirs.add(new Reine(cases[3][0], context, layout, true, this));
        blancs.add(new Reine(cases[3][7], context, layout, false, this));


        manche(true);

    }


    public Case[][] getCases() {
        return cases;
    }

    public void reset() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j].imageView.setOnClickListener(null);
                if (cases[i][j].hasPiece())
                    cases[i][j].piece.imageView.setOnClickListener(null);
            }
        }
    }

    public void resetCase(Piece maPiece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j].imageView.setOnClickListener(null);
                cases[i][j].clickable(true);
            }
        }
        for (Piece piece : blancs) {
            if (!(piece.equals(maPiece)))
                piece.isOnClick = false;
        }
        for (Piece piece : noirs) {
            if (!(piece.equals(maPiece)))
                piece.isOnClick = false;
        }
    }

    public void manche(boolean tour) {
        boolean mat = false;
        if (tour) {

          /*  for (Piece piece : noirs) {
                Case tmpCase = piece.getCase();
                tmpCase.setPiece(new Pion(tmpCase, context, layout, false, this));
                for (Piece piece2 : noirs) {

                    List<Case> list = piece2.getListOfPossibleCases();
                    for (Case aCase : list) {
                        if (aCase.equals(tmpCase)) {
                            tmpCase.imageView.setBackgroundColor(Color.RED);
                        }
                    }
                }
                tmpCase.piece.deletePiece();
                tmpCase.setPiece(piece);
            }
            echecNoir(roiB.getCase());
            matNoir();*/
            transformationCheck(!tour);
            for (Piece piece : blancs) {
                piece.showDeplacement();
            }
        } else {/*
            echecBlanc(roiN.getCase());
            matBlanc();*/

            transformationCheck(!tour);
            for (Piece piece : noirs) {
                piece.showDeplacement();
            }
        }

    }

    public void transformationCheck(boolean couleur) {
        List<Piece> deleteList = new ArrayList<>();
        if (couleur) {
            for (Piece piece : blancs) {
                if (piece instanceof Pion) {
                    if (piece.getCase().nomCaseY == 0) {
                        deleteList.add(piece);
                    }
                }
            }
            for (Piece piece : deleteList) {
                blancs.add(new Reine(piece.getCase(), context, layout, false, this));
                piece.deletePiece();
            }

        } else {
            for (Piece piece : noirs) {
                if (piece instanceof Pion) {
                    if (piece.getCase().nomCaseY == 7) {
                        deleteList.add(piece);
                    }
                }
            }
            for (Piece piece : deleteList) {
                noirs.add(new Reine(piece.getCase(), context, layout, true, this));
                piece.deletePiece();
            }
        }


    }

    public boolean echecNoir(Case maCase) {
        for (Piece piece : noirs) {
            List<Case> list;

            list = (piece).getListOfPossibleTaken();


            for (Case aCase : list) {
                if (aCase.equals(maCase)) {
                    Log.e("Echec", "des noirs");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean echecBlanc(Case maCase) {
        for (Piece piece : blancs) {
            List<Case> list = piece.getListOfPossibleCases();
            for (Case aCase : list) {
                if (aCase.equals(maCase)) {
                    Log.e("Echec", "des blancs");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean matNoir() {
        List<Case> n = roiB.getListOfPossibleCases();
        for (Case aCase : n) {
            if (!echecNoir(aCase)) {
                return false;
            }
        }
        Log.e("mat", "des noirs");
        return true;
    }

    public boolean matBlanc() {
        List<Case> n = roiN.getListOfPossibleCases();
        for (Case aCase : n) {
            if (!echecBlanc(aCase)) {
                return false;
            }
        }
        Log.e("mat", "des noirs");
        return true;
    }

}





