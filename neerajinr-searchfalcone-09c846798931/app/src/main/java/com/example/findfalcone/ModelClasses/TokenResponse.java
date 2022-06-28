package com.example.findfalcone.ModelClasses;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {

    @SerializedName("token")
    String token;

    public String getToken() {
        return token;
    }
}
