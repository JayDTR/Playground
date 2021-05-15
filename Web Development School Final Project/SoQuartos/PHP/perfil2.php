<?php 

include 'config.php';

 $msg = "";
$utilizador = $_SESSION['id'];

  $sql = "SELECT id,foto FROM utilizador where  utilizador.id=".$utilizador.";";

        

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

          $msg .= '<img width="160px" id="perfilimg" src="'.$row['foto'].'" class="avatar img-circle img-thumbnail" alt="avatar">';
          

        }

      	echo $msg;
        mysqli_close($conn);
 


  ?> 