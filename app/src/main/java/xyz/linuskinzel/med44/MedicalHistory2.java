package xyz.linuskinzel.med44;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MedicalHistory2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, addConditionFragment.DialogListener, addVisitFragment.DialogListener2 {

    databaseActions dbActions;

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String condition, String date, String identifier) {
        // User touched the dialog's positive button

        dbActions = new databaseActions(this);
        dbActions.open();
        
        if (identifier.equals("condition")) {
            dbActions.insertCondition(condition, date);
            Intent myIntent = new Intent(this, MedicalHistory2.class);
            startActivity(myIntent);
        }
        else {
            dbActions.insertVisit(date, condition, identifier);
            // TODO: 04/11/2016 make the activity switch to the visits tab automatically
            Intent myIntent = new Intent(this, MedicalHistory2.class);
            startActivity(myIntent);
        }
        
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history2);
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
        View header = navigationView.getHeaderView(0);
        TextView name2 = (TextView) header.findViewById(R.id.name);
        name2.setText(name);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new fragmentTabsAdapter(getSupportFragmentManager(),
                MedicalHistory2.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
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
        getMenuInflater().inflate(R.menu.medical_history2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
            Intent anIntent = new Intent(this, Vaccines.class);
            startActivity(anIntent);
        } else if (id == R.id.nav_contact) {
            Intent anIntent = new Intent(this, seeDoctor.class);
            startActivity(anIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
