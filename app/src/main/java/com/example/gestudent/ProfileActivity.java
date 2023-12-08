package com.example.gestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestudent.POJO.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser fbUser;
    private User u;
    private ImageView ivProf;
    private TextView tvName, tvCorreo, tvTemario, tvProgreso;
    private String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivProf = (ImageView) findViewById(R.id.ivProf);
        tvName = (TextView) findViewById(R.id.tvNameP);
        tvCorreo = (TextView) findViewById(R.id.tvCorreoP);
        tvTemario = (TextView) findViewById(R.id.tvTemarioP);
        tvProgreso = (TextView) findViewById(R.id.tvProgresoP);

        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();
        uuid = fbUser.getUid();

        u = new User(fbUser.getDisplayName(), fbUser.getEmail());
        //TODO REHACER USER E IMPLEMENTARLO BIEN
        tvName.setText(u.getName());
        tvCorreo.setText(u.getMail());


    }
}