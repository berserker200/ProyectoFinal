package com.example.proyectofinal1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyectofinal1.database.Persona;
import com.example.proyectofinal1.database.PersonaLab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {

    // Lista de personas
    public ArrayList<Persona> listaPersonas=new ArrayList<>();
    // RecyclerView para mostrar la lista de personas
    public RecyclerView lista;
    // Adaptador para la lista de personas
    public RecyclerAdapter adapter;
    // Instancia de PersonaLab para interactuar con la base de datos
    private PersonaLab personaLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar PersonaLab para acceder a la base de datos
        personaLab=new PersonaLab(this);

        // Inicializar RecyclerView y asignar un LayoutManager
        lista=(RecyclerView) findViewById(R.id.recyclerview);
        lista.setLayoutManager(new LinearLayoutManager(this));

        // Obtener todas las personas de la base de datos y mostrarlas en el RecyclerView
        getAllPersonas();

        // Inicializar el adaptador y asignarlo al RecyclerView
        adapter = new RecyclerAdapter(this, listaPersonas, this);
        lista.setAdapter(adapter);

        // Configurar un OnClickListener para el botón de agregar nuevo contacto
        ImageButton imageButton = findViewById(R.id.imageButton);

        // Establece un OnClickListener para el ImageButton
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad del formulario al hacer clic en el botón
                Intent intent = new Intent(MainActivity.this, form.class);
                startActivity(intent);
            }
        });
    }

    // Método para manejar el clic en un elemento del RecyclerView
    @Override
    public void onItemClick(int position) {
        // Asegúrate de que position es un índice válido en listaPersonas
        if (position >= 0 && position < listaPersonas.size()) {

            // Obtener la persona seleccionada
            Persona personaSeleccionada = listaPersonas.get(position);

            // Crear un Intent para pasar a la segunda actividad
            Intent intent = new Intent(MainActivity.this, contacto.class);

            // Puedes pasar datos adicionales a la segunda actividad usando el Intent
            intent.putExtra("NOMBRE", personaSeleccionada.getNombre());
            intent.putExtra("APELLIDO", personaSeleccionada.getUrl_foto());
            intent.putExtra("DIRECCION", personaSeleccionada.getDireccion());
            intent.putExtra("TELEFONO", personaSeleccionada.getTelefono());

            // Iniciar la segunda actividad
            startActivity(intent);
        }
    }

    // Método para obtener todas las personas de la base de datos y actualizar la lista
    public void getAllPersonas(){
        listaPersonas.clear();
        listaPersonas.addAll(personaLab.getPersonas());

    }

    // Método onResume para actualizar la lista de personas al volver a la actividad
    @Override
    protected void onResume(){
        super.onResume();
        getAllPersonas();
        adapter.notifyDataSetChanged();
    }

    // Método para crear el menú de opciones en la barra de acción
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Método para manejar las opciones del menú de opciones en la barra de acción
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_new_contact){
            // Abrir la actividad del formulario al seleccionar la opción del menú
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,
                    form.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}