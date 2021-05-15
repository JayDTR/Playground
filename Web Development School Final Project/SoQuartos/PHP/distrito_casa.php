<?php 
    include 'PHP/config.php';

  
  $sql = "select id,descricao from distrito";
  $result = $conn->query($sql);
  
  

  $msg = "<select id='rdistritocasa' style='width:60%' class='form-control form-control-lg form-control-a'>";
  
  
  if ($result->num_rows > 0) {
    // output data of each row

    
    
    while($row = $result->fetch_assoc()) {
      $msg .= "<option value =" . $row["id"] .">" . $row["descricao"]. "</option>";
    }
  } else {
    $msg .= "-";
  }
  $msg .= "</select>";

  
  echo $msg;
  $conn->close();


 ?>