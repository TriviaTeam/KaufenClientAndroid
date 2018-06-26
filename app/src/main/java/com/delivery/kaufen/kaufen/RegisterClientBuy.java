package com.delivery.kaufen.kaufen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterClientBuy extends AppCompatActivity {

    private TextView email_register_client;
    private TextView password_register_client;
    private Button button_register_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client_buy);

        email_register_client = findViewById(R.id.email_register_client);
        password_register_client = findViewById(R.id.password_register_client);
        button_register_client = findViewById(R.id.button_register_client);

        button_register_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
