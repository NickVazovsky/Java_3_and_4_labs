package com.example.maxim.laba3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editLogin, editPass, editPass2, editName, editDate;
    private Button btnReg;

    DBHelper dbHelper;
    SQLiteDatabase db;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        editLogin = (EditText) findViewById(R.id.editLogin);
        editPass = (EditText) findViewById(R.id.editPass);
        editPass2 = (EditText) findViewById(R.id.editPass2);
        editName = (EditText) findViewById(R.id.editName);
        editDate = (EditText) findViewById(R.id.editDate);
        btnReg = (Button) findViewById(R.id.btnReg);

        btnReg.setOnClickListener(this);

        dbHelper = new DBHelper(this);

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
        if((editLogin.getText().toString().trim().length() == 0) && (editPass.getText().toString().trim().length() == 0)){
            Toast.makeText(this, "Fill login and password", Toast.LENGTH_LONG).show();
        } else {
            String pass = editPass.getText().toString();
            String pass2 = editPass2.getText().toString();
            if (pass.equals(pass2)) {
                db = dbHelper.getWritableDatabase();

                String login = editLogin.getText().toString();
                Boolean is_reg = false;
                Cursor c = db.query("mytable", new String[]{"login"}, "login = ?", new String[]{login}, null, null, null);

                if (c.moveToFirst()) {
                    int loginCol = c.getColumnIndex("login");

                    if (login.equals(c.getString(loginCol))) {
                        is_reg = true;
                    }

                    c.close();
                }

                if (is_reg) {
                    Toast.makeText(this, "Login is busied. Try another", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues cv = new ContentValues();

                    String name = editName.getText().toString();
                    String date = editDate.getText().toString();

                    cv.put("login", login);
                    cv.put("pass", pass);
                    cv.put("name", name);
                    cv.put("date", date);

                    long rowID = db.insert("mytable", null, cv);
                    Log.d(LOG_TAG, "row inserted, ID = " + rowID);

                    dbHelper.close();

                    Intent intent = new Intent(this, AuthActivity.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
            }
        }
    }

    static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "login text,"
                    + "pass text,"
                    + "name text,"
                    + "date date"+ ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
