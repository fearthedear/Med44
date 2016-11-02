package xyz.linuskinzel.med44;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if name is set in sharedpreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String name = prefs.getString("name", "no shared preference stored");

        if (name.equals("no shared preference stored")) {
            Intent goToSetup = new Intent(this, setupActivity.class);
            startActivity(goToSetup);
        }
    }

    public void onClick_UserProfile (View v) {
        Intent anIntent = new Intent(this, UserProfile2.class);
        startActivity(anIntent);
    }

    public void onClick_medicalHistory (View v) {
        Intent anIntent = new Intent(this, MedicalHistory2.class);
        startActivity(anIntent);
    }

    public void onClick_prescriptions (View v) {
        Intent anIntent = new Intent(this, Prescriptions2.class);
        startActivity(anIntent);
    }

    public void onClick_vaccines (View v) {
        Intent anIntent = new Intent(this, Vaccines.class);
        startActivity(anIntent);
    }

    public void onClick_seeDoctor (View v) {
        Intent anIntent = new Intent(this, seeDoctor.class);
        startActivity(anIntent);
    }
}
