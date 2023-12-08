package com.example.gestudent;

import static android.content.Context.WINDOW_SERVICE;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gestudent.POJO.ExamenApunte;
import com.example.gestudent.POJO.TareaApunte;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<TareaApunte> listaApunte = new ArrayList<>();
    //private ArrayList<ExamenApunte> listaExamen = new ArrayList<>();/*lista que se agregara despeus cuadno hagamos la cardview de examenes*/

    private RecyclerView recy;
    private AdaptadorTareas adapterT;
    private Spinner spinnerHome;
    private ArrayList<String> spinerFiltros = new ArrayList<>();

    private Bundle args;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;


    private FloatingActionsMenu grupoBotones;
    private FloatingActionButton btnAgregarApunte,btnAgregarExamen,btnEliminarLista;



    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {


        bajarLista();

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

        spinnerHome = view.findViewById(R.id.spinnerHome);


        TareaApunte t1 = new TareaApunte("TAREA" , "LALALALALLALALLAL");
        listaApunte.add(t1);





        spinerFiltros.add("Solo apuntes");
        spinerFiltros.add("Solo para estudios");

        cambioSpinner(spinnerHome , view);

        grupoBotones = view.findViewById(R.id.grupoBotones);
        btnAgregarApunte = view.findViewById(R.id.btnAgregarApunte);


        btnAgregarExamen = view.findViewById(R.id.btnAgregarExamen);
        btnEliminarLista = view.findViewById(R.id.btnEliminarLista);


        btnAgregarApunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {

                Intent i = new Intent(getActivity(), NuevoApunteActivity.class);
                startActivity(i);

            }
        });





    }

    @Override
    public void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult(requestCode , resultCode , data);

        if(requestCode==1&&resultCode== Activity.RESULT_OK){
            bajarLista();
        }

    }




    public void bajarLista(){

        mAuth = FirebaseAuth.getInstance();
        String Uid = mAuth.getCurrentUser().getUid();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Uid).child("Notes");

        if(Uid!=null){

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange ( @NonNull DataSnapshot snapshot ) {

                    listaApunte.clear();
                    for(DataSnapshot data: snapshot.getChildren()){

                        TareaApunte tar = data.getValue(TareaApunte.class);
                        listaApunte.add(tar);
                    }

                    adapterT.notifyDataSetChanged();
                }

                @Override
                public void onCancelled ( @NonNull DatabaseError error ) {

                }
            });
        }
    }


    public void cambioSpinner ( Spinner spinner , View view ) {

        final ArrayAdapter<String> adapterS = new ArrayAdapter<>(view.getContext() , androidx.appcompat.R.layout.select_dialog_item_material , spinerFiltros);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterS);

    }





    public void filtrar () {


    }



}