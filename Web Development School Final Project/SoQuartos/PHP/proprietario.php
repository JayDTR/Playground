<?php

include 'PHP/config.php';

$msg = "";
          
 $idquarto=$_GET['id'];


        $sql = "SELECT utilizador.nome,utilizador.telefone,utilizador.email,utilizador.foto FROM utilizador,casa,quarto WHERE utilizador.id = casa.idsenhorio AND casa.id = quarto.idcasa AND quarto.id = $idquarto;";


        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

          $msg .= "

<div class='row'>
            <div class='col-md-6 col-lg-4'>
              <img src=".$row["foto"]." class='img-fluid'>
            </div>
            <div class='col-md-6 col-lg-4'>
              <div class='property-agent'>
                <h4 class='title-agent'>".$row["nome"]."</h4>
                <p class='color-text-a'>
                  
                </p>
                <ul class='list-unstyled'>
                  <li class='d-flex justify-content-between'>
                    <strong>telefone:</strong>
                    <span class='color-text-a'>".$row["telefone"]."</span>
                  </li>
                  <li class='d-flex justify-content-between'>
                    <strong>Email:</strong>
                    <span class='color-text-a'>".$row["email"]."</span>
                  </li>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>";

 }

        echo $msg;
        mysqli_close($conn);

  ?>
