<?php 
include 'config.php';
 


if ($op==1) {
  $quarto = $_GET['id'];
$sql = "select id, descricao,preco from quarto where id=".$quarto;
            $result = $conn->query($sql);
$msg = "";

            if ($result->num_rows > 0) {
              while($row = $result->fetch_assoc()) {
                  $msg .= '<br><label for="fname">Insira uma breve descrição</label><br>
        <input style="width:60%" type="text" id="edesc" name="desc" required value="'.$row["descricao"].'"><br>
    <br>
    <label for="lname">Insira o preço</label><br>
    <input style="width:20%" type="number" id="eprice" name="price" value="'.$row["preco"].'" required>€<br>';
                  
              
              }

           }
  
              $msg .= '<input type="number" id="idquarto" style="visibility: hidden;" value='.$quarto.'>';
                  echo $msg;
                $conn->close();

}elseif ($op==2) {

 $quarto = $_GET['id'];
$cama="";

  $sql = "select id,idcama as cama from quarto where id=".$quarto.";";
  $result = $conn->query($sql);
  if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
     $cama=$row['cama']; 

    }

  };

  $sql = "select id,descricao from tipocama";
  $result = $conn->query($sql);
  
  $msg = "<select id='etipocama' style='width:60%' class='form-control form-control-lg form-control-a'>";

  if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      if ($cama==$row['id']) {
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

}elseif ($op==3) {
$quarto = $_GET['id'];
$sql = "select id, wcprivado as wc from quarto where id=".$quarto;
            $result = $conn->query($sql);
$msg = " <select class='form-control form-control-lg form-control-a' style='width:60%'' id='ewc' name='einternet' required>";

            if ($result->num_rows > 0) {
              while($row = $result->fetch_assoc()) {
               
                if ($row['wc']==1) {

                  $msg .= "<option value ='1' selected='selected'>sim</option>";
                  $msg .= "<option value ='0'>não</option>";
                }else{
                   $msg .= "<option value ='1' >sim</option>";
                  $msg .= "<option value ='0' selected='selected'>não</option>";
                }
            
              }

            }
  
              $msg .= "</select>"; 
              $msg .= '<input type="number" id="idquarto" style="visibility: hidden;" value='.$quarto.'>';
                  echo $msg;
                $conn->close();
}

?>  