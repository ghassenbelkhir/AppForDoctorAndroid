package com.oc.rss.appfordocter.WebService;

/**
 * Created by Ghassen on 01/05/2017.
 */

public class RestService {

    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    private static final String URL = "http://localhost/";
    private retrofit.RestAdapter restAdapter;
    private Services apiService;

    public RestService()
    {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(Services.class);
    }

    public Services getService()
    {
        return apiService;
    }
}
