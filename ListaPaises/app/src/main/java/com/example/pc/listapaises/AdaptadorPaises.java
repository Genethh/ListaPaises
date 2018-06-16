package com.example.pc.listapaises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorPaises extends ArrayAdapter<Paises> {
    public AdaptadorPaises(Context context, ArrayList<Paises> datos) {
        super(context, R.layout.imgpaises, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater. from(getContext());
        View item = inflater.inflate(R.layout.imgpaises, null);

        TextView name = (TextView)item.findViewById(R.id.lblName);
        name.setText(getItem(position).getName());

        TextView code = (TextView)item.findViewById(R.id.lblCode2);
        code.setText(getItem(position).getCode2());

        ImageView imgvw= (ImageView) item.findViewById(R.id.imgPais);
        Glide.with(this.getContext())
                .load(getItem(position).getUrl())
                .error(R.drawable.imgnotfound)
                .into(imgvw);

        return(item);
    }

}
