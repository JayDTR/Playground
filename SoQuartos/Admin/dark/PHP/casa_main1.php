<?php 
include 'config.php';
        $idcasa = $_GET["id"];
    
                 if (!$conn) {
                   die("Erro: " . mysqli_connect_error());
                 }

         $sql = "SELECT casa.id, casa.morada as morada,casa.idtipocasa,casa.internet as internet,casa.foto,distrito.descricao as distrito FROM CASA,DISTRITO WHERE casa.iddistrito = distrito.id and casa.id = $idcasa";

    $result = $conn->query($sql);
    $rsp = "";
    $internet ="";
 
    if ($result->num_rows > 0) {
        // output data of each row

        while($row = $result->fetch_assoc()) {
        	   if($row["internet"]==1)
    	{
    		$internet = "Sim";
    	}else{
    		$internet = "Não";
    	}
            $rsp .= ' <div class="col-md-6">
            						<h2 class="text-themecolor">Propriedades</h2>
                                    <div style="align-self:left;"><img src="../../'.$row["foto"].'" style="width: 80%"></div>
                                    <div style="width:204px;"><h3>Morada:</h3><p style="color:white;">'.$row["morada"].'</p></div>
                                </div>
                                <div class="col-md-6">
                                    <div style="align-self: right;"><h3><center><h3 style="a">Detalhes</h3></center><br>Distrito :</h3> <p style="color:white;">'.$row["distrito"].'</p><h3>Nr de Quartos :</h3><p style="color:white;"> '.$row["idtipocasa"].'</p><h3>Internet :</h3><p style="color:white;"> '.$internet.'</p></div>
                                    <a href="editar_casa.php?idcasa='.$idcasa.'" class="btn btn-primary">Editar</a> 
                                </div>';
        }
    }
    
    echo $rsp;
    mysqli_close($conn);
      
?>