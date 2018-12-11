package com.oc.rss.appfordocter.Model;

/**
 * Created by Ghassen on 05/05/2017.
 */

public class ExamenImage {

    private int ExamenImageId;
    private int ExamenId;

    private String Image;

    public int GetExamenId(){return ExamenId;}
    public void SetExamenId(int id){ExamenId=id;}

    public int GetExamenImageId(){return ExamenImageId;}
    public void SetExamenImageId(int id){ExamenImageId=id;}

    public String GetImage(){return  Image;}
}
