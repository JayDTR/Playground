<?php 

$op = $_GET["op"];

include 'PHP/config.php';



if ($op == 1) {

	$msg = "";

	$valor = $_GET["valor"];

	$selectdestrito = $_GET["selectdestrito"];

	$selecttipocama = $_GET["selectquartos"];

	$selectcasadebanho = $_GET["selectcasadebanho"];

	$selectcinternet = $_GET["selectcinternet"];


$distrito = "";


  $sql = "SELECT descricao FROM distrito WHERE ID = $selectdestrito ;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

           $distrito = $row["descricao"];
           

          }
        }

$tipocama = "";


  $sql = "SELECT descricao FROM tipocama WHERE id = $selecttipocama ;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

           $tipocama = $row["descricao"];
           

          }
        }



        if (isset($_GET['pageno'])) {
            $pageno = $_GET['pageno'];
        } else {
            $pageno = 1;
        }

        

        $no_of_records_per_page = 6;
        $offset = ($pageno-1) * $no_of_records_per_page;

        $total_pages_sql = "SELECT COUNT(*) FROM quarto, casa, distrito,tipocama where tipocama.id = quarto.idcama and casa.ID=quarto.IDCasa and Casa.iddistrito=Distrito.id and Quarto.id NOT IN (SELECT Aluguer.idquarto from Aluguer)";
        $result = mysqli_query($conn,$total_pages_sql);

        if (!$total_pages_sql) {
            printf("Error: %s\n", mysqli_error($conn));
            exit();
            }

        $total_rows = mysqli_fetch_array($result)[0];
        $total_pages = ceil($total_rows / $no_of_records_per_page);



        $sql = "SELECT Quarto.id as IDQuarto, Quarto.preco as preco, Quarto.foto as foto, Quarto.wcprivado as WC,Casa.morada as morada, tipocama.descricao as camas,Casa.internet as internet, Distrito.descricao AS Distrito FROM quarto, casa, distrito,tipocama where tipocama.id = quarto.idcama and casa.ID=quarto.IDCasa and Casa.iddistrito=Distrito.id AND quarto.preco <= $valor AND distrito.descricao ='$distrito' AND tipocama.descricao = '$tipocama' AND quarto.wcprivado = $selectcasadebanho AND casa.internet = $selectcinternet and Quarto.id NOT IN (SELECT Aluguer.idquarto from Aluguer) LIMIT $offset, $no_of_records_per_page ;";

        $msg .= "<section class='property-grid grid'>
                <div class='container'>
                <div class='row'>
                <div class='col-sm-12'>
                <div class='grid-option'>

      
          </div>
        </div>";
        

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

 $msg.="<div class='col-md-4'>
          <div class='card-box-a card-shadow'>
            <div class='img-box-a'>
              <img src='".$row["foto"]."' alt='image' class='fotosize2'>
            </div>
            <div class='card-overlay'>
              <div class='card-overlay-a-content'>
                <div class='card-header-a'>
                  <h3 class='card-title-a'>
                    ".$row["Distrito"]."
                    
                  </h3>
                  <h3 class='card-title-a'>
                    ".$row["morada"]."
                  </h3>
                </div>
                <div class='card-body-a'>
                  <div class='price-box d-flex'>
                    <a class='price-a' href='single.php?id=".$row['IDQuarto']."&class='link-a'>Renda:".$row['preco']."€</a>
                  </div>
                  <a class='link-a' href='single.php?id=".$row['IDQuarto']."&class='link-a'>Ver quarto
                   </a>
                    <span class='ion-ios-arrow-forward'></span>
                  
                </div>
                <div> 
               <div class='card-footer-a'>
                  <ul class='card-info d-flex justify-content-around'>
                    <li>
                      <h4 class='card-info-title'></h4>
                      <span></span>
                    </li>
                     <li>
                      <h4 class='card-info-title'>Cama</h4>
                      <span>".$row['camas']."</span>
                    </li>
                    <li>
                      <h4 class='card-info-title'>WC privado</h4>
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
          </div>
        </div>";

        }

        

        echo $msg;
        mysqli_close($conn);
    
          

}




 ?>