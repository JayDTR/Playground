<?php 
include 'config.php';

$msg = "";

$id = $_SESSION["id"];
  $sql = "SELECT casa.id as id, casa.morada FROM CASA WHERE casa.idsenhorio = $id;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

           $msg.= '<li> 
                        <a  href="gestao_quartos_inquilino.php?id='.$row["id"].'" aria-expanded="false"><i><img style="height: 30px;width: 30px;" src="icone/casa.png"></i><span class="hide-menu">'.$row["morada"].'</span></a>
                            
                   </li>';
           

          }
          echo $msg;
          mysqli_close($conn);
        }

      
?>