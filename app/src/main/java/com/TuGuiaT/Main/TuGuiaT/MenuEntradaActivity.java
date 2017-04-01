package com.TuGuiaT.Main.TuGuiaT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.TuGuiaT.IMC.ImcActivity;
import com.TuGuiaT.RegistersDatabase.RegisterBBDDActivity;
import com.TuGuiaT.RegistersDatabase.VieweRegisterBBDDActivity;

/**
 * Created by i42mogoj on 17/2/15.
 */
public class MenuEntradaActivity extends Activity implements OnClickListener {

    //creo variables internas para llamar a la funcion onclick
    private ImageButton btn_calculoIMC, btn_Cuestionario, btn_InsertarBD, btn_VistaRegistros,btn_VerPdf,btn_Graficas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuentrada_layout);

        btn_calculoIMC= (ImageButton) findViewById(R.id.btn_calculoIMC);
        btn_calculoIMC.setOnClickListener(this);
        btn_Cuestionario= (ImageButton) findViewById(R.id.btn_Cuestionario);
        btn_Cuestionario.setOnClickListener(this);
        btn_InsertarBD=(ImageButton) findViewById(R.id.btn_InsertarBD);
        btn_InsertarBD.setOnClickListener(this);
        btn_VistaRegistros = (ImageButton)findViewById(R.id.verRegistros);
        btn_VistaRegistros.setOnClickListener(this);
        btn_VerPdf = (ImageButton)findViewById(R.id.btn_Pdf);
        btn_VerPdf.setOnClickListener(this);
       // btn_Graficas = (Button)findViewById(R.id.btn_Graficas);
        //btn_Graficas.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {

            case R.id.btn_calculoIMC:
                intent = new Intent(MenuEntradaActivity.this,ImcActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Cuestionario:
                intent = new Intent(MenuEntradaActivity.this,InicioCuestionarioActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            case R.id.btn_InsertarBD:
                intent= new Intent(MenuEntradaActivity.this,RegisterBBDDActivity.class);
                startActivity(intent);
                break;
            case R.id.verRegistros:
                intent= new Intent(MenuEntradaActivity.this,VieweRegisterBBDDActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Pdf:
                intent= new Intent(MenuEntradaActivity.this,MenuGuiasActivity.class);
                startActivity(intent);
                break;
            // case R.id.btn_Graficas:
              //  intent= new Intent(MenuEntradaActivity.this,SimpleXYPlotActivity.class);
                //startActivity(intent);
                //break;

            }
        }

}

