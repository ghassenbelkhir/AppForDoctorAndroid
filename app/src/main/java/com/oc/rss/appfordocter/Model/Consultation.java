package com.oc.rss.appfordocter.Model;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class Consultation {



    private   int ConsultationId;

    private  String  Date;

    private  String Etablissement;
    private  String Raison ;

    private   int PatientId;
    private  int PersonneId;


    public int GetDossierId(){return PatientId;}
    public void SetDossierId(int id){PatientId=id;}
    public int GetMedecinId(){return PersonneId;}
    public void SetMedecinId(int id){PersonneId=id;}


    public int GetCondultationId(){return ConsultationId;}
    public void SetCondultationId(int id){ConsultationId=id;}



    public String GetDate(){return Date;}
    public void SetDate(String date){Date=date;}

    public  String GetRaison(){return Raison;}
    public  void SetRaison(String raison){ Raison= raison; }

    public  String GetEtabmissement(){return Etablissement;}
    public  void SetEtablissemnt(String etablissement){ Etablissement= etablissement; }

}
