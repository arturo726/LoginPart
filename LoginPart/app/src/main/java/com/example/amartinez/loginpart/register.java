package com.example.amartinez.loginpart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;


public class register extends AppCompatActivity {

   private EditText usernameEt, passwordEt, firstNameEt, lastNameEt, confirmPasswordEt;
    AlertDialog inputError;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        usernameEt = (EditText) findViewById(R.id.usernameField);
        passwordEt = (EditText) findViewById(R.id.passwordField);
        firstNameEt = (EditText) findViewById(R.id.firstNameField);
        lastNameEt = (EditText) findViewById(R.id.lastNameField);
        confirmPasswordEt = (EditText) findViewById(R.id.ConfirmPasswordField);

        returnHome();
    }

    public void returnHome() {

        Button button;
        button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent startActivity = new Intent(register.this, MainActivity.class);
                startActivity(startActivity);
            }

        });
    }

    public void OnRegister(View view) {

        String username;
        String password;
        String firstName;
        String lastName;
        String confirmPassword;

        username = usernameEt.getText().toString();
        password = passwordEt.getText().toString();
        firstName = firstNameEt.getText().toString();
        lastName = lastNameEt.getText().toString();
        confirmPassword = confirmPasswordEt.getText().toString();


        inputError = new AlertDialog.Builder(this).create();
        inputError.setMessage("Please fill out all data.");

        inputError.setTitle("Invalid Input");

        if (    username.equals("") ||
                password.equals("") ||
                firstName.equals("") ||
                lastName.equals("") ||
                confirmPassword.equals("")
                )
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
            String type = "register";
            userProcessing register = new userProcessing(this);
            register.execute(type, username, password, firstName, lastName, confirmPassword);
        }
    }


}



