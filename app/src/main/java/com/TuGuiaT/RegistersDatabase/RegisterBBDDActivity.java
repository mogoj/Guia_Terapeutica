package com.TuGuiaT.RegistersDatabase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.TuGuiaT.Main.TuGuiaT.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by i42mogoj on 7/3/15.
 */
public class RegisterBBDDActivity extends Activity implements OnClickListener{

   Button btn_Enviar;
   EditText internal_Dolor,internal_peso,internal_altura,internal_imc;
   Date internalFecha;
   private float m_peso,m_Dolor,m_altura,m_imc;
   String valor1,valor2,valor3,valor4;
   int id_internal;

   BBDDActivity MDB = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_layout);

    internal_Dolor= (EditText)findViewById(R.id.txtDolor);
    internal_peso= (EditText)findViewById(R.id.txtPeso);
    internal_altura= (EditText)findViewById(R.id.txtAltura);
    internal_imc=(EditText)findViewById((R.id.txtIMC));
    btn_Enviar= (Button)findViewById(R.id.btn_Enviar);
    btn_Enviar.setOnClickListener(this);

    MDB = new BBDDActivity(getApplicationContext());

    }


    @Override
    public void onClick(View v) {

        valor1=internal_Dolor.getText().toString();
        Log.d("ResgistroActivity", "Dolor = '" + valor1 +"'");
        valor2=internal_peso.getText().toString();
        Log.d("ResgistroActivity", "Peso= '" + valor2 + "'");
        valor3=internal_altura.getText().toString();
        Log.d("ResgistroActivity", "Altura= '" + valor3 + "'");
        valor4=internal_imc.getText().toString();
        Log.d("ResgistroActivity", "IMC = '" + valor4 + "'");

        internalFecha= new Date();


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateString = formatter.format(internalFecha);

        m_Dolor = Float.valueOf(valor1);
        m_peso = Float.valueOf(valor2);
        m_altura = Float.valueOf(valor3);
        m_imc= Float.valueOf(valor4);

      /*  switch (v.getId()) {

           /* case R.id.ayuda:
                Intent i = new Intent(this, Ayuda.class);
                startActivity(i);
                break;

            case R.id.btn_Enviar:

                if (m_peso == 0) {

                    Toast.makeText(RegisterBBDDActivity.this,
                            "No te olvides de rellenar el peso", Toast.LENGTH_SHORT).show();
                } else {
                    if (m_altura == 0) {


                        Toast.makeText(RegisterBBDDActivity.this,"No te olvides de rellenar la altura",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        String[] imc =this.calcularIMC(m_peso,m_altura);
                        RegisterBBDDActivity.setText("Tu IMC es " + imc[0] + "\n" + imc[1]);

                    }
                }
        }*/

        MDB.insertContact(m_Dolor,m_peso,m_altura,m_imc,formattedDateString);


        Toast.makeText(this, "Datos almacenados correctamente", Toast.LENGTH_SHORT).show();
        internal_Dolor.setText("");
        internal_peso.setText("");
        internal_altura.setText("");
        internal_imc.setText("");


    }
}
