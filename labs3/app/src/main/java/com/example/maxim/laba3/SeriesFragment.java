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

public class SeriesFragment extends android.support.v4.app.Fragment {
    private ArrayList<Film> films = new ArrayList<>();
    private FilmAdapter adapter;
    private ListView film;

    public Film series1 = new Film(R.drawable.bb,
            "Breaking bad",
            "USA",
            88,
            "Школьный учитель химии Уолтер Уайт узнаёт, что болен раком лёгких." +
                    " Учитывая сложное финансовое состояние дел семьи, а также перспективы," +
                    " Уолтер решает заняться изготовлением метамфетамина. Для этого он " +
                    "привлекает своего бывшего ученика Джесси Пинкмана, когда-то исключённого " +
                    "из школы при активном содействии Уайта. Пинкман сам занимался «варкой мета»," +
                    " но накануне, в ходе рейда УБН, он лишился подельника и лаборатории…");

    public Film series2 = new Film(R.drawable.univer,
            "Универ",
            "Russia",
            67.0,
            "Оставленным на второй год пятикурсникам Антону и Кузе привалило счастье." +
                    " Их старая общага идет под снос, и в новом корпусе их селят в блок с " +
                    "тремя девушками — Кристиной, Машей и Яной. В роли аспиранта в универ " +
                    "возвращается и Майкл — чтобы откосить от армии. Ребята стали старше и… " +
                    "романтичнее. Теперь им хочется не только секса и тусовок, но и настоящих чувств. " +
                    "Однако вызвать взаимность у дам сердца не так просто, как кажется.");

    public Film[] film_arr = {series1, series2};

    public static SeriesFragment newInstance() {
        SeriesFragment fragment = new SeriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_series, container, false);

        getActivity().setTitle("Series");

        film = (ListView) v.findViewById(R.id.seriesId);

        films.add(film_arr[0]);
        films.add(film_arr[1]);

        adapter = new FilmAdapter(films, getActivity());
        film.setAdapter(adapter);

        film.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FilmInfo.class);
                intent.putExtra("id", position);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });

        return v;
    }
}
