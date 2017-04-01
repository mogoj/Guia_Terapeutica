package com.TuGuiaT.RegistersDatabase;

        import android.app.Activity;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.TuGuiaT.Main.TuGuiaT.R;
        import com.TuGuiaT.Questionnaire.TitularClass;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        

public class VieweRegisterBBDDActivity extends Activity{

    private TextView lblEtiqueta;
    private ListView lstOpciones;
    private SQLiteDatabase db;
    public BBDDActivity usdbh;

    private TitularClass[] datos = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistaregistros_layout);

        lblEtiqueta = (TextView)findViewById(R.id.LblEtiqueta);
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        usdbh = new BBDDActivity(this);
        db = usdbh.getReadableDatabase();

        if(db != null){
            Log.d("Menu Entrada", "db != null " );
        }

        Cursor c = db.rawQuery("SELECT id_USER,dolor,peso,altura,imc,fecha FROM DatosUsuario ORDER BY fecha ;", null);

        Log.d("Menu Entrada", "cursor.size() = " + c.getCount());
        Log.d("Menu Entrada", "cursor.position() = " + c.getPosition());
        Log.d("Menu Entrada", "cursor.position() = " + c.getPosition());

        datos= new TitularClass[c.getCount()];
        ContactClass registros[]= new ContactClass[c.getCount()];

        if (c.moveToFirst()) {
            Log.d("Menu Entrada", "cursor1.size() = " + c.getCount());
            Log.d("Menu Entrada", "cursor1.position() = " + c.getPosition());
            Log.d("Menu Entrada", "cursor1.position() = " + c.getPosition());

            //Recorremos el cursor hasta que no haya mas registros
            do {
                //Crear formato fecha
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = null;
                try {
                    Log.d("Fecha Formateada","fecha" + fecha);

                    fecha = formatter.parse(c.getString(5));


                   // String formattedDateString = formatter.format(c.getString(5));
                    //Log.d("Fecha Formateada","fechaFormateada" + formattedDateString);


                    registros[c.getPosition()] = new ContactClass(c.getInt(0), c.getFloat(1), c.getFloat(2), c.getFloat(3),c.getFloat(4), fecha.toString());
                    datos[c.getPosition()] = new TitularClass("Nivel de Dolor:"+ registros[c.getPosition()].getDolor()+"--"+registros[c.getPosition()].getfecha(),"Peso:" + String.valueOf(registros[c.getPosition()].getpeso() +" Altura:" + registros[c.getPosition()].getaltura()+" IMC:"+registros[c.getPosition()].getIMC()));

                    Log.d("Menu Entrada", "datos[" + c.getPosition() + "]: " + datos[c.getPosition()].getTitulo() + " - " + datos[c.getPosition()].getSubtitulo());
                } catch (ParseException pe) {
                    fecha = null;
                    datos = null;
                }
            } while (c.moveToNext());

            //Cerrar por este orden, si no peta
            c.close();
            db.close();

        }
        if (datos != null) {
            Log.d("Menu Entrada", "datos.length() = " + datos.length);
        }else{
            Log.d("Menu Entrada", "datos = " + datos);
        }


        //Adaptador
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this, datos);
        lstOpciones.setAdapter(adaptador);

        //Eventos
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                String opcionSeleccionada =
                        ((TitularClass)a.getItemAtPosition(position)).getTitulo();



                lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    class AdaptadorTitulares extends ArrayAdapter<TitularClass> {

        public AdaptadorTitulares(Context context, TitularClass[] datos) {
            super(context, R.layout.listitem_titular, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_titular, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(datos[position].getTitulo());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(datos[position].getSubtitulo());

            return(item);
        }
    }
}
