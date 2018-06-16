package com.example.pc.listapaises;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paises {
    private String name;
    private String code2;
    private String url;
    //http://www.geognos.com/api/en/countries/flag/IS.png

    public Paises(JSONObject a) throws JSONException {
        name = a.getString("name").toString();
        code2 = a.getString("alpha2_code").toString();
        url= "http://www.geognos.com/api/en/countries/flag/"+a.getString("alpha2_code").toString() +".png";
    }


/*
    public Paises(String name, String code2, String url) {
        this.name = name;
        this.code2 = code2;
        this.url = url;
    }*/

    public String getName() {
        return name;
    }

    public String getCode2() {
        return code2;
    }

    public String getUrl() {
        return url;
    }

    public static ArrayList<Paises> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Paises> listaPaises = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            listaPaises.add(new Paises(datos.getJSONObject(i)));
        }
        return listaPaises;

    }
}
