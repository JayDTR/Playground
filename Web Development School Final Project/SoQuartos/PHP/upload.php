<?php
include 'config.php';
session_start();

$op = $_GET['op'];
if($op == 1){
  $msg = "";

  if(!empty($_FILES)){
    $today = date("Y-m-d H:i:s");
    $link = "";
    $n = 1;
    $utilizador = $_SESSION['id'];
    for($i = 0;$i<$n;$i++){
      $nome = "file".$i;
      $temp = $_FILES[$nome]['tmp_name'];

      $path_parts = pathinfo($_FILES[$nome]["name"]);
      $extension = $path_parts['extension'];
      //$link = "uploads/".$_FILES[$nome]["name"];
      $link = '../upload/user/'.$utilizador.".".$extension;
      
$link2 = 'upload/user/'.$utilizador.".".$extension;




      if(move_uploaded_file($_FILES[$nome]['tmp_name'], $link)){


        
        $sql = 'UPDATE utilizador SET foto = "'.$link2.'" WHERE id='.$utilizador.';';
        if (mysqli_query($conn,$sql) === TRUE) {
          $msg = "Utilizador registado com Sucesso.";
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
if ($link2 != "") {
 $_SESSION["foto"] = $link2;
}
  
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
      $link = '../../upload/casa/'.$senhorioid.$idcasa.'.'.$extension;
      
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
  
}

?>