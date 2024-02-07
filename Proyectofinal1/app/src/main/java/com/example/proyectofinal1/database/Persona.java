package com.example.proyectofinal1.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Definición de una entidad para la tabla "persona" en la base de datos SQLite
@Entity (tableName = "persona")
public class Persona {

    // Identificador único de la persona en la tabla
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private int id;


        @ColumnInfo(name = "nombre")
        @NonNull
        private String nombre;

        @ColumnInfo(name = "url_foto")
        private  String url_foto;

        @ColumnInfo(name = "direccion")
        private String direccion;

        @ColumnInfo(name = "telefono")
        private String telefono;

        // métodos de acceso (getters y setters) para los campos de la entidad

        public String getDireccion(){
            return  direccion;
        }

        public void setDireccion(String direccion){
            this.direccion=direccion;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre( String nombre) {
            this.nombre = nombre;
        }

        public String getUrl_foto() {
            return url_foto;
        }

        public void setUrl_foto(String url_foto) {
            this.url_foto = url_foto;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
}
