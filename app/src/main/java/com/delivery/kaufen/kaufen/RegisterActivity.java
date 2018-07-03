package com.delivery.kaufen.kaufen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private TextView button_want_buy;
    private TextView button_want_deliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button_want_buy = findViewById(R.id.button_want_buy);
        button_want_deliver = findViewById(R.id.button_want_deliver);

        button_want_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_intent = new Intent(RegisterActivity.this, RegisterClientBuy.class);
                startActivity(go_intent);
            }
        });
    }
}
