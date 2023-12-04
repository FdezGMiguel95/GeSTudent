package com.example.gestudent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestudent.POJO.TareaApunte;

import java.util.ArrayList;

public class AdaptadorTareas extends RecyclerView.Adapter<AdaptadorTareas.ViewHolder> {


    private ArrayList<TareaApunte>listItem;

    public AdaptadorTareas ( ArrayList<TareaApunte> listItem ) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public AdaptadorTareas.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.cardapunte,parent,false);
        AdaptadorTareas.ViewHolder holder = new AdaptadorTareas.ViewHolder(viewItem);



        return holder;
    }

    @Override
    public void onBindViewHolder ( @NonNull AdaptadorTareas.ViewHolder holder , int position ) {

        holder.tvTitulo.setText(listItem.get(position).getTitulo());
        holder.tvDescripcion.setText(listItem.get(position).getDescripcion());

    }

    @Override
    public int getItemCount () {
        return listItem.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{


        private TextView tvTitulo, tvDescripcion;

        public ViewHolder ( @NonNull View itemView ) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);


        }




    }


}
