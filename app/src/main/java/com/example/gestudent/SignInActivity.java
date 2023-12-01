package com.example.gestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestudent.POJO.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;//guarda objetos en una base de datos
    private FirebaseUser fbUser;//permite acceder a los credenciales del usuario que haya sido creado.
    private EditText etName, etMail, etPassword, etConfPassword;
    private String name, mail, password, confPword;
    private Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfPassword = (EditText) findViewById(R.id.etSecondPassword);
        btnSignIn = (Button) findViewById(R.id.btnSign);
        //TODO firebase
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString().trim();
                mail = etMail.getText().toString().trim();

                if (etPassword.getText().toString().trim() ==
                        etConfPassword.getText().toString().trim()){
                    password = etPassword.getText().toString().trim();
                    confPword = etConfPassword.getText().toString().trim();

                    User u = new User(name, mail);
                }else{
                    etName.setText(name);
                    etMail.setText(mail);
                    Toast.makeText(SignInActivity.this, "Las contrasenas no son iguales", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}