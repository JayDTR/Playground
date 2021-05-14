<?php 
include '../config.php';

$msg = "";

$idcasa = $_GET["idcasa"];



 $sql = "SELECT casa.morada FROM casa WHERE casa.id = $idcasa;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


          $msg .= "Morada:" . $row["morada"]. "<br>";



          }
          
         
        }





   $sql = "SELECT DISTINCT utilizador.nome AS senhorio FROM utilizador,aluguer,quarto,casa WHERE casa.idsenhorio = utilizador.id  AND casa.id = $idcasa;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


          $msg .= "<br>Senhorio:" . $row["senhorio"]. "<br>";



          }
          
         
        }








$valor = 0;

$msg .= "<br>Inquilinos:";


$inquilinos = array();

   $sql = "SELECT utilizador.nome AS inquilino FROM utilizador,aluguer,quarto,casa WHERE aluguer.idinquilino = utilizador.id AND aluguer.idquarto = quarto.id AND quarto.idcasa = casa.id AND casa.id = $idcasa;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


          if ($valor != 0) {

            $x = ",";
          }else{

            $x = "";
          }


          $inquilinos[] = $row["inquilino"];

          
           $msg .= $x . $inquilinos[$valor];

           $valor++;

          }
          
         
        }

        $valor = 1;

        $msg .= "<br><br>";


           $sql = "SELECT descricao FROM tipodespesas;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


          $msg .= "Despesa" . $valor. ":" . $row["descricao"] . "<br>";


             
             $valor++;

          }
          
         
        }

          
      echo $msg;


 mysqli_close($conn);
      
?>