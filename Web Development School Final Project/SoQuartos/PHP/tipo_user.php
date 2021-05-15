<?php 
session_start();

 include 'config.php';

 $msg = "";

 $id = $_SESSION["id"];
  $op = $_GET["op"];
 $_SESSION["inquilino"] = 0;

 if ($op == 1) {
   $sql = "SELECT DISTINCT idinquilino FROM aluguer WHERE idinquilino = $id";

        

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

       
      $_SESSION["inquilino"] = 1;
        

        }

        $msg = $_SESSION["inquilino"];
        echo $msg;
 }

 else if ($op == 2) {
   $_SESSION["senhorio"] = 0;


  $sql = "SELECT idsenhorio FROM casa WHERE idsenhorio = $id ";

        

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

       
      $_SESSION["senhorio"] = 1;

        }

        $msg = $_SESSION["senhorio"];
        echo $msg;
 }


  

      
        mysqli_close($conn);
 


  ?> 