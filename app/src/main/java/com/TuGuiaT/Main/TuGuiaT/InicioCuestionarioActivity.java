package com.TuGuiaT.Main.TuGuiaT;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by i42mogoj on 12/9/15.
 */
public class InicioCuestionarioActivity extends Activity implements OnClickListener {


    private Button btn_Inicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_cuestionario_layout);


        btn_Inicio = (Button) findViewById(R.id.inicio);
        btn_Inicio.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {

            case R.id.inicio:
                intent = new Intent(InicioCuestionarioActivity.this, CuestionarioActivity.class);
                startActivity(intent);
                break;
        }
    }
}