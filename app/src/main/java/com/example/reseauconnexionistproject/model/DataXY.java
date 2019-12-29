package com.example.reseauconnexionistproject.model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.reseauconnexionistproject.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataXY{
    private List<XY> inputOutputs;
    private int sizeOfX;
    Context context;

    public DataXY(Context context){
        inputOutputs = new ArrayList<>();
        sizeOfX = 2;
        this.context = context;
    }

    public boolean fetchData( int resfile) throws Exception{
        InputStream inputStream =context.getResources().openRawResource(resfile);
        InputStreamReader file = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(file);

        String line;
        while ((line = reader.readLine()) != null){

            String[] row = line.split(";");
            MyVector x = new MyVector();
            MyVector y = new MyVector();
            x.putValue(0,(float) Float.parseFloat(row[0]));
            x.putValue(1,(float) Float.parseFloat(row[1]));
            x.putValue(2,(float) Float.parseFloat(row[2]));
            y.putValue(0, (float) Float.parseFloat(row[3]));
            XY xy = new XY(x,y);
            inputOutputs.add(xy);

        }

       /* for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {

                MyVector x = new MyVector();
                MyVector y = new MyVector();
                x.putValue(0,(float) 1);
                x.putValue(1,(float) i);
                x.putValue(2,(float) j);
                y.putValue(0, (float) ((i==1)||(j==1)?1:0));
                XY xy = new XY(x,y);
                inputOutputs.add(xy);
                //Log.i("Vecteur X", "fetchData: "+1+","+x.getValue(1)+","+x.getValue(2)+"   "+y.getValue(0) );
            }
        }*/


        return true;
    }


    public List<XY> getAll() {
        return inputOutputs;
    }

    public XY get(int indice){
        return inputOutputs.get(indice);
    }

    public int getSizeOfX(){
        return inputOutputs.get(0).getX().size();
    }

    public int getSize(){
        return inputOutputs.size();
    }
}
