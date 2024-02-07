package com.example.proyectofinal1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {
    // Consulta para obtener todas las personas de la tabla persona
    @Query("select * from persona")
    List<Persona> getPersonas();

    // Consulta para obtener una persona por su ID
    @Query("select * from persona where id LIKE:uuid")
    Persona getPersona(String uuid);

    // Operaciones de inserción, actualización y eliminación para la entidad Persona
    @Insert
    void insertPersona(Persona persona);

    @Update
    void updatePersona(Persona persona);
    @Delete
    void deletePersona(Persona persona);

}
