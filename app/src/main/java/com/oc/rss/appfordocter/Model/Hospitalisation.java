package com.oc.rss.appfordocter.Model;

/**
 * Created by Ghassen on 06/05/2017.
 */

public class Hospitalisation {

    private  int HospitalisationId ;
    private String Etablissement;
    private String DateEntree;
    private String DateSortie;
    private int FicheDeSoinsId;
    private int PatientId;
    private int PersonneId;

    public int GetHospitalisationId (){return HospitalisationId;}
    public void SetHospitalisationId(int id) {HospitalisationId=id;}

    public int GetFicheDeSoinsId(){return FicheDeSoinsId;}
    public void SetFicheDeSoinsId(int id){FicheDeSoinsId=id;}

    public int GetDossierId(){return PatientId;}
    public void SetDossierId(int id){PatientId=id;}

    public String GetDateEntree(){return DateEntree;}
    public void SetDateEntree(String date){DateEntree=date;}

    public String GetDateSortie(){return DateSortie;}
    public void SetDateSortie(String date){DateSortie=date;}

    public  String GetEtablissement(){return Etablissement;}
    public  void SetEtablissement(String etat){Etablissement= etat; }

    public int GetMedecinId(){return PersonneId;}
    public void SetMedecinId(int id){PersonneId=id;}

}
