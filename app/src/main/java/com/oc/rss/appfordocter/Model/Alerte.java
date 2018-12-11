package com.oc.rss.appfordocter.Model;

/**
 * Created by Ghassen on 05/05/2017.
 */

public class Alerte {

    private int AlerteId;

    private String Date;
    private String Contenu;

    private   int PatientId;
    private  int PersonneId;
    public int GetDossierId(){return PatientId;}
    public void SetDossierId(int id){PatientId=id;}
    public int GetMedecinId(){return PersonneId;}
    public void SetMedecinId(int id){PersonneId=id;}


    public int GetAlerteId(){return AlerteId;}
    public void SetAlerteId(int id){AlerteId=id;}



    public String GetDate(){return Date;}
    public void SetDate(String date){Date=date;}

    public String GetContenu(){return Contenu;}
    public void SetContenu(String contenu){Contenu=contenu;}



}
