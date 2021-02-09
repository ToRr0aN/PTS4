package com.example.pts4;

import android.content.Context;
import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Cheval extends Piece{

    public Cheval(Case aCase, Context context, ConstraintLayout layout, boolean isBlack, Echiquier echiquier) {
        super(aCase, context, layout, isBlack, echiquier);
        if (isBlack)
            imageView.setImageResource(R.drawable.pion);
        else
            imageView.setImageResource(R.drawable.pion);

        layout.addView(imageView);
        imageView.getLayoutParams().height = (int) (getCase().getTaille());
        imageView.getLayoutParams().width = (int) (getCase().getTaille());
        imageView.setY(getCase().coordPixelY + getCase().taille / 2 - imageView.getLayoutParams().height / 2);
        imageView.setX(getCase().coordPixelX);
    }

    @Override
    public void showDeplacement() {

        for (int i=1; i<3; i++){
            if (aCase.nomCaseX > 2 && aCase.nomCaseY < 7){
                cases[aCase.nomCaseX + 2][aCase.nomCaseY +1].imageView.setBackgroundColor(Color.GREEN);
            }
        }

    }
}
