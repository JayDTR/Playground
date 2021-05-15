<?php
include 'config.php';
session_start();

$op = $_GET['op'];
if($op == 1){
 $msg = "";
$senhorioid=$_SESSION['id'];

$sql = 'SELECT id,mesemissao
FROM despesasgerais
WHERE id = ( SELECT MAX(id) FROM despesasgerais ) ;';
$result = $conn->query($sql);

if ($result->num_rows > 0) {

              while($row = $result->fetch_assoc()) {
             $iddespesa=$row['id'];
             $mes=$row['mesemissao'];
              }
}
 


  if(!empty($_FILES)){
    $today = date("Y-m-d H:i:s");
    $link = "";
    $n = 1;
//AQUI

    for($i = 0;$i<$n;$i++){
      $nome = "file".$i;
      $temp = $_FILES[$nome]['tmp_name'];

      $path_parts = pathinfo($_FILES[$nome]["name"]);
      $extension = $path_parts['extension'];
      //$link = "uploads/".$_FILES[$nome]["name"];
      $link = '../../../upload/despesas/'.$senhorioid.$iddespesa.$mes.'.'.$extension;
      
$link2 = 'upload/despesas/'.$senhorioid.$iddespesa.$mes.'.'.$extension;




      if(move_uploaded_file($_FILES[$nome]['tmp_name'], $link)){


        
        $sql = 'UPDATE despesasgerais SET foto = "'.$link2.'" WHERE id='.$iddespesa.';';
        if (mysqli_query($conn,$sql) === TRUE) {
          $msg .= "Despesa registada com Sucesso.";
          $val = 1;
        } else {
          $msg .= "Error: " . $sql . "<br>" . mysqli_error($conn);
          $val = 2;
        }      
      } else {
        $msg .= "".$link." NOT uploaded ...<br>";
        $val = 3;
      } 
    } 
  }
  $ir = array("val"=>$val,"msg"=>$msg);
  echo json_encode($ir);

}else if($op == 2){
$msg = "";
$senhorioid=$_SESSION['id'];

$sql = 'select max(id) as id from casa;';
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    

    
    
              while($row = $result->fetch_assoc()) {
             $idcasa=$row['id'];

              }
               
               }






  if(!empty($_FILES)){
    $today = date("Y-m-d H:i:s");
    $link = "";
    $n = 1;
//AQUI

    for($i = 0;$i<$n;$i++){
      $nome = "file".$i;
      $temp = $_FILES[$nome]['tmp_name'];

      $path_parts = pathinfo($_FILES[$nome]["name"]);
      $extension = $path_parts['extension'];
      //$link = "uploads/".$_FILES[$nome]["name"];
      $link = '../../../upload/casa/'.$senhorioid.$idcasa.'.'.$extension;
      
$link2 = 'upload/casa/'.$senhorioid.$idcasa.'.'.$extension;




      if(move_uploaded_file($_FILES[$nome]['tmp_name'], $link)){


        
        $sql = 'UPDATE casa SET foto = "'.$link2.'" WHERE id='.$idcasa.';';
        if (mysqli_query($conn,$sql) === TRUE) {
          $msg .= "casa registado com Sucesso.";
          $val = 1;
        } else {
          $msg .= "Error: " . $sql . "<br>" . mysqli_error($conn);
          $val = 2;
        }      
      } else {
        $msg .= "".$link." NOT uploaded ...<br>";
        $val = 3;
      } 
    } 
  }
  $ir = array("val"=>$val,"msg"=>$msg);
  echo json_encode($ir);

}else if($op == 3){
  $msg = "";
$senhorioid=$_SESSION['id'];

$sql = 'select max(id) as id from quarto;';
$result = $conn->query($sql);

if ($result->num_rows > 0) {

         while($row = $result->fetch_assoc()) {
             $idquarto=$row['id'];

          }
               
}






  if(!empty($_FILES)){
    $today = date("Y-m-d H:i:s");
    $link = "";
    $n = 1;
//AQUI

    for($i = 0;$i<$n;$i++){
      $nome = "file".$i;
      $temp = $_FILES[$nome]['tmp_name'];

      $path_parts = pathinfo($_FILES[$nome]["name"]);
      $extension = $path_parts['extension'];
      //$link = "uploads/".$_FILES[$nome]["name"];
      $link = '../../../upload/quarto/'.$senhorioid.$idquarto.'.'.$extension;
      
      $link2 = 'upload/quarto/'.$senhorioid.$idquarto.'.'.$extension;


      if(move_uploaded_file($_FILES[$nome]['tmp_name'], $link)){


        
        $sql = 'UPDATE quarto SET foto = "'.$link2.'" WHERE id='.$idquarto.';';
        if (mysqli_query($conn,$sql) === TRUE) {
          $msg .= "Quarto registado com Sucesso.";
          $val = 1;
        } else {
          $msg .= "Error: " . $sql . "<br>" . mysqli_error($conn);
          $val = 2;
        }      
      } else {
        $msg .= "".$link2." NOT uploaded ...<br>";
        $val = 3;
      } 
    } 
  }
  $ir = array("val"=>$val,"msg"=>$msg);
  echo json_encode($ir);
  
}

?>