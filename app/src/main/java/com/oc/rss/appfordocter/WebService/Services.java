package com.oc.rss.appfordocter.WebService;

import android.media.Image;

import com.oc.rss.appfordocter.Model.Alerte;
import com.oc.rss.appfordocter.Model.Antecedents;
import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.Model.DossierMedical;
import com.oc.rss.appfordocter.Model.Examen;
import com.oc.rss.appfordocter.Model.ExamenImage;
import com.oc.rss.appfordocter.Model.FicheDeSoins;
import com.oc.rss.appfordocter.Model.Hospitalisation;
import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.Model.Medecin;
import com.oc.rss.appfordocter.Model.Medicament;
import com.oc.rss.appfordocter.Model.Patient;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Ghassen on 01/05/2017.
 */

public interface Services {

    //Retrofit turns our institute WEB API into a Java interface.
    //So these are the list available in our WEB API and the methods look straight forward

    @GET("/Immunisations")
    public void getImmunisations(Callback<List<Immunisation>> callback);

    //Get immunisation record base on ID
    @GET("/Immunisations/{id}")
    public void getStudentById(@Path("id") Integer id, Callback<Immunisation> callback);


    /********************************************************/
    //Get immunisation par patient
    @GET("/Dossier/{id}/Immunisations")
    public void getImmunisationsById(@Path("id") Integer id,Callback <List<Immunisation>> callback);

    /**********************************************************************/




    //Delete immunisation record base on ID
    @DELETE("/Immunisations/{id}")
    public void deleteStudentById(@Path("id") Integer id,Callback<Immunisation> callback);

    //PUT immunisation record and post content in HTTP request BODY
    @PUT("/Immunisations/{id}")
    public void updateStudentById(@Path("id") Integer id,@Body Immunisation immunisation,Callback<Immunisation> callback);

    //Add immunisation record and post content in HTTP request BODY
    @POST("/Immunisations")
    public void addStudent(@Body Immunisation immunisation, Callback<Immunisation> callback);
    /********************************/

    @GET("/api/Patients")
    public void getPatient(Callback<List<Patient>> callback);

    //Get immunisation record base on ID
    @GET("/api/Patients/{id}")
    public void getPatientById(@Path("id") Integer id, Callback<Patient> callback);

    /*************************************************/

    @GET("/Medicaments")
    public void getMedicaments(Callback<List<Medicament>> callback);

    //Get immunisation record base on ID
    @GET("/Medicaments/{id}")
    public void getMedicamentsById(@Path("id") Integer id, Callback<Medicament> callback);

    @POST("/api/Medicaments")
    public void addMedicamment(@Body Medicament medicament, Callback<Medicament> callback);

    //Get immunisation par patient
    @GET("/Dossier/{id}/Medicaments")
    public void getMedicamentsByIdDossier(@Path("id") Integer id,Callback <List<Medicament>> callback);

    /*************************************************************/

    @GET("/Consultations")
    public void getConsultation(Callback<List<Consultation>> callback);

    //Get immunisation record base on ID
    @GET("/Consultations/{id}")
    public void getConsultationById(@Path("id") Integer id, Callback<Consultation> callback);

    @POST("/api/Consultations")
    public void addConsultation(@Body Consultation consultation, Callback<Consultation> callback);

    @GET("/Dossier/{id}/Consultations")
    public void getConsultationsByIdDossier(@Path("id") Integer id,Callback <List<Consultation>> callback);


    /*************************************************************/

    @GET("/Antecedents")
    public void getAntecedent(Callback<List<Antecedents>> callback);

    //Get immunisation record base on ID
    @GET("/Antecedents/{id}")
    public void getAntecedentById(@Path("id") Integer id, Callback<Antecedents> callback);

    @POST("/Antecedents")
    public void addAntecedents(@Body Antecedents antecedents, Callback<Antecedents> callback);

    @GET("/Dossier/{id}/Antecedents")
    public void getAntecedentsByIdDossier(@Path("id") Integer id,Callback <List<Antecedents>> callback);

    /***********************************************************************/

    @GET("/api/Alertes")
    public void getAlertes(Callback<List<Alerte>> callback);

    //Get immunisation record base on ID
    @GET("/api/Alertes/{id}")
    public void getAlertesById(@Path("id") Integer id, Callback<Alerte> callback);

    @POST("/api/Alertes")
    public void addAlertes(@Body Alerte alerte, Callback<Alerte> callback);

    @DELETE("/api/Alertes/{id}")
    public void deleteAlertesById(@Path("id") Integer id,Callback<Alerte> callback);

    @GET("/Dossier/{id}/Alertes")
    public void getAlertesByIdDossier(@Path("id") Integer id,Callback <List<Alerte>> callback);
    /******************************************************************************/

    @GET("/Examen")
    public void getExamens(Callback<List<Examen>> callback);

    //Get immunisation record base on ID
    @GET("/Examen/{id}")
    public void getExamensById(@Path("id") Integer id, Callback<Examen> callback);

    @GET("/Dossier/{id}/Examens")
    public void getExamensByIdDossier(@Path("id") Integer id,Callback <List<Examen>> callback);
/******************************************************************************************/
    @GET("/ExamenImages")
    public void getExamenImage(Callback<List<ExamenImage>> callback);

    //Get immunisation record base on ID
    @GET("/ExamenImages/{id}")
    public void getExamenImageById(@Path("id") Integer id, Callback<ExamenImage> callback);

    @GET("/Images/{id}/ExamensImages")
    public void getExamensImagesByIdDossier(@Path("id") Integer id,Callback <List<ExamenImage>> callback);

    /****************************************************************************************/

    @GET("/api/Hospitalisations")
    public void getHospitalisations(Callback<List<Hospitalisation>> callback);

    //Get immunisation record base on ID
    @GET("/api/Hospitalisations/{id}")
    public void getHospitalisationsById(@Path("id") Integer id, Callback<Hospitalisation> callback);

    @GET("/Dossier/{id}/Hospitalisations")
    public void getHospitalisationsByIdDossier(@Path("id") Integer id,Callback <List<Hospitalisation>> callback);

    @POST("/api/Hospitalisations")
    public void addHospitalisation(@Body Hospitalisation hospitalisation, Callback<Hospitalisation> callback);

    /**********************************************************************************/
    //Get immunisation record base on ID
    @GET("/DossierMedicals/{id}")
    public void getDossierMedicalById(@Path("id") Integer id, Callback<DossierMedical> callback);

    /////////////////************************************************************/

    //Get immunisation record base on ID
    @GET("/Dossier/{id}/{mot}/medecin")
    public void getDossierMedecinByMot(@Path("id") Integer id,@Path("mot") String mot ,Callback<List<Medecin>> callback);

    @GET("/medecin/{id}/{mot}/medecin")
    public void getMedecinBypsswd(@Path("id") Integer id,@Path("mot") String mot ,  Callback<Medecin> callback);

    @GET("/api/Medecins/{id}")
    public void getMedecinById(@Path("id") Integer id, Callback<Medecin> callback);

    @PUT("/api/Medecins/{id}")
    public void updateMedecinsById(@Path("id") Integer id,@Body Medecin medecin,Callback<Medecin> callback);
/**************************************************************************************************/

@GET("/FicheDeSoins")
public void getFicheDeSoins(Callback<List<FicheDeSoins>> callback);

    //Get immunisation record base on ID
    @GET("/api/FicheDeSoins/{id}")
    public void getFicheDeSoinsById(@Path("id") Integer id, Callback<FicheDeSoins> callback);

    @GET("/Dossier/{id}/Examens")
    public void getFicheDeSoinsExamensByIdDossier(@Path("id") Integer id,Callback <List<FicheDeSoins>> callback);

    //Add immunisation record and post content in HTTP request BODY
    @POST("/api/FicheDeSoins")
    public void addFicheDeSoins(@Body FicheDeSoins ficheDeSoins, Callback<FicheDeSoins> callback);

/********************************************/

@GET("/Nom/{id}/Patients")
public void getPatientByNom(@Path("id") String id, Callback<List<Patient>> callback);

}
