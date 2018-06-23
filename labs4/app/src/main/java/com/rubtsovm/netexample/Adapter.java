package com.rubtsovm.netexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubtsovm.netexample.net.request.characters.model.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Character> character;
    Context context;
    LayoutInflater inflater;

    public Adapter(ArrayList<Character> character, Context context) {
        this.character = character;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return character.size();
    }

    @Override
    public Object getItem(int position) {
        return character.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = inflater.inflate(R.layout.adapter, parent, false);
        }

        Character character = (Character) getItem(position);

        String imagePath = character.getThumbnail().getPath() + "/standard_xlarge" + ".";
        String imageExtension = character.getThumbnail().getExtension();
        String imageUrl = imagePath + imageExtension;

        ImageView characterImage = (ImageView) view.findViewById(R.id.characterImage);
        Picasso.get().load(imageUrl).into(characterImage);

        ((TextView)view.findViewById(R.id.characterName)).setText(character.getName());

        return view;
    }
}
