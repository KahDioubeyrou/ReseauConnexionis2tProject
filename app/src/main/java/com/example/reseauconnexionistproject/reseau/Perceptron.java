package com.example.reseauconnexionistproject.reseau;

import android.util.Log;
import com.example.reseauconnexionistproject.model.DataXY;
import com.example.reseauconnexionistproject.model.MyVector;

public class Perceptron {
    private final String TAG ="VectorW ";
    private Float[] W;
    private int sizeOfX = 2;

    public Perceptron() {
        W = new Float[3];
        for (int i = 0; i<= sizeOfX; i++){
            W[i] = (float) 1;
        }

    }

    public  Float[] learn(DataXY data){

        for (int i = 0; i < data.getSize(); i++) {
            MyVector x = data.get(i).getX();
            MyVector y = data.get(i).getY();
            Log.i("Row", "  "+x.getValue(0)+" , "+x.getValue(1)+" , "+x.getValue(2)+"     "+y.getValue(0));
        }

        Log.i(TAG+0+" iteration :", "learn:"+ W[0] +" , "+ W[1]+" , "+ W[2]);
        Float somme = (float) 0;
        int outPut = 0 ;
        for (int i = 0; i < data.getSize(); i++) {
            MyVector x = data.get(i).getX();
            MyVector y = data.get(i).getY();
            Float c = y.getValue(0);
            somme = Float.valueOf(0);

            for (int j = 0;j <= sizeOfX;j++){
                somme += x.getValue(j) * W[j] ;
            }

            if (somme > 0) outPut = 1;
            else outPut =  0;

            for (int k = 0;k <= 2; k++) {
                Log.i("Update", "learn: "+(W[k] + (c-outPut)*x.getValue(k)));
                W[k] = W[k] + (c-outPut)*x.getValue(k);
            }
            Log.i(TAG+(i+1)+" iteration :", "learn :"+ W[0] +" , "+ W[1]+" , "+ W[2]);
        }
        return W;

    }

    public float testModel(DataXY data){
        int success = 0;
        Log.i(TAG, "testModel: testData size "+data.getSize());
        for (int i = 0; i < data.getSize(); i++) {

            MyVector x = data.get(i).getX();
            MyVector y = data.get(i).getY();
            Float[] testValue;
            testValue = new Float[3];
            testValue[0] =(float) 1;
            testValue[1] = x.getValue(1);
            testValue[2] = x.getValue(2);
           // Log.i(TAG, "unitTest: "+testValue[0]+" , "+testValue[1]+" , "+testValue[2]);
            if (y.getValue(0) == unitTest(testValue)){
                //Log.i(TAG, "testModel:  Success-----------");
                success++;
            }
            else {
                Log.i(TAG, "testModel: "+testValue[0]+" , "+testValue[1]+" , "+testValue[2]+" FAILLED-----------");
            }


        }

        Float percent = (float) success/data.getSize();
        Log.i(TAG, "testModel:  Total"+ (percent*100));
        return percent;
    }

    public int unitTest(Float[] x){
        float result = 0;

        for (int i = 0; i <= sizeOfX; i++) {
            result += x[i]*W[i];
        }
        if (result > 0) return 1;
        return 0;
    }
}