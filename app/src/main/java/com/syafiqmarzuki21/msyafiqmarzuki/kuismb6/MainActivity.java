package com.syafiqmarzuki21.msyafiqmarzuki.kuismb6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextKD;
    private EditText editTextNama;
    private  EditText editTextJenis;
    private EditText editTextAkreditas;

    private Button btnDaftar;
    private Button btnView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextKD = (EditText) findViewById(R.id.editTextKD);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextJenis = (EditText) findViewById(R.id.editTextJenis);
        editTextAkreditas = (EditText) findViewById(R.id.editTextAkreditas);

        btnDaftar = (Button) findViewById(R.id.buttonDaftar);
        btnView = (Button) findViewById(R.id.buttonView);

        btnDaftar.setOnClickListener(this);
        btnDaftar.setOnClickListener(this);
        btnView.setOnClickListener(this);

    }

    private  void addKampus(){
        final String kd_kampus = editTextKD.getText().toString().trim();
        final String nm_kampus = editTextNama.getText().toString().trim();
        final String jn_kampus = editTextJenis.getText().toString().trim();
        final String ak_kampus = editTextAkreditas.getText().toString().trim();


         class  AddKampus extends AsyncTask<Void,Void,String> {
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(Void... v) {
                    HashMap<String,String> params = new HashMap<>();
                    params.put(Konfigurasi.KEY_EMP_KD_KAMPUS,kd_kampus);
                    params.put(Konfigurasi.KEY_EMP_NM_KAMPUS,nm_kampus);
                    params.put(Konfigurasi.KEY_EMP_JN_KAMPUS,jn_kampus);
                    params.put(Konfigurasi.KEY_EMP_AK_KAMPUS,ak_kampus);

                    RequestHandler rh = new RequestHandler();
                    String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                    return res;
                }
            }

            if (kd_kampus != null && nm_kampus != null && jn_kampus != null && ak_kampus != null){
                AddKampus ak = new AddKampus();
                ak.execute();
            }else{
                if(TextUtils.isEmpty(kd_kampus)){
                    editTextKD.setError("Kode Wajib Di Inputkan!");
                }

                if(TextUtils.isEmpty(nm_kampus)){
                    editTextNama.setError("Nama Wajib Di Inputkan!");
                }

                if(TextUtils.isEmpty(jn_kampus)){
                    editTextJenis.setError("Jenis Wajib Di Inputkan!");
                }

                if(TextUtils.isEmpty(ak_kampus)){
                    editTextNama.setError("Akreditasi Wajib Di Inputkan!");
                }
            }




    }
//
    @Override
    public void onClick(View v) {
        if (v == btnView){
            startActivity(new Intent(this,TampilSemua.class));
        } else{
            if(editTextKD.getText().toString().equals("")){
                editTextKD.setError("Wajib Diisi");
            }else if (editTextNama.getText().toString().equals("")){
                editTextNama.setError("Wajib Diisi");
            }else if (editTextJenis.getText().toString().equals("")){
                editTextJenis.setError("Wajib Diisi");
            }else if (editTextAkreditas.getText().toString().equals("")){
                editTextAkreditas.setError("Wajib Diisi");
            } else {
                if(v == btnDaftar){
                    addKampus();

                }

            }

        }
    }
}
