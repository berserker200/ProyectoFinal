package com.example.proyectofinal1;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class miposicion implements LocationListener {
    // Variables estáticas para almacenar la latitud, longitud y estado del GPS
    public static double latitud;
    public static double longitud;
    public static boolean statusGPS;
    public static Location coordenadas;

    // Método invocado cuando la ubicación del dispositivo cambia
    @Override
    public void onLocationChanged(Location loc) {
        // Actualizar las variables de latitud, longitud y coordenadas con la nueva ubicación
        latitud = loc.getLatitude();
        longitud = loc.getLongitude();
        coordenadas = loc;
    }

    // Método invocado cuando cambia el estado del proveedor de ubicación
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    // Método invocado cuando se habilita el proveedor de ubicación
    public void onProviderEnabled(String provider) {
        // Establecer el estado del GPS como activo
        statusGPS = true;
    }

    // Método invocado cuando se deshabilita el proveedor de ubicación
    public void onProviderDisabled(String provider) {
        // Establecer el estado del GPS como inactivo
        statusGPS = false;
    }

}
