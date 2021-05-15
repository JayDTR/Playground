<?php 
include 'config.php';
        $idcasa = $_GET["id"];
    
                 if (!$conn) {
                   die("Erro: " . mysqli_connect_error());
                 }

          if (isset($_GET['pageno'])) {
            $pageno = $_GET['pageno'];
        } else {
            $pageno = 1;
        }

        $msg = "";

        $no_of_records_per_page = 3;
        $offset = ($pageno-1) * $no_of_records_per_page;





        $total_pages_sql = "SELECT count(*) FROM (SELECT DISTINCT aluguer.idinquilino from aluguer,quarto,casa WHERE aluguer.idquarto = quarto.id AND quarto.idcasa = casa.id AND casa.id = $idcasa) as temp;";
        $result = mysqli_query($conn,$total_pages_sql);

        if (!$total_pages_sql) {
            printf("Error: %s\n", mysqli_error($conn));
            exit();
            }

        $total_rows = mysqli_fetch_array($result)[0];
        $total_pages = ceil($total_rows / $no_of_records_per_page);



         $sql = "SELECT DISTINCT aluguer.idinquilino,utilizador.nome as nome,utilizador.foto as foto FROM utilizador,aluguer,quarto WHERE utilizador.id = aluguer.idinquilino and aluguer.idquarto = quarto.id and quarto.idcasa=$idcasa and now() < datasaida LIMIT $offset, $no_of_records_per_page ;";

    $result = $conn->query($sql);
    $rsp = '
    <div class="row" ><h2 class="text-themecolor">Quarto Alugado a:</h2></div>
    
    ';
    $internet ="";
 
    if ($result->num_rows > 0) {
        // output data of each row
        $i=0;
            
        $j=0;
                while($row = $result->fetch_assoc()) {

                    if ($i==0) {
                     $rsp.='<div class="row el-element-overlay">';
                      $rsp .= ' 
                        <div class="col-lg-3 col-md-3">
                            <div class="card">
                                <div class="el-card-item" >
                                    <div class="el-card-avatar el-overlay-1"> <img class="fotoi" src="../../'.$row["foto"].'" style="border-radius: 30%;height:100px;width: 120px;object-fit: cover;" alt="user">
                                    
                                    </div>
                                    <div class="el-card-content">
                                        <h3 class="box-title">'.$row["nome"].'</h3> 
                                     </div>
                              </div>
                            </div>
                        </div>
                        ';
                        $j=1;
                        $i++;
                    }elseif ($i==1) {
                       $rsp .= ' 
                        <div class="col-lg-3 col-md-3">
                            <div class="card">
                                <div class="el-card-item" >
                                    <div class="el-card-avatar el-overlay-1"> <img class="fotoi" src="../../'.$row["foto"].'" style="border-radius: 30%;height:100px;width: 120px;object-fit: cover;" alt="user">
                                    
                                    </div>
                                    <div class="el-card-content">
                                        <h3 class="box-title">'.$row["nome"].'</h3> 
                                     </div>
                              </div>
                            </div>
                        </div>
                        ';
                        $i++;
                        
                    }else{
                        $rsp .= ' 
                        <div class="col-lg-3 col-md-3">
                            <div class="card">
                                <div class="el-card-item" >
                                    <div class="el-card-avatar el-overlay-1"> <img class="fotoi" src="../../'.$row["foto"].'" style="border-radius: 30%;height:100px;width: 120px;object-fit: cover;" alt="user">
                                    
                                    </div>
                                    <div class="el-card-content">
                                        <h3 class="box-title">'.$row["nome"].'</h3> 
                                     </div>
                              </div>
                            </div>
                        </div>
                        ';
                        $rsp .= " </div>";
                        $i=0;
                        $j=0;
                    }

                   
                       
                   

                }

                if ($j!=0) {
                  $rsp .= " </div>";  
                }
    }


    echo $rsp;
   mysqli_close($conn);
      
?>

<!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
 
  <ul style="position: relative;" class="pagination justify-content-end">
            
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno=1&id=<?php echo $idcasa; ?>">Inicio</a>
              </li>
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link <?php if($pageno <= 1){ echo 'disabled'; } ?>" href="<?php if($pageno <= 1){ echo '#'; } else { echo "?type=test&status=test&pageno=".($pageno - 1)."&id=" .$idcasa; } ?>"><</a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="<?php if($pageno >= $total_pages){ echo '#'; } else { echo "?type=test&status=test&pageno=".($pageno + 1)."&id=" .$idcasa;  } ?>">></a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno=<?php echo $total_pages; ?>&id=<?php echo $idcasa; ?>">Fim</a>
                  
            </li>
          </ul>

</body>
</html>