<?php
session_start();
include 'config.php';
$op = $_GET["op"];
if ($op == 1) {
$senhorioid = $_SESSION["id"];
$nif = $_GET["nifinq"];
$idquarto = $_GET["idquarto"];
$entrada = $_GET["entrada"];
$saida = $_GET["saida"];
$idinq = 0;
$valor = 0;

if (!$conn) {
		die("Erro: " . mysqli_connect_error());
	}


    $sql = "SELECT utilizador.id FROM utilizador WHERE utilizador.nif = $nif";

    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
        	$idinq = $row['id'];
        }
    }

			$sql2 = "INSERT INTO aluguer VALUES ('',".$idquarto.",".$idinq.", '".$entrada."','".$saida."'); ";

			if ($conn->query($sql2) === TRUE) {
			//	echo "Aluguer registado com sucesso";
				$date1 = $entrada;
				$date2 = $saida;

				$ts1 = strtotime($date1);
				$ts2 = strtotime($date2);

				$year1 = date('Y', $ts1);
				$year2 = date('Y', $ts2);

				$month1 = date('m', $ts1);
				$month2 = date('m', $ts2);

				$diff = (($year2 - $year1) * 12) + ($month2 - $month1) + 1;

				$sql3 = "SELECT *,aluguer.id as idaluguer FROM aluguer,quarto WHERE aluguer.idquarto= quarto.id and aluguer.idinquilino = $idinq and aluguer.idquarto = $idquarto";

			    $result = $conn->query($sql3);
			    if ($result->num_rows > 0) {
			        while($row = $result->fetch_assoc()) {

			        	for ($i=0; $i < $diff ; $i++) {

			        		$sql4 = "INSERT INTO despesa_aluguer (idaluguer, valor,estado)
                    VALUES ('".$row['idaluguer']."', '".number_format($row['preco'])."',2)";

		                     if ($conn->query($sql4) === TRUE) {

		                     	$valor = 1;
						
					        } else {
						echo "Erro: " . $sql . "<br>" . $conn->error;
					}
					}

                   
		}    
			} else {
				echo "Erro: " . $sql . "<br>" . $conn->error;
			}
	$conn->close();		
	}
}


 if ($valor == 1) {
 	
 	echo "Aluguer registado com sucesso";
 }
	


  ?>