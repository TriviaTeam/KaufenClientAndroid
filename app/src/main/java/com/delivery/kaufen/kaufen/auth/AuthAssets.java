package com.delivery.kaufen.kaufen.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.delivery.kaufen.kaufen.RegisterClientBuy;
import com.delivery.kaufen.kaufen.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AuthAssets {

    public boolean password_verification(String password){

        if (password == null || password.length() == 0 || password.length() < 6){
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            if (!Character.isWhitespace(password.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
