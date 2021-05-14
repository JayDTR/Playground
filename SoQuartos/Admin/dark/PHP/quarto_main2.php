<?php 
include 'config.php';
$idcasa = $_GET["idcasa"];
$idquarto = $_GET["idq"];
$op = $_SESSION["op_tipo_utilizador"];
    
                 if (!$conn) {
                   die("Erro: " . mysqli_connect_error());
                 }

                 if ($op == 0) {
                          $sql = "SELECT aluguer.idinquilino,utilizador.nome as nome,utilizador.telefone as tel,utilizador.email as email,utilizador.foto as foto FROM aluguer,utilizador WHERE aluguer.idinquilino = utilizador.id and aluguer.idquarto = $idquarto AND NOW() < DATASAIDA";

    $result = $conn->query($sql);
    $rsp = "";

    if ($result->num_rows > 0) {
        // output data of each row

        while($row = $result->fetch_assoc()) {
            $rsp .= '  <div class="row">  
                <div class="col-md-6">

                                    <h2 class="text-themecolor">Quarto Alugado a:</h2>
                                    <br>
                                    <div style="align-self: left;margin-top:5%"><img src="../../'.$row["foto"].'" style="width:100%"></div>
                                    
                                </div>
                                <div class="col-md-6">
                                    <div style="align-self: right;"><h3><center><h3 style="a">Detalhes</h3></center><br>Tel:</h3> <p style="color:white;">'.$row["tel"].'</p><h3>Email :</h3><p style="color:white;"> '.$row["email"].'</p></div>
                                    <div style="width: 204px;"><h3>Nome:</h3><p style="color:white;">'.$row["nome"].'</p></div>
                                </div>
                                </div>';
        }
    }else{
         $rsp .= ' <div class="col">
                                    <h2 class="text-themecolor">Inserir Inquilino</h2>
                                    <h3>Insira o NIF do Inquilino (Tem de ser fornecido pelo mesmo)</h3>
                                    <div style="align-self: left;">
                                    <input type="number" id="nifinq" name="nifinq">';
                                    
                                    
                                    $rsp.='<input type="hidden" value='.$idquarto.' id="idquarto" name="idquarto">
                                    </div> <br>
                                    <div>
                                        <h3>Data de Entrada:</h3>
                                        <input type="date" name="entrada" id="entrada">
                                        <br><br>
                                        <h3>Data de Saida:</h3>
                                        <input type="date" name="saida" id="saida">
                                    </div>
                                    <button style="margin-left:80%" class="btn btn-primary" onclick="registoaluguer()">Alugar</button>
            </div>';
    }
                 

                 }else{




                        $sql = "SELECT casa.idsenhorio,utilizador.nome as nome,utilizador.telefone as tel,utilizador.email as email,utilizador.foto as foto FROM casa,utilizador WHERE casa.idsenhorio = utilizador.id and casa.id = $idcasa ";

    $result = $conn->query($sql);
    $rsp = "";

    if ($result->num_rows > 0) {
        // output data of each row

        while($row = $result->fetch_assoc()) {
            $rsp .= '  <div class="row">  
                <div class="col-md-6">

                                    <h2 class="text-themecolor">Senhorio:</h2>
                                    <br>
                                    <div style="align-self: left;margin-top:5%"><img src="../../'.$row["foto"].'" style="width:100%"></div>
                                    
                                </div>
                                <div class="col-md-6">
                                    <div style="align-self: right;"><h3><center><h3 style="a">Detalhes</h3></center><br>Tel:</h3> <p style="color:white;">'.$row["tel"].'</p><h3>Email :</h3><p style="color:white;"> '.$row["email"].'</p></div>
                                    <div style="width: 204px;"><h3>Nome:</h3><p style="color:white;">'.$row["nome"].'</p></div>
                                </div>
                                </div>';
        }
    }





                 }

    
    
    echo $rsp;
    mysqli_close($conn);
      
?>