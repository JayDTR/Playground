<?php
session_start();
include 'config.php';
$op = $_GET["op"];
if ($op == 1) {
	$senhorioid = $_SESSION["id"];
$tipocasa = $_GET["tipocasa"];
$morada = $_GET["morada"];
$selectinternet = $_GET["selectinternet"];
$distrito = $_GET["distrito"];


if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}

			$sql = "INSERT INTO casa VALUES ('','.$tipocasa.', '".$senhorioid."', '".$morada."','.$distrito.','.$selectinternet.',''); ";

			if ($conn->query($sql) === TRUE) {
				echo "Casa registado com sucesso";
			} else {
				echo "Erro: " . $sql . "<br>" . $conn->error;
			}
	$conn->close();		
}

	


  ?>