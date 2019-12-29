package com.example.reseauconnexionistproject.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MyVector {
    private List<List<Float>> matrix;

    public MyVector(){
        matrix = new ArrayList<>();
        matrix.add(new ArrayList<Float>());
    }

    public Float getValue(int line,int column){
        return matrix.get(line).get(column);
    }

    public void putValue(int line, int column, Float value){
        if (matrix.get(line) == null){
            matrix.add(line,new ArrayList<Float>());
        }

        matrix.get(line).add(column,value);
    }

    public Float getValue(int indice){
        return matrix.get(0).get(indice);
    }


    public void putValue(int indice, Float value){
        matrix.get(0).add(indice,value);
    }

    public int size(){
        int size = matrix.size();
        if (size != 0) size = size*matrix.get(0).size();
        return size;
    }

    public int getLineSize(){
        return matrix.size();
    }

    public int getColSize(){
        int size = 0;
        if (matrix != null) size = matrix.get(0).size();
        return size;
    }

    @NonNull
    @Override
    public String toString() {
        String s="";
        for (int i = 0; i < getColSize(); i++) {
           s= s+"x "+i+" =";
        }
        return s;
    }
}
