package com.example.reseauconnexionistproject.model;


public class XY {
    private MyVector x;
    private MyVector y;

    public XY(MyVector x,MyVector y){
        this.x = x;
        this.y = y;
    }

    public MyVector getX() {
        return x;
    }

    public void setX(MyVector x) {
        this.x = x;
    }

    public MyVector getY() {
        return y;
    }

    public void setY(MyVector y) {
        this.y = y;
    }
}
