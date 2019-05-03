
<?php 
 
 /*
 
 penulis: Muhammad yusuf
 website: https://www.kodingindonesia.com/
 
 */
 
 //Mendapatkan Nilai ID
 $kd_kampus = $_GET['kd_kampus'];
 
 //Import File Koneksi Database
 require_once('config.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tblkampus WHERE kd_kampus=$kd_kampus;";
 
 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Kampus';
 }else{
 echo 'Gagal Menghapus Kampus';
 }
 
 mysqli_close($con);
 ?>