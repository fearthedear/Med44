package xyz.linuskinzel.med44;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

        EditText nameTextView = (EditText)findViewById(R.id.nameTextView);
        EditText ageTextView = (EditText)findViewById(R.id.ageTextView);
        EditText weightTextView = (EditText)findViewById(R.id.weightTextView);
        EditText heightTextView = (EditText)findViewById(R.id.heightTextView);


        nameTextView.setText(name);
        ageTextView.setText(Integer.toString(age));
        weightTextView.setText(String.valueOf(weight));
        heightTextView.setText(String.valueOf(height));


        RadioGroup genderRadio = (RadioGroup)findViewById(R.id.genderRadio);
        if ("Male".equals(gender)) {
            genderRadio.check(R.id.radioButtonMale);
        }

        else if ("Female".equals(gender)){
            genderRadio.check(R.id.radioButtonFemale);
        }

        RadioGroup bloodRadio = (RadioGroup)findViewById(R.id.bloodRadio);
        if ("A".equals(blood)) {
            bloodRadio.check(R.id.radioButtonA);
        }
        else if ("B".equals(blood)) {
            bloodRadio.check(R.id.radioButtonB);
        }
        else if ("AB".equals(blood)) {
            bloodRadio.check(R.id.radioButtonAB);
        }
        else if ("O".equals(blood)) {
            bloodRadio.check(R.id.radioButtonO);
        }

        final View parentLayout = findViewById(R.id.activity_user_profile);
        Snackbar bar = Snackbar.make(parentLayout, "", Snackbar.LENGTH_INDEFINITE)
                .setAction("Save Changes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick_saveChanges(parentLayout);
                    }
                });

        bar.show();
    }

    public void onClick_saveChanges (View v) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        EditText nameTextView = (EditText)findViewById(R.id.nameTextView);
        EditText ageTextView = (EditText)findViewById(R.id.ageTextView);
        EditText weightTextView = (EditText)findViewById(R.id.weightTextView);
        EditText heightTextView = (EditText)findViewById(R.id.heightTextView);

        String nameStr = nameTextView.getText().toString();
        int ageInt;
        try {
            ageInt = Integer.valueOf(ageTextView.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Please enter a valid Age", Toast.LENGTH_SHORT).show();
            return;
        }

        Float heightFloat = null;
        try {
            heightFloat = Float.valueOf(heightTextView.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Please enter a valid height", Toast.LENGTH_SHORT).show();
            return;
        }

        Float weightFloat = null;
        try {
            weightFloat = Float.valueOf(weightTextView.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show();
            return;
        }


        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", nameStr);
        editor.putInt("age", ageInt);
        editor.putFloat("height", heightFloat);
        editor.putFloat("weight", weightFloat);
        editor.apply();

        Snackbar.make(v, "Changes saved!", Snackbar.LENGTH_SHORT).show();
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        // TODO: 29/10/2016 save clicked button to sharedpreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        switch(view.getId()) {
            case R.id.radioButtonA:
                if (checked) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("bloodtype", "A");
                    editor.apply();
                    }
                    break;
            case R.id.radioButtonAB:
                if (checked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("bloodtype", "AB");
                    editor.apply();
                }
                    break;
            case R.id.radioButtonB:
                if (checked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("bloodtype", "B");
                    editor.apply();
                }
                break;
            case R.id.radioButtonO:
                if (checked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("bloodtype", "O");
                    editor.apply();
                }
                    break;
            case R.id.radioButtonMale:
                if (checked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("gender", "Male");
                    editor.apply();
                }
                    break;
            case R.id.radioButtonFemale:
                if (checked){
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("bloodtype", "Female");
                    editor.apply();
                }
                    break;
        }
    }
}
