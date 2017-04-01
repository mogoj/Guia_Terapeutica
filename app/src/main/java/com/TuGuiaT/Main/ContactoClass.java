package com.TuGuiaT.Main;

import java.util.Date;


/**
 * Created by i42mogoj on 16/3/15.
 */
public class ContactoClass {

    private int id;
    private float dolor;
    private float altura;
    private float peso;
    private float imc;
    private Date fecha;

    // Constructor de un objeto ContactoClass
    public ContactoClass(int id, float dolor, float altura, float peso, float imc, Date fecha) {
        this.id = id;
        this.dolor = dolor;
        this.altura = altura;
        this.peso = peso;
        this.fecha = fecha;
        this.imc= imc;
    }

    // Recuperar/establecer ID
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    // Recuperar/establecer NOMBRE
    public float getDolor() {
        return dolor;
    }
    public void setNOMBRE(String nombre) {
        this.dolor = dolor;
    }

    //Recuperar/establecer altura
    public float getaltura() {
        return altura;
    }
    public void setaltura(float altura) {
        this.altura = altura;
    }

    //Recuperar/establecer peso
    public float getpeso() {
        return peso;
    }
    public void setpeso(float peso) {
        this.peso = peso;
    }
    //Recuperar/establecer fecha

    public Date getfecha() {
        return fecha;
    }
    public void setfecha(Date fecha) {this.fecha = fecha;}

    public float getIMC() {
        return imc;
    }
    public void setIMC(float imc) {this.imc = imc;}


}