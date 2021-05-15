<?php 
include '../config.php';

$msg = "";

$idcasa = $_GET["idcasa"];
$idquarto = $_GET["idquarto"];
$iddespesa = $_GET["iddespesa"];






 $sql = "SELECT casa.morada as morada,distrito.descricao as descricao FROM casa,distrito WHERE casa.iddistrito = distrito.id AND casa.id = $idcasa;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


          $morada = $row["morada"] . "," . $row["descricao"];



          }
          
         
        }





   $sql = "SELECT DISTINCT utilizador.nif as nifinq,utilizador.nome AS senhorio FROM utilizador,aluguer,quarto,casa WHERE casa.idsenhorio = utilizador.id  AND casa.id = $idcasa;";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {


          $nomesenhorio = $row["senhorio"];
          $contribuinte_senhorio = $row["nifinq"];



          }
          
         
        }



       $loop = "<table border='1'><tr><th width='200'> Despesa</th><th width='270'>Mês de emissão  </th>
          <th width='270'> Valor  </th>
          <th width='270'> Estado </th><th width='270'> Data limite </th></tr>";


           $sql = "SELECT DISTINCT despesasgerais.mesemissao as mes,utilizador.nif as nif,despesasgerais.datalimite as datalimite,utilizador.nome as inquilino,despesa_aluguer.valor as valor,estadodespesa.descricao as descricao_estado,
            tipodespesas.descricao as tipo_despesa 
            from aluguer,despesa_aluguer,utilizador,estadodespesa,despesasgerais,tipodespesas,casa,quarto WHERE despesasgerais.idtipodespesa = tipodespesas.id AND despesasgerais.id = despesa_aluguer.iddespesa AND
          despesa_aluguer.idaluguer = aluguer.id  AND aluguer.idinquilino = utilizador.id 
        AND despesa_aluguer.estado = estadodespesa.id AND despesasgerais.idcasa = casa.id AND quarto.idcasa = casa.id AND aluguer.idquarto = quarto.id AND casa.id = $idcasa AND quarto.id = $idquarto AND despesa_aluguer.id = $iddespesa;";

        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {

          $nomeinq = $row["inquilino"];
          $contribuinte_inquilino = $row["nif"];

          

         $text_despesas .= $despesas[$valor] . ": " . $despesasvalor[$valor] . " €<br>";

         $meses = array("Janeiro", "Fevereiro", "Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");

          $loop .= "
          <tr><td>".$row["tipo_despesa"]."</td>
          <td>".$meses[$row["mes"] - 1]."</td>
          <td>".number_format($row["valor"],2)."€</td>";

          if ($row["descricao_estado"] == "Pago") {
           

            $loop .= '<td color="green" >'.$row["descricao_estado"].'</td></tr>';

         }else{

          $loop .= '<td color="red" >'.$row["descricao_estado"].'</td>';
         }

         $data_limite=date_create($row["datalimite"]);
         


        $loop .= '<td>'.date_format($data_limite,"d/m/Y").'</td></tr>';
            

             break;

          }
          
         
        }

        $loop .= "</table>";

          
      


 mysqli_close($conn);
      
?>