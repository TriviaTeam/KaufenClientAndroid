package com.delivery.kaufen.kaufen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {

    public static int SPLASH_TIME_OUT = 2000;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent go_intent;

                if (mAuth.getCurrentUser() == null){
                    go_intent = new Intent(MainActivity.this, SignInActivity.class);
                }
                else {
                    go_intent = new Intent(MainActivity.this, HomeActivity.class);
                }

                startActivity(go_intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
