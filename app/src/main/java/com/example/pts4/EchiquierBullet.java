package com.example.pts4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

public class EchiquierBullet extends Echiquier {

    boolean first = true;
    boolean firstFinish = true;
    CountDownTimer countDownTimerBlanc, countDownTimerNoirs;
    TextView textBlancsTimer, textNoirsTimer;
    long counterBlancs = 60000, counterNoirs = 60000;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public EchiquierBullet(Context context, ConstraintLayout layout) {
        super(context, layout);
    }

    @Override
    public void manche(boolean tour) {
        textBlancsTimer = layout.findViewById(R.id.countDownBlancs);
        textNoirsTimer = layout.findViewById(R.id.countDownNoirs);

        boolean mat = false;


        if (tour) {

            countDownTimerBlanc = new CountDownTimer(counterBlancs, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    textBlancsTimer.setText(Long.toString(millisUntilFinished / 1000)+"s");
                    counterBlancs = millisUntilFinished;
                }

                @Override
                public void onFinish() {
                    if (firstFinish) {
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("Les noirs ont gagné par le temps!");
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
                }
            }.start();
        } else {
            if (countDownTimerNoirs != null) {
                countDownTimerBlanc.cancel();
                counterBlancs += 3000;
            }
            textBlancsTimer.setText(Long.toString(counterBlancs / 1000)+"s");
        }
        if (!tour) {

            countDownTimerNoirs = new CountDownTimer(counterNoirs, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    textNoirsTimer.setText(Long.toString(millisUntilFinished / 1000)+"s");
                    counterNoirs = millisUntilFinished;


                }

                @Override
                public void onFinish() {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
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
            }.start();
        } else {
            if (countDownTimerNoirs != null)
                countDownTimerNoirs.cancel();
            counterNoirs += 3000;

            if (firstFinish) {
                textNoirsTimer.setText(Long.toString(counterNoirs / 1000)+"s");
            }else{
                textNoirsTimer.setText(Long.toString(60)+"s");
            }
        }


        if (tour) {
            for (Piece piece : noirs) {
                piece.imageView.setOnClickListener(null);
            }
            first = !first;
        } else {


            for (Piece piece : blancs) {
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

}





