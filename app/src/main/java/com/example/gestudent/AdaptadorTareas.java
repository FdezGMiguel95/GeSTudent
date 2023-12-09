package com.example.gestudent;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestudent.POJO.ExamenApunte;
import com.example.gestudent.POJO.TareaApunte;

import java.util.ArrayList;

public class AdaptadorTareas extends RecyclerView.Adapter<AdaptadorTareas.ViewHolder> {

    private boolean mostrarEliminar=false;
    private ArrayList<TareaApunte>listItem;

    private ArrayList<ExamenApunte>listExam;


    private OnItemClickListener listener;



    public AdaptadorTareas ( ArrayList<TareaApunte> listItem , ArrayList<ExamenApunte> listExam ) {
        this.listItem = listItem;
        this.listExam = listExam;
    }

    public void setMostrarIcono(boolean mostrarIcono){
        this.mostrarEliminar = mostrarIcono;
        notifyDataSetChanged();

    }

    public void setItemClickListener(OnItemClickListener clickListener){

        listener = clickListener;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }


    public AdaptadorTareas ( ArrayList<TareaApunte> listItem ) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public AdaptadorTareas.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.cardapunte,parent,false);
        AdaptadorTareas.ViewHolder holder = new AdaptadorTareas.ViewHolder(viewItem,listener);



        return holder;
    }

    @Override
    public void onBindViewHolder ( @NonNull AdaptadorTareas.ViewHolder holder , int position ) {

        holder.tvTitulo.setText(listItem.get(position).getTitulo());
        holder.tvDescripcion.setText(listItem.get(position).getDescripcion());


        holder.imagenBasura.setVisibility(mostrarEliminar ? View.VISIBLE :View.GONE );/*si el mostrarEliminar es true =  visible
                                                                                        sino .GONE*/
    }







    @Override
    public int getItemCount () {
        return listItem.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{


        private TextView tvTitulo, tvDescripcion;
        private ImageView imagenBasura;

        public ViewHolder ( @NonNull View itemView,OnItemClickListener listener ) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            imagenBasura = itemView.findViewById(R.id.imagenBasura);


            imagenBasura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick ( View v ) {

                listener.onItemClick(getAdapterPosition());

                }
            });



        }




    }


}
