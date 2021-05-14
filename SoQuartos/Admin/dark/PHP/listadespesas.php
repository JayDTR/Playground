<?php 

include 'config.php';

$msg = "";
$idcasa=$_GET['id'];
$i=1;
$tipocasa = 0;
$id = $_SESSION["id"];
$op = $_SESSION["op_tipo_utilizador"];
$descricao = array();
$valor = 0;
$valor2 = 0;
$h = 0;
$p = 0;
$array_iddespesa = array();
$array_valor = array();
$array_idcasa = array();
$id_fatura = array();


 if (isset($_GET['pageno'])) {
            $pageno = $_GET['pageno'];
        } else {
            $pageno = 1;
        }

        $msg = "";

        $no_of_records_per_page = 14;
        $offset = ($pageno-1) * $no_of_records_per_page;

        $total_pages_sql = "SELECT COUNT(*) FROM despesasgerais WHERE despesasgerais.idcasa = $idcasa;";
        $result = mysqli_query($conn,$total_pages_sql);

        if (!$total_pages_sql) {
            printf("Error: %s\n", mysqli_error($conn));
            exit();
            }

        $total_rows = mysqli_fetch_array($result)[0];
        $total_pages = ceil($total_rows / $no_of_records_per_page);



        $sql = "SELECT COUNT(*) AS total FROM aluguer,quarto,casa WHERE aluguer.idquarto = quarto.id AND quarto.idcasa = casa.id AND casa.id = $idcasa AND NOW() between dataentrada and datasaida;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


        $nquartos = $row["total"];
                        

         }
          
         
      }



        $sql = "SELECT iddespesa from despesa_aluguer;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


        $id_fatura[$p] = $row["iddespesa"];
                        
           $p++;
         }
          
         
      }


        $x = 0;


                 
             $aluguer_sql = "SELECT DISTINCT aluguer.id AS aluguer FROM aluguer,utilizador,casa,quarto WHERE aluguer.idquarto = quarto.id AND quarto.idcasa = casa.id AND casa.id = $idcasa;";
             $result = $conn->query($aluguer_sql);

             if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {


                         $inquilinos_casa[$x] = $row["aluguer"];


                           $x++;
                        

                         }
          
         
                    }


if ($op == 0) {





   $sql = "SELECT despesasgerais.id as iddespesa, despesasgerais.DATETIME AS DataInsercao,mes.descricao AS MesEmissao, tipodespesas.descricao AS tdespesa, despesasgerais.valor AS valor,despesasgerais.datalimite AS limite, 
     estadodespesa.descricao AS estado,despesasgerais.foto AS foto
     FROM despesasgerais,tipodespesas,mes,estadodespesa
     WHERE despesasgerais.idtipodespesa = tipodespesas.id AND despesasgerais.mesemissao = mes.id AND despesasgerais.idestado = estadodespesa.id and despesasgerais.idcasa = $idcasa LIMIT $offset, $no_of_records_per_page ;";

        $result = $conn->query($sql);

        $valor = 1;
       
          $msg.="<div><table>";
          $msg.="<th>Data Inserção</th><th>Mes Emissão</th><th>Tipo Despesa</th><th>Valor</th><th>Data Limite</th><th>Estado</th>";
          if ($result->num_rows > 0) {
          while($row = $result->fetch_assoc()) {

              $valor = $row['valor'];

            

                if ($nquartos != 0) {
                    $valor = $valor / $nquartos;
                    
                }

            

              $msg.='<tr><td>'.$row['DataInsercao'].'</td><td>'.$row['MesEmissao'].'</td><td>'.$row['tdespesa'].'</td><td>'.$row['valor'].'€</td><td>'.$row['limite'].'</td><td>'.$row['estado'].'</td>';

                    for ($i = 0; $i < count($descricao); $i++) {

                    if ($descricao[$i] == $row["estado"]) {

                     $msg .= '<option value="'.$descricao[$i].'" selected>'.$descricao[$i].'</option>';


                        }
                        else {
                         $msg .= '<option value="'.$descricao[$i].'" selected>'.$descricao[$i].'</option>';
                            }
                                } 

                                $array_iddespesa[$h] = $row['iddespesa'];
                                $array_valor[$h] = number_format($valor,2);                
                                $array_idcasa[$h] = $idcasa;

                                if ($row["foto"] == "") {
                                $msg .= '<td><button  style="font-size:80%"class="btn btn-secondary" onclick="return_false()");">Ver Fatura</button> </td>

              <script>
         
                  function return_false(){
                   
                    alert("Não adicionou uma copia da fatura");
                   
                  }

              </script>
              ';
                

              
            }else{
                  

                   $msg .= '<td> <a href="../../'.$row["foto"].'" style="font-size:80%"class="btn btn-primary" target="_blank">Ver Fatura</a> </td>';


            }

            $flag = true;

            for ($i=0; $i < count($id_fatura) ; $i++) { 
               
               if ($id_fatura[$i] == $row['iddespesa'] ) {
                 $flag = false;
                 break;
               }else{
                   $flag = true;
                   

               }

            }

            if ($flag == false) {
             $msg .= '<td><button style="font-size:80%" id = "emitir' . $h.'" class="btn btn-secondary" onclick="emitir_alerta()">Emitir</button> </td></tr>';
            }else{
            $msg .= '<td><button style="font-size:80%" id = "emitir' . $h.'" class="btn btn-primary" onclick="emitir' . $h.'()">Emitir</button> </td></tr>';

            }

            $msg .= '<script>
           var op'.$h.' = false;


            function emitir_alerta(){

              alert("Fatura já foi emitida");
            }
         
                  function emitir'.$h.'(){

                     


                if (confirm("Tem a certeza que quer emitir esta fatura?")) {

                  var iddespesa = '.$array_iddespesa[$h].';
                    var valor = '.$array_valor[$h].'; 
                    var idcasa = '.$array_idcasa[$h].';
                    
                   

                   if(op'.$h.' != true){

                     var xmlhttp = new XMLHttpRequest();  
  
      
                    xmlhttp.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                       
                       op'.$h.' = true;
                       document.getElementById("emitir' . $h.'").setAttribute("class", "btn btn-secondary");
                       location.reload();
                      };
                    };
                    
                    
                    
                    xmlhttp.open("GET", "php/regdespalug.php?op=1&iddespesa=" + iddespesa + "&valor=" + valor  + "&idcasa=" + idcasa, true);
                    
                   
                    xmlhttp.send();
                   }else{

                  emitir_alerta();
                   

                   }
                } 



                       }

              </script>'; 

              $h++;
                
             }
           }
        }





          $msg.="</ol></table>";
          echo $msg;
          mysqli_close($conn);
        

      
?>
<!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
 <br><br>
  <ul class="pagination justify-content-end">
            
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno=1&id=<?php echo $idcasa; ?>">Inicio</a>
              </li>
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link <?php if($pageno <= 1){ echo 'disabled'; } ?>" href="<?php if($pageno <= 1){ echo '#'; } else { echo "?type=test&status=test&pageno=".($pageno - 1)."&id=" .$idcasa; } ?>"><</a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="<?php if($pageno >= $total_pages){ echo '#'; } else { echo "?type=test&status=test&pageno=".($pageno + 1)."&id=" .$idcasa;  } ?>">></a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno=<?php echo $total_pages; ?>&id=<?php echo $idcasa; ?>">Fim</a>
                  
            </li>
                  </ul>

</body>
</html>
