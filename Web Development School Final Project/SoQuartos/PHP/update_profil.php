<?php
session_start();
include 'config.php';

$msg = "";

$id = $_SESSION["id"];
$nome = $_GET["nome"];
$email = $_SESSION["email"];
$telefone = $_GET["telefone"];
$BI = $_GET["BI"];
$psw = $_GET["psw"];
$psw2 = $_GET["psw2"];




if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}

 $sql = "UPDATE utilizador SET nome = '$nome', telefone = $telefone,nif = $BI,password = '$psw' WHERE id = $id; ";
	
	
			

			if ($conn->query($sql) === TRUE) {
				$msg .= "Perfil atualizado";
			} else {
				$msg .= "Erro: " . $sql . "<br>" . $conn->error;
			}

	echo $msg;

	$conn->close();		


	


  ?>