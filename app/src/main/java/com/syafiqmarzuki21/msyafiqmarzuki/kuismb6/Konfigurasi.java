package com.syafiqmarzuki21.msyafiqmarzuki.kuismb6;

public class Konfigurasi {
    public static final String URL_ADD="http://192.168.43.136/apikuis/create.php";
    public static final String URL_GET_ALL = "http://192.168.43.136/apikuis/readall.php";
    public static final String URL_GET_EMP = "http://192.168.43.136/apikuis/read.php?kd_kampus=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.136/apikuis/edit.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.136/apikuis/delete.php?kd_kampus=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_KD_KAMPUS = "kd_kampus";
    public static final String KEY_EMP_NM_KAMPUS = "nm_kampus";
    public static final String KEY_EMP_JN_KAMPUS = "jn_kampus";
    public static final String KEY_EMP_AK_KAMPUS = "ak_kampus";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_KD_KAMPUS = "kd_kampus";
    public static final String TAG_NM_KAMPUS = "nm_kampus";
    public static final String TAG_JN_KAMPUS = "jn_kampus";
    public static final String TAG_AK_KAMPUS = "ak_kampus";

    //kd_kampus karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_KD_KAMPUS = "emp_KD_kampus";
}
