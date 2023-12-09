package com.example.gestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    private EditText editText_EmailLogIn, editText_PasswordLogIn;
    private TextView textView_RememberLogIn, textView_RegisterNewUser;
    private Button button_LogInEmail;
    private Button button_LogInGoogle;
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
        button_LogInEmail = (Button) findViewById(R.id.button_LogInEmail);
        button_LogInGoogle = (Button) findViewById(R.id.button_LogInGoogle);

        /* Verificar si el usuario ya está autenticado */
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            // El usuario está autenticado, realizar acciones necesarias
            Toast.makeText(this, "Usuario autenticado", Toast.LENGTH_SHORT).show();

            //SharedPreferences preferences = getSharedPreferences("sharedPreferences_Login", Context.MODE_PRIVATE);
            //user = preferences.getString("user",null);
            //editText_EmailLogIn.setText(user);

            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            //finish();

        } else {
            // El usuario no está autenticado
            Toast.makeText(LoginActivity.this, "No estás identificado", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }

        /* Configurar opciones de inicio de sesión de Google */
        /* Mirar también https://firebase.google.com/docs/auth/android/google-signin?hl=es#java */
        button_LogInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);
                Toast.makeText(LoginActivity.this, "Usuario autenticado con GOOGLE" + mGoogleSignInClient, Toast.LENGTH_SHORT).show();

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 1);
            }
        });

        textView_RememberLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RememberPassword.class);
                startActivity(i);
            }
        });

        button_LogInEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editText_EmailLogIn.getText().toString().trim();
                password = editText_PasswordLogIn.getText().toString().trim();
                //if(email == "" || password == ""){
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                //} else {
                    //Toast.makeText(LoginActivity.this, "Datos incompletos", Toast.LENGTH_SHORT).show();
                //}
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In fue exitoso, autentica con Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In falló, actualiza la interfaz de usuario con información de error
                Log.w("GoogleSignIn", "Google sign in failed", e);
            }
        } else {
            Toast.makeText(this, "AAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(i);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleSignIn", "signInWithCredential:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // El usuario está autenticado, realizar acciones necesarias
            Toast.makeText(this, "Usuario autenticado: " + user.getEmail(), Toast.LENGTH_SHORT).show();
            String usuario = sharedPreferences_Login.getString("user", "uID");
            editText_EmailLogIn.setText(usuario);
        } else {
            // El usuario no está autenticado
            Toast.makeText(LoginActivity.this, "Bienvenido usuario, no estás autenticado", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }
    }

    public void signInWithEmail(View view) {
        // Implementa la autenticación con correo y contraseña aquí
    }

}