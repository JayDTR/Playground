 <?php 

              include 'config.php';
  
  
            $sql = "select id, descricao from tipocama";
            $result = $conn->query($sql);
  
            $msg = "<select id = 'tipocamatop' class='form-control form-control-lg form-control-a'>";
  
            if ($result->num_rows > 0) {
    
    
    
             while($row = $result->fetch_assoc()) {
              $msg .= "<option value =" . $row["id"] .">" . $row["descricao"]. "</option>";
             }
              } else {
              $msg .= "-";
              }
              $msg .= "</select>";

  
            echo $msg;
          $conn->close();





           ?>