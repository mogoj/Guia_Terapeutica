

//package com.androidplot.demos;
package com.TuGuiaT.Main.TuGuiaT;
        import android.app.Activity;

        import android.os.Bundle;
        import android.view.WindowManager;
        import com.androidplot.xy.SimpleXYSeries;
        import com.androidplot.xy.XYSeries;
        import com.androidplot.xy.*;

        import java.util.Arrays;

/**
 * A straightforward example of using AndroidPlot to plot some data.
 */
public class SimpleXYPlotActivity extends Activity
{

    private XYPlot plot;
   // public BBDDActivity usdbh;
   //private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // fun little snippet that prevents users from taking screenshots
        // on ICS+ devices :-)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.simple_xy_plot_example);

        // initialize our XYPlot reference:

        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        //Recuperar datos de bbdd
/*
        usdbh = new BBDDActivity(this);
        db = usdbh.getReadableDatabase();

        if(db != null){
            Log.d("Menu Entrada", "db != null ");
        }

        Cursor c = db.rawQuery("SELECT id_USER,peso,imc,fecha FROM DatosUsuario ORDER BY fecha ;", null);

*/
        // Create a couple arrays of y-values to plot:
        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
        Number[] series2Numbers = {4, 6, 3, 8, 2, 10};
       /* if (c.moveToFirst()) {

            Log.d("Menu Entrada", "cursor1.size() = " + c.getCount());
            Log.d("Menu Entrada", "cursor1.position() = " + c.getPosition());
            Log.d("Menu Entrada", "cursor1.position() = " + c.getPosition());
           do {

                    series1Numbers[c.getPosition()] = c.getFloat(1);
                    series2Numbers[c.getPosition()] = c.getFloat(2);


            } while (c.moveToNext());

            //Cerrar por este orden, si no peta
            c.close();
            db.close();
        }
*/
        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Series1");                             // Set the display title of the series

        // same as above
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, series2Format);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);

    }
}