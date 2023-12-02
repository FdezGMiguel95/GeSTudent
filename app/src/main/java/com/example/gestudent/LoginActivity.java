package com.example.gestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //private FirebaseAuth mAuth;

    private EditText editText_EmailLogIn, editText_PasswordLogIn;
    private TextView textView_RememberLogIn, textView_RegisterNewUser;
    private Button button_LogIn;
    private String email, password;
    private String user = "";
    SharedPreferences sharedPreferences_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_EmailLogIn = (EditText) findViewById(R.id.editText_EmailLogIn);
        editText_PasswordLogIn = (EditText) findViewById(R.id.editText_PasswordLogIn);
        textView_RememberLogIn = (TextView) findViewById(R.id.textView_RememberLogIn);
        textView_RegisterNewUser = (TextView) findViewById(R.id.textView_RegisterNewUser);
        button_LogIn = (Button) findViewById(R.id.button_LogIn);

        /*
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            usuario = sharedPreferences_Login.getString("usuario", "uID");
            etCorreo.setText(usuario);
        } else {
            Toast.makeText(LoginActivity.this, "Bienvenido usuario, no estás conectado", Toast.LENGTH_SHORT).show();
            //FirebaseAuth.getInstance().signOut();
        }
        */

        textView_RememberLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(LoginActivity.this, RememberLogInActivity.class);
                //startActivity(i);
            }
        });

        button_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editText_EmailLogIn.getText().toString().trim();
                password = editText_PasswordLogIn.getText().toString().trim();


                /*
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Intent i = new Intent(LoginActivity.this, ActivityPerfil.class);
                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                */
            }
        });

        textView_RegisterNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });

    }
}