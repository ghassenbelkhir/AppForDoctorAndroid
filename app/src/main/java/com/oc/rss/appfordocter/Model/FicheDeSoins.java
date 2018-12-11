package com.oc.rss.appfordocter.Model;

import java.io.Serializable;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class FicheDeSoins implements Serializable {

    private  int HospitalisationId ;

    public String Motif ;
    public String HistoireDeLaMaladie;
    public String RevueDeSysteme ;
    public String ExamenCardiovasculaire ;
    public String ExamenTeteEtCou ;
    public String ExamenMembresInferieurs ;
    public String AutresExamen ;
    public String Decision ;

    // Examen physique
    public float Temperature ;
    public int FrequenceCardiaque ;
    public int TensionArterielle ;
    public int FrequenceRespiratoire ;

    //Exmaen examenPleuroPulomnaire

    public String Inspection ;
    public String Palpation ;
    public String Percussion ;
    public String Auscultation ;

    public int GetHospitalisationId (){return HospitalisationId;}
    public void SetHospitalisationId(int id) {HospitalisationId=id;}

    public String GetMotif() {return Motif;}
    public void SetMotif(String motif) {Motif=motif;}

    public String GetHistoireDeLaMaladie() {return HistoireDeLaMaladie;}
    public void SetHistoireDeLaMaladie(String HDM) {HistoireDeLaMaladie=HDM;}

    public String GetRevueDeSysteme(){return RevueDeSysteme;}
    public void SetRevueDeSysteme(String Rv){RevueDeSysteme=Rv;}

    public String GetExamenCardiovasculaire() {return ExamenCardiovasculaire;}
    public void  SetExamenCardiovasculaire(String ECV){ExamenCardiovasculaire=ECV;}

    public String GetExamenTeteEtCou() {return ExamenTeteEtCou;}
    public void SetExamenTeteEtCou(String ETC) { ExamenTeteEtCou=ETC;}

    public String GetExamenMembreInferieur(){return ExamenMembresInferieurs;}
    public void SetExamenMembreInferieur(String EMI){ExamenMembresInferieurs=EMI;}

    public String GetAutresExamen(){return AutresExamen;}
    public void SetAutresExamen(String AE){ AutresExamen=AE;}

    public String GetDecision(){return Decision;}
    public void SetDecision(String AE){ Decision=AE;}

    public String GetInspection(){return Inspection;}
    public void SetInspection(String ins){Inspection=ins;}

    public String GetPalpation(){return Palpation;}
    public void SetPalpation(String ins){Palpation=ins;}

    public String GetPercussion(){return Percussion;}
    public void SetPercussion(String ins){Percussion=ins;}

    public String GetAuscultation(){return Auscultation;}
    public void SetAuscultation(String ins){Auscultation=ins;}

    public int GetTensionArterielle(){return TensionArterielle;}
    public void SetTensionArterielle(int ins){TensionArterielle=ins;}

    public int GetFrequenceCardiaque(){return FrequenceCardiaque;}
    public void SetFrequenceCardiaque(int ins){FrequenceCardiaque=ins;}

    public int GetFrequenceRespiratoire(){return FrequenceRespiratoire;}
    public void SetFrequenceRespiratoire(int ins){FrequenceRespiratoire=ins;}

    public float GetTemperature(){return Temperature;}
    public void SetTemperature(float ins){Temperature=ins;}
}
