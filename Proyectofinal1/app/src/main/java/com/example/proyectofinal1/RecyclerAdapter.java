package com.example.proyectofinal1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofinal1.database.Persona;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<Persona> datos;// Lista de datos a mostrar
    private Context context; // Contexto de la aplicación
    private OnItemClickListener listener; // Listener para manejar los clics en los elementos del RecyclerView

    // Interfaz para manejar los clics en los elementos del RecyclerView
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Constructor del adaptador
    public RecyclerAdapter(Context context, ArrayList<Persona> datos, OnItemClickListener listener) {
        this.datos = datos;
        this.context = context;
        this.listener = listener;
    }

    // Método para inflar el diseño de un elemento de la lista
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_list,viewGroup,false);

        return new ViewHolder(view);
    }

    // Método para establecer los datos en las vistas de un elemento de la lista
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Establecer el nombre en el TextView
        holder.getTextView().setText(datos.get(position).getNombre());

        // Cargar la imagen utilizando Glide (una biblioteca para cargar imágenes)
        Glide.with(context).load("https://goo.gl/gEgYUd").into(holder.getImg());

        // Variable final para ser utilizada en el OnClickListener
        final int clickedPosition = position;

        // Establecer un OnClickListener para el elemento de la lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método onItemClick del listener al hacer clic
                listener.onItemClick(clickedPosition);
            }
        });
    }

    // Método para obtener la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return datos.size();
    }

    // Clase ViewHolder para mantener las vistas de un elemento de la lista
    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtnombre;
        private final TextView textViewDireccion;
        private final TextView textViewTelefono;
        private final ImageView img;

        // Constructor ViewHolder para inicializar las vistas
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombre = itemView.findViewById(R.id.textNombre);
            textViewDireccion = itemView.findViewById(R.id.textViewDireccion);
            textViewTelefono = itemView.findViewById(R.id.textViewTelefono);
            img = itemView.findViewById(R.id.imageView);
        }

        // Métodos para obtener las vistas del ViewHolder
        public TextView getTextView() {
            return txtnombre;
        }

        public TextView getTextViewDireccion() {
            return textViewDireccion;
        }

        public TextView getTextViewTelefono() {
            return textViewTelefono;
        }

        public ImageView getImg() {
            return img;
        }
    }

}
