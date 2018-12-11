package com.oc.rss.appfordocter.Model;

/**
 * Created by Ghassen on 05/05/2017.
 */

public class Examen {

    private int DossierId ;
    private int ExamenId;
    private String Date;
    private String Type;

    public int GetDossierId(){return DossierId;}
    public void SetDossierId(int id){DossierId=id;}

    public int GetExamenId(){return ExamenId;}
    public void SetExamenId(int id){ExamenId=id;}

    public String GetDate(){return Date;}
    public void SetDate(String date){Date=date;}

    public String GetType() {return Type;}
    public void SetType(String type) {Type=type;}
}
