package com.example.findfalcone.ModelClasses;

public class SearchBody {

    String token;
    String planet_names[];
    String vehicle_names[];

    public SearchBody(String token, String[] planet_names, String[] vehicle_names) {
        this.token = token;
        this.planet_names = planet_names;
        this.vehicle_names = vehicle_names;
    }
}
