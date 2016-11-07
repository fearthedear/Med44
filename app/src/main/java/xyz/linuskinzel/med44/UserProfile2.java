package xyz.linuskinzel.med44;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import static java.lang.String.valueOf;

public class UserProfile2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        //calculate and set bmi
        TextView bmiTextView = (TextView) findViewById(R.id.bmi);
        float bmi = round(weight/(height*height)*10000,2);
        String bmistr = valueOf(bmi);
        bmiTextView.setText(bmistr);

        //in between: setting name in navigation drawer
        View header = navigationView.getHeaderView(0);
        TextView name2 = (TextView) header.findViewById(R.id.name);
        name2.setText(name);

        nameTextView.setText(name);
        ageTextView.setText(Integer.toString(age));
        weightTextView.setText(valueOf(weight));
        heightTextView.setText(valueOf(height));


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

        final View parentLayout = findViewById(R.id.content_user_profile2);
        Snackbar bar = Snackbar.make(parentLayout, "", Snackbar.LENGTH_INDEFINITE)
                .setAction("Save Changes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick_saveChanges(parentLayout);
                    }
                });

        bar.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //disabling because settings removed
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_userProfile) {
            Intent anIntent = new Intent(this, UserProfile2.class);
            startActivity(anIntent);
        } else if (id == R.id.nav_medhistory) {
            Intent anIntent = new Intent(this, MedicalHistory2.class);
            startActivity(anIntent);
        } else if (id == R.id.nav_prescriptions) {
            Intent anIntent = new Intent(this, Prescriptions2.class);
            startActivity(anIntent);
        } else if (id == R.id.nav_vaccines) {
            Intent anIntent = new Intent(this, Vaccines2.class);
            startActivity(anIntent);
        } else if (id == R.id.nav_contact) {
            Intent anIntent = new Intent(this, seeDoctor2.class);
            startActivity(anIntent);
        } else if (id == R.id.nav_about) {
            Intent anIntent = new Intent(this, About.class);
            startActivity(anIntent);
        } else if (id == R.id.nav_main_menu) {
            Intent anIntent = new Intent(this, MainActivity.class);
            startActivity(anIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick_saveChanges (View v) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        EditText nameTextView = (EditText)findViewById(R.id.nameTextView);
        EditText ageTextView = (EditText)findViewById(R.id.ageTextView);
        EditText weightTextView = (EditText)findViewById(R.id.weightTextView);
        EditText heightTextView = (EditText)findViewById(R.id.heightTextView);
        TextView bmiTextView = (TextView) findViewById(R.id.bmi);

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

        //calculate and set bmi
        float bmi = round(weightFloat/(heightFloat*heightFloat)*10000,2);
        String bmistr = valueOf(bmi);
        bmiTextView.setText(bmistr);

        Snackbar.make(v, "Changes saved!", Snackbar.LENGTH_SHORT).show();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                showSnackbar();
            }
        }, 5000);

    }

    public void showSnackbar() {
        final View parentLayout = findViewById(R.id.content_user_profile2);
        Snackbar bar = Snackbar.make(parentLayout, "", Snackbar.LENGTH_INDEFINITE)
                .setAction("Save Changes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick_saveChanges(parentLayout);
                    }
                });

        bar.show();
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

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
                    editor.putString("gender", "Female");
                    editor.apply();
                }
                break;
        }
    }
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
