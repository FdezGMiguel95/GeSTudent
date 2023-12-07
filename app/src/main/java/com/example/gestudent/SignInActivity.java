package com.example.gestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestudent.POJO.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
                password = etPassword.getText().toString().trim();
                confPword = etConfPassword.getText().toString().trim();
                if (password.equals(confPword)) {
                    mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                fbUser = mAuth.getCurrentUser();
                                String uID = fbUser.getUid();
                                //GuardarPreferencias(uID);
                                User u = new User(name, mail);
                                mRef.child(uID).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent i = new Intent(SignInActivity.this, LoginActivity.class);
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(SignInActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignInActivity.this, "Credenciales incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    etName.setText(name);
                    etMail.setText(mail);
                    etPassword.setText("");
                    etConfPassword.setText("");
                    Toast.makeText(SignInActivity.this, "Las contrasenas no son iguales", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void GuardarPreferencias(String uID){
        SharedPreferences sharedPreferences_Login = getSharedPreferences("user", 0);
        SharedPreferences.Editor user = sharedPreferences_Login.edit();
        user.putString("user", uID);
        //login.putFloat("contrasena", contrasena);
        user.commit();
    }
}