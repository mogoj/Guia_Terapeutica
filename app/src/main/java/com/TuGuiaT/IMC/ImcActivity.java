package com.TuGuiaT.IMC;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.TuGuiaT.Main.TuGuiaT.R;

import java.lang.String;


/**
 * Created by i42mogoj on 16/2/15.
 */
public class ImcActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    EditText peso, altura;
    TextView resultadoIMC;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_layout);

        Button calcular = (Button) findViewById(R.id.calcular);
        calcular.setOnClickListener(this);

        //Button ayuda = (Button) findViewById(R.id.ayuda);
        //ayuda.setOnClickListener(this);

        peso = (EditText) findViewById(R.id.InputPeso);
        altura = (EditText) findViewById(R.id.InputAltura);
        resultadoIMC = (TextView) findViewById(R.id.ResultIMC);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

           /* case R.id.ayuda:
                Intent i = new Intent(this, Ayuda.class);
                startActivity(i);
                break;*/

            case R.id.calcular:

                if (peso.getText().toString().trim().length() == 0) {
                    peso.requestFocus();

                    Toast.makeText(ImcActivity.this,
                            "No te olvides de rellenar el peso", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    if (altura.getText().toString().trim().length() == 0) {
                        altura.requestFocus();

                        Toast.makeText(ImcActivity.this,
                                "No te olvides de rellenar la altura",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String[] imc =this.calcularIMC(Float.parseFloat(peso.getText().toString()), Float.parseFloat(altura.getText().toString()));
                        resultadoIMC.setText("Tu IMC es " + imc[0] + "\n" + imc[1]);

                    }
                }
        }
    }

    public String[] calcularIMC(float peso,float altura){
        String[] resultado = null;
        Log.d("IMC","Altura=>"+ altura);
        Log.d("IMC","Peso=>"+ peso);

        float m = (altura / 100);
        double bmi =round((peso) / (m * m),2);


        Log.d("IMC","IMC=>"+ bmi);
        //bmi = bmi/1000;

        String bmi2 = String.valueOf(bmi);
        //Rango 1
        if ((bmi < 18.5) ) {
            resultado = new String[2];
            resultado[0] = bmi2;
            resultado[1] = "Peso Insuficiente";
            //Rango 2
        } else {
            if (((bmi > 18.5) && (bmi < 24.9)) ) {
                resultado = new String[2];
                resultado[0] = bmi2;
                resultado[1] = "Normopeso";
                //Rango 3
            } else {
                if ((bmi > 25) && (bmi < 26.9)) {
                    resultado = new String[2];
                    resultado[0] = bmi2;
                    resultado[1] = "Sobrepeso grado 1";
                    //Rango 4
                } else {
                    if ((bmi > 27) && (bmi < 29.9) ) {
                        resultado = new String[2];
                        resultado[0] = bmi2;
                        resultado[1] = "Sobrepeso grado 2(preobesidad)";
                        //Rango 5
                    } else {
                        if ((bmi > 30) && (bmi < 34.9)) {
                            resultado = new String[2];
                            resultado[0] = bmi2;
                            resultado[1] = "Obesidad de tipo 1";

                            //Rango 6
                        } else {
                            if ((bmi > 35) && (bmi < 39.9)) {
                                resultado = new String[2];
                                resultado[0] = bmi2;
                                resultado[1] = "Obesidad de tipo 2";
                                //Rango 7
                            } else {
                                if ((bmi > 40) && (bmi < 49.9)) {
                                    resultado = new String[2];
                                    resultado[0] = bmi2;
                                    resultado[1] = "Obesidad de tipo 3(mórbida)";


                                    //Rango 8
                                } else {
                                    if ((bmi > 50)) {
                                        resultado = new String[2];
                                        resultado[0] = bmi2;
                                        resultado[1] = "Obesidad de tipo 4(extrema)";

                                    }
                                }

                            }
                        }

                    }
                }
            }

        }
    return resultado;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
