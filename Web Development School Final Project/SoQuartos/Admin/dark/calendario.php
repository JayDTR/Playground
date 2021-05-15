<?php 

session_start();
include 'PHP/config.php';

$eventos = [];
$date = new Datetime ('+1 day');


$idquarto = $_GET["idq"];


$sql = "SELECT utilizador.nome as nome, aluguer.dataentrada, aluguer.datasaida FROM utilizador, aluguer WHERE utilizador.id = aluguer.idinquilino AND aluguer.idquarto = $idquarto ";
        $result = mysqli_query($conn,$sql);
        if ($result->num_rows > 0) {
          while($row = $result->fetch_array()){
            /*$id = $row['id'];*/
$title = "Alugado a: ".$row['nome'];



$start = $row['dataentrada'];
/*$lotacao = $row['lotacao'];*/
/*$tipo_evento = $row['tipo_evento'];*/

$date = $row['datasaida'];
$date2 = date('Y-m-d', strtotime($date. ' + 1 days'));	
$end = $date2;

$eventos[] = [
/*'id' => $id,*/
'title' => $title,
/*'entidade' => $entidade,*/
/*'descricao' => $descricao,*/
/*'preco' => $preco,*/
/*'lotacao' => $lotacao,*/
/*'tipo_evento' => $tipo_evento,*/
'start' => $start,
'end' => $end,
]; 
}
          }
        
echo json_encode($eventos);

?>
