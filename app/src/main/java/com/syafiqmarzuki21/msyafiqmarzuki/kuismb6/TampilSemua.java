package com.syafiqmarzuki21.msyafiqmarzuki.kuismb6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemua extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void showKampus(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String kd_kampus = jo.getString(Konfigurasi.TAG_KD_KAMPUS);
                String nm_kampus = jo.getString(Konfigurasi.TAG_NM_KAMPUS);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Konfigurasi.TAG_KD_KAMPUS,kd_kampus);
                employees.put(Konfigurasi.TAG_NM_KAMPUS,nm_kampus);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilSemua.this, list, R.layout.list_item,
                new String[]{Konfigurasi.TAG_KD_KAMPUS,Konfigurasi.TAG_NM_KAMPUS},
                new int[]{R.id.kd_kampus, R.id.nm_kampus});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSemua.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showKampus();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilKampus.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empKD_Kampus = map.get(Konfigurasi.TAG_KD_KAMPUS).toString();
        intent.putExtra(Konfigurasi.EMP_KD_KAMPUS,empKD_Kampus);
        startActivity(intent);
    }

}
