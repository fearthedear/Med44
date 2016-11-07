package xyz.linuskinzel.med44;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static xyz.linuskinzel.med44.AppConfig.GEOMETRY;
import static xyz.linuskinzel.med44.AppConfig.GOOGLE_BROWSER_API_KEY;
import static xyz.linuskinzel.med44.AppConfig.ICON;
import static xyz.linuskinzel.med44.AppConfig.LATITUDE;
import static xyz.linuskinzel.med44.AppConfig.LOCATION;
import static xyz.linuskinzel.med44.AppConfig.LONGITUDE;
import static xyz.linuskinzel.med44.AppConfig.MIN_DISTANCE_CHANGE_FOR_UPDATES;
import static xyz.linuskinzel.med44.AppConfig.MIN_TIME_BW_UPDATES;
import static xyz.linuskinzel.med44.AppConfig.NAME;
import static xyz.linuskinzel.med44.AppConfig.OK;
import static xyz.linuskinzel.med44.AppConfig.PLACE_ID;
import static xyz.linuskinzel.med44.AppConfig.PLAY_SERVICES_RESOLUTION_REQUEST;
import static xyz.linuskinzel.med44.AppConfig.PROXIMITY_RADIUS;
import static xyz.linuskinzel.med44.AppConfig.REFERENCE;
import static xyz.linuskinzel.med44.AppConfig.STATUS;
import static xyz.linuskinzel.med44.AppConfig.SUPERMARKET_ID;
import static xyz.linuskinzel.med44.AppConfig.TAG;
import static xyz.linuskinzel.med44.AppConfig.VICINITY;
import static xyz.linuskinzel.med44.AppConfig.ZERO_RESULTS;

//check below for duplicates

public class seeDoctor2 extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, LocationListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    CoordinatorLayout mainCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isGooglePlayServicesAvailable()) {
            return;
        }
        setContentView(R.layout.activity_see_doctor2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // setting name in navigation drawer
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String name = prefs.getString("name", "no shared preference stored");
        View header = navigationView.getHeaderView(0);
        TextView name2 = (TextView) header.findViewById(R.id.name);
        name2.setText(name);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mainCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.mainCoordinatorLayout);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showLocationSettings();
        }
    }

    private void showLocationSettings() {
        Snackbar snackbar = Snackbar
                .make(mainCoordinatorLayout, "Location Error: GPS Disabled!",
                        Snackbar.LENGTH_LONG)
                .setAction("Enable", new View.OnClickListener() {
                    @Override                    public void onClick(View v) {

                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        snackbar.show();
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
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement


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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        showCurrentLocation();
    }

    private void showCurrentLocation() {
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(bestProvider);

        if (location != null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
    }

    private void loadNearByPlaces(double latitude, double longitude) {

        String type = "doctor";
        StringBuilder googlePlacesUrl =
                new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=").append(latitude).append(",").append(longitude);
        googlePlacesUrl.append("&radius=").append(PROXIMITY_RADIUS);
        googlePlacesUrl.append("&types=").append(type);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + GOOGLE_BROWSER_API_KEY);

        JsonObjectRequest request = new JsonObjectRequest(googlePlacesUrl.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject result) {

                        Log.i(TAG, "onResponse: Result= " + result.toString());
                        parseLocationResult(result);
                    }
                },
                new Response.ErrorListener() {
                    @Override                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: Error= " + error);
                        Log.e(TAG, "onErrorResponse: Error= " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(request);
    }

    private void parseLocationResult(JSONObject result) {

        String id, place_id, placeName = null, reference, icon, vicinity = null;
        double latitude, longitude;

        try {
            JSONArray jsonArray = result.getJSONArray("results");

            if (result.getString(STATUS).equalsIgnoreCase(OK)) {

                mMap.clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject place = jsonArray.getJSONObject(i);

                    id = place.getString(SUPERMARKET_ID);
                    place_id = place.getString(PLACE_ID);
                    if (!place.isNull(NAME)) {
                        placeName = place.getString(NAME);
                    }
                    if (!place.isNull(VICINITY)) {
                        vicinity = place.getString(VICINITY);
                    }
                    latitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)
                            .getDouble(LATITUDE);
                    longitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)
                            .getDouble(LONGITUDE);
                    reference = place.getString(REFERENCE);
                    icon = place.getString(ICON);

                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng latLng = new LatLng(latitude, longitude);
                    markerOptions.position(latLng);
                    markerOptions.title(placeName + " : " + vicinity);

                    mMap.addMarker(markerOptions);
                }

                Snackbar.make(mainCoordinatorLayout, jsonArray.length() + " doctors found.",
                        Snackbar.LENGTH_SHORT).show();
            } else if (result.getString(STATUS).equalsIgnoreCase(ZERO_RESULTS)) {
                Snackbar.make(mainCoordinatorLayout, "No doctors found within a 5km radius",
                        Snackbar.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {

            e.printStackTrace();
            Log.e(TAG, "parseLocationResult: Error=" + e.getMessage());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        loadNearByPlaces(latitude, longitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
