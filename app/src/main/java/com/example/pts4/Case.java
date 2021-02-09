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
    //boolean isClicked;

    public ImageView getImageView() {
        return imageView;
    }

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

    public void putPion(Piece piece) {
        this.piece = piece;
    }

    public int getTaille() {
        return taille;
    }

    public void clickable(boolean isClicked){
        if (!isClicked)imageView.setBackgroundColor(Color.GREEN);
        else imageView.setBackgroundColor(mainColor);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasBlackPiece(){
        if ( piece!=null)
            if (piece.isBlack) return true;
        return false;
    }

    public boolean hasWhitePiece(){
        if ( piece!=null)
            if (!piece.isBlack) return true;
        return false;
    }

    public boolean hasPiece(){
        if (piece==null) return false;
        return true;
    }

}
