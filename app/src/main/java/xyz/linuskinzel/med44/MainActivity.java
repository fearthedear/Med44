package xyz.linuskinzel.med44;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //check if name is set in sharedpreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String name = prefs.getString("name", "no shared preference stored");

        if (name == "no shared preference stored") {
            Intent goToSetup = new Intent(this, setupActivity.class);
            startActivity(goToSetup);
        }
        else {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick_UserProfile (View v) {
        Intent anIntent = new Intent(this, UserProfile.class);
        startActivity(anIntent);
    }

    public void onClick_medicalHistory (View v) {
        Intent anIntent = new Intent(this, MedicalHistory.class);
        startActivity(anIntent);
    }

    public void onClick_prescriptions (View v) {
        Intent anIntent = new Intent(this, Prescriptions.class);
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
