<?php 
include 'config.php';

$msg = "";

$id = $_SESSION["id"];
$op = $_SESSION["op_tipo_utilizador"];




if ($op == 0) {

   $sql = "SELECT casa.id as id, casa.morada FROM CASA WHERE casa.idsenhorio = $id;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

           $msg.= '<li> 
                        <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i><img style="height: 30px;width: 30px;" src="icone/casa.png"></i><span class="hide-menu">'.$row["morada"].'</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="casa_main.php?id='.$row["id"].'">Gestão Geral</a></li>
                                <li><a href="gestaoquartos.php?id='.$row["id"].'">Gestão de Quartos</a></li>
                                <li><a href="gestaodespesas.php?id='.$row["id"].'">Gestão de Despesas</a></li>
                                <li><a href="inserirdespesa.php?id='.$row["id"].'">Inserir Despesa</a></li>
                            </ul>
                   </li>';
           

          }
          echo $msg;
          mysqli_close($conn);
        }


}

else if ($op == 1) {
   $sql = "SELECT casa.id as id, casa.morada FROM CASA,quarto,aluguer,utilizador WHERE casa.id = quarto.idcasa AND quarto.id = aluguer.idquarto AND aluguer.idinquilino = utilizador.id AND utilizador.id = $id;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

          $idcasa = $row["id"];

           $msg.= '<li> 
                        <a href="gestaoquartos.php?id='.$row["id"].'" aria-expanded="false"><i><img style="height: 30px;width: 30px;" src="icone/casa.png"></i>'.$row["morada"].'</a>
                            
                   </li>';
           

          }
          echo $msg;
          mysqli_close($conn);
        }
}

 

      
?>