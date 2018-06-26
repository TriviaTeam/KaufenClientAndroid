package com.delivery.kaufen.kaufen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity {

    private ImageView button_want_buy;
    private ImageView button_want_deliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button_want_buy = findViewById(R.id.quero_comprar);
        button_want_deliver = findViewById(R.id.quero_entregar);

        button_want_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_intent = new Intent(RegisterActivity.this, RegisterClientBuy.class);
                startActivity(go_intent);
            }
        });

    }
}
