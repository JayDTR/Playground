<?php 

include 'PHP/config.php';
  
  
            $sql = "select id, descricao from tipocama";
            $result = $conn->query($sql);
         
            $msg = "<center><select id = 'selecttipocama2' class='form-control form-control-lg form-control-a sizebox'>";
  
            if ($result->num_rows > 0) {
    
    
    
             while($row = $result->fetch_assoc()) {
              $msg .= "<option value =" . $row["id"] ."><center>" . $row["descricao"]. "</center></option>";
             }
              } else {
              $msg .= "-";
              }
              $msg .= "</select></center>";

  
            echo $msg;
          $conn->close();





           ?>