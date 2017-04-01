package com.TuGuiaT.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.TuGuiaT.Main.TuGuiaT.R;


/**
 * Created by i42mogoj on 26/3/15.
 */
public class MenuGuiasActivity extends Activity implements OnClickListener {
    //creo variables internas para llamar a la funcion onclick
    private ImageButton btn_Guia1, btn_Guia2, btn_Guia3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visorguias_layout);

        btn_Guia1= (ImageButton) findViewById(R.id.btn_Guia1);
        btn_Guia1.setOnClickListener(this);
        btn_Guia2= (ImageButton) findViewById(R.id.btn_Guia2);
        btn_Guia2.setOnClickListener(this);
        btn_Guia3=(ImageButton) findViewById(R.id.btn_Guia3);
        btn_Guia3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_Guia1:
                intent = new Intent(MenuGuiasActivity.this,ViewerPDFActivity.class);
                intent.putExtra("filename", "Guia1.pdf");

                break;
            case R.id.btn_Guia2:
                intent = new Intent(MenuGuiasActivity.this,ViewerPDFActivity.class);
                intent.putExtra("filename", "Guia2.pdf");
                break;
            case R.id.btn_Guia3:
                intent= new Intent(MenuGuiasActivity.this,ViewerPDFActivity.class);

                intent.putExtra("filename", "Guia3.pdf");
                break;
        }
        if(intent != null){
            startActivity(intent);
            finish();
        }
    }
}


