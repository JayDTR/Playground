<?php
session_start();
include 'config.php';
$op = $_GET["op"];
if ($op == 1) {
$iddespesa = $_GET["iddespesa"];
$valor = $_GET["valor"];
$idcasa = $_GET["idcasa"];
$x = 0;

$inquilinos_casa = [];

                 
						$sql = "SELECT DISTINCT aluguer.id AS aluguer FROM aluguer,utilizador,casa,quarto WHERE aluguer.idquarto = quarto.id AND quarto.idcasa = casa.id AND casa.id = $idcasa;";
                        $result = $conn->query($sql);

                        if ($result->num_rows > 0) {
                         while($row = $result->fetch_assoc()) {


                         $inquilinos_casa[$x] = $row["aluguer"];

                           $x++;
                        

                         }
           }

           

if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}

		  for ($y=0; $y < count($inquilinos_casa); $y++) { 
                      $sql1 = "INSERT INTO despesa_aluguer (idaluguer, iddespesa, valor,estado)
                    VALUES ('".$inquilinos_casa[$y]."', '".$iddespesa."', '".number_format($valor,2)."',2)";

                    if ($conn->query($sql1) === TRUE) {
                        $x = 1;
                    } else {
                        $x = 0;
                    }  

                  }

                  echo "alert(".$x.")";

                  if ($x == 1) {
                    echo "Fatura emitida!";
                  }else{
                    echo "Erro!";
                  }

	$conn->close();		
}

	


  ?>