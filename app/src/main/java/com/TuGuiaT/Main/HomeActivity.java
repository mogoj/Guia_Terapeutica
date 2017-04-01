package com.TuGuiaT.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.TuGuiaT.IMC.ImcActivity;
import com.TuGuiaT.Main.TuGuiaT.R;
import com.TuGuiaT.Questionnaire.HelpQuestionnaireActivity;
import com.TuGuiaT.RegistersDatabase.RegisterBBDDActivity;
import com.TuGuiaT.RegistersDatabase.VieweRegisterBBDDActivity;
import com.firebase.client.annotations.NotNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by i42mogoj on 17/2/15.
 */
public class HomeActivity extends Activity implements OnClickListener {

    //creo variables internas para llamar a la funcion onclick
    private ImageButton btn_calculoIMC, btn_Cuestionario, btn_InsertarBD, btn_VistaRegistros,btn_VerPdf,btn_Graficas;
    //Añadido modulo Firebase autentificación
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuentrada_layout);

        mAuth = FirebaseAuth.getInstance();
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

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {

            case R.id.btn_calculoIMC:
                intent = new Intent(HomeActivity.this,ImcActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Cuestionario:
                intent = new Intent(HomeActivity.this,HelpQuestionnaireActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            case R.id.btn_InsertarBD:
                intent= new Intent(HomeActivity.this,RegisterBBDDActivity.class);
                startActivity(intent);
                break;
            case R.id.verRegistros:
                intent= new Intent(HomeActivity.this,VieweRegisterBBDDActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Pdf:
                intent= new Intent(HomeActivity.this,MenuGuiasActivity.class);
                startActivity(intent);
                break;
            // case R.id.btn_Graficas:
              //  intent= new Intent(HomeActivity.this,SimpleXYPlotActivity.class);
                //startActivity(intent);
                //break;

            }
        }

}

