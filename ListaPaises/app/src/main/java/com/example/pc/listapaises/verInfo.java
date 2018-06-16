package com.example.pc.listapaises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class verInfo extends AppCompatActivity implements Asynchtask {
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_info);

        //Recuperamos la información pasada en el intent
        Bundle bu = this.getIntent().getExtras();
        String codeDos= bu.getString("CODE").toString();
        Map<String, String> datoss = new HashMap<String, String>();
        WebService wss= new WebService("http://www.geognos.com/api/en/countries/info/"+bu.getString("CODE")+".json",datoss, verInfo.this, verInfo.this);
        wss.execute("");

    }



    @Override
    public void processFinish(String result) throws JSONException {
        TextView pais = (TextView)findViewById(R.id.lblPais);
        //TextView capital = (TextView)findViewById(R.id.lblCapital);

        JSONObject objdatos = new JSONObject(result);
        JSONObject objarray = objdatos.getJSONObject("Results");


        pais.setText(objarray.getString("Name"));


    }
}
