package com.example.proyectofinal1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Define la base de datos utilizando RoomDatabase para la aplicación Android
@Database(entities = {Persona.class},version = 1)
public abstract class PersonaDatabase extends RoomDatabase {
    // Método abstracto para obtener el DAO (Data Access Object) de Persona
    public abstract PersonDAO getPersonaDAO();
}
