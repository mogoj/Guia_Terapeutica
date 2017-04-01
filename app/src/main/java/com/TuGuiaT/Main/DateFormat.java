package com.TuGuiaT.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Date nowDate = new Date();
        java.text.DateFormat formatoFechaDMY = new SimpleDateFormat("dd/MM/yyyy");

        String fechaFormateada = formatoFechaDMY.format(nowDate);
        System.out.println(" Fecha: " + fechaFormateada);
    }
}