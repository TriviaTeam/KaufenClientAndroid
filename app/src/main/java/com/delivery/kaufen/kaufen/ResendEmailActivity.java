package com.delivery.kaufen.kaufen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResendEmailActivity extends AppCompatActivity {

    private Button resend_email_button;
    private EditText email_to_verify;
    private EditText password_to_verify;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resend_email);

        resend_email_button = findViewById(R.id.resend_email_button);
        password_to_verify = findViewById(R.id.resend_email_password_input);
        email_to_verify = findViewById(R.id.resend_email_input);
        mAuth = FirebaseAuth.getInstance();

        resend_email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_to_verify.getText().toString();
                String password = password_to_verify.getText().toString();
                loginAndCheckEmail(email,password);
            }
        });

    }

    private void loginAndCheckEmail(String email, String password){

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                ResendEmailActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Email ou senha invalidos.",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                        else{
                            FirebaseUser user = mAuth.getCurrentUser();
                            checkIfEmailVerified(user);
                        }
                    }
                });
    }

    private void checkIfEmailVerified(FirebaseUser user) {

        if (user.isEmailVerified()) {
            // user email is verified.
            finish();
            Toast.makeText(
                    ResendEmailActivity.this,
                    "O email já foi verificado!",
                    Toast.LENGTH_SHORT
            ).show();
            Intent go_intent = new Intent(ResendEmailActivity.this, HomeActivity.class);
            startActivity(go_intent);
        }
        else {
            sendEmailVerification(user);
        }
    }

    private void sendEmailVerification(FirebaseUser user) {
        Log.d("TAG", "createUserWithEmail:onComplete: DEU CERTO");

        if (user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        mAuth.signOut();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Email de verificaçao enviado \n " +
                                        "para o email cadastrado",
                                Toast.LENGTH_LONG
                        );
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        startActivity(new Intent(ResendEmailActivity.this, SignInActivity.class));
                        finish();
                    }
                }
            });
        }
        else {
            Log.d("TAG", "createUserWithEmail:onComplete: DEU ERRADO AQUI");
        }
    }
}
