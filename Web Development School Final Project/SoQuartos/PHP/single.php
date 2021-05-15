<?php

   include 'PHP/config.php';
   
       $wc = "";
          $internet = "";



     $idquarto=$_GET['id'];

       $sql = "SELECT Quarto.id as IDQuarto, Quarto.preco as preco, quarto.descricao as descricao, Quarto.foto as foto, Quarto.wcprivado as WC,Casa.morada as morada, tipocama.descricao as camas,Casa.internet as internet, Distrito.descricao AS Distrito,casa.foto as fotocasa FROM quarto, casa, distrito,tipocama where tipocama.id = quarto.idcama and casa.ID=quarto.IDCasa and Casa.iddistrito=Distrito.id and quarto.id=$idquarto and Quarto.id NOT IN (SELECT Aluguer.idquarto from Aluguer) LIMIT 1 ;";

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){


          

          if ($row["WC"] == 1) {
            $wc = "Sim";
          }else{

            $wc = "Não";
          }

          if ($row["internet"] == 1) {
            $internet = "Sim";
          }else{

            $internet = "Não";
          }


           $msg.='<div class="container" id="detalhes">
      <div class="row">
        <div class="col-sm-12">
          <div id="property-single-carousel" class="owl-carousel owl-arrow gallery-property">
            <div class="carousel-item-b">
              <img src="'.$row['foto'].'" alt="" class="imgquarto">
            </div>
          </div>
          <div class="row justify-content-between">
            <div class="col-md-5 col-lg-4">
              <div class="property-price d-flex justify-content-center foo">
                <div class="card-header-c d-flex">
                  <div class="card-box-ico">
                    <span class="ion-money">'.$row['preco'].'€</span>
                  </div>
                </div>
              </div>
              <div class="property-summary">
                <div class="row">
                  <div class="col-sm-12">
                    <div class="title-box-d section-t4">
                      <h3 class="title-d">Detalhes</h3>
                    </div>
                  </div>
                </div>
                <div class="summary-list">
                  <ul class="list">
                    <li class="d-flex justify-content-between">
                      <strong>Distrito:</strong>
                      <span>'.$row['Distrito'].'</span>
                    </li>
                  <li class="d-flex justify-content-between">
                      <strong>Morada:</strong>
                      <span>'.$row['morada'].'</span>
                    </li>
                    <li class="d-flex justify-content-between">
                      <strong>Tipo de Cama:</strong>
                      <span>'.$row['camas'].'</span>
                    </li>
                    <li class="d-flex justify-content-between">
                      <strong>Internet:</strong>
                      <span>'.$internet.'</span>
                    </li>
                    <li class="d-flex justify-content-between">
                      <strong>Casa de banho Privada:</strong>
                      <span>'.$wc.'</span>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
               <div class="col-md-7 col-lg-7 section-md-t3">
              <div class="row">
                <div class="col-sm-12">
                  <div class="title-box-d">
                    <h3 class="title-d">descrição do quarto</h3>
                  </div>
                </div>
              </div>
              <div class="property-description">
                <p class="description color-text-a">'.$row['descricao'].'</p>
              </div>
                <br><br><br>
              <img src="'.$row['fotocasa'].'" alt="" class="fotosize3">
            
              ';

        }

        echo $msg;
        mysqli_close($conn);
    ?>