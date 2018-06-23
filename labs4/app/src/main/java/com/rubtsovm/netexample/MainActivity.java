package com.rubtsovm.netexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rubtsovm.netexample.net.MarvelApi;
import com.rubtsovm.netexample.net.request.characters.model.Character;
import com.rubtsovm.netexample.net.request.characters.model.CharacterDataWrapper;
import com.rubtsovm.netexample.utils.CredentialsUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;

import rx.schedulers.Schedulers;

import static com.rubtsovm.netexample.utils.CredentialsUtils.public_key;
import static com.rubtsovm.netexample.utils.CredentialsUtils.ts;
import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class MainActivity extends AppCompatActivity {

    // ui
    @BindView(R.id.listView)
    ListView listView;

    ArrayList<Character> characters = new ArrayList<>();
    Adapter adapter;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MarvelApi marvelApi = MarvelApi.getInstance();
        subscription = marvelApi.getMarvel(ts, public_key, CredentialsUtils.getHash())
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe(new Subscriber<CharacterDataWrapper>() {
                               @Override
                               public void onCompleted() {
                                   Log.d("MainActivity", "onCompleted");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("MainActivity", "onError => " + e.getMessage());
                                   Log.e("MainActivity", "onError => " + e.getStackTrace().toString());
                                   for(StackTraceElement item : e.getStackTrace()){
                                       Log.e("MainActivityonError", "line " + String.valueOf(item.getLineNumber()));
                                       Log.e("MainActivityonError", "method " + item.getMethodName());
                                       Log.e("MainActivityonError", "className " + item.getClassName());

                                   }
                               }

                               @Override
                               public void onNext(CharacterDataWrapper response) {
                                   Log.d("MainActivity", "onNext => " + response);
                                   try{
                                       if(response.getData().getResults().size() > 0){
                                           fillData(response, response.getData().getResults().size());
                                           adapter = new Adapter(characters, getApplicationContext());
                                           listView.setAdapter(adapter);
                                       }
                                   }catch (NullPointerException e){
                                       Log.e("MainActivity", "NullPointerException  => " + e.getMessage());
                                   }
                               }
                           }
                );

        if(listView != null){
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent idCharacter = new Intent(getApplicationContext(), CharacterActivity.class);
                Log.d("MainActivity","intent id = " + characters.get(position).getId());
                idCharacter.putExtra("id", characters.get(position).getId());
                startActivity(idCharacter);
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    void fillData(CharacterDataWrapper character, int countItems){
        for (int i = 0; i < countItems; i++) {
            characters.add(new Character(character.getData().getResults().get(i).getName(),
                    character.getData().getResults().get(i).getThumbnail(),
                    character.getData().getResults().get(i).getId(),
                    character.getData().getResults().get(i).getDescription()));
        }
    }

}
