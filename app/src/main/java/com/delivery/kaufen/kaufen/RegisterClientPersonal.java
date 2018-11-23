package com.delivery.kaufen.kaufen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.delivery.kaufen.kaufen.APICall.ClientDataService;
import com.delivery.kaufen.kaufen.APICall.RetrofitInstance;
import com.delivery.kaufen.kaufen.Models.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterClientPersonal extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView register_name;
    private TextView register_cpf;
    private TextView register_birthdate_day;
    private TextView register_birthdate_month;
    private TextView register_birthdate_year;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client_personal);

        auth = FirebaseAuth.getInstance();
        register_name = findViewById(R.id.register_complete_name);
        register_cpf = findViewById(R.id.register_cpf);
        register_birthdate_day = findViewById(R.id.register_birthdate_day);
        register_birthdate_month = findViewById(R.id.register_birthdate_month);
        register_birthdate_year = findViewById(R.id.register_birthdate_year);
        register_button = findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FirebaseUser user = auth.getCurrentUser();
                String id = user.getUid();

                String name = register_name.getText().toString();
                String cpf = register_cpf.getText().toString();
                String birth_day = register_birthdate_day.getText().toString();
                String birth_month = register_birthdate_month.getText().toString();
                String birth_year = register_birthdate_year.getText().toString();
                String birthdate = birth_year+"-"+birth_month+"-"+birth_day;

                Client client = new Client(id,name, cpf, birthdate);

                ClientDataService cds = RetrofitInstance.getRetrofitInstance().create(
                        ClientDataService.class
                );

                Call<Client> call = cds.postClientData(client);

                call.enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Usuário registrado",
                                Toast.LENGTH_LONG
                        );
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        sendEmailVerification(user);
                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "ERRO ao criar usuário, por favor tente novamente mais tarde",
                                Toast.LENGTH_LONG
                        );
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        startActivity(new Intent(RegisterClientPersonal.this, RegisterClientBuy.class));
                    }
                });
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
                        startActivity(new Intent(RegisterClientPersonal.this, SignInActivity.class));
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
