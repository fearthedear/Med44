package xyz.linuskinzel.med44;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class UserProfile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String name = prefs.getString("name", "no shared preference stored");
        int age = prefs.getInt("age", 0);
        float weight = prefs.getFloat("weight", 0);
        float height = prefs.getFloat("height", 0);
        String gender = prefs.getString("gender", "gender not known");
        String blood = prefs.getString("bloodtype", "blood type not known");

        TextView nameTextView = (TextView)findViewById(R.id.nameTextView);
        TextView ageTextView = (TextView)findViewById(R.id.ageTextView);
        TextView weightTextView = (TextView)findViewById(R.id.weightTextView);
        TextView heightTextView = (TextView)findViewById(R.id.heightTextView);


        nameTextView.setText(name);
        ageTextView.setText(Integer.toString(age));
        weightTextView.setText(String.valueOf(weight));
        heightTextView.setText(String.valueOf(height));

        RadioGroup genderRadio = (RadioGroup)findViewById(R.id.genderRadio);
        if (gender == "male") {
            genderRadio.check(R.id.radioButtonMale);
        }

        else if (gender == "female"){
            genderRadio.check(R.id.radioButtonFemale);
        }

        RadioGroup bloodRadio = (RadioGroup)findViewById(R.id.bloodRadio);
        if (blood == "A") {
            bloodRadio.check(R.id.radioButtonA);
        }
        else if (blood == "B") {
            bloodRadio.check(R.id.radioButtonB);
        }
        else if (blood == "AB") {
            bloodRadio.check(R.id.radioButtonAB);
        }
        else if (blood == "O") {
            bloodRadio.check(R.id.radioButtonO);
        }



    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonA:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radioButtonAB:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radioButtonB:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radioButtonO:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radioButtonMale:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radioButtonFemale:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }
}
