package com.example.maxim.laba3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAuth;
    private Button btnReg;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAuth = (Button) findViewById(R.id.btnAuth);
        btnReg = (Button) findViewById(R.id.btnReg);

        btnAuth.setOnClickListener(this);
        btnReg.setOnClickListener(this);

        preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);

        int is_auth = preferences.getInt("is_auth", 0);

        if(is_auth == 1){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAuth:
                Intent intent = new Intent(this, AuthActivity.class);
                startActivity(intent);
                break;
            case R.id.btnReg:
                Intent intent2 = new Intent(this, RegActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
