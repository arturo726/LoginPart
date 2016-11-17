package com.example.amartinez.loginpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class afterLoginHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_home);

        createGameClicked();
    }

    public void createGameClicked() {

        Button button;
        button = (Button) findViewById(R.id.CreateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent startActivity = new Intent(afterLoginHome.this, createGameOptions.class);
                startActivity(startActivity);
            }

        });
    }
}
