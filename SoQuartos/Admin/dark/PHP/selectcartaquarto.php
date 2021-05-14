<?php 

include 'config.php';

$msg = "";
$idcasa=$_GET['id'];
$i=1;
$tipocasa = 0;
$id = $_SESSION["id"];
$op = $_SESSION["op_tipo_utilizador"];


if ($op == 0) {
   $sql = "SELECT Quarto.id as IDQuarto,Quarto.foto as foto,Quarto.preco as preco, casa.idtipocasa FROM quarto, casa where quarto.idcasa=casa.id and casa.id=".$idcasa.";";

        $result = $conn->query($sql);
                     
          if ($result->num_rows > 0) {
          while($row = $result->fetch_assoc()) {
                         $msg.='<div class="col">  <div class="card" style="width: 20rem;">
                                                <div>
                                          <img style="height:200px;width:320px; z-index:-1;" class="card-img-top" src=../../'.$row["foto"].'  alt="Card image cap">';
                                          

                                     $sql1 = "SELECT * from quarto,aluguer where quarto.id = aluguer.idquarto and quarto.id = ".$row['IDQuarto']." AND NOW() between dataentrada and datasaida;";
                                      $result1 = $conn->query($sql1);

                                       if ($result1->num_rows > 0) {

                                        $sql2 =" SELECT * from despesa_aluguer,aluguer where despesa_aluguer.idaluguer = aluguer.id and despesa_aluguer.estado!=1 and aluguer.idquarto = ".$row['IDQuarto']." AND despesa_aluguer.iddespesa IS NOT NULL ";


                                          



                                      $result2 = $conn->query($sql2);
                                      $pillow = "";

                                      if ($result2->num_rows > 0) {
                                        while($row2 = $result2->fetch_assoc()) {
                                        if($row2['estado']==1)
                                        {
                                          $pillow= '<img style="left:0px; top:0px; position: absolute; z-index:1;" src="icone/estado/estado_verde.png">';
                                          
                                        }else if ($row2['estado']==2) {
                                          $pillow=  '<img style="right:0.1%; bottom:35%; position: absolute; z-index:1;" src="icone/estado/estado_amarelo.png">';

                                        }else if  ($row2['estado']==3){
                                          $pillow=  '<img style="left:0px; top:0px; position: absolute; z-index:1;" src="icone/estado/estado_vermelho.png">';

                                        }
                                      }
                                    }else{
                                    	$pillow=  '<img style="left:0px; top:0px; position: absolute; z-index:1;" src="icone/estado/estado_verde.png">';
                                    }
                                }else{

                                $pillow=  '<img style="right:0.1%; bottom:35%; position: absolute; z-index:1;" src="icone/estado/estado_amarelo.png">';

                                }



                                $msg .= $pillow;
                                              





                                              $msg.='</div>
                                                <div class="card-body">
                                                  <h4 class="card-title">Quarto '.$i.'</h4>
                                                  <a href="quarto_main.php?idq='.$row["IDQuarto"].'&idcasa='.$idcasa.'" class="btn btn-primary">Detalhes</a>
                                                </div>
                                              </div>
                                            </div>';
                 

                  $i++;
                  $tipocasa = $row["idtipocasa"];
                          

              }


                if($i!=($tipocasa+1)){
                    $msg.='<div class="col"> 
                        <div class="card" style="width: 20rem;">
                                              <img class="card-img-top" src="icone/+.png" style="height:200px;width:250px;" alt="Card image cap">
                                              <div class="card-body">
                                              <h4 class="card-title"></h4>
                                                <a href="adicionarquarto.php?id='.$idcasa.'"" class="btn btn-primary">Adicionar Quarto</a>
                                              </div>
                                            </div> 
                                          </div>';
                        }
              
                                 }else{
                      $msg.='<div class="col"> 
                    <div class="card" style="width: 20rem;">
                                          <img class="card-img-top" src="icone/+.png" style="height:200px;width:250px;" alt="Card image cap">
                                          <div class="card-body">
                                          <h4 class="card-title"></h4>
                                            <p class="card-text"></p>
                                            <a href="adicionarquarto.php?id='.$idcasa.'"" class="btn btn-primary">Adicionar Quarto</a>
                                          </div>
                                        </div> 
                                      </div>';      
        }
}

else if($op == 1){

$sql = "SELECT DISTINCT Quarto.id as IDQuarto,Quarto.foto as foto
 FROM quarto, casa, aluguer ,utilizador 
where quarto.idcasa=casa.id AND quarto.id = aluguer.idquarto AND casa.id=".$idcasa." AND aluguer.idinquilino = ".$id." ;";

        $result = $conn->query($sql);
                     
          if ($result->num_rows > 0) {
          while($row = $result->fetch_assoc()) {
                         $msg.='<div class="col">  <div class="card" style="width: 20rem;">
                                                <div>
                                          <img style="height:200px;width:320px; z-index:-1;" class="card-img-top" src=../../'.$row["foto"].'  alt="Card image cap">
                                          
                    </div>
                                                <div class="card-body">
                                                  <h4 class="card-title">Quarto '.$i.'</h4>
                                                  <a href="quarto_main.php?idq='.$row["IDQuarto"].'&idcasa='.$idcasa.'" class="btn btn-primary">Detalhes</a>
                                                </div>
                                              </div>
                                            </div>';
                 

                  $i++;
                  
                          

              }

                  }

}



    
          echo $msg;
          mysqli_close($conn);
        

      
?>