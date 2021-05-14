<?php
session_start();
include 'config.php';


$iddespesa = $_GET["iddespesa"];
$idaluguer = $_GET["idaluguer"];
$x = 0;




                 
						$sql = "UPDATE despesa_aluguer SET estado = 1 WHERE iddespesa = $iddespesa AND idaluguer = $idaluguer;";


            
                        $result = $conn->query($sql);

                  if ($conn->query($sql) === TRUE) {
                       

                         $sql1 = "SELECT * from despesa_aluguer where iddespesa = $iddespesa and (estado=2 OR estado=3)";

                            $result = $conn->query($sql1);

                              if ($result->num_rows > 0) {
                                 
                                 $x = 1;


                              }else{
                                $sql = "UPDATE despesasgerais SET idestado = 1 WHERE id= $iddespesa";
                                $result = $conn->query($sql1);

                                if ($conn->query($sql) === TRUE) {

                                  echo "Faturas Parciais Pagas";
                                  $x = 2;

                                }

                              }


                    } else {
                        $x = 0;
                    }  


                if ($x == 1) {
                    echo "Fatura paga!";
                  }else if ($x==0){
                    echo "Erro!";
                  }


	$conn->close();		

  ?>