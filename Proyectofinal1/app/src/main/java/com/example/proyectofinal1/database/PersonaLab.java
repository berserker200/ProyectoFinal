package com.example.proyectofinal1.database;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class PersonaLab {
    ///Esta anotación evita que el compilador muestre advertencias sobre la fuga de memoria estática.
    @SuppressLint("StaticFieldLeak")
    // Instancia única de PersonaLab
    private static PersonaLab sPersonaLab;

    // DAO para acceder a la base de datos de Persona
    private PersonDAO mPersonaDao;

    // Constructor de PersonaLab
    public PersonaLab(Context context){

        // Obtiene el contexto de la aplicación
        Context appContext=context.getApplicationContext();

        // Crea una instancia de la base de datos de Room para la entidad Persona
        PersonaDatabase database = Room.databaseBuilder(appContext, PersonaDatabase.class,"persona")
                // Permite consultas en el hilo principal
                .allowMainThreadQueries().build();

        // Obtiene el DAO de Persona de la base de datos
        mPersonaDao=database.getPersonaDAO();
    }
    // Método estático para obtener la instancia única de PersonaLab
    public static PersonaLab get(Context context){

        // Si sPersonaLab aún no está inicializado, se crea una nueva instancia de PersonaLab
        if (sPersonaLab==null){
            sPersonaLab=new PersonaLab(context);
        }
        // Retorna la instancia única de PersonaLab
        return sPersonaLab;
    }
    // Método para obtener todas las personas de la base de datos
    public List<Persona> getPersonas(){
        return  mPersonaDao.getPersonas();
    }

    // Método para obtener una persona por su ID de la base de datos
    public Persona getPersona(String id){
        return mPersonaDao.getPersona(id);
    }

    // Método para agregar, eliminar  una persona a la base de datos
    public void addPersona(Persona persona){
        mPersonaDao.insertPersona(persona);
    }
    public void deletePersona(Persona persona){
        mPersonaDao.deletePersona(persona);
    }
    // Método para actualizar los datos de una persona en la base de datos
    public void updatePersona(Persona persona){
        mPersonaDao.updatePersona(persona);
    }

}
