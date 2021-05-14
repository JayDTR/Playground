<?php 

 include 'PHP/config.php';

 $msg = "";

  $sql = "SELECT Quarto.id as IDQuarto, Quarto.preco as preco, Quarto.foto as foto, Quarto.wcprivado as WC,Casa.morada as morada, tipocama.descricao as camas,Casa.internet as internet, Distrito.descricao AS Distrito FROM quarto, casa, distrito,tipocama where tipocama.id = quarto.idcama and casa.ID=quarto.IDCasa and Casa.iddistrito=Distrito.id AND Quarto.id IN (SELECT idquarto from destaque_quarto);";

        

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

          $msg .= "<div class='carousel-item-a intro-item bg-image' style='background-image: url(".$row["foto"].")'>
               
        <div class='overlay overlay-a'></div>
        <div class='intro-content display-table'>
          <div class='table-cell'>
            <div class='container'>
              <div class='row'>
                <div class='col-lg-8'>
                  <div class='intro-body'>
                    <h3 style='color: white;'>".$row["Distrito"]."</h3>
                    <h1 class='intro-title mb-4'>" .$row["morada"]."</h1>
                      <a style='color:#2eca6a' href='single.php?id=".$row["IDQuarto"]."&class='link-a'>Ver quarto</a>
                    <p class='intro-subtitle intro-price'><a href='single.php?id=".$row["IDQuarto"]."&class='link-a'><span class='price-a'>Renda:".$row["preco"]."€</span></a></p>
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