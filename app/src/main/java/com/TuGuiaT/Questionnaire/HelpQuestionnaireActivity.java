package com.TuGuiaT.Questionnaire;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

import com.TuGuiaT.Main.TuGuiaT.R;

/**
 * Created by i42mogoj on 12/9/15.
 */
public class HelpQuestionnaireActivity extends Activity implements OnClickListener {


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
                intent = new Intent(HelpQuestionnaireActivity.this, QuestionnaireActivity.class);
                startActivity(intent);
                break;
        }
    }
}