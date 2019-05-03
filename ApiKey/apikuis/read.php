<?php 
	
	//Mendapatkan Nilai Dari Variable kd_kampus Pegawai yang ingin ditampilkan
	$kd_kampus = $_GET['kd_kampus'];
	
	//Importing database
	require_once('config.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai kd_kampus
	$sql = "SELECT * FROM tblkampus WHERE kd_kampus=$kd_kampus";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
		"kd_kampus"=>$row['kd_kampus'],
		"nm_kampus"=>$row['nm_kampus'],
		"jn_kampus"=>$row['jn_kampus'],
		"ak_kampus"=>$row['ak_kampus']
		));
 
	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>