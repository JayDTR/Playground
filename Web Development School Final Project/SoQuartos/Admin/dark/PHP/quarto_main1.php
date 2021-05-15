<?php 
include 'config.php';
        $idcasa = $_GET["idcasa"];
        $idquarto = $_GET["idq"];
        $id_user = $_SESSION["id"];
        $op = 0;

    
       $sql = "SELECT id FROM casa WHERE casa.idsenhorio = $id_user;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

            if ($idcasa == $row["id"]) {
                $op = 1;
            }
  

           

          }
          
         
        }


    
                 if (!$conn) {
                   die("Erro: " . mysqli_connect_error());
                 }

         $sql = "SELECT quarto.id,quarto.idcasa,quarto.preco as preco,quarto.foto as foto, quarto.descricao as descricao,quarto.wcprivado as wc,quarto.idcama , tipocama.descricao as cama from quarto,tipocama where quarto.idcama = tipocama.id and quarto.idcasa = $idcasa and quarto.id=$idquarto";

    $result = $conn->query($sql);
    $rsp = "";


    if ($result->num_rows > 0) {
        // output data of each row

        while($row = $result->fetch_assoc()) {
        	    if($row['wc']==1)
        {
            $wc = "Sim";
        }else{
            $wc = "Não";
        }
            $rsp .= ' <div class="col-md-6">
            						<h2 class="text-themecolor">Propriedades</h2>
                                    <div style="align-self: left;"><img src="../../'.$row["foto"].'" style="width: 80%"></div>
                                    <div style="width: 204px;"><h3>Descrição:</h3><p style="color:white;">'.$row["descricao"].'</p></div>
                                </div>
                                <div class="col-md-6">
                                    <div style="align-self: right;"><h3><center><h3 style="a">Detalhes</h3></center><br>Preço :</h3> <p style="color:white;">'.$row["preco"].'</p><h3>WC Privativa :</h3><p style="color:white;"> '.$wc.'</p><h3>Tipo de Cama :</h3><p style="color:white;"> '.$row["cama"].'</p></div>';
                                    if($op == 1){
                                    $rsp .=' <a href="editarquarto.php?id='.$row["id"].'" class="btn btn-primary">Editar</a> ';

                                    }

                                   
                               $rsp .=' </div>';
        }
    }
    
    echo $rsp;
    mysqli_close($conn);
      
?>