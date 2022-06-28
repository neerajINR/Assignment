package com.example.findfalcone;

import com.example.findfalcone.ModelClasses.SearchBody;
import com.example.findfalcone.ModelClasses.SearchResponse;
import com.example.findfalcone.ModelClasses.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServerApiInterface {

    @POST("/token")
    Call<TokenResponse> getTokenIdResponse(@Header("Accept") String header);

    @POST("/find")
    Call<SearchResponse> getSearchResponse(@Header("Accept") String headerOne, @Header("Content-Type") String headerTwo, @Body SearchBody searchBody);



}
