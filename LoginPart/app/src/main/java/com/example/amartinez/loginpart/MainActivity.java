package com.example.amartinez.loginpart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEt, passwordEt;
    private AlertDialog inputError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEt = (EditText) findViewById(R.id.usernameField);
        passwordEt = (EditText) findViewById(R.id.passwordField);
        signupClicked();
    }

    public void OnLogin(View view) {
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        inputError = new AlertDialog.Builder(this).create();
        inputError.setMessage("Please fill out all data.");

        inputError.setTitle("Invalid Input");

        if (    username.equals("") ||
                password.equals(""))
        {

            inputError.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    inputError.dismiss();
                }
            });

            inputError.show();

        }
        else
        {
            String type = "login";
            userProcessing process = new userProcessing(this);
            process.execute(type, username, password);

        }

    }

    public void signupClicked() {

        Button button;
        button = (Button) findViewById(R.id.SignUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent startActivity = new Intent(MainActivity.this, register.class);
                startActivity(startActivity);
            }

        });
    }

}
