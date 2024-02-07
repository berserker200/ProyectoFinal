package com.example.proyectofinal1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class contacto extends AppCompatActivity {

    // Código de solicitud para capturar la imagen
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    // ImageView para mostrar la foto capturada
    private ImageView imageViewFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        // Recuperar datos del Intent que inició esta actividad
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("NOMBRE");
        String apellido = intent.getStringExtra("APELLIDO");
        String telefono = intent.getStringExtra("TELEFONO");
        String direccion = intent.getStringExtra("DIRECCION");

        // Mostrar los datos en TextViews u otros elementos de tu interfaz
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewApellido = findViewById(R.id.textViewApellido);
        TextView textViewDireccion = findViewById(R.id.textViewDireccion);
        TextView textViewTelefono = findViewById(R.id.textViewTelefono);

        textViewNombre.setText(nombre);
        textViewApellido.setText(apellido);
        textViewDireccion.setText(direccion);
        textViewTelefono.setText(telefono);

        // Inicializar el ImageView para mostrar la foto
        imageViewFoto = findViewById(R.id.imageViewFoto);

        // Invocar el método para capturar la imagen
        capturarImagen();
    }

    // Método para iniciar la captura de imagen
    private void capturarImagen() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "No se puede abrir la cámara", Toast.LENGTH_SHORT).show();
        }
    }
    // Método para manejar el resultado de la captura de imagen
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imageBitmap);
        } else {
            // Método para manejar el resultado de la captura de imagen
            Toast.makeText(this, "No se pudo capturar la imagen", Toast.LENGTH_SHORT).show();
        }
    }
}