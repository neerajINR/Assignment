package com.example.findfalcone.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.findfalcone.R;

public class FinalOutput extends AppCompatActivity {

    TextView mMessageOneTextView, mMessageTwoTextView, mTimeTakenTextView, mPlanetNameTextView;
    Button mStartAgainBtn;
    String mMessageOne, mMessageTwo, mTimeTaken, mPlanetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_final_output);
        mMessageOne = getIntent().getStringExtra("MessageOne");
        mMessageTwo = getIntent().getStringExtra("MessageTwo");
        mTimeTaken = getIntent().getStringExtra("TimeTaken");
        mPlanetName = getIntent().getStringExtra("PlanetName");

        mMessageOneTextView = (TextView) findViewById(R.id.message_one_text_view);
        mMessageTwoTextView = (TextView) findViewById(R.id.message_two_text_view);
        mTimeTakenTextView = (TextView) findViewById(R.id.time_taken_text_view);
        mPlanetNameTextView = (TextView) findViewById(R.id.planet_name_text_view);
        mStartAgainBtn = (Button) findViewById(R.id.start_again_btn);

        mMessageOneTextView.setText(mMessageOne);
        mMessageTwoTextView.setText(mMessageTwo);
        mTimeTakenTextView.setText(mTimeTaken);
        mPlanetNameTextView.setText(mPlanetName);

        mStartAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinalOutput.this, Planets.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FinalOutput.this, Planets.class));
        finish();
    }
}