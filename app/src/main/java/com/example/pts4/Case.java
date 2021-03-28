package com.example.pts4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;


public class Case {

    int taille, coordPixelX, coordPixelY, nomCaseX, nomCaseY;
    Context context;
    ImageView imageView;
    Piece piece;
    int mainColor;

    /**
     * Déclaration d'une case
     * @param taille taille d'un côté d'une case
     * @param coordPixelX coordonnée en x du côté haut gauche de la case
     * @param coordPixelY coordonnée en y du côté haut gauche de la case
     * @param context context de la case
     * @param nomCaseX numéro de la case en x (0 sera la premiere colone)
     * @param nomCaseY numéro de la case en y (0 sera la premiere ligne)
     * @param color couleur d'origine de la case
     * @param layout layout sur lequel la case sera affichée
     */
    public Case(int taille, int coordPixelX, int coordPixelY, Context context, int nomCaseX, int nomCaseY, int color, ConstraintLayout layout) {
        this.taille = taille;
        this.coordPixelX = coordPixelX;
        this.coordPixelY = coordPixelY;
        this.nomCaseX = nomCaseX;
        this.nomCaseY = nomCaseY;
        this.context = context;
        mainColor = color;

        imageView = new ImageView(context);
        imageView.setBackgroundColor(color);
        imageView.setX(coordPixelX);
        imageView.setY(coordPixelY);
        layout.addView(imageView);
        imageView.getLayoutParams().width = taille;
        imageView.getLayoutParams().height = taille;

    }


    public ImageView getImageView() {
        return imageView;
    }

    public int getTaille() {
        return taille;
    }

    /**
     * Colorie la case pour undiquer si on peut ou non cliquer dessus
     * @param isClicked true la fait passer en clickable
     */
    public void clickable(boolean isClicked) {
        if (!isClicked) {
            imageView.setBackgroundColor(Color.GREEN);
        } else {
            imageView.setBackgroundColor(mainColor);
        }
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasBlackPiece() {
        if (piece != null)
            if (piece.isBlack) return true;
        return false;
    }

    public boolean hasWhitePiece() {
        if (piece != null)
            if (!piece.isBlack) return true;
        return false;
    }

    public boolean hasPiece() {
        if (piece == null) return false;
        return true;
    }

}
