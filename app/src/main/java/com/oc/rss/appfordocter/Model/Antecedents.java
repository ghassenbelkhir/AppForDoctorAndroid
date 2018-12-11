package com.oc.rss.appfordocter.Model;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class Antecedents {

    private int AntecedentId;
    private int DossierId;
    private String Diagnostic ;
    private String Date;
    private String Etat;

    public int GetAntecedentId(){return AntecedentId;}
    public void SetAntecedentId(int id){AntecedentId=id;}

    public int GetDossierId(){return DossierId;}
    public void SetDossierId(int id){DossierId=id;}

    public String GetDate(){return Date;}
    public void SetDate(String date){Date=date;}

    public  String GetDiagnostic(){return Diagnostic;}
    public  void SetDiagnostic(String diagnostic){ Diagnostic= diagnostic; }

    public  String GetEtat(){return Etat;}
    public  void SetEtat(String etat){ Etat= etat; }
}
