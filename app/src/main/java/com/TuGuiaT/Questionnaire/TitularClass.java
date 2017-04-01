package com.TuGuiaT.Questionnaire;

/**
 * Created by i42mogoj on 11/3/15.
 */

public class TitularClass extends QuestionnaireActivity {
    private String titulo;
    private String subtitulo;

    public TitularClass(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }
}

