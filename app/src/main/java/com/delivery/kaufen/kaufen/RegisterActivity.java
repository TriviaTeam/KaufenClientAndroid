package com.delivery.kaufen.kaufen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    private Button comprar;
    private Button entregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        comprar = findViewById(R.id.button_comprar);
        entregar = findViewById(R.id.button_entregar);

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar_comrpar = new Intent(RegisterActivity.this, RegisterClientBuy.class);
                startActivity(registrar_comrpar);
            }
        });

        entregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar_entregar = new Intent(RegisterActivity.this, RegisterClientDeliver.class);
                startActivity(registrar_entregar);
            }
        });

    }
}
