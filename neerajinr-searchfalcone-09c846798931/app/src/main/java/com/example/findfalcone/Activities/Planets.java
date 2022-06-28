package com.example.findfalcone.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.findfalcone.ModelClasses.SearchBody;
import com.example.findfalcone.ModelClasses.SearchResponse;
import com.example.findfalcone.ServerApiInterface;
import com.example.findfalcone.ApiMachine;
import com.example.findfalcone.Fragments.VehiclesFragment;
import com.example.findfalcone.ModelClasses.TokenResponse;
import com.example.findfalcone.R;

import java.io.Console;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Planets extends AppCompatActivity implements VehiclesFragment.VehiclesDialogFragmentListener {

ImageView mDonlonImg, mEnchaiImg, mJebingImg, mSapirImg, mLerBinImg, mPingaSorImg;
Button mDoneBtn;
ProgressBar mProgressbar;
ArrayList planetArrayList;
ArrayList vehicleArrayList;
String planet_names[];
String vehicle_names[];
int mTimeTaken = 0;
CheckBox mDonlonCheckbox, mEnchaiCheckbox, mJebingCheckbox, mSapirCheckbox, mLerbinCheckbox, mPingasorCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_planets);
        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());

        planetArrayList = new ArrayList();
        vehicleArrayList = new ArrayList();

        mDoneBtn = (Button) findViewById(R.id.done_btn);
        mProgressbar = (ProgressBar) findViewById(R.id.planets_progressbar);

        mDonlonImg = (ImageView) findViewById(R.id.donlon_img);
        mEnchaiImg = (ImageView) findViewById(R.id.enchai_img);
        mJebingImg = (ImageView) findViewById(R.id.jebing_img);
        mSapirImg = (ImageView) findViewById(R.id.sapir_img);
        mLerBinImg = (ImageView) findViewById(R.id.lerbin_img);
        mPingaSorImg = (ImageView) findViewById(R.id.pingasor_img);

        mDonlonCheckbox = (CheckBox) findViewById(R.id.donlon_checkbox);
        mEnchaiCheckbox = (CheckBox) findViewById(R.id.enchai_checkbox);
        mJebingCheckbox = (CheckBox) findViewById(R.id.jebing_checkbox);
        mSapirCheckbox = (CheckBox) findViewById(R.id.sapir_checkbox);
        mLerbinCheckbox = (CheckBox) findViewById(R.id.lerbin_checkbox);
        mPingasorCheckbox = (CheckBox) findViewById(R.id.pingasor_checkbox);


        mDonlonImg.startAnimation(rotate);
        mEnchaiImg.startAnimation(rotate);
        mJebingImg.startAnimation(rotate);
        mSapirImg.startAnimation(rotate);
        mLerBinImg.startAnimation(rotate);
        mPingaSorImg.startAnimation(rotate);

        mDonlonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                planetArrayItem("Donlon");

            }
        });

        mEnchaiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                planetArrayItem("Enchai");

            }
        });

        mJebingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                planetArrayItem("Jebing");
            }
        });

        mSapirImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                planetArrayItem("Sapir");
            }
        });

        mLerBinImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                planetArrayItem("Lerbin");

            }
        });

        mPingaSorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                planetArrayItem("Pingasor");
            }
        });

        mDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(planetArrayList.size()==4) {
                    mProgressbar.setVisibility(View.VISIBLE);
                    callTokenApi();
                }
                else
                {
                    Toast.makeText(Planets.this, "SELECT 4 PLANETS",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void callTokenApi() {
        ServerApiInterface serverApiInterface = ApiMachine.getApiClient().create(ServerApiInterface.class);
        Call<TokenResponse> call = serverApiInterface.getTokenIdResponse("application/json");
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                TokenResponse tokenResponse = response.body();
               // Toast.makeText(Planets.this, "token"+tokenResponse.getToken(),Toast.LENGTH_SHORT).show();
                Log.d("Token", tokenResponse.getToken());
                callResultApi(tokenResponse.getToken());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                mProgressbar.setVisibility(View.GONE);
                Toast.makeText(Planets.this, "Network Issue"+t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());

            }
        });
    }

    public void callResultApi(String token) {
        planet_names = new String[4];
        vehicle_names = new String[4];
        planet_names[0] = planetArrayList.get(0).toString();
        planet_names[1] = planetArrayList.get(1).toString();
        planet_names[2] = planetArrayList.get(2).toString();
        planet_names[3] = planetArrayList.get(3).toString();

        vehicle_names[0] = vehicleArrayList.get(0).toString();
        vehicle_names[1] = vehicleArrayList.get(1).toString();
        vehicle_names[2] = vehicleArrayList.get(2).toString();
        vehicle_names[3] = vehicleArrayList.get(3).toString();


        for(int i=0; i<4; i++)
        {
           mTimeTaken =  calculateTime(mTimeTaken, planet_names[i], vehicle_names[i]);
        }

        ServerApiInterface serverApiInterface = ApiMachine.getApiClient().create(ServerApiInterface.class);
        SearchBody searchBody =new SearchBody(token, planet_names, vehicle_names);
        Call<SearchResponse> call = serverApiInterface.getSearchResponse("application/json", "application/json", searchBody);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                Log.d("Status", "name "+searchResponse.getPlanet_name()+"status"+searchResponse.getStatus() );
                if(searchResponse.getPlanet_name() != null) {
                    Intent mIntent = new Intent(Planets.this, FinalOutput.class);
                    mIntent.putExtra("MessageOne", "SUCCESS!");
                    mIntent.putExtra("MessageTwo", "CONGRATULATION ON FINDING FALCONE,  KING SHAN IS MIGHTY PLEASED" );
                    mIntent.putExtra("TimeTaken", "TIME TAKEN : "+mTimeTaken);
                    mIntent.putExtra("PlanetName", "PLANET NAME : "+searchResponse.getPlanet_name());
                    startActivity(mIntent);
                }
                else
                {
                    Intent mIntent = new Intent(Planets.this, FinalOutput.class);
                    mIntent.putExtra("MessageOne", "FAILURE!");
                    mIntent.putExtra("MessageTwo", "BETTER LUCK NEXT TIME" );
                    mIntent.putExtra("TimeTaken", "TIME TAKEN : "+mTimeTaken);
                    mIntent.putExtra("PlanetName", "PLANET NOT FOUND" );
                    startActivity(mIntent);
                }
                mProgressbar.setVisibility(View.GONE);
                finish();
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                mProgressbar.setVisibility(View.GONE);
                Toast.makeText(Planets.this, "Network Issue"+t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());

            }
        });
    }

    public void planetArrayItem(String mString)
    {

        if(planetArrayList.contains(mString))
        {
            if(vehicleArrayList.size()>0) {
                for (int i = 0; i < planetArrayList.size(); i++) {
                    if (planetArrayList.get(i).toString().contains(mString))
                        vehicleArrayList.remove(i);
                }
            }


            planetArrayList.remove(mString);
            Log.d("StringRemoved ", mString);
            if(mString.equals("Donlon"))
                mDonlonCheckbox.setChecked(false);
            if(mString.equals("Enchai"))
                mEnchaiCheckbox.setChecked(false);
            if(mString.equals("Jebing"))
                mJebingCheckbox.setChecked(false);
            if(mString.equals("Sapir"))
                mSapirCheckbox.setChecked(false);
            if(mString.equals("Lerbin"))
                mLerbinCheckbox.setChecked(false);
            if(mString.equals("Pingasor"))
                mPingasorCheckbox.setChecked(false);

        }
        else if(planetArrayList.size()<4){
            planetArrayList.add(mString);
            Log.d("StringAdded ", mString);
            if(vehicleArrayList.size() >= planetArrayList.size())
            {
                int x = vehicleArrayList.size() - planetArrayList.size();
                for(int i=0; i<x; i++ )
                {
                    vehicleArrayList.remove(vehicleArrayList.size()-1);
                }
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag("VehiclesFragment");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            DialogFragment dialogFragment = new VehiclesFragment(mString, planetArrayList, vehicleArrayList);
            dialogFragment.show(ft, "VehiclesFragment");

            CheckBoxSelection(mString, true);

        }
        else{
            Toast.makeText(Planets.this,"4 PLanets SELECTED ALREADY",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetData(int timeTaken, String str1, String str2, String mVehicleName) {
       // Toast.makeText(Planets.this, "string1"+str1, Toast.LENGTH_SHORT).show();
        if(str1.equals("SELECTED"))
        {
            CheckBoxSelection(str2, true);
            vehicleArrayList.add(mVehicleName);
        }
        if(str1.equals("UNSELECTED"))
        {

                CheckBoxSelection(str2, false);
                planetArrayItem(str2);
                vehicleArrayList.remove(mVehicleName);

        }
        Log.d("planetSize",""+planetArrayList.size());
        Log.d("vehicleSize",""+vehicleArrayList.size());

    }

    public void CheckBoxSelection(String mString, boolean mChecked)
    {
       if(mChecked)
       {
           if(mString.equals("Donlon"))
               mDonlonCheckbox.setChecked(true);
           if(mString.equals("Enchai"))
               mEnchaiCheckbox.setChecked(true);
           if(mString.equals("Jebing"))
               mJebingCheckbox.setChecked(true);
           if(mString.equals("Sapir"))
               mSapirCheckbox.setChecked(true);
           if(mString.equals("Lerbin"))
               mLerbinCheckbox.setChecked(true);
           if(mString.equals("Pingasor"))
               mPingasorCheckbox.setChecked(true);


       }
       else
       {
           if(mString.equals("Donlon"))
               mDonlonCheckbox.setChecked(false);
           if(mString.equals("Enchai"))
               mEnchaiCheckbox.setChecked(false);
           if(mString.equals("Jebing"))
               mJebingCheckbox.setChecked(false);
           if(mString.equals("Sapir"))
               mSapirCheckbox.setChecked(false);
           if(mString.equals("Lerbin"))
               mLerbinCheckbox.setChecked(false);
           if(mString.equals("Pingasor"))
               mPingasorCheckbox.setChecked(false);

       }
    }

    public int calculateTime(int totalTimeTaken, String planetName, String vehicleName)
    {
        int timeTaken = 0;

        if(planetName.contains("Donlon") && vehicleName.contains("Space pod"))
        {
            timeTaken = 50;
        }
        else if(planetName.contains("Donlon") && vehicleName.contains("Space rocket"))
        {
            timeTaken = 25;
        }
        else if(planetName.contains("Donlon") && vehicleName.contains("Space shuttle"))
        {
            timeTaken = 20;
        }
        else if(planetName.contains("Donlon") && vehicleName.contains("Space ship"))
        {
            timeTaken = 10;
        }

        else if(planetName.contains("Enchai") && vehicleName.contains("Space pod"))
        {
            timeTaken = 100;
        }
        else if(planetName.contains("Enchai") && vehicleName.contains("Space rocket"))
        {
            timeTaken = 50;
        }
        else if(planetName.contains("Enchai") && vehicleName.contains("Space shuttle"))
        {
            timeTaken = 40;
        }
        else if(planetName.contains("Enchai") && vehicleName.contains("Space ship"))
        {
            timeTaken = 20;
        }


        else if(planetName.contains("Jebing") && vehicleName.contains("Space rocket"))
        {
            timeTaken = 75;
        }
        else if(planetName.contains("Jebing") && vehicleName.contains("Space shuttle"))
        {
            timeTaken = 60;
        }
        else if(planetName.contains("Jebing") && vehicleName.contains("Space ship"))
        {
            timeTaken = 30;
        }


        else if(planetName.contains("Sapir") && vehicleName.contains("Space shuttle"))
        {
            timeTaken = 80;
        }
        else if(planetName.contains("Sapir") && vehicleName.contains("Space ship"))
        {
            timeTaken = 40;
        }

        else if(planetName.contains("Lerbin") && vehicleName.contains("Space ship"))
        {
            timeTaken = 50;
        }

        else if(planetName.contains("Pingasor") && vehicleName.contains("Space ship"))
        {
            timeTaken = 60;
        }

        totalTimeTaken = timeTaken + totalTimeTaken;

        return totalTimeTaken;
    }
}