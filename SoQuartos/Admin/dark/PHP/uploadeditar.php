<?php
include 'config.php';
session_start();

$op = $_GET['op'];
if($op == 1){
  $idcasa=$_GET['id'];
$msg = "";
$senhorioid=$_SESSION['id'];



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

}elseif ($op==2) {
   $quarto=$_GET['id'];
$msg = "";
$senhorioid=$_SESSION['id'];



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
      $link = '../../../upload/quarto/'.$senhorioid.$quarto.'.'.$extension;
      
$link2 = 'upload/quarto/'.$senhorioid.$quarto.'.'.$extension;




      if(move_uploaded_file($_FILES[$nome]['tmp_name'], $link)){


        
        $sql = 'UPDATE quarto SET foto = "'.$link2.'" WHERE id='.$quarto.';';
        if (mysqli_query($conn,$sql) === TRUE) {
          $msg .= "Quarto registado com Sucesso.";
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
}

?>  