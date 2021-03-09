package com.example.pts4;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    ImageView imageView;
    Case aCase;
    Context context;
    boolean isBlack;
    Echiquier echiquier;
    List<Case> list;
    Case cases[][];
    boolean isOnClick = false;
    boolean firstMoove = true;
    boolean isProtected;
    ConstraintLayout layout;

    public Piece(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        aCase.setPiece(this);
        this.aCase = aCase;
        this.layout = layout;
        cases = echiquier.getCases();
        this.echiquier = echiquier;
        imageView = new ImageView(context);
        this.isBlack = isBlack;
    }

    public abstract void showDeplacement();

    public void deplacement(Case moovingCase) {
        if (moovingCase.piece != null) moovingCase.piece.deletePiece();
        aCase.setPiece(null);
        moovingCase.setPiece(this);
        aCase = moovingCase;
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);
        echiquier.reset();
        for (Case laCase : list) {
            laCase.clickable(true);
        }
        echiquier.manche(isBlack);

    }

    public void prise(Case priseCase) {
        priseCase.piece.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //priseCase.piece.deletePiece();
                priseCase.piece.imageView.setVisibility(View.INVISIBLE);
                deplacement(priseCase);
                firstMoove = false;

            }
        });
    }


    public Case getCase() {
        return aCase;
    }

    public Piece getPiece() {
        return this;
    }

    public abstract List<Case> getListOfPossibleCases();


    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    /*public List<Case> getListOfPossibleTaken() {
        List<Case> list = getListOfPossibleCases();
        List<Case> listRemove = new ArrayList<>();
        Log.e("test",Integer.toString(list.size()));
        for (Case maCase:list) {
            if (maCase.piece != null && maCase.piece instanceof Roi){
                Log.e("test","tee");
                listRemove.add(maCase);
            }
        }
        list.removeAll(listRemove);
        return list;
    }*/
    public List<Case> getListOfPossibleTaken() {
        return getListOfPossibleCases();
    }

    public void deletePiece() {
        layout.removeView(imageView);
        imageView = null;
        aCase.setPiece(null);
        aCase = null;
        if (isBlack)
            echiquier.noirs.remove(this);
        else
            echiquier.blancs.remove(this);
        echiquier = null;

    }

    public boolean isKing(Case maCase) {
        if (maCase.piece != null && maCase.piece instanceof Roi)
            return true;
        return false;
    }

    public boolean canMove(Case maCase) {
        Case original = getCase();

        Piece pOriginal = null;
        if (maCase.piece != null) {
            pOriginal = maCase.piece;
            if (pOriginal.isBlack) echiquier.noirs.remove(pOriginal);
            else echiquier.blancs.remove(pOriginal);
        }
        aCase.piece = null;
        aCase = maCase;

        maCase.piece = this;
        if (isBlack) {
            if (echiquier.echecBlanc(echiquier.roiN.getCase())) {
                aCase = original;
                if (pOriginal != null) {
                    if (pOriginal.isBlack) echiquier.noirs.add(pOriginal);
                    else echiquier.blancs.add(pOriginal);
                }
                maCase.piece = pOriginal;
                aCase.piece = this;
                return false;
            }
        } else {
            if (echiquier.echecNoir(echiquier.roiB.getCase())) {
                aCase = original;
                if (pOriginal != null) {
                    if (pOriginal.isBlack) echiquier.noirs.add(pOriginal);
                    else echiquier.blancs.add(pOriginal);
                }
                maCase.piece = pOriginal;
                aCase.piece = this;
                return false;
            }
        }
        aCase = original;
        if (pOriginal != null) {
            if (pOriginal.isBlack) echiquier.noirs.add(pOriginal);
            else echiquier.blancs.add(pOriginal);
        }
        maCase.piece = pOriginal;
        aCase.piece = this;
        return true;

    }
}
