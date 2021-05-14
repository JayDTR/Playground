<?php 

include 'PHP/config.php';
          
$msg = "";

        $sql = "SELECT Quarto.id as IDQuarto, Quarto.preco as preco, Quarto.foto as foto, Quarto.wcprivado as WC,Casa.morada as morada, tipocama.descricao as camas,Casa.internet as internet, Distrito.descricao AS Distrito FROM quarto, casa, distrito,tipocama where tipocama.id = quarto.idcama and casa.ID=quarto.IDCasa and Casa.iddistrito=Distrito.id and Quarto.id NOT IN (SELECT Aluguer.idquarto from Aluguer) order by Quarto.id desc LIMIT 6 ;";
        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){


          $wc = "";
          $internet = "";

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


$msg .= "<div class='carousel-item-b'>
          <div class='card-box-a card-shadow'>
            <div class='img-box-a'>
              <img src='".$row["foto"]."' alt='image' class='fotosize'>
            </div>
            <div class='card-overlay'>
              <div class='card-overlay-a-content'>
                <div class='card-header-a'>
                  <h2 class='card-title-a'>
                    <h3 style='color:white;'>".$row["morada"]."</h3>
                  </h2>
                </div>
                <div class='card-body-a'>
                  <div class='price-box d-flex'>
                  <a class='price-a' href='single.php?id=".$row['IDQuarto']."&class='link-a' >Renda:" .$row['preco']."€</a> 
                  </div>
                  <a style='color:#2eca6a' href='single.php?id=".$row['IDQuarto']."&class='link-a'>Ver quarto
                   </a>
                    <span class='ion-ios-arrow-forward'></span>
                  </a>
                </div>
                <div class='card-footer-a'>
                  <ul class='card-info d-flex justify-content-around'>
                    <li>
                      <h4 class='card-info-title'>Cama</h4>
                      <span>".$row["camas"]."</span>
                    </li>
                    <li>
                      <h4 class='card-info-title'>WC Privado</h4>
                      <span>".$wc."</span>
                    </li>
                    <li>
                      <h4 class='card-info-title'>Internet</h4>
                      <span>".$internet."</span>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>";

 }

        echo $msg;
        mysqli_close($conn);
 ?>