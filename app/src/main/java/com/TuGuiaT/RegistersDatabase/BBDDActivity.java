package com.TuGuiaT.RegistersDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by i42mogoj on 5/3/15.
 */
public class BBDDActivity extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 14;
    private static final String NOMBRE_BASEDATOS = "mibasedatos.db";
    private static final String sqlCreateIcm = "CREATE TABLE DatosUsuario (id_USER INTEGER UNSIGNED AUTO_INCREMENT, dolor FLOAT UNSIGNED NOT NULL, peso FLOAT UNSIGNED NOT NULL,altura FLOAT UNSIGNED NOT NULL,imc float UNSIGNED NOT NULL, fecha VARCHAR(20) NOT NULL);";

    private int count = 0;

    public BBDDActivity(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }


    public BBDDActivity(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {

        super(contexto, nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreateIcm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Borrar tablas

        db.execSQL("DROP TABLE IF EXISTS DatosUsuario");
        onCreate(db);

    }

    public void insertContact(float dolor, float peso,float altura,float imc, String fecha) {
        Log.d("BBDD", "insertContact(" + dolor + ", " + peso + ", " + altura + ", " + imc + "," + fecha + ")");
        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {

           // DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            //String.format(fecha, formato);
            Log.d("BBDD", "insertContact(" + dolor + ", " + peso + ", " + altura + ", " + imc + "," + fecha + ")");
            db.execSQL("INSERT INTO DatosUsuario (id_USER, dolor, peso,altura, imc, fecha) VALUES (" + (++this.count) + ", " + dolor + ","+ peso +","+ altura +", " + imc + ", '" + fecha + "')");
            db.close();


        }
    }
}
