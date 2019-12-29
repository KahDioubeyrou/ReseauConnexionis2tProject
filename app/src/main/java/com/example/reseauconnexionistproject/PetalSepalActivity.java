package com.example.reseauconnexionistproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reseauconnexionistproject.model.DataXY;
import com.example.reseauconnexionistproject.model.Model;
import com.example.reseauconnexionistproject.model.MyVector;
import com.example.reseauconnexionistproject.reseau.Perceptron;


public class PetalSepalActivity extends AppCompatActivity {
    private TextView sepalLengthTV;
    private TextView petalLengthTV;
    private TextView perceptronTV;
    private Button validateBtn;
    Perceptron perceptron;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        perceptron = new Perceptron();
        DataXY trainingData = Model.getModel(this).gettrainingData();
        DataXY testingData = Model.getModel(this).getTestingData();
        try {
            trainingData.fetchData(R.raw.base_test);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Float[] w = perceptron.learn(trainingData);
        try {
            testingData.fetchData(R.raw.base_apprentisage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView percentTV = findViewById(R.id.percentTV);
        percentTV.setText(R.string.test_modal);
        percentTV.append(String.valueOf(perceptron.testModel(testingData)*100)+" %");
        sepalLengthTV = findViewById(R.id.sepal_length_value);
        petalLengthTV = findViewById(R.id.petal_length_value);
        perceptronTV = findViewById(R.id.perceptronTV);
        validateBtn = findViewById(R.id.validate_battun);

        perceptronTV.setText(String.valueOf("("+w[0]+" , "+w[1]+" , "+w[2]+")"));

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sepalLengthTV.getText().toString().isEmpty()&&!petalLengthTV.getText().toString().isEmpty()){
                    float sepalLength = Float.parseFloat(sepalLengthTV.getText().toString());
                    float petalLength = Float.parseFloat(petalLengthTV.getText().toString());
                    /*MyVector testValue = new MyVector();
                    testValue.putValue(0, (float) 1);
                    testValue.putValue(1,sepalLength);
                    testValue.putValue(2,petalLength);*/
                    Float[] testValue;
                    testValue = new Float[3];
                    testValue[0] =(float) 1;
                    testValue[1] = sepalLength;
                    testValue[2] = petalLength;
                    Log.i("Test", "unitTest: "+testValue[0]+" , "+testValue[1]+" , "+testValue[2]);

                    int r = perceptron.unitTest(testValue);
                    String result;
                    if (r==0) result = "Iris-setosa";
                    else result = "Iris-versicolor";
                    Toast.makeText(PetalSepalActivity.this, "result is "+result, Toast.LENGTH_SHORT).show();
                }

            }
        });







    }
}
