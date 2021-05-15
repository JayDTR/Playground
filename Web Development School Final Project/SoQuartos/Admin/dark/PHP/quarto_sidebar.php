<?php 

include 'config.php';
  
  $id = $_SESSION["id"];
  $idcasa = $_GET["idcasa"];
  $idq = $_GET["idq"];
  $op = $_SESSION["op_tipo_utilizador"];

 $sql = "SELECT Quarto.id as IDQuarto,Quarto.foto as foto,Quarto.preco as preco, casa.idtipocasa FROM quarto, casa where quarto.idcasa=casa.id and casa.id=".$idcasa.";";
  $result = $conn->query($sql);
  $msg = "";
  $i = 1;  
  if ($result->num_rows > 0) {
    // output data of each row

    while($row = $result->fetch_assoc()) {

      if ($op == 0) {
          if ($row["IDQuarto"]  == $idq) {
         $msg .= "<a href='quarto_main.php?idq=".$row['IDQuarto']."&idcasa=".$idcasa."' class='list-group-item active'>Quarto $i</a>";
      }else{
         $msg .= "<a href='quarto_main.php?idq=".$row['IDQuarto']."&idcasa=".$idcasa."' class='list-group-item'>Quarto $i</a>";
      }
         }else{

             if ($row["IDQuarto"]  == $idq) {
         $msg .= "<a href='quarto_main.php?idq=".$row['IDQuarto']."&idcasa=".$idcasa."' class='list-group-item active'>Quarto $i</a>";
      }else{
         $msg .= "<a href='#' class='list-group-item'>Quarto $i</a>";
      }


         }
    
      $i++;

    }
}     
  

  
  echo $msg;
  $conn->close();





 ?>