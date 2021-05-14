<?php 

include 'config.php';
  
  $id = $_SESSION["id"];
  $idcasa = $_GET["id"];

 $sql = "SELECT casa.id as idcasa, casa.morada as morada FROM CASA WHERE casa.idsenhorio = $id;";
  $result = $conn->query($sql);
  $msg = "";
  
  if ($result->num_rows > 0) {
    // output data of each row

    while($row = $result->fetch_assoc()) {
     if ($row["idcasa"]  == $idcasa) {
         $msg .= "<a href='casa_main.php?id=".$row['idcasa']."' class='list-group-item active'>".$row['morada']."</a>";
      }else{
         $msg .= "<a href='casa_main.php?id=".$row['idcasa']."' class='list-group-item'>".$row['morada']."</a>";
      }
  }

  }
  echo $msg;
  $conn->close();





 ?>