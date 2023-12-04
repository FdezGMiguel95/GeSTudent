package com.example.gestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RememberPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editText_RememberEmail;
    private Button button_RememberPassword;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_password);

        mAuth = FirebaseAuth.getInstance();
        editText_RememberEmail = (EditText) findViewById(R.id.editText_RememberEmail);
        button_RememberPassword = (Button) findViewById(R.id.button_RememberPassword);

        button_RememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editText_RememberEmail.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(RememberPassword.this, LoginActivity.class);
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }
}