package com.example.maxim.laba3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maxim on 10.04.2018.
 */

public class FilmAdapter extends BaseAdapter {
    private ArrayList<Film> films;
    private Context context;
    private LayoutInflater inflater;

    public FilmAdapter(ArrayList<Film> films, Context context) {
        this.films = films;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int position) {
        return films.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.activity_film_adapter, parent, false);
        }

        Film film = (Film) getItem(position);

        ImageView poster = (ImageView) view.findViewById(R.id.poster);
        TextView name = (TextView) view.findViewById(R.id.txtName);
        TextView country = (TextView) view.findViewById(R.id.txtCountry);
        TextView rating = (TextView) view.findViewById(R.id.txtRating);

        poster.setImageResource(film.getImageId());
        name.setText(film.getName());
        country.setText(film.getCountry());
        rating.setText(String.valueOf(film.getRating()));
        return view;
    }
}
