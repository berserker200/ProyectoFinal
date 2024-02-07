package com.example.proyectofinal1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class Mapa extends AppCompatActivity  implements OnMapReadyCallback, View.OnClickListener{

    private GoogleMap mMap; // Objeto GoogleMap para mostrar el mapa
    private Button btnmap; // Botón para mostrar la ubicación guardada
    double latitude; // Latitud guardada
    double longitude; // Longitud guardada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Obtener el fragmento del mapa y cargar el mapa asincrónicamente
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // Inicializar el botón y establecer un OnClickListener
        btnmap = findViewById(R.id.btnlocali);
        btnmap.setOnClickListener(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * <p>
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    // Método onClick para manejar los clics en el botón
    @Override
    public void onClick(View v) {
        if (v == btnmap) {

            // Obtener la ubicación guardada desde SharedPreferences
            LatLng savedLocation = getSavedLocationFromSharedPreferences();

            if (savedLocation != null) {

                // Si la ubicación está guardada, agregar un marcador en el mapa
                mMap.addMarker(new MarkerOptions().position(savedLocation).title("Ubicación guardada"));

                // Mostrar un Toast con la latitud y longitud de la ubicación guardada
                Toast.makeText(Mapa.this, "Latitud: " + savedLocation.latitude + ", Longitud: " + savedLocation.longitude, Toast.LENGTH_SHORT).show();

                // Abrir la actividad del formulario y pasar la dirección guardada
                Intent intent = new Intent(Mapa.this,form.class);
                String direc =String.valueOf(savedLocation);
                intent.putExtra("DIRECCION",direc);
                startActivity(intent);
            } else {
                // Si no hay ubicación guardada, mostrar un mensaje Toast
                Toast.makeText(Mapa.this, "No se ha guardado ninguna ubicación", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método onMapReady para manejar cuando el mapa está listo para ser utilizado
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Asignar el objeto GoogleMap
        mMap = Objects.requireNonNull(googleMap);

        // Establecer una ubicación predeterminada y agregar un marcador en el mapa
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marcador"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Establecer un escuchador para el evento de clic largo en el mapa
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // Guardar la ubicación al hacer clic largo en el mapa
                saveLocationToSharedPreferences(latLng.latitude, latLng.longitude);
                // Mostrar un mensaje Toast indicando que la ubicación se ha guardado
                Toast.makeText(Mapa.this, "Ubicación guardada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para guardar la ubicación en SharedPreferences
    private void saveLocationToSharedPreferences(double latitude, double longitude) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat("LATITUDE", (float) latitude);
        editor.putFloat("LONGITUDE", (float) longitude);

        editor.apply();
    }

    // Método para obtener la ubicación guardada desde SharedPreferences
    private LatLng getSavedLocationFromSharedPreferences() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        latitude = sharedPreferences.getFloat("LATITUDE", 0.0f);
        longitude = sharedPreferences.getFloat("LONGITUDE", 0.0f);

        if (latitude != 0.0 && longitude != 0.0) {
            return new LatLng(latitude, longitude);
        } else {
            return null;
        }
    }

    // Método para obtener la ubicación del usuario
    private void miPosicion() {
        LocationManager objLocation = null;
        LocationListener objLocListener;
        objLocation = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        objLocListener = new miposicion();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        objLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, objLocListener);
        if (objLocation.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (miposicion.latitud != 0) {
                double lat = miposicion.latitud;
                double lon = miposicion.longitud;
                LatLng ubi = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(ubi).title("Mi ubicacion"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(ubi));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);

            } else {
                Toast.makeText(Mapa.this, "Lalocalizacion no esta disponible", Toast.LENGTH_SHORT).show();

            }
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(Mapa.this);
            alert.setTitle("GPS NO ESTA ACTIVO");
            alert.setMessage("Conectando con GPS");
            alert.setPositiveButton("OK", null);
            alert.create().show();
        }

    }
}