package com.example.findfalcone;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMachine {
    public static final String Base_URL="https://findfalcone.herokuapp.com";

    public static Retrofit retrofit=null;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
