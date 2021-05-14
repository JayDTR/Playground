<?php
session_start();
include 'config.php';
$op = $_GET["op"];
if ($op == 1) {
$idcasa = $_GET["idcasa"];
$price = $_GET["price"];
$mes = $_GET["mes"];
$limite = $_GET["limite"];
$tipodespesa = $_GET["tipodespesa"];
$msg="";
if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}


			$sql = "INSERT INTO despesasgerais(idcasa,idtipodespesa,datalimite,mesemissao,valor,foto,idestado) VALUES (".$idcasa.",".$tipodespesa.", '".$limite."','".$mes."',".$price.",'','2'); ";

			if ($conn->query($sql) === TRUE) {
				$msg= "Despesa Adicionada Com Sucesso.";
			} else {
				$msg= "Erro: " . $sql . "<br>" . $conn->error;
			}

	echo $msg;		
	$conn->close();		
}

	


  ?>