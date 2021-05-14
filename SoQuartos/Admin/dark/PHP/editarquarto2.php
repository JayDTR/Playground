<?php
session_start();
include 'config.php';

$idquarto=$_GET["quarto"];
$senhorioid = $_SESSION["id"];
$price = $_GET["price"];
$wc = $_GET["wc"];
$desc = $_GET["desc"];
$tipocama = $_GET["tipocama"];


if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}

			$sql = 'UPDATE quarto SET preco = "'.$price.'", wcprivado='.$wc.', descricao="'.$desc.'", idcama="'.$tipocama.'"  WHERE id='.$idquarto.';';

			if ($conn->query($sql) === TRUE) {
				echo "Quarto registado com sucesso";
			} else {
				echo "Erro: " . $sql . "<br>" . $conn->error;
			}
	$conn->close();		

  ?>