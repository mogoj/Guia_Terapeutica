package com.TuGuiaT.IMC;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.TuGuiaT.Main.HomeActivity;
import com.TuGuiaT.Main.TuGuiaT.R;
import com.TuGuiaT.RegistersDatabase.BBDDActivity;

import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PopupActivity extends Activity implements OnClickListener {

    TextView Puntacion,resultadoIMC,resultadoGuia;
    private Button btnExit,btnCalcular,btnAñadir;

    private int sumaCuestionario=0,guia=0,sumTotal=0;
    Bundle datos;
    private String cadena;
    EditText peso, altura;
    String Total;
    String [] imc;
    BBDDActivity MDB = null;
    Date internalFecha;
    private float m_peso,m_Dolor,m_altura,m_imc;
    String valor1,valor2,valor3,valor4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);
        datos = this.getIntent().getExtras();
        sumaCuestionario= datos.getInt("suma");
        cadena = String.valueOf(sumaCuestionario);

        peso = (EditText) findViewById(R.id.Peso);
        altura = (EditText) findViewById(R.id.Altura);
        resultadoIMC = (TextView) findViewById(R.id.ResultIMC);
        resultadoGuia = (TextView) findViewById(R.id.ResultadoGuia);
        Puntacion = (TextView) findViewById(R.id.Puntuacion);

        btnExit =(Button) findViewById(R.id.cerrar);
        btnExit.setOnClickListener(this);
        btnCalcular =(Button) findViewById(R.id.calcular);
        btnCalcular.setOnClickListener(this);
        btnAñadir =(Button) findViewById(R.id.btn_añadir);
        btnAñadir.setOnClickListener(this);
        btnAñadir.setVisibility(View.INVISIBLE);

        Puntacion.setText(cadena);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.btn_añadir:
                valor1=Puntacion.getText().toString();
                valor2=peso.getText().toString();
                valor3=altura.getText().toString();
                //valor4=resultadoIMC.getText().toString();


                internalFecha= new Date();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDateString = formatter.format(internalFecha);

                m_Dolor = Float.valueOf(valor1);
                m_peso = Float.valueOf(valor2);
                m_altura = Float.valueOf(valor3);
                m_imc= Float.valueOf(imc[0]);

                Log.d("ResgistroActivity", "Dolor = '" + m_Dolor +"'");
                Log.d("ResgistroActivity", "Peso= '" + m_peso + "'");
                Log.d("ResgistroActivity", "Altura= '" + m_altura + "'");
                Log.d("ResgistroActivity", "IMC = '" + m_imc + "'");
                Log.d("ResgistroActivity", "Fecha = '" + internalFecha + "'");
                Log.d("ResgistroActivity", "Fecha = '" + formattedDateString + "'");

                MDB.insertContact(m_Dolor, m_peso, m_altura, m_imc,formattedDateString);


                Toast.makeText(this, "Datos almacenados correctamente", Toast.LENGTH_SHORT).show();

                //btnAñadir.setVisibility(View.INVISIBLE);
                break;

            case R.id.cerrar:
                intent = new Intent(PopupActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.calcular:

                if (peso.getText().toString().trim().length() == 0) {
                    peso.requestFocus();

                    Toast.makeText(PopupActivity.this,
                            "No te olvides de rellenar el peso", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    if (altura.getText().toString().trim().length() == 0) {
                        altura.requestFocus();

                        Toast.makeText(PopupActivity.this,
                                "No te olvides de rellenar la altura",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        imc =this.calcularIMC(Float.parseFloat(peso.getText().toString()), Float.parseFloat(altura.getText().toString()));
                        resultadoIMC.setText("Tu IMC es " + imc[0] + "\n" + imc[1]);
                        Log.d("Puntacion","Guia nº=>"+ guia);
                        Log.d("Puntacion","sumaCuestionario"+ sumaCuestionario);
                        if (sumaCuestionario <70 ) guia=1;
                        if ((sumaCuestionario >=70) && (sumaCuestionario <130) ) guia=2;
                        if (sumaCuestionario >=130) guia=3;

                        resultadoGuia.setText(String.valueOf(guia));

                    }
                }
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    public String[] calcularIMC(float peso,float altura){
        String[] resultado = null;

        float m = (altura / 100);
        double bmi = round((peso) / (m * m), 2);

        Total+= bmi;


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
                        resultado[1] = "Sobrepeso grado 2";
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
                                    resultado[1] = "Obesidad de tipo 3";


                                    //Rango 8
                                } else {
                                    if ((bmi > 50)) {
                                        resultado = new String[2];
                                        resultado[0] = bmi2;
                                        resultado[1] = "Obesidad de tipo 4";

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

