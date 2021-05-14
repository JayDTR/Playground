<?php
include 'config.php';

$msg = "";
$op  = $_SESSION["op_tipo_utilizador"];

$id     = $_SESSION["id"];
$sql    = "SELECT casa.id as idcasa, 
          casa.foto as foto, 
          casa.morada as morada, 
          casa.internet as internet, 
          Distrito.descricao AS Distrito 
          FROM casa, distrito
          where casa.iddistrito = distrito.ID AND casa.idsenhorio = $id;";
$result = $conn->query($sql);

if ($op == 0) {
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            
            
            $msg .= '<div class="col"> 
                    <div class="card" style="width: 20rem;">
                    <div>
                  <img style="height:200px;width:320px; z-index:-1;" class="card-img-top" src=../../' . $row["foto"] . '  alt="Card image cap">';
            
            
            
            $sql1    = "SELECT * from casa,quarto,aluguer where casa.id = quarto.idcasa and quarto.id = aluguer.idquarto and casa.id = " . $row['idcasa'] . " AND NOW() between dataentrada and datasaida;";
            $result1 = $conn->query($sql1);
            
            if ($result1->num_rows > 0) {
                $sql2    = "SELECT * from despesasgerais where despesasgerais.idcasa =" . $row['idcasa'] . "";
                $result2 = $conn->query($sql2);
                $pillow = "";
                if ($result2->num_rows > 0) {
                    
                    while ($row2 = $result2->fetch_assoc()) {
                        if ($row2['idestado'] == 1) {
                            $pillow = '<img style="left:0px; top:0px; position: absolute; z-index:1;" src="icone/estado/estado_verde.png">';
                        } else if ($row2['idestado'] == 2) {
                           $pillow = '<img style="right:0.1%; bottom:35%; position: absolute; z-index:1;" src="icone/estado/estado_amarelo.png">';
                        } else if ($row2['idestado'] == 3) {
                            $pillow = '<img style="left:0px; top:0px; position: absolute; z-index:1;" src="icone/estado/estado_vermelho.png">';  
                        }
                    }
                } else {
                    $pillow ='<img style="left:0px; top:0px; position: absolute; z-index:1;" src="icone/estado/estado_verde.png">';
                }
            } else {
                
               $pillow = '<img style="right:0.1%; bottom:35%; position: absolute; z-index:1;" src="icone/estado/estado_amarelo.png">';
            }
            
            $msg .= $pillow;
            
            
            
            
            $msg .= '</div>
                                          <div class="card-body">
                                            <h4 class="card-title">' . $row["morada"] . '</h4>
                                            <a href="casa_main.php?id=' . $row["idcasa"] . '" class="btn btn-primary">Detalhes</a>
                                          </div>
                                        </div> </div>';
            
            
            
            
        }
        
        
        
        
        
        $msg .= '<div class="col"> 
                    <div class="card" style="width: 20rem;">
                                              <img class="card-img-top" src="icone/+.png" style="height:200px;width:250px;" alt="Card image cap">
                                          <div class="card-body" style="padding-top:35px;">
                                            <h4 class="card-title"></h4>
                                            <p class="card-text"></p>
                                            <a href="adicionarcasa.php"  class="btn btn-primary">Adicionar Casa</a>
                                          </div>
                                        </div> </div>';
        echo $msg;
        mysqli_close($conn);
    }
    
    
}

else if ($op == 1) {
    
    
    $sql    = "SELECT DISTINCT casa.id as idcasa, 
          quarto.foto as foto, 
          casa.morada as morada, 
          Distrito.descricao AS Distrito 
          FROM casa, distrito,quarto,aluguer,utilizador
          where casa.iddistrito = distrito.ID AND casa.id = quarto.idcasa AND aluguer.idquarto = quarto.id AND aluguer.idinquilino = $id;";
    $result = $conn->query($sql);
    
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            
            
            $msg .= '<div class="col"> 
                    <div class="card" style="width: 20rem;">
                    <div>
                                          <img style="height:200px;width:320px; z-index:-1;" class="card-img-top" src=../../' . $row["foto"] . '  alt="Card image cap">
                                          
                    </div>
                                          <div class="card-body">
                                            <h4 class="card-title">' . $row["morada"] . '</h4>
                                            <a href="casa_main.php?id=' . $row["idcasa"] . '" class="btn btn-primary">Detalhes</a>
                                          </div>
                                        </div> </div>';
            
            
            
            
        }
        
        
        
        echo $msg;
        mysqli_close($conn);
    }
}




?>