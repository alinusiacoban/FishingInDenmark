package com.aliniacoban.fishingindenmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextView headlineRegister, registerButton;
    EditText registerUsername, registerPassword, registerEmail, registerConfirmPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        headlineRegister = (TextView) findViewById(R.id.registerHeadline);
        headlineRegister.setOnClickListener(this);

        registerButton=(Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        registerUsername = (EditText) findViewById(R.id.inputUsername);
        registerEmail = (EditText) findViewById(R.id.inputEmail);
        registerPassword = (EditText) findViewById(R.id.inputPassword);
        registerConfirmPassword = (EditText) findViewById(R.id.confirmPassword);


    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.registerHeadline:
                startActivity(new Intent(this, MainActivity.class));
                        break;
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser()
    {
        String email = registerEmail.getText().toString().trim();
        String username = registerUsername.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String confirmPassord = registerConfirmPassword.getText().toString().trim();

        if(username.isEmpty()){
            registerUsername.setError("Username is required");
            registerUsername.requestFocus();
            return;
        }
        if(password.isEmpty()){
            registerPassword.setError("Password is required");
            registerPassword.requestFocus();
            return;
        }
        if(confirmPassord.isEmpty()){
            registerConfirmPassword.setError("Please confirm the password");
            registerConfirmPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            registerEmail.setError("Please provide a valid email");
            registerEmail.requestFocus();
            return;
        }
        if(password.length()<6){
            registerPassword.setError("Password needs to be longer than 6 characters");
            registerPassword.requestFocus();
            return;
        }
        if(password!=confirmPassord)
        {
            registerPassword.setError("Passwords do not match");
            registerConfirmPassword.setError("Passwords do not match");
            registerPassword.requestFocus();
            registerConfirmPassword.requestFocus();
            return;
        }

    }
}