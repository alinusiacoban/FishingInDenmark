package com.aliniacoban.fishingindenmark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextView headlineRegister, registerButton, loginText;
    EditText registerUsername, registerPassword, registerEmail, registerConfirmPassword;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        headlineRegister = (TextView) findViewById(R.id.registerHeadline);
        headlineRegister.setOnClickListener(this);

        loginText = (TextView) findViewById(R.id.loginText);
        loginText.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        registerUsername = (EditText) findViewById(R.id.inputUsername);
        registerEmail = (EditText) findViewById(R.id.inputEmail);
        registerPassword = (EditText) findViewById(R.id.inputPassword);
        registerConfirmPassword = (EditText) findViewById(R.id.confirmPassword);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerHeadline:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerButton:
                registerUser();
                break;
            case R.id.loginText:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void registerUser() {
        String email = registerEmail.getText().toString().trim();
        String username = registerUsername.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String confirmPassord = registerConfirmPassword.getText().toString().trim();

        if (username.isEmpty()) {
            registerUsername.setError("Username is required");
            registerUsername.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            registerPassword.setError("Password is required");
            registerPassword.requestFocus();
            return;
        }
        if (confirmPassord.isEmpty()) {
            registerConfirmPassword.setError("Please confirm the password");
            registerConfirmPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmail.setError("Please provide a valid email");
            registerEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            registerPassword.setError("Password needs to be longer than 6 characters");
            registerPassword.requestFocus();
            return;
        }
        if (!(password.equals(confirmPassord))) {
            registerPassword.setError("Passwords do not match");
            registerConfirmPassword.setError("Passwords do not match");
            registerPassword.requestFocus();
            registerConfirmPassword.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            registerEmail.setError("Email is required");
            registerEmail.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.VISIBLE);
                        User user = new User(username, password);
                        FirebaseDatabase.getInstance("https://fishingindk-default-rtdb.europe-west1.firebasedatabase.app/").getReference("User")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                .addOnCompleteListener(task1 -> {

                                    if (task1.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "User has been registered succesfully!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });

                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }
}