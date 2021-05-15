<?php 

	$servername = "localhost";
	$username = "root";
	$password = "";
	$bd = "soquartos";

	$conn = new mysqli($servername, $username, $password,$bd);

	
	mysqli_set_charset($conn,'utf8');

		// Check connection
		if($conn->connect_error) {
    	die("Connection failed: " . $conn->connect_error);
    	
                
		} 


         

 ?>