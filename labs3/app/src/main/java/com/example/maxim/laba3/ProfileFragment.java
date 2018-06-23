package com.example.maxim.laba3;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by maxim on 10.04.2018.
 */

public class ProfileFragment extends android.support.v4.app.Fragment {
    private TextView textName, textDate, textLogin;
    private Button btnExit;

    RegActivity.DBHelper dbHelper;
    SQLiteDatabase db;

    private SharedPreferences preferences;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        getActivity().setTitle("Profile");

        textName = (TextView) v.findViewById(R.id.textName);
        textDate = (TextView) v.findViewById(R.id.textDate);
        textLogin = (TextView) v.findViewById(R.id.textLogin);
        btnExit = (Button) v.findViewById(R.id.btnExit);

        preferences = getActivity().getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);

        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preferences.edit().remove("is_auth").apply();
                preferences.edit().remove("login").apply();
                Intent exit = new Intent(getActivity(), MainActivity.class);
                startActivity(exit);
            }
        });

        String user = preferences.getString("login","");

        dbHelper = new RegActivity.DBHelper(getActivity());

        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, "login = ?", new String[]{user}, null, null, null);

        if(c.moveToFirst()){
            int nameCol = c.getColumnIndex("name");
            int loginCol = c.getColumnIndex("login");
            int dateCol = c.getColumnIndex("date");

            textName.setText("Name: " + c.getString(nameCol));
            textDate.setText("Login: " + c.getString(loginCol));
            textLogin.setText("Date of bird: " + c.getString(dateCol));
        } else {
            textName.setText("No information");
        }
        c.close();
        dbHelper.close();

        return v;
    }
}
