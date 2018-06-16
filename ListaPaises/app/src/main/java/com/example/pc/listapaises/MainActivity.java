package com.example.pc.listapaises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    private ArrayList<Paises> ListaPaises =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvimgPaises = (ListView) findViewById(R.id.lvPaises);
        //Listener para el Evento Click
        lvimgPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, verInfo.class);
                Bundle b = new Bundle();
                b.putString( "CODE", ((Paises)a.getItemAtPosition(position)).getCode2());
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://services.groupkt.com/country/get/all",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("");


    }

    @Override
    public void processFinish(String result) throws JSONException {
     /*   JSONObject objdatos = new JSONObject(result);
        JSONArray objarray = objdatos.getJSONArray("RestResponse"); //RestResponse result

        JSONArray objPaises = objarray.getJSONArray(1);
        JSONArray objinfo = objPaises.getJSONArray(Integer.parseInt("result"));*/

        JSONObject objdatos = new JSONObject(result);
        JSONObject objarray = objdatos.getJSONObject("RestResponse");
        JSONArray objinfo = objarray.getJSONArray("result");

        /*for(int i=0; i< objarray.length();i++)
        {

             objdatos = objPaises.getJSONArray("result");
            //Paises data= Paises.JsonObjectsBuild(objPaises);
            ListaPaises = Paises.JsonObjectsBuild(objdatos);
             //Paises data= new Paises(objPaises.getString("name"),objPaises.getString("alpha2_code"),"http://www.geognos.com/api/en/countries/flag/"+objPaises.getString("alpha2_code").toString() +".png");
            //ListaPaises.add(data);

        }*/
        ArrayList<Paises> paises = Paises.JsonObjectsBuild(objinfo);
        AdaptadorPaises adpPaises = new AdaptadorPaises(this, (ArrayList<Paises>) paises);

        ListView listaPaises = (ListView) findViewById(R.id.lvPaises);
        listaPaises.setAdapter(adpPaises);

    }
}
