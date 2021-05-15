<?php
session_start();
include 'config.php';
$op = $_GET["op"];
if ($op == 1) {
$senhorioid = $_SESSION["id"];
$idcasa = $_GET["idcasa"];
$price = $_GET["price"];
$rwc = $_GET["rwc"];
$desc = $_GET["desc"];
$tipocama = $_GET["tipocama"];

if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}

			$sql = "INSERT INTO quarto VALUES ('',".$idcasa.",".$price.", '".$desc."','".$rwc."','',".$tipocama."); ";

			if ($conn->query($sql) === TRUE) {
				//echo "Casa registado com sucesso";
			} else {
				echo "Erro: " . $sql . "<br>" . $conn->error;
			}
	$conn->close();		
}

	


  ?>