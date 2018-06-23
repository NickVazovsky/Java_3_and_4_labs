package com.example.maxim.laba3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editLogin;
    private EditText editPass;
    private Button btnEnter;

    private SharedPreferences preferences;

    RegActivity.DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        editLogin = (EditText) findViewById(R.id.editLogin);
        editPass = (EditText) findViewById(R.id.editPass);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(this);

        dbHelper = new RegActivity.DBHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String login = editLogin.getText().toString().trim();
        String pass = editPass.getText().toString().trim();

        db = dbHelper.getWritableDatabase();

        Cursor c = db.query("mytable", new String[]{"login", "pass"}, "login = ? AND pass = ?", new String[]{login, pass}, null, null, null);

        Boolean is_auth = false;

        if(c.moveToFirst()){
            int loginCol = c.getColumnIndex("login");
            int passCol = c.getColumnIndex("pass");

            if(login.equals(c.getString(loginCol)) && pass.equals(c.getString(passCol))){
                is_auth = true;
            } else {
                is_auth = false;
            }

        } else {
            Toast.makeText(this, "There are no users", Toast.LENGTH_LONG).show();
            return;
        }

        if(is_auth){
            preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("is_auth", 1);
            editor.putString("login", login);
            editor.apply();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Wrong login or password", Toast.LENGTH_LONG).show();
        }

        c.close();
        dbHelper.close();
    }
}
