package com.example.proyectofinal1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal1.database.Persona;
import com.example.proyectofinal1.database.PersonaLab;

import java.util.List;

public class form extends AppCompatActivity implements View.OnClickListener {

    // Campos de entrada de texto para el nombre, URL de foto, dirección y teléfono
    private EditText editTextNombre;
    private EditText editTextUrlFoto;
    private EditText editTextDireccion;
    private EditText editTextTelefono;

    // Instancia de PersonaLab para interactuar con la base de datos
    private PersonaLab personaLab;

    // Botón para guardar los datos del formulario y mostrar el mapa
    Button btnguardar;
    Button btnmapa;

    // Variable para almacenar la dirección
    String direc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Inicializar vistas y asignar aciones a los botones
        btnmapa =findViewById(R.id.bnt_mapa);
        btnguardar =(Button) findViewById(R.id.buttonGuardar);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextUrlFoto = findViewById(R.id.editTextUrlFoto);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextTelefono = findViewById(R.id.editTextTelefono);

        // Crear una instancia de PersonaLab para interactuar con la base de datos
        personaLab=new PersonaLab(this);

        // Asignar los escuchadores a los botones
        btnmapa.setOnClickListener(this);
        btnguardar.setOnClickListener(this);
    }

    // Método para manejar los clics en los botones
    @Override
    public void onClick(View view) {
        if(view==btnguardar){
            // Si se hace clic en el botón de guardar, llamar al método para insertar personas
            insertPersonas();
            // Obtener y mostrar todas las personas almacenadas en la base de datos
            getAllPersonas();

        }if(view==btnmapa){
            // Si se hace clic en el botón de mapa, abrir la actividad del mapa
            getmapa();

        }
    }

    // Método para insertar una nueva persona en la base de datos
    public void insertPersonas() {

        // Obtener datos del formulario
        String nombre = editTextNombre.getText().toString();
        String urlFoto = editTextUrlFoto.getText().toString();
        String direccion = editTextDireccion.getText().toString();
        String telefono = editTextTelefono.getText().toString();

        if (nombre.equals("")) {
            // Validar que el campo de nombre no esté vacío
            editTextNombre.setError("Ingrese el nombre");
            editTextTelefono.setError("Ingrese el telefono");
        } else {
            // Crear una instancia de Persona con los datos del formulario
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setUrl_foto(urlFoto);
            persona.setDireccion(direccion);
            persona.setTelefono(telefono);

            // Agregar la nueva persona a la base de datos utilizando PersonaLab
            personaLab.addPersona(persona);

            // Limpiar los campos del formulario después de guardar la persona
            editTextNombre.setText("");
            editTextUrlFoto.setText("");
            editTextDireccion.setText("");
            editTextTelefono.setText("");

        }
    }
    // Método para obtener y mostrar todas las personas almacenadas en la base de datos
    public void getAllPersonas(){
        // Obtener todas las personas de la base de datos utilizando PersonaLab
        PersonaLab personaLab= PersonaLab.get(getApplicationContext());
            List<Persona> personas=personaLab.getPersonas();
        // Recorrer la lista de personas y mostrar sus datos en el registro
        for(Persona p:personas){
                Log.d("InserData","Id:"+p.getId()+"Nombre: "+p.getNombre()+
                        " Url Foto: "+p.getUrl_foto()+" Direccion: "+p.getDireccion()+
                        " Telefono: "+p.getTelefono());
            }
        }

        // Método para abrir la actividad del mapa
        public void getmapa(){
            Intent intent = new Intent(form.this, Mapa.class);

            startActivity(intent);


        }
    @Override
    protected void onResume(){
        // Obtener la dirección de la actividad anterior y mostrarla en el campo de dirección
        super.onResume();
        Intent intent =getIntent();
        String direccion = intent.getStringExtra("DIRECCION");
        editTextDireccion.setText(direccion);
    }


}
