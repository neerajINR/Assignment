package com.example.findfalcone.ModelClasses;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("planet_name")
    String planet_name;

    @SerializedName("status")
    String status;

    @SerializedName("error")
    String error;

    public String getPlanet_name() {
        return planet_name;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
