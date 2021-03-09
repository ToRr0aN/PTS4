package com.example.pts4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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
    TextView turn;
    int height, width;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public Echiquier(Context context, ConstraintLayout layout) {
        this.context = context;
        this.layout = layout;
        turn = layout.findViewById(R.id.turn);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getRealMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
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

       /* for (int i = 0; i < 8; i++) {
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
*/
        roiN = new Roi(cases[4][0], context, layout, true, this);
        noirs.add(roiN);

        roiB = new Roi(cases[4][7], context, layout, false, this);
        blancs.add(roiB);


        //noirs.add(new Reine(cases[3][0], context, layout, true, this));
        blancs.add(new Reine(cases[3][7], context, layout, false, this));

        blancs.add(new Reine(cases[2][7], context, layout, false, this));
        blancs.add(new Reine(cases[1][7], context, layout, false, this));
        blancs.add(new Reine(cases[5][7], context, layout, false, this));
        blancs.add(new Reine(cases[6][7], context, layout, false, this));


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

        Log.e("nb noirs", Integer.toString(noirs.size()));
        Log.e("nb blacos", Integer.toString(blancs.size()));
        if (tour){
            for (Piece piece:noirs) {
                piece.imageView.setOnClickListener(null);
            }
        }else {
            for (Piece piece:blancs) {
                piece.imageView.setOnClickListener(null);
            }
        }

        if (tour) {
            echecNoir(roiB.getCase());
            if (roiB.isMat()) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();    // On créé un Alert dialog pour expliquer les règles
                alertDialog.setTitle("Les noirs ont gagné !");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Retour menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, Menu.class);
                        context.startActivity(intent);
                    }
                });
                alertDialog.show();
                TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
                textView.setTextSize(15);
            }
            if (roiB.isPat()) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();    // On créé un Alert dialog pour expliquer les règles
                alertDialog.setTitle("Égalité !");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Retour menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, Menu.class);
                        context.startActivity(intent);
                    }
                });
                alertDialog.show();
                TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
                textView.setTextSize(15);
            }
            turn.setY(height - turn.getHeight());
            turn.setRotation(0);
            transformationCheck(!tour);

            for (Piece piece : blancs) {
                piece.showDeplacement();
            }
        } else {
            echecNoir(roiN.getCase());
            if (roiN.isMat()) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();    // On créé un Alert dialog pour expliquer les règles
                alertDialog.setTitle("Les blancs ont gagné !");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Retour menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, Menu.class);
                        context.startActivity(intent);
                    }
                });
                alertDialog.show();
                TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
                textView.setTextSize(15);
            }
            if (roiN.isPat()) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();    // On créé un Alert dialog pour expliquer les règles
                alertDialog.setTitle("Égalité !");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Retour menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, Menu.class);
                        context.startActivity(intent);
                    }
                });
                alertDialog.show();
                TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
                textView.setTextSize(15);
            }
            turn.setY(0);
            turn.setRotation(180);
            layout.findViewById(R.id.tempTurn).setVisibility(View.INVISIBLE);
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
                if (aCase.nomCaseY == maCase.nomCaseY && aCase.nomCaseX == maCase.nomCaseX) {
                    Log.e("Echec", "des noirs");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean echecBlanc(Case maCase) {
        for (Piece piece : blancs) {
            List<Case> list;

            list = (piece).getListOfPossibleTaken();


            for (Case aCase : list) {
                if (aCase.nomCaseY == maCase.nomCaseY && aCase.nomCaseX == maCase.nomCaseX) {
                    Log.e("Echec", "des noirs");
                    return true;
                }
            }
        }
        return false;
    }

}





