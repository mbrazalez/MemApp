package com.pk.memapp20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {
    Location currentLocation;
    EditText addr;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private GoogleMap mMap;
    int count=0;
    SharedPreferences.Editor editor;
    SharedPreferences settings;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        settings=getSharedPreferences("MODE",Context.MODE_PRIVATE);
        addr=findViewById(R.id.txtAddress);
        j=0;

        if (count==0){
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fetchLastLocation();
        }else{
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location !=null){
                    currentLocation=location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude()
                            +""+currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);

        LatLng ubi=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(ubi).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubi));


        String locationsStr=settings.getString("locations","");
        String positionsStr=settings.getString("latlng","");
        String[] locations=locationsStr.split(",");
        String[] positions=positionsStr.split(",");

        for (int i=0; i<positions.length; i+=2){
            if (i<positions.length-1){
                if (j<locations.length){
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(positions[i]),Double.parseDouble(positions[i+1]))).title(locations[j]));
                    j++;
                }else{
                    break;
                }
            }else{
                break;
            }
        }

        this.mMap.setOnMarkerClickListener(this);


    }




    private void getCity(LatLng myLatLng){
        Geocoder geocoder=new Geocoder(MapsActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(myLatLng.latitude, myLatLng.longitude, 1);
            String address=addresses.get(0).getAddressLine(0);
            addr.setText(""+address);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }

    public void currentLocation(View view) {
        LatLng ubi = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        getCity(ubi);

        mMap.addMarker(new MarkerOptions().position(ubi).title("My Location"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                ubi, 16f);
        mMap.animateCamera(cameraUpdate);
    }

    public void irMaps(View view){
        Intent i=new Intent(this, MapsMenuActivity.class);
        startActivity(i);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        getCity(latLng);

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        getCity(marker.getPosition());
        return false;
    }
}