<?php
session_start();
include 'config.php';

$idcasa=$_GET["casa"];
$senhorioid = $_SESSION["id"];
$tipocasa = $_GET["tipocasa"];
$internet = $_GET["selectinternet"];


if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}

			$sql = 'UPDATE casa SET internet = "'.$internet.'", idtipocasa='.$tipocasa.'  WHERE id='.$idcasa.';';

			if ($conn->query($sql) === TRUE) {
				echo "Casa registado com sucesso";
			} else {
				echo "Erro: " . $sql . "<br>" . $conn->error;
			}
	$conn->close();		

  ?>