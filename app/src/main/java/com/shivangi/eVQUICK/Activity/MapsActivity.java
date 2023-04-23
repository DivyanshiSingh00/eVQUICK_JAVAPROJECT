package com.shivangi.eVQUICK.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.shivangi.eVQUICK.R;
import com.shivangi.eVQUICK.databinding.ActivityMapsBinding;
import com.shivangi.eVQUICK.databinding.BottomSheetLayoutBinding;

@SuppressWarnings("ALL")
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;

    private Marker marker;
    private ActivityMapsBinding binding;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private FirebaseAuth mAuth;

    private static final int Request_code = 101;

    private BottomSheetBehavior<RelativeLayout> bottomSheetBehavior;
    private BottomSheetLayoutBinding bottomSheetLayoutBinding;

    private double lat, lng;



    ImageButton atm, hosp, rest, chargeStation, btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        atm = findViewById(R.id.atms);
        hosp= findViewById(R.id.hospitals);
        rest= findViewById(R.id.restaurants);
        chargeStation = findViewById(R.id.charging_stations);
        btnLogout= findViewById(R.id.Logoutbtn);


        fusedLocationProviderClient=
                LocationServices.getFusedLocationProviderClient(this.getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //FirebaseAuth.getInstance().signOut();
                if (v.getId() == R.id.Logoutbtn) {
                    mAuth.getInstance().signOut();
                    Intent intent = new Intent(MapsActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                }
            }
        });



        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                StringBuilder sb = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

                sb.append("location="+lat+","+lng);
                sb.append("&radius=1000");
                sb.append("&type=atm");
                sb.append("&sensor=true");
                sb.append("&key="+getResources().getString(R.string.GOOGLE_MAPS_API_KEY));

                String url = sb.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(dataFetch);


            }
        });
        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

                sb.append("location="+lat+","+lng);
                sb.append("&radius=1000");
                sb.append("&type=hospital");
                sb.append("&sensor=true");
                sb.append("&key="+getResources().getString(R.string.GOOGLE_MAPS_API_KEY));

                String url = sb.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(dataFetch);

            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

                sb.append("location="+lat+","+lng);
                sb.append("&radius=1000");
                sb.append("&type=restaurant");
                sb.append("&sensor=true");
                sb.append("&key="+getResources().getString(R.string.GOOGLE_MAPS_API_KEY));

                String url = sb.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(dataFetch);

            }
        });
        chargeStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

                sb.append("location="+lat+","+lng);
                sb.append("&radius=2000");
                sb.append("&type=gas_station");
                sb.append("&sensor=true");
                sb.append("&key="+getResources().getString(R.string.GOOGLE_MAPS_API_KEY));

                String url = sb.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(dataFetch);

            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        getCurrentLocation();
    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
            return;
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);



        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Toast.makeText(getApplicationContext(), "location result is="+locationResult,
                        Toast.LENGTH_LONG).show();

                if(locationResult == null){

                    Toast.makeText(getApplicationContext(), "Current location is null"
                            ,Toast.LENGTH_LONG).show();

                    return;
                }

                for(Location location:locationResult.getLocations()){

                    if(location != null){
                        Toast.makeText(getApplicationContext(), "Current location is"+location.getLongitude()
                                ,Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null){

                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    BitmapDescriptor icon  = BitmapDescriptorFactory.fromResource(R.drawable.myplaceholder);

                    LatLng latLng = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("current location").icon(icon));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            }
        });

    }

    @SuppressLint("MissingSuperCall")

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (Request_code) {
            case Request_code -> {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                }
            }
        }
    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}