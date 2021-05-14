<?php 

include 'config.php';

 $msg = "";


 if ($op==1) {

$casa = $_GET['idcasa'];


  $sql = "SELECT id,foto FROM casa where  casa.id=".$casa.";";

        

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

          $msg .= '<img style="width:160px;margin-right:35%" id="perfilimg" src="../../'.$row['foto'].'" class="avatar img-circle img-thumbnail" alt="avatar">';
          

        }

      	echo $msg;
        mysqli_close($conn);
 
}elseif ($op==2) {
	$quarto = $_GET['id'];


  $sql = "SELECT id,foto FROM quarto where  quarto.id=".$quarto.";";

        

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

          $msg .= '<img style="width:160px;margin-right:35%" id="perfilimg" src="../../'.$row['foto'].'" class="avatar img-circle img-thumbnail" alt="avatar">';
          

        }

      	echo $msg;
        mysqli_close($conn);
	
}

  ?> 

 
