package com.example.gestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gestudent.POJO.TareaApunte;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NuevoApunteActivity extends AppCompatActivity {


    private ArrayList<TareaApunte>nuevosApuntes = new ArrayList<>();

    private EditText etTituloNuevoApunte,etDescripApunte;
    private Button botonAgregarApunte;
    private ImageButton btnAtrasAPunte;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_apunte);

        etTituloNuevoApunte = findViewById(R.id.etTituloNuevoApunte);
        etDescripApunte = findViewById(R.id.etDescripApunte);
        botonAgregarApunte = findViewById(R.id.botonAgregarApunte);
        btnAtrasAPunte = findViewById(R.id.btnAtrasAPunte);

        mAuth = FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("Users");



        btnAtrasAPunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {

                Intent i = new Intent(NuevoApunteActivity.this, HomeActivity.class);
                startActivity(i);


            }
        });

       botonAgregarApunte.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick ( View v ) {

               String titulo = etTituloNuevoApunte.getText().toString().trim();
               String descripcion = etDescripApunte.getText().toString().trim();

               TareaApunte tareaNueva = new TareaApunte(titulo,descripcion);


               String uid = mAuth.getCurrentUser().getUid();
               String idApunte = mRef.push().getKey();

               mRef= FirebaseDatabase.getInstance().getReference().child("Users");
               mRef.child(uid).child("Notes").child(idApunte).setValue(tareaNueva).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete ( @NonNull Task<Void> task ) {

                       if(task.isSuccessful()){
                           Intent i = new Intent(NuevoApunteActivity.this,HomeActivity.class);
                           startActivity(i);
                           finish();
                       }

                   }
               });






           }
       });





    }
}