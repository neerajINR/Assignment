package com.example.findfalcone.Fragments;

import static com.example.findfalcone.R.color.black;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.findfalcone.Activities.Planets;
import com.example.findfalcone.R;

import java.util.ArrayList;

public class VehiclesFragment extends DialogFragment {

    Context context;
    Button mFindBtn;
    ImageButton mBackBtnImg;
    public VehiclesDialogFragmentListener listener;
    RadioButton mRadioButton, mRadioBtnOne, mRadioBtnTwo, mRadioBtnThree, mRadioBtnFour;
    RadioGroup mRadioGroup;
    String mPlanetName;
    ArrayList<String> mPlanetArrayList;
    ArrayList<String> mVehicleArrayList;
    boolean spacePodFlag, spaceShipFlag;
    String mVehicleName;
    int textColor;

    public interface VehiclesDialogFragmentListener {
        void onGetData(int timeTaken, String str1, String str2, String mVehicleName);
    }


    public VehiclesFragment(String mString, ArrayList<String> mPArraylist, ArrayList<String> mVArraylist)
    {
        mPlanetName = mString;
        mPlanetArrayList = mPArraylist;
        mVehicleArrayList = mVArraylist;
        textColor = Color.parseColor("#B86566");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        try {
            listener = (VehiclesDialogFragmentListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement VehiclesDialogFragmentListener");
        }

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_vehicles, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCanceledOnTouchOutside(false);
            listener = (Planets)getActivity();

        }

        mRadioGroup=(RadioGroup)v.findViewById(R.id.radio_group);
        mRadioBtnOne = (RadioButton)v.findViewById(R.id.space_pod_radio_btn);
        mRadioBtnTwo = (RadioButton)v.findViewById(R.id.space_rocket_radio_btn);
        mRadioBtnThree = (RadioButton)v.findViewById(R.id.space_shuttle_radio_btn);
        mRadioBtnFour = (RadioButton)v.findViewById(R.id.space_ship_radio_btn);
        mBackBtnImg = (ImageButton) v.findViewById(R.id.back_btn_img);
        mFindBtn = (Button) v.findViewById(R.id.find_btn);
        RelativeLayout mYesBtn = (RelativeLayout) v.findViewById(R.id.yes_btn);





        for(int i=0; i<mVehicleArrayList.size(); i++) {
            if (mVehicleArrayList.get(i).contains("Space pod")) {
                if(!spacePodFlag) {
                    mRadioBtnOne.setClickable(true);
                    mRadioBtnOne.setText("SPACE POD (1/2)");
                    spacePodFlag = true;
                }
                else
                {
                    mRadioBtnOne.setEnabled(false);
                    mRadioBtnOne.setClickable(false);
                    mRadioBtnOne.setText("SPACE POD (0/2)");
                    spacePodFlag = true;
                    mRadioBtnOne.setTextColor(textColor);
                    mRadioBtnOne.setButtonTintList(ColorStateList.valueOf(textColor));

                }
            }
            else if (mVehicleArrayList.get(i).contains("Space rocket")) {
                mRadioBtnTwo.setEnabled(false);
                mRadioBtnTwo.setClickable(false);
                mRadioBtnTwo.setText("SPACE ROCKET (0/1)");
                mRadioBtnTwo.setTextColor(textColor);
                mRadioBtnTwo.setButtonTintList(ColorStateList.valueOf(textColor));

            }
            else if (mVehicleArrayList.get(i).contains("Space shuttle")) {
                mRadioBtnThree.setEnabled(false);
                mRadioBtnThree.setClickable(false);
                mRadioBtnThree.setText("SPACE SHUTTLE (0/1) ");
                mRadioBtnThree.setTextColor(textColor);
                mRadioBtnThree.setButtonTintList(ColorStateList.valueOf(textColor));

            }
            else if (mVehicleArrayList.get(i).contains("Space ship")) {
                 if(!spaceShipFlag) {
                    mRadioBtnFour.setClickable(true);
                    mRadioBtnFour.setText("SPACE SHIP (1/2)");
                    spaceShipFlag = true;
                }
                else
                {
                    mRadioBtnFour.setEnabled(false);
                    mRadioBtnFour.setClickable(false);
                    mRadioBtnFour.setText("SPACE SHIP (0/2)");
                    spaceShipFlag = true;
                    mRadioBtnFour.setTextColor(textColor);
                    mRadioBtnFour.setButtonTintList(ColorStateList.valueOf(textColor));

                }
            }

        }


        if(mPlanetName.equals("Jebing"))
        {
            mRadioBtnOne.setEnabled(false);
            mRadioBtnOne.setClickable(false);
            mRadioBtnOne.setTextColor(textColor);
            mRadioBtnOne.setButtonTintList(ColorStateList.valueOf(textColor));
        }
        else if(mPlanetName.equals("Sapir"))
        {
            mRadioBtnOne.setEnabled(false);
            mRadioBtnTwo.setEnabled(false);
            mRadioBtnOne.setClickable(false);
            mRadioBtnTwo.setClickable(false);
            mRadioBtnOne.setTextColor(textColor);
            mRadioBtnOne.setButtonTintList(ColorStateList.valueOf(textColor));
            mRadioBtnTwo.setTextColor(textColor);
            mRadioBtnTwo.setButtonTintList(ColorStateList.valueOf(textColor));
        }
        else if(mPlanetName.equals("Lerbin"))
        {
            mRadioBtnOne.setEnabled(false);
            mRadioBtnTwo.setEnabled(false);
            mRadioBtnThree.setEnabled(false);
            mRadioBtnOne.setClickable(false);
            mRadioBtnTwo.setClickable(false);
            mRadioBtnThree.setClickable(false);
            mRadioBtnOne.setTextColor(textColor);
            mRadioBtnOne.setButtonTintList(ColorStateList.valueOf(textColor));
            mRadioBtnTwo.setTextColor(textColor);
            mRadioBtnTwo.setButtonTintList(ColorStateList.valueOf(textColor));
            mRadioBtnThree.setTextColor(textColor);
            mRadioBtnThree.setButtonTintList(ColorStateList.valueOf(textColor));
        }
        else if(mPlanetName.equals("Pingasor"))
        {
            mRadioBtnOne.setEnabled(false);
            mRadioBtnTwo.setEnabled(false);
            mRadioBtnThree.setEnabled(false);
            mRadioBtnOne.setClickable(false);
            mRadioBtnTwo.setClickable(false);
            mRadioBtnThree.setClickable(false);
            mRadioBtnOne.setTextColor(textColor);
            mRadioBtnOne.setButtonTintList(ColorStateList.valueOf(textColor));
            mRadioBtnTwo.setTextColor(textColor);
            mRadioBtnTwo.setButtonTintList(ColorStateList.valueOf(textColor));
            mRadioBtnThree.setTextColor(textColor);
            mRadioBtnThree.setButtonTintList(ColorStateList.valueOf(textColor));
        }

        mBackBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // listener.onGetData(156, "UNSELECTED", mPlanetName);
                getDialog().dismiss();
            }
        });


        mFindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = mRadioGroup.getCheckedRadioButtonId();
                mRadioButton = (RadioButton) v.findViewById(selectedId);
                if(selectedId==-1){
                    mVehicleName = "";
                    Toast.makeText(getActivity(), "Nothing selected", Toast.LENGTH_SHORT).show();
                    listener.onGetData(156, "UNSELECTED", mPlanetName, mVehicleName);
                }
                else{
                    if(mRadioButton.getText().toString().contains("SPACE POD"))
                        mVehicleName = "Space pod";
                    if(mRadioButton.getText().toString().contains("SPACE ROCKET"))
                        mVehicleName = "Space rocket";
                    if(mRadioButton.getText().toString().contains("SPACE SHUTTLE"))
                        mVehicleName = "Space shuttle";
                    if(mRadioButton.getText().toString().contains("SPACE SHIP"))
                        mVehicleName = "Space ship";

                    Log.d("VechileName", mVehicleName);
                   // Toast.makeText(getActivity(), mRadioButton.getText(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(getActivity(), ""+selectedId, Toast.LENGTH_SHORT).show();
                   //  Log.d("name"+mRadioButton.getText(), ""+selectedId);
                    listener.onGetData(156, "SELECTED", mPlanetName, mVehicleName);
                }


                getDialog().dismiss();
               // getActivity().onBackPressed();



            }
        });
        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        window.setLayout((int) (width * 0.90), (int) (height * 0.90));
        window.setGravity(Gravity.CENTER);
        getDialog().getWindow().setDimAmount(0.9f);



    }




}

