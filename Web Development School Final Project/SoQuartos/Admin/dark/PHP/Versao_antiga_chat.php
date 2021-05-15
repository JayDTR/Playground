<?php 
session_start();
include '../../../PHP/config.php';

$op = $_GET["op"];
$chat = $_GET["casa"];
$user = $_SESSION["login"];
$id_user = $_SESSION["id"];
$foto = $_SESSION["foto"];



$msg = "";


if ($op == 1) {


  $msg2 = $_GET["msg"];

$t=time();

date("Y-m-d H:i:s",$t);


$DataHora = date("Y-m-d H:i:s",$t);


if (!$conn) {
    die("Erro: " . mysqli_connect_error());
  }



$sql = "insert into chat(id_user,user,texto,idconversa,DataHora,foto) values ('.$id_user.','".$user."','".$msg2."','.$chat.','".$DataHora."','".$foto."');";
            


            if ($conn->query($sql) === TRUE) {

              echo "<br>Sucess";
        
      } else{


        echo "<br>Erro: " . $sql . "<br>" . $conn->error;
      }

  $conn->close(); 

         
  
              
}else{
      
  if (!$conn) {
    die("Erro: " . mysqli_connect_error());
  }



  $sql = 'select user,texto,DataHora,foto from chat where idconversa = '.$chat.';';
            $result = $conn->query($sql);
  
       $msg .= '<div class="container-fluid" style="height:650px;">';

       $msg .= '<div class="chat-right-aside">
                                    <div class="chat-main-header">
                                        <div class="p-20 b-b">
                                            <h3 class="box-title">Chat Message</h3>
                                        </div>
                                    </div>
                                    <div class="chat-rbox">
                                        <ul class="chat-list p-20">
                                            ';
  
            if ($result->num_rows > 0) {
    
              while($row = $result->fetch_assoc()) {
           
               $msg.= '      <!--chat Row -->
                                            <li>
                                                <div class="chat-img"><img src="../../'.$row["foto"].'" alt="user" /></div>
                                                <div class="chat-content">
                                                    <h5>'.$row["user"].'</h5>
                                                    <div class="box bg-light-info">'.$row["texto"].'</div>
                                                </div>
                                                <div class="chat-time">'.$row["DataHora"].'</div>
                                            </li>
                                            <!--chat Row -->';

               

          /*

             $msg .= '<p>'.$row["DataHora"].':'.$row["user"].':'.$row["texto"].'</p>';
             */
              }
              } 

              $msg .= '</ul>
                                    </div>
                                    
                                </div>
                                <!-- .chat-right-panel -->
                            </div>
                            <!-- /.chat-row -->
                        </div>
                    </div>
                </div>


                ';


              

                  echo $msg;
                $conn->close();


}




           



 ?>