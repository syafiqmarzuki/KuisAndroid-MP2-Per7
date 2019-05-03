<?php 
 
 /*
 
 penulis: Muhammad yusuf
 website: https://www.kodingindonesia.com/
 
 */
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//MEndapatkan Nilai Dari Variable 
		$kd_kampus = $_POST['kd_kampus'];
		$nm_kampus = $_POST['nm_kampus'];
		$jn_kampus = $_POST['jn_kampus'];
		$ak_kampus = $_POST['ak_kampus'];
		
		//import file koneksi database 
		require_once('config.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tblkampus SET nm_kampus = '$nm_kampus', jn_kampus = '$jn_kampus', ak_kampus = '$ak_kampus' WHERE kd_kampus = $kd_kampus;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
		echo 'Berhasil Update Data Kampus';
		}else{
		echo 'Gagal Update Data Kampus';
		}
		
		mysqli_close($con);
	}
?>