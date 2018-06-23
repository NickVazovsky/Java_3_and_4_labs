package com.example.maxim.laba3;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by maxim on 10.04.2018.
 */

public class FilmsFragment extends android.support.v4.app.Fragment {
    private ArrayList<Film> films = new ArrayList<>();
    private FilmAdapter adapter;
    private ListView film;

    public Film film1 = new Film(R.drawable.deadpool,
            "DeadPool 2",
            "USA",
            92,
            "Единственный и неповторимый болтливый наемник — " +
                    "вернулся! Ещё более масштабный, ещё более разрушительный" +
                    " и даже ещё более голозадый, чем прежде! Когда в его жизнь " +
                    "врывается суперсолдат с убийственной миссией, Дэдпул вынужден " +
                    "задуматься о дружбе, семье и о том, что на самом деле значит " +
                    "быть героем, попутно надирая 50 оттенков задниц. Потому что " +
                    "иногда чтобы делать хорошие вещи, нужно использовать грязные приёмчики.");

    public Film film2 = new Film(R.drawable.rampage,
            "Rampage",
            "USA",
            64,
            "Приматолог Дэвис Окойи предпочитает держаться подальше от " +
                    "людей и дружит с Джорджем, удивительно умным самцом " +
                    "гориллы, которого он воспитывал с самого рождения. " +
                    "Но вышедший из-под контроля генетический эксперимент " +
                    "превращает послушную обезьяну в яростного монстра. " +
                    "Более того, вскоре обнаруживается, что существуют и " +
                    "другие модифицированные хищники. Пока свежесозданные " +
                    "чудовища превращают в руины Северную Америку, стирая " +
                    "с лица Земли все, что попадается у них на пути, Окойи " +
                    "вместе с опальным генным инженером пытается изобрести " +
                    "антидот, прорываясь по бесконечному полю боя не только, " +
                    "чтобы предотвратить глобальную катастрофу, но и спасти " +
                    "бесстрашное существо, которое когда-то было его другом.");

    public Film[] film_arr = {film1, film2};

    public static FilmsFragment newInstance() {
        FilmsFragment fragment = new FilmsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_films, container, false);

        getActivity().setTitle("Films");

        film = (ListView) v.findViewById(R.id.filmId);

        films.add(film_arr[0]);
        films.add(film_arr[1]);

        adapter = new FilmAdapter(films, getActivity());
        film.setAdapter(adapter);

        film.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FilmInfo.class);
                intent.putExtra("id", position);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

        return v;
    }
}
