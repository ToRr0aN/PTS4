package com.example.pts4;

public abstract class Piece {

    int x, y;

    public Piece(int x, int y){
        this.x = x;
        this.y = y;
    }
    public abstract void deplacement();


}
