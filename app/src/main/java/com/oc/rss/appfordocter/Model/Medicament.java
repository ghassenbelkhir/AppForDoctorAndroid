package com.oc.rss.appfordocter.Model;

import java.util.Date;

/**
 * Created by Ghassen on 03/05/2017.
 */

public class Medicament {



    private int MedicamentId;
    private String Date ;
    private String NomMedicamment ;
    private String Ordonnance ;

    private   int PatientId;
    private  int PersonneId;


    public int GetDossierId(){return PatientId;}
    public void SetDossierId(int id){PatientId=id;}
    public int GetMedecinId(){return PersonneId;}
    public void SetMedecinId(int id){PersonneId=id;}

    public Medicament() {}

    public Medicament(int dossierId,String date,String NomMedicament,String Ordonnance)
    {
        this.PatientId=dossierId;
        Date=date;
        this.NomMedicamment=NomMedicament;
        this.Ordonnance=Ordonnance;

    }


    public int GetMedicamentId(){return MedicamentId;}
    public void SetMedicamentId(int id){MedicamentId=id;}

    public String GetDate(){return Date;}
    public void SetDate(String date){Date=date;}

    public  String GetNomMedicament(){return NomMedicamment;}
    public  void SetNomMedicament(String medicament){ NomMedicamment= medicament; }

    public String GetOrdonnance() {return Ordonnance;}
    public void SetOrdonnance(String Ordonnances) {Ordonnance=Ordonnances;}


}
