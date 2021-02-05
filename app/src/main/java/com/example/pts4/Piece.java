package com.example.pts4;

import android.content.Context;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class Piece {

    Case aCase;
    Context context;

    public Piece(Case aCase, Context context){
        this.aCase = aCase;
    }
    public abstract void deplacement(Case aCases);

    public Case getCase() {
        return aCase;
    }
}
