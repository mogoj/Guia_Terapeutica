package com.TuGuiaT.Questionnaire;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.TuGuiaT.IMC.PopupActivity;
import com.TuGuiaT.Main.TuGuiaT.R;

public class QuestionnaireActivity extends Activity {

    //Preguntas
    private String[][] Question;
    //Respuestas y posicion en el cuestionario
    private int values[][],  currentQuestion[];

    private String[] Apartado,str_instrucciones;
    private TextView titulo_apartado,instrucciones,id_pregunta,txt_pregunta;
    private Button btn_atras,btn_siguiente;
    private RadioGroup rdgroup;
    private RelativeLayout llay;
    private float[][] ptsDragAndDrop;
    private float x, y, finalx, finaly;

    private String txtSiguiente, txtTerminar;
    //variable auxiliar donde se suma valores de todas las respuestas
    int suma = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartado_layout);
        Bundle bundle;
        txtTerminar = "Siguiente";
        txtSiguiente = getResources().getString(R.string.btn_Siguiente);

        //Iniciar array a las preguntas

        Question = new String[3][];
        Question[0]= getResources().getStringArray(R.array.QuestionsA);
        Question[1]= getResources().getStringArray(R.array.QuestionsB);
        Question[2]= getResources().getStringArray(R.array.QuestionsC);

        //Iniciar las respuestas a -1: 0= Ninguno 1 =Poco ..... 4=Muchisimo
        values = new int[Question.length][];

            for (int i=0;i<values.length;i++)
            {
                values[i]=new int[Question[i].length];

                for (int j=0;j<values[i].length;j++) values[i][j]=-1;

            }
        //Iniciar numero pregunta actual : [0]->pos Apartado,[1]->pos Question
        currentQuestion= new int[2];
        currentQuestion[0]= 0;
        currentQuestion[1]= 0;

        //Iniciar variables

        Apartado= getResources().getStringArray(R.array.Apartados);
        str_instrucciones= getResources().getStringArray(R.array.instrucciones);

        titulo_apartado= (TextView) findViewById(R.id.Titulo_apartado);
        titulo_apartado.setText(Apartado[currentQuestion[0]]);

        //titulo_apartado.setText(R.string.Apartado_A);
        instrucciones= (TextView) findViewById(R.id.instrucciones);
        instrucciones.setText(getResources().getTextArray(R.array.instrucciones)[0]);
        id_pregunta= (TextView) findViewById(R.id.id_question);
        id_pregunta.setText("Pregunta "+ String.valueOf(currentQuestion[1]+1) + "/" + Question[currentQuestion[0]].length);
        txt_pregunta=(TextView) findViewById(R.id.txt_question);
        txt_pregunta.setText(Question[currentQuestion[0]][currentQuestion[1]]);

        //Iniciar radiogroup
        rdgroup = (RadioGroup) findViewById(R.id.rdGroup);

        rdgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    //Peso de cada pregunta

                    case R.id.radioButtonNinguno:

                        values[currentQuestion[0]][currentQuestion[1]]=0;
                        Log.d("Puntuacion", "push("+checkedId + values[currentQuestion[0]][currentQuestion[1]] + ") => NINGUNO");

                        break;
                    case R.id.radioButtonPoco:

                        values[currentQuestion[0]][currentQuestion[1]]=1;
                        Log.d("Puntuacion", "push(" + checkedId + values[currentQuestion[0]][currentQuestion[1]]+ ") => POCO");
                        break;
                    case R.id.radioButtonBastante:

                        values[currentQuestion[0]][currentQuestion[1]]=2;
                        Log.d("Puntuacion", "push(" + checkedId + values[currentQuestion[0]][currentQuestion[1]]+ ") => BASTANTE");
                        break;
                    case R.id.radioButtonMucho:

                        values[currentQuestion[0]][currentQuestion[1]]=3;
                        Log.d("Puntuacion", "push(" + checkedId + values[currentQuestion[0]][currentQuestion[1]]+ ") => MUCHO");
                        break;
                    case R.id.radioButtonMuchisimo:

                        values[currentQuestion[0]][currentQuestion[1]]=4;
                        Log.d("Puntuacion", "push(" + checkedId + values[currentQuestion[0]][currentQuestion[1]]+ ") => MUCHISIMO");
                        break;
                }
                //Si estoy en el ultimo apartado
                if(currentQuestion[0] + 1 == values.length) {
                    //si estoy en el ultima pregunta del ultimo apartado
                    if (currentQuestion[1] + 1 == values[currentQuestion[0]].length) {
                        //Verfico que esta todas rellanadas
                        boolean todas = true;
                        Log.d("Puntuacion","Puntuacion=>"+ suma);
                        for (int i = 0; i < values.length && todas; i++) {
                            for (int j = 0; j < values[i].length && todas; j++) {
                                if (values[i][j] == -1) {
                                    todas = false;
                                    suma=0;
                                }else{
                                    //Cambiar error, parcheado****
                                    suma += values[i][j]/4;
                       //             Log.d("Puntuacion","Puntuacion=>"+ suma);
                                }
                            }
                        }
                        if (todas) {
                            btn_siguiente.setText("TERMINAR");
                            btn_siguiente.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(QuestionnaireActivity.this, PopupActivity.class);
                                    intent.putExtra("suma", suma);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }

                }
            }
        });

        //Iniciar Botones
        btn_atras = (Button) findViewById(R.id.btn_anterior);
        btn_atras.setVisibility(View.INVISIBLE);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            //Menu de botones para mostrar boton Atras
            @Override
            public void onClick(View v) {
                if(currentQuestion[1] > 0){
                    currentQuestion[1]--;
                    //btn_siguiente.setText(btn_siguiente);
                    btn_siguiente.setText(txtSiguiente);
                    btn_siguiente.setVisibility(View.VISIBLE);
                    switch(values[currentQuestion[0]][currentQuestion[1]]){
                        case 0:
                            rdgroup.check(R.id.radioButtonNinguno);
                            break;
                        case 1:
                            rdgroup.check(R.id.radioButtonPoco);
                            break;
                        case 2:
                            rdgroup.check(R.id.radioButtonBastante);
                            break;
                        case 3:
                            rdgroup.check(R.id.radioButtonMucho);
                            break;
                        case 4:
                            rdgroup.check(R.id.radioButtonMuchisimo);
                            break;
                        default:
                            rdgroup.clearCheck();
                            break;
                    }
                    txt_pregunta.setText(Question[currentQuestion[0]][currentQuestion[1]]);
                    id_pregunta.setText("Pregunta "+ String.valueOf(currentQuestion[1]+1) + "/" + Question[currentQuestion[0]].length);
                    if(currentQuestion[1] == 0){
                        btn_atras.setVisibility(View.INVISIBLE);
                    }else if (currentQuestion[0] + 1 == values.length) {
                        if ((currentQuestion[1] + 2) == Question[currentQuestion[0]].length) {
                            btn_siguiente.setText(txtSiguiente);
                        }
                    }
                }
            }
        });

        btn_siguiente = (Button) findViewById(R.id.btn_siguiente);
        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentQuestion[1] + 1) < Question[currentQuestion[0]].length) {
                    currentQuestion[1]++;
                    btn_atras.setVisibility(View.VISIBLE);
                    switch (values[currentQuestion[0]][currentQuestion[1]]) {
                        case 0:
                            rdgroup.check(R.id.radioButtonNinguno);
                            break;
                        case 1:
                            rdgroup.check(R.id.radioButtonPoco);
                            break;
                        case 2:
                            rdgroup.check(R.id.radioButtonBastante);
                            break;
                        case 3:
                            rdgroup.check(R.id.radioButtonMucho);
                            break;
                        case 4:
                            rdgroup.check(R.id.radioButtonMuchisimo);
                            break;
                        default:
                            rdgroup.clearCheck();
                            break;
                    }
                    txt_pregunta.setText(Question[currentQuestion[0]][currentQuestion[1]]);
                    id_pregunta.setText("Pregunta " + String.valueOf(currentQuestion[1] + 1) + "/" + Question[currentQuestion[0]].length);

                    if (currentQuestion[0] + 1 < values.length) {
                        if ((currentQuestion[1] + 1) == Question[currentQuestion[0]].length) {
                            btn_siguiente.setVisibility(View.INVISIBLE);
                        }
                    } else if (currentQuestion[0] + 1 == values.length) {
                        if ((currentQuestion[1] + 1) == Question[currentQuestion[0]].length) {

                            boolean todas = true;

                            for (int i = 0; i < values.length && todas; i++) {
                                for (int j = 0; j < values[i].length && todas; j++) {
                                    if (values[i][j] == -1) {
                                        todas = false;
                                        suma = 0;
                                    } else {

                                        suma += values[i][j];

                                    }
                                }
                            }
                            if (todas) {
                                btn_siguiente.setText(txtTerminar);
                                btn_siguiente.setVisibility(View.VISIBLE);
                            }
                        } else if ((currentQuestion[1] + 1) > Question[currentQuestion[0]].length) {
                            //lanzar popup
                            btn_siguiente.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(QuestionnaireActivity.this, PopupActivity.class);
                                    intent.putExtra("suma", suma);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            }
                        }
                    }
                }
            }

            );
//Codigo para para deslizar entre apartados
            ptsDragAndDrop=new float[2][2];
            for(
            int i = 0;
            i<ptsDragAndDrop.length;i++)

            {
                for (int j = 0; j < ptsDragAndDrop.length; j++) {
                    ptsDragAndDrop[i][j] = -1;
                }
            }

            llay=(RelativeLayout)

            findViewById(R.id.layApartado);

            llay.setOnTouchListener(new View.OnTouchListener()

                                    {
                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                            int action = event.getAction();
                                            switch (action) {
                                                case MotionEvent.ACTION_DOWN:
                                                    Log.d("LinearLayout", "MotionEvent.ACTION_DOWN => punto de origen: (" + event.getX() + ", " + event.getY() + ")");
                                                    x = 0f;
                                                    ptsDragAndDrop[0][0] = event.getX();
                                                    ptsDragAndDrop[0][1] = event.getY();
                                                    return true;
                                                case MotionEvent.ACTION_UP:
                                                    Log.d("LinearLayout", "MotionEvent.ACTION_UP => punto de destino: (" + event.getX() + ", " + event.getY() + ")");
                                                    ptsDragAndDrop[1][0] = event.getX();
                                                    ptsDragAndDrop[1][1] = event.getY();
                                                    if ((ptsDragAndDrop[0][0] != -1) && (ptsDragAndDrop[0][1] != -1)) {
                                                        if (ptsDragAndDrop[0][0] < ptsDragAndDrop[1][0]) {
                                                            if (currentQuestion[0] > 0) {
                                                                currentQuestion[0]--;
                                                                currentQuestion[1] = 0;
                                                                btn_siguiente.setVisibility(View.VISIBLE);
                                                                btn_atras.setVisibility(View.INVISIBLE);
                                                                switch (values[currentQuestion[0]][currentQuestion[1]]) {
                                                                    case 0:
                                                                        rdgroup.check(R.id.radioButtonNinguno);
                                                                        break;
                                                                    case 1:
                                                                        rdgroup.check(R.id.radioButtonPoco);
                                                                        break;
                                                                    case 2:
                                                                        rdgroup.check(R.id.radioButtonBastante);
                                                                        break;
                                                                    case 3:
                                                                        rdgroup.check(R.id.radioButtonMucho);
                                                                        break;
                                                                    case 4:
                                                                        rdgroup.check(R.id.radioButtonMuchisimo);
                                                                        break;
                                                                    default:
                                                                        rdgroup.clearCheck();
                                                                        break;
                                                                }
                                                                titulo_apartado.setText(getResources().getTextArray(R.array.Apartados)[currentQuestion[0]]);
                                                                instrucciones.setText(getResources().getTextArray(R.array.instrucciones)[currentQuestion[0]]);
                                                                txt_pregunta.setText(Question[currentQuestion[0]][currentQuestion[1]]);
                                                                id_pregunta.setText("Pregunta " + String.valueOf(currentQuestion[1] + 1) + "/" + Question[currentQuestion[0]].length);
                                                                if ((currentQuestion[1] + 1) == Question[currentQuestion[0]].length) {
                                                                    btn_siguiente.setVisibility(View.INVISIBLE);
                                                                }
                                                            }
                                                        } else if (ptsDragAndDrop[0][0] > ptsDragAndDrop[1][0]) {
                                                            if (currentQuestion[0] + 1 < Question.length) {
                                                                currentQuestion[0]++;
                                                                currentQuestion[1] = 0;
                                                                btn_siguiente.setVisibility(View.VISIBLE);
                                                                btn_atras.setVisibility(View.INVISIBLE);
                                                                switch (values[currentQuestion[0]][currentQuestion[1]]) {
                                                                    case 0:
                                                                        rdgroup.check(R.id.radioButtonNinguno);
                                                                        break;
                                                                    case 1:
                                                                        rdgroup.check(R.id.radioButtonPoco);
                                                                        break;
                                                                    case 2:
                                                                        rdgroup.check(R.id.radioButtonBastante);
                                                                        break;
                                                                    case 3:
                                                                        rdgroup.check(R.id.radioButtonMucho);
                                                                        break;
                                                                    case 4:
                                                                        rdgroup.check(R.id.radioButtonMuchisimo);
                                                                        break;
                                                                    default:
                                                                        rdgroup.clearCheck();
                                                                        break;
                                                                }
                                                                titulo_apartado.setText(getResources().getTextArray(R.array.Apartados)[currentQuestion[0]]);
                                                                instrucciones.setText(getResources().getTextArray(R.array.instrucciones)[currentQuestion[0]]);
                                                                txt_pregunta.setText(Question[currentQuestion[0]][currentQuestion[1]]);
                                                                id_pregunta.setText("Pregunta " + String.valueOf(currentQuestion[1] + 1) + "/" + Question[currentQuestion[0]].length);
                                                                if ((currentQuestion[1] + 1) == Question[currentQuestion[0]].length) {
                                                                    btn_siguiente.setVisibility(View.INVISIBLE);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    // Reestablezco los valores por defecto de la variable ptsDragAndDrop
                                                    for (int i = 0; i < ptsDragAndDrop.length; i++) {
                                                        for (int j = 0; j < ptsDragAndDrop.length; j++) {
                                                            ptsDragAndDrop[i][j] = -1;
                                                        }
                                                    }

                                                    return true;
                                            }
                                            return false;
                                        }
                                    }

            );

        }

    }