package com.aliniacoban.fishingindenmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.registerText);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId()){
            case R.id.registerText:
                startActivity(new Intent (this, RegisterActivity.class));
                break;
        }
    }
}