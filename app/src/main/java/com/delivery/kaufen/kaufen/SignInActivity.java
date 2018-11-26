package com.delivery.kaufen.kaufen;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private TextView register_now;
    private TextView email_sign_in;
    private TextView password_sign_in;
    private TextView resend_email_verif;
    private Button button_sign_in;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        register_now = findViewById(R.id.register_now);
        button_sign_in = findViewById(R.id.button_sign);
        password_sign_in = findViewById(R.id.password_sign);
        email_sign_in = findViewById(R.id.email_sign);
        resend_email_verif = findViewById(R.id.text_reenviar_email);
        mAuth = FirebaseAuth.getInstance();

        register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_register = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(go_register);
            }
        });

        resend_email_verif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_register = new Intent(SignInActivity.this, ResendEmailActivity.class);
                startActivity(go_register);
            }
        });

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_sign_in.getText().toString();
                String password = password_sign_in.getText().toString();

                if (email.length() == 0 || password.length() == 0){
                    Toast.makeText(getApplicationContext(),"Por favor, preencha os campos " +
                            "corretamente", Toast.LENGTH_LONG).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                            SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Login ou senha invalidos.",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                            else{
                                checkIfEmailVerified();
                            }
                        }
                    });
                }
            }
        });

    }

    private void checkIfEmailVerified() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.isEmailVerified()) {
            // user email is verified.
            finish();
            Toast.makeText(
                    SignInActivity.this,
                    "Logado com sucesso",
                    Toast.LENGTH_SHORT
            ).show();
            Intent go_intent = new Intent(SignInActivity.this, HomeActualActivity.class);
            startActivity(go_intent);
        }
        else {
            Toast.makeText(
                    SignInActivity.this,
                    "Por Favor, verifique seu email antes de logar",
                    Toast.LENGTH_SHORT
            ).show();
            mAuth.signOut();
            //restart this activity

        }
    }
}
