package com.example.gestudent;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gestudent.POJO.TareaApunte;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<TareaApunte>listaApunte = new ArrayList<>();

        private RecyclerView recy;
        private AdaptadorTareas adapterT ;


    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home , container , false);
    }

    @Override
    public void onViewCreated ( @NonNull View view , @Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view , savedInstanceState);

        adapterT = new AdaptadorTareas(listaApunte);

        recy = view.findViewById(R.id.recy);

        recy.setLayoutManager(new LinearLayoutManager(getContext()));

        recy.setHasFixedSize(true);
        recy.setAdapter(adapterT);


        TareaApunte t1 = new TareaApunte("TAREA","LALALALALLALALLAL");
        listaApunte.add(t1);



    }
}