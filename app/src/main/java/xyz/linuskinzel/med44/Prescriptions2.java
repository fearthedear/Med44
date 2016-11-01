package xyz.linuskinzel.med44;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Prescriptions2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, addPrescriptionFragment.DialogListener {

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, int perday, int fordays) {
        // User touched the dialog's positive button
        Toast.makeText(this, "Take for days: " + String.valueOf(perday), Toast.LENGTH_LONG).show();
        //take the data here and write in the database
        //then reload the activity to display the new prescription
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPrescription();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //in between: setting name in navigation drawer
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String name = prefs.getString("name", "no shared preference stored");
        View header = navigationView.getHeaderView(0);
        TextView name2 = (TextView) header.findViewById(R.id.name);

        name2.setText(name);


        dbActions = new databaseActions(this);

        is_empty();
    }

    databaseActions dbActions;

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
        getMenuInflater().inflate(R.menu.prescriptions2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //disabling because I removed settings
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
            Intent anIntent = new Intent(this, MedicalHistory.class);
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

    public void is_empty() {
        dbActions = new databaseActions(this);
        dbActions.open();
        Cursor cursor = dbActions.getAllPrescriptionRecords();

        if (cursor.moveToFirst());
        else {
            View relativeLayout =  findViewById(R.id.content_prescriptions2);
            //LinearLayout layout = (LinearLayout) findViewById(R.id.info);

            TextView noPrescriptions = new TextView(this);
            String Str = this.getString(R.string.noPrescriptions);
            noPrescriptions.setText(Str);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            noPrescriptions.setLayoutParams(params);
            ((RelativeLayout) relativeLayout).addView(noPrescriptions);
        }

        dbActions.close();
    }
    public void addPrescription() {
        DialogFragment newFragment = new addPrescriptionFragment();
        newFragment.show(getFragmentManager(), "addPrescription");
    }

}
