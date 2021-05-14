<?php 
include 'config.php';
 


if ($op==1) {
  $casa = $_GET['idcasa'];
$sql = "select id, internet from casa where id=".$casa;
            $result = $conn->query($sql);
$msg = " <select class='form-control form-control-lg form-control-a' style='width:60%'' id='einternet' name='einternet' required>";

            if ($result->num_rows > 0) {
              while($row = $result->fetch_assoc()) {
               
                if ($row['internet']==1) {

                  $msg .= "<option value ='1' selected='selected'>sim</option>";
                  $msg .= "<option value ='0'>não</option>";
                }else{
                   $msg .= "<option value ='1' >sim</option>";
                  $msg .= "<option value ='0' selected='selected'>não</option>";
                }
            
              }

              
  }
  
              $msg .= "</select>"; 
              $msg .= '<input type="number" id="idcasa" style="visibility: hidden;" value='.$casa.'>';
                  echo $msg;
                $conn->close();

}elseif ($op==2) {

  $casa = $_GET['idcasa'];
$tipo="";

  $sql = "select id,idtipocasa as tipo from casa where id=".$casa.";";
  $result = $conn->query($sql);
  if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
     $tipo=$row['tipo']; 
    }

  };

  $sql = "select id,descricao from tipocasa";
  $result = $conn->query($sql);
  
  $msg = "<select id='etipocasa' style='width:60%' class='form-control form-control-lg form-control-a'>";

  if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      if ($tipo==$row['id']) {
        $msg .= "<option value =" . $row["id"] ." selected='selected'>" . $row["descricao"]. "</option>";
      }else{
       $msg .= "<option value =" . $row["id"] .">" . $row["descricao"]. "</option>"; 
      }
      
    }
  } else {
    $msg .= "-";
  }
  $msg .= "</select>";
  
  echo $msg;
  $conn->close();



  
}



           ?>  