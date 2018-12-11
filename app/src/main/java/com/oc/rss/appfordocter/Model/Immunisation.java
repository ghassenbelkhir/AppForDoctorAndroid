package com.oc.rss.appfordocter.Model;

import java.util.Date;

/**
 * Created by Ghassen on 01/05/2017.
 */

public class Immunisation {

    private int DossierId ;
    private String Type ;
    private String Date ;
    private int Nombre ;


    public Immunisation(){}
    public  Immunisation(int DossierId,String Type,String Date,int Nombre)
    {
        this.DossierId=DossierId;
        this.Type=Type;
        this.Date=Date;
        this.Nombre=Nombre;
    }

    public int GetDossierId(){return DossierId;}
    public void SetDossierId(int id){DossierId=id;}


    public String GetType(){return Type;}
    public void SetType(String type){Type=type;}

    public String GetDate(){return Date;}
    public void SetDate(String date){Date=date;}

    public int GetNombre(){return Nombre;}
    public void SetNombre(int nombre){Nombre=nombre;}
}
