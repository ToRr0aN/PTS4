package com.example.pts4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main);
        //Case aCase = new Case(100, 0, 0, this, 1, 1, Color.BLACK, layout);
        Echiquier echiquier = new Echiquier(this, layout);
        //test

            //cases[i%8][incrémenteur] = aCase;


    }
}