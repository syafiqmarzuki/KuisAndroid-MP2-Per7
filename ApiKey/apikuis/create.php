<?php
//Import File Koneksi database
		require_once('config.php');
 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$kd_kampus = $_POST['kd_kampus'];
		$nm_kampus = $_POST['nm_kampus'];
		$jn_kampus = $_POST['jn_kampus'];
		$ak_kampus = $_POST['ak_kampus'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tblkampus (kd_kampus,nm_kampus,jn_kampus,ak_kampus) VALUES ('$kd_kampus','$nm_kampus','$jn_kampus','$ak_kampus')";
		
		
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
		echo 'Berhasil Menambahkan Kampus';
		}else{
		echo 'Gagal Menambahkan Kampus';
		}
		
		mysqli_close($con);
	}
?>
	