package com.example.maxim.laba3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by maxim on 16.04.2018.
 */

public class FilmInfo extends AppCompatActivity {
    private TextView txtName, txtCountry, txtRating, txtInfo;
    private ImageView poster;

    private FilmsFragment arr_f = new FilmsFragment();
    private SeriesFragment arr_s = new SeriesFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_info);

        Intent intent = getIntent();
        int filmId = intent.getIntExtra("id", 0);
        int type = intent.getIntExtra("type", 1);

        txtName = (TextView) findViewById(R.id.txtName);
        txtCountry = (TextView) findViewById(R.id.txtCountry);
        txtRating = (TextView) findViewById(R.id.txtRating);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        poster = (ImageView) findViewById(R.id.poster);

        switch (type){
            case 1:
                txtName.setText(arr_f.film_arr[filmId].getName());
                txtCountry.setText(arr_f.film_arr[filmId].getCountry());
                txtRating.setText(String.valueOf(arr_f.film_arr[filmId].getRating()));
                txtInfo.setText(arr_f.film_arr[filmId].getInfo());
                poster.setImageResource(arr_f.film_arr[filmId].getImageId());

                setTitle(arr_f.film_arr[filmId].getName());
                break;
            case 2:
                txtName.setText(arr_s.film_arr[filmId].getName());
                txtCountry.setText(arr_s.film_arr[filmId].getCountry());
                txtRating.setText(String.valueOf(arr_s.film_arr[filmId].getRating()));
                txtInfo.setText(arr_s.film_arr[filmId].getInfo());
                poster.setImageResource(arr_s.film_arr[filmId].getImageId());

                setTitle(arr_s.film_arr[filmId].getName());
                break;
            default:
                break;
        }
    }
}
