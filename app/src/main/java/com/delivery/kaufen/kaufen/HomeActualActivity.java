package com.delivery.kaufen.kaufen;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActualActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actual);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_sair:
                this.logout();
                return true;
            case R.id.action_config:
                Intent intent = new Intent(HomeActualActivity.this, AccountConfBuyerActivity.class);
                startActivity(intent);
                return true;
        }

        return false;
    }

    private void logout(){
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent intent = new Intent(HomeActualActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}

