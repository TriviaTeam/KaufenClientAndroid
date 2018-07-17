package com.delivery.kaufen.kaufen;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterClientDeliver extends AppCompatActivity {

    private TextView email_register_client;
    private TextView password_register_client;
    private Button button_register_client;
    private TextView warning_info;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client_deliver);

        email_register_client = findViewById(R.id.email_register_deliver);
        password_register_client = findViewById(R.id.password_register_deliver);
        button_register_client = findViewById(R.id.button_register_deliver);
        warning_info = findViewById(R.id.warning_info);
        auth = FirebaseAuth.getInstance();

        button_register_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = email_register_client.getText().toString();
                String password = password_register_client.getText().toString();

                if(email.length() == 0 || password.length() == 0 || password.length() < 8){
                    warning_info.setText(R.string.warning_wrong_login_register);
                    warning_info.setTextColor(Color.RED);
                }
                else{
                    auth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(RegisterClientDeliver.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    if (!task.isSuccessful()){
                                        Log.d("TAG", "createUserWithEmail:onComplete: DEU ERRADO");
                                    }
                                    else {
                                        FirebaseUser user = auth.getCurrentUser();
                                        sendEmailVerification(user);
                                        //user.getDisplayName();
                                        //user.getUid();
                                    }

                                }
                            });
                }
            }
        });
    }

    private void sendEmailVerification(FirebaseUser user) {
        Log.d("TAG", "createUserWithEmail:onComplete: DEU CERTO");

        if (user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        auth.signOut();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Email de verificaçao enviado \n " +
                                        "para o email cadastrado",
                                Toast.LENGTH_LONG
                        );
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        startActivity(new Intent(RegisterClientDeliver.this, SignInActivity.class));
                        finish();
                    }
                }
            });
        }
        else {
            Log.d("TAG", "createUserWithEmail:onComplete: DEU ERRADO AQUI");
        }
    }

    private void insertUserDB(FirebaseUser user){
        //metodo para persistir usuario no nosso db
    }
}
