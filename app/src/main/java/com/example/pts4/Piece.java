package com.example.pts4;

import android.content.Context;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class Piece {

    ImageView imageView;
    Case aCase;
    Context context;
    boolean isBlack;


    public abstract void deplacement(Case aCases);

    public Case getCase() {
        return aCase;
    }
}
