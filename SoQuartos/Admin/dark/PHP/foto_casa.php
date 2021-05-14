<?php 

include 'config.php';

 $msg = "";
$idcasa = $_GET['idcasa'];

  $sql = "SELECT id,foto FROM casa where  casa.id=".$idcasa.";";

        

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

          $msg .= '<img width="160px" id="perfilimg" src="../../'.$row["foto"].'" class="avatar img-circle img-thumbnail" alt="avatar">';
          

        }

      	echo $msg;
        mysqli_close($conn);
 


  ?> 