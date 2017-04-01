package com.TuGuiaT.Main.TuGuiaT;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PruebaFecha {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Date nowDate = new Date();
        DateFormat formatoFechaDMY = new SimpleDateFormat("dd/MM/yyyy");

        String fechaFormateada = formatoFechaDMY.format(nowDate);
        System.out.println(" Fecha: " + fechaFormateada);
    }
}