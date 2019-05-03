package com.syafiqmarzuki21.msyafiqmarzuki.kuismb6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class TampilKampus extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextKD;
    private EditText editTextNama;
    private  EditText editTextJenis;
    private EditText editTextAkreditas;

    private Button btnUpdate;
    private Button btnDelete;

    private String kd_kampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_kampus);

        Intent intent = getIntent();
        kd_kampus = intent.getStringExtra(Konfigurasi.EMP_KD_KAMPUS);

        editTextKD = (EditText) findViewById(R.id.editTextKD);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextJenis = (EditText) findViewById(R.id.editTextJenis);
        editTextAkreditas = (EditText) findViewById(R.id.editTextAkreditas);

        btnDelete = (Button) findViewById(R.id.buttonDelete);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        editTextKD.setText(kd_kampus);
        getKampus();

    }

    private void getKampus(){
        class GetKampus extends  AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilKampus.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showKampus(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_EMP,kd_kampus);
                return s;
            }
        }
        GetKampus gk = new GetKampus();
        gk.execute();
    }

    private void showKampus(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String kd_kampus = c.getString(Konfigurasi.TAG_KD_KAMPUS);
            String nm_kampus = c.getString(Konfigurasi.TAG_NM_KAMPUS);
            String jn_kampus = c.getString(Konfigurasi.TAG_JN_KAMPUS);
            String ak_kampus = c.getString(Konfigurasi.TAG_AK_KAMPUS);

            editTextKD.setText(kd_kampus);
            editTextNama.setText(nm_kampus);
            editTextJenis.setText(jn_kampus);
            editTextAkreditas.setText(ak_kampus);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateKampus(){
        final String nm_kampus = editTextNama.getText().toString().trim();
        final String jn_kampus = editTextJenis.getText().toString().trim();
        final String ak_kampus = editTextAkreditas.getText().toString().trim();

        class UpdateKampus extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilKampus.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilKampus.this,s,Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_EMP_KD_KAMPUS,kd_kampus);
                hashMap.put(Konfigurasi.KEY_EMP_NM_KAMPUS,nm_kampus);
                hashMap.put(Konfigurasi.KEY_EMP_JN_KAMPUS,jn_kampus);
                hashMap.put(Konfigurasi.KEY_EMP_AK_KAMPUS,ak_kampus);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateKampus uk = new UpdateKampus();
        uk.execute();

    }

    private void deleteKampus(){
        class DeleteKampus extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilKampus.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilKampus.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_DELETE_EMP, kd_kampus);
                return s;
            }
        }

        DeleteKampus dk = new DeleteKampus();
        dk.execute();

    }

    private void confirmDeleteKampus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Kampus ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteKampus();
                        startActivity(new Intent(TampilKampus.this,TampilSemua.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnUpdate){
            updateKampus();
            startActivity(new Intent(TampilKampus.this,TampilSemua.class));

        }

        if(v == btnDelete){
            confirmDeleteKampus();
        }
    }
}

