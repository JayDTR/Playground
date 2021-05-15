 <?php 
include 'config.php';
$idcasa = $_GET["idcasa"];
$idquarto = $_GET["idq"];
$op = $_SESSION["op_tipo_utilizador"];
$descricao = array();
$inquilinos_casa = array();
$valor = 0;
$valor2 = 0;
$nquartos = 0;
$h = 0;
$p = 0;
$array_iddespesa = array();
$array_idaluguer = array();
$array_renda = array();




 if (isset($_GET['pageno'])) {
            $pageno = $_GET['pageno'];
        } else {
            $pageno = 1;
        }

        $msg = "";

        $no_of_records_per_page = 4;
        $offset = ($pageno-1) * $no_of_records_per_page;


        $total_pages_sql = "SELECT COUNT(*) from (SELECT DISTINCT despesa_aluguer.id FROM despesa_aluguer,aluguer WHERE despesa_aluguer.idaluguer = aluguer.id AND aluguer.idquarto = $idquarto) as temp;";
        $result = mysqli_query($conn,$total_pages_sql);

        if (!$total_pages_sql) {
            printf("Error: %s\n", mysqli_error($conn));
            exit();
            }

        $total_rows = mysqli_fetch_array($result)[0];
        $total_pages = ceil($total_rows / $no_of_records_per_page);


        $sql = "SELECT id from despesa_aluguer WHERE estado = 1;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

           
        $id_fatura[$p] = $row["id"];
                        
           $p++;
         }
          
         
      }else{

        $id_fatura[$p] = "";
        $p++;
      }







           // muda isto     
                 
  $sql = "SELECT despesa_aluguer.iddespesa as id_tipo_despesa,despesa_aluguer.id AS id_des_aluguer,despesasgerais.id as id_despesas,despesa_aluguer.idaluguer AS idaluguer,despesa_aluguer.valor AS valor, despesa_aluguer.iddespesa AS iddespesa, aluguer.idquarto AS idquarto,
   despesasgerais.datalimite AS limite,despesasgerais.foto as foto,despesasgerais.idestado,tipodespesas.descricao AS tdespesa ,mes.id,mes.descricao AS MesEmissao, estadodespesa.descricao as estado
   FROM despesa_aluguer,aluguer,despesasgerais,tipodespesas,mes,estadodespesa
   WHERE despesa_aluguer.idaluguer=aluguer.id AND  despesa_aluguer.iddespesa = despesasgerais.id AND despesasgerais.idtipodespesa = tipodespesas.id AND despesasgerais.mesemissao = mes.id 
   AND despesasgerais.idtipodespesa = tipodespesas.id AND despesa_aluguer.estado = estadodespesa.id AND aluguer.idquarto = $idquarto order by despesasgerais.idestado desc,mes.id desc LIMIT $offset, $no_of_records_per_page ;";

   //

    $result = $conn->query($sql);
    $rsp = "";
    $valor = 1;
   
    

    if ($result->num_rows > 0) {
        // output data of each row
       $rsp.="<table style='font-size:80%'>";
        $rsp.="<th>Mes Emissão</th><th>Tipo Despesa</th><th>Valor</th><th>Data Limite</th><th>Estado</th><th></th><th></th>";

         

        while($row = $result->fetch_assoc()) {   
                

                 if ($op == 0) {



                  $rsp .= '<tr><td>'.$row['MesEmissao'].'</td><td>'.$row['tdespesa'].'</td><td>'.$row['valor'].'€</td><td>'.$row['limite'].'</td><td>'.$row['estado'].'</td>';


                     $array_iddespesa[$h] = $row['iddespesa'];
                     $array_idaluguer[$h] = $row['idaluguer'];


                       $flag = true;

            for ($i=0; $i < count($id_fatura) ; $i++) { 
               
               if ($id_fatura[$i] == $row['id_des_aluguer'] ) {
                 $flag = false;
                 break;
               }else{
                   $flag = true;
                   

               }

            }

             
                                
                   

                 if ($row["foto"] == "") {

                         if ($flag == false) {

             $rsp .= '<td> <button style="font-size:80%" class="btn btn-secondary" onclick="return_false()");">Ver Fatura</button></td><td><button id = "pagar_fatura" style="font-size:80%" class="btn btn-secondary" onclick="emitir_alerta()">Pago</button></td></tr>';

                
          
            }else{

              $rsp .= '<td> <button style="font-size:80%" class="btn btn-secondary" onclick="return_false()");">Ver Fatura</button></td><td><button id = "pagar_fatura" style="font-size:80%" class="btn btn-primary" onclick="pagar_fatura'.$h.'()">Pago</button></td></tr>';


            }

                                

             $rsp .= ' <script>
         
                  function return_false(){
                   
                    alert("Não adicionou uma copia da fatura");
                   
                  }

              </script>
              '; 

              
            }else{

                      if ($flag == false) {

             $rsp .= '<td> <a href="../../'.$row["foto"].'" style="font-size:80%"class="btn btn-primary" target="_blank">Ver Fatura</a></td><td><button id = "pagar_fatura' . $h.'" style="font-size:80%" class="btn btn-secondary" onclick="emitir_alerta()">Pago</button></td></tr>';

                
          
            }else{

              $rsp .= '<td> <a href="../../'.$row["foto"].'" style="font-size:80%"class="btn btn-primary" target="_blank">Ver Fatura</a></td><td><button id = "pagar_fatura' . $h.'" style="font-size:80%" class="btn btn-primary" onclick="pagar_fatura' . $h.'()">Pago</button></td></tr>';


            }
                  

            }


                   $rsp .= '<script>

            


            function emitir_alerta(){

              alert("Fatura já esta paga");
            }
         
                  function pagar_fatura'.$h.'(){

                    var op;


                if (confirm("Tem a certeza ?")) {

                  var iddespesa = '.$array_iddespesa[$h].';
                  var idaluguer = '.$array_idaluguer[$h].';
                    
                   if(op != true){


                     var xmlhttp = new XMLHttpRequest();  
  
      
                    xmlhttp.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                       alert(this.responseText);
                       op = true;
                       document.getElementById("pagar_fatura' . $h.'").setAttribute("class", "btn btn-secondary");
                       location.reload();
                       
                       
                        
                      };
                    };
                    
                    
                    
                    xmlhttp.open("GET", "php/pagar_fatura.php?op=1&iddespesa=" + iddespesa + "&idaluguer=" + idaluguer, true);
                    
                   
                    xmlhttp.send();


                   }else{

                  emitir_alerta();
                   

                   }
                } 



                       }



                    

              </script>'; 

              $h++;

            
           



                }else{
               

                  $iddespesa = $row['id_des_aluguer'];

         
                $rsp .= '<tr><td>'.$row['MesEmissao'].'</td><td>'.$row['tdespesa'].'</td><td>'.$row['valor'].'€</td><td>'.$row['limite'].'</td><td>

                <a href="PHP/mpdf/despesas_soquartos.php?idcasa='.$idcasa.'&idquarto='.$idquarto.'&iddespesa='.$iddespesa.'" style="font-size:80%"class="btn btn-primary" target="_blank">Ver Fatura</a> 
                </td></tr>';

            

                }
           
           

            
        }

        if ($op == 0) {
          $meses = array("Janeiro", "Fevereiro", "Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
        $month = 0;
                  
        $sql2 = "SELECT *,despesa_aluguer.id as iddespesa FROM despesa_aluguer,aluguer,estadodespesa where despesa_aluguer.idaluguer = aluguer.id and despesa_aluguer.estado = estadodespesa.id and aluguer.idquarto = $idquarto and despesa_aluguer.iddespesa is null;";
                  $result2 = $conn->query($sql2);
                   if ($result2->num_rows > 0) {

                      

                     while($row2 = $result2->fetch_assoc()) {  

                      $array_renda[$month] = $row2["iddespesa"]; 


                       $rsp .= '<script>

                  function pagar_renda'.$month.'(){

                    var op;


                if (confirm("Tem a certeza ?")) {

                  var iddespesa = '.$array_renda[$month].';
                  
                    
                   if(op != true){


                     var xmlhttp = new XMLHttpRequest();  
  
      
                    xmlhttp.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                       alert(this.responseText);
                       op = true;
                       document.getElementById("pagar_renda' . $month.'").setAttribute("class", "btn btn-secondary");
                       location.reload();
                       
                       
                        
                      };
                    };
                    
                    
                    
                    xmlhttp.open("GET", "php/pagar_renda.php?iddespesa=" + iddespesa, true);
                    
                   
                    xmlhttp.send();


                   }else{

                  emitir_alerta();
                   

                   }
                } 


                       }


             </script>';


                        $rsp .='<tr><td>'.$meses[$month].'</td><td>Renda</td><td>'.$row2['valor'].'€</td><td>--------</td><td>'.$row2['descricao'].'</td>';
                        if ($row2['descricao'] == 'Pago') {
                          $rsp .= '<td><button id = "pagar_renda" style="font-size:80%;margin-left:100%;" onclick = "pagar_renda' . $month.'()" class="btn btn-secondary">Pago</button></td></tr>';
                        }else{
                          $rsp .= '<td><button id = "pagar_renda" style="font-size:80%;margin-left:100%;" class="btn btn-primary" onclick = "pagar_renda' . $month.'()">Pago</button></td></tr>';
                        }


                    

                        $month++;
                     }


                   }

            


        }else{


           $meses = array("Janeiro", "Fevereiro", "Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
        $month = 0;
                  
        $sql2 = "SELECT * FROM despesa_aluguer,aluguer,estadodespesa where despesa_aluguer.idaluguer = aluguer.id and despesa_aluguer.estado = estadodespesa.id and aluguer.idquarto = $idquarto and despesa_aluguer.iddespesa is null";
                  $result2 = $conn->query($sql2);
                   if ($result2->num_rows > 0) {
                     while($row2 = $result2->fetch_assoc()) {   
                        $rsp .='<tr><td>'.$meses[$month].'</td><td>Renda</td><td>'.$row2['valor'].'€</td><td>--------</td><td> 
                        </td><td></tr>';
                        $month++;
                       
                     }
                   }



        }

       

         $rsp.='</table>';

    }else{


      if ($op == 0) {


            $meses = array("Janeiro", "Fevereiro", "Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
      $month = 0;

   $rsp.="<table style='font-size:80%'>";
   $rsp.="<th>Mes Emissão</th><th>Tipo Despesa</th><th>Valor</th><th>Data Limite</th><th>Estado</th><th></th><th></th>";
    
                  
        $sql2 = "SELECT *,despesa_aluguer.id as iddespesa FROM despesa_aluguer,aluguer,estadodespesa where despesa_aluguer.idaluguer = aluguer.id and despesa_aluguer.estado = estadodespesa.id and aluguer.idquarto = $idquarto and despesa_aluguer.iddespesa is null;";
                  $result2 = $conn->query($sql2);
                   if ($result2->num_rows > 0) {

                      

                     while($row2 = $result2->fetch_assoc()) {  
                $array_renda[$month] = $row2["iddespesa"];

                      $rsp .= '<script>

                  function pagar_renda'.$month.'(){

                    var op;


                if (confirm("Tem a certeza ?")) {

                  var iddespesa = '.$array_renda[$month].';
                  
                    
                   if(op != true){


                     var xmlhttp = new XMLHttpRequest();  
  
      
                    xmlhttp.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                       alert(this.responseText);
                       op = true;
                       document.getElementById("pagar_renda' . $month.'").setAttribute("class", "btn btn-secondary");
                       location.reload();
                       
                       
                        
                      };
                    };
                    
                    
                    
                    xmlhttp.open("GET", "php/pagar_renda.php?iddespesa=" + iddespesa, true);
                    
                   
                    xmlhttp.send();


                   }else{

                  emitir_alerta();
                   

                   }
                } 


                       }


             </script>';

                      $array_renda[$month] = $row2["iddespesa"]; 

            $rsp .='<tr><td>'.$meses[$month].'</td><td>Renda</td><td>'.$row2['valor'].'€</td><td>--------</td><td>'.$row2['descricao'].'</td>';
                        if ($row2['descricao'] == 'Pago') {
                          $rsp .= '<td><button id = "pagar_renda" style="font-size:80%;margin-left:100%;" onclick = "pagar_renda' . $month.'()" class="btn btn-secondary">Pago</button></td></tr>';
                        }else{
                          $rsp .= '<td><button id = "pagar_renda" style="font-size:80%;margin-left:100%;" class="btn btn-primary" onclick = "pagar_renda' . $month.'()">Pago</button></td></tr>';
                        }


                    

                        $month++;

              }
                   }

           $rsp.='</table>';

        
      }else{
      $meses = array("Janeiro", "Fevereiro", "Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
      $month = 0;

   $rsp.="<table style='font-size:80%'>";
   $rsp.="<th>Mes Emissão</th><th>Tipo Despesa</th><th>Valor</th><th>Data Limite</th><th>Estado</th><th></th><th></th>";
     $sql2 = "SELECT * FROM despesa_aluguer,aluguer,estadodespesa where despesa_aluguer.idaluguer = aluguer.id and despesa_aluguer.estado = estadodespesa.id and aluguer.idquarto = $idquarto and despesa_aluguer.iddespesa is null";
                  $result2 = $conn->query($sql2);
                   if ($result2->num_rows > 0) {
                     while($row2 = $result2->fetch_assoc()) {   
                        $rsp .='<tr><td>'.$meses[$month].'</td><td>Renda</td><td>'.$row2['valor'].'€</td><td>--------</td><td>'.$row2['descricao'].'</td>';
                          $rsp .= '<td></td></tr>';


                          $month++;
                       
                     }
                   }

           $rsp.='</table>';

}
    }
    

    
    echo $rsp;
    mysqli_close($conn);
      
?>

     <!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
 <br>
  <ul style="position: relative;" class="pagination justify-content-end">
            
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno=1&idcasa=<?php echo $idcasa; ?>&idq=<?php echo $idquarto; ?>">Inicio</a>
              </li>
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link <?php if($pageno <= 1){ echo 'disabled'; } ?>" href="<?php if($pageno <= 1){ echo '#'; } else { echo "?type=test&status=test&pageno=".($pageno - 1)."&idcasa=" .$idcasa . "&idq=".$idq; } ?>"><</a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="<?php if($pageno >= $total_pages){ echo '#'; } else { echo "?type=test&status=test&pageno=".($pageno + 1)."&idcasa=" .$idcasa. "&idq=".$idq;  } ?>">></a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno=<?php echo $total_pages; ?>&idcasa=<?php echo $idcasa; ?>&idq=<?php echo $idquarto; ?>">Fim</a>
                  
            </li>
          </ul>

</body>
</html>
     
  