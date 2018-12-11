package com.oc.rss.appfordocter.Model;

import java.io.Serializable;

/**
 * Created by Ghassen on 03/05/2017.
 */

public class Patient implements Serializable {

    public int PersonneId ;

    public String Nom;
    public String Prenom ;
    public String DNaissance ;
    public String Adresse ;
    public String Sexe ;
    public String Statut ;
    public String Email ;
    public String Tel ;

    public String Photo ;

    public int GetPersonneId () {return PersonneId;}
    public String GetNom() {return Nom;}
    public String GetPrenom() {return Prenom;}
    public String GetDate() {return DNaissance;}



}
