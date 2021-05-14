<?php  
session_start();


  include '../../../PHP/config.php';
    $op = $_GET["op"];
  

    $last_ID = "";
    $chat = $_GET["casa"];
    $user = $_SESSION["login"];
    $id_user = $_SESSION["id"];
    $foto = $_SESSION["foto"];

      $msg = "";

      if ($op == 2) {
 
  
        if (!$conn) {
        die("Erro: " . mysqli_connect_error());
        }



            $sql = 'select id,id_user,user,texto,DataHora,foto from chat where idconversa = '.$chat.';';
            $result = $conn->query($sql);


            $msg .= '<tr><div id="content">';
  
            if ($result->num_rows > 0) {
    
              while($row = $result->fetch_assoc()) {

                $last_ID = $row["id"];

                if ($id_user != $row["id_user"]) {
                    $msg .= '<li id = "'.$row["id"].'">
                                                <div class="chat-img"><img src="../../'.$row["foto"].'" alt="user" /></div>
                                                <div class="chat-content">   
                                                    <div class="box bg-light-inverse">'.$row["texto"].'</div>
                                                    <h6>'.$row["user"].'</h6>
                                                </div>
                                                <div class="chat-time">'.$row["DataHora"].'</div>
                                            </li>';
                }else{

                    $msg .= '<li id = "'.$row["id"].'" class="reverse">
                                                <div class="chat-content">
                                                    <div class="box bg-light-info">'.$row["texto"].'</div>
                                                     <h6>'.$row["user"].'</h6>
                                                </div>
                                                <div class="chat-img"><img src="../../'.$row["foto"].'" alt="user" /></div>
                                                <div class="chat-time">'.$row["DataHora"].'</div>
                                            </li>';

                }
         

         }
              } 

              $msg .= '</div>';

        /*
                <div class="card-body b-t"></div>                   
 
        */



                       echo $msg;
                      $conn->close();


} 
else if ($op == 1) {


  $msg2 = $_GET["msg"];

  $msg = "";

  $msg .= $msg2 . "|";

  if ($msg2 != "") {
    $t=time();

date("Y-m-d H:i:s",$t);


$DataHora = date("m-d H:i",$t);


if (!$conn) {
    die("Erro: " . mysqli_connect_error());
  }



            $sql = "insert into chat(id_user,user,texto,idconversa,DataHora,foto) values ('.$id_user.','".$user."','".$msg2."','.$chat.','".$DataHora."','".$foto."');";
            


                 if ($conn->query($sql) === TRUE) {

                    
            
        
               } else{


                   $msg .= "<br>Erro: " . $sql . "<br>" . $conn->error;
                }

          echo $msg;

          $conn->close(); 

          }            
        }



?>