package com.edu.pe.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexionAPI {
    private static Retrofit retrofit = null;

    private static String RUTA_API = "http://10.0.2.2:9090/api/";
    public static Retrofit getConnection(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(RUTA_API).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
