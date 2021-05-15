<table style="width:105%">
      
         <tr>
        <td>

          <?php 

include 'PHP/config.php';
  
  
            $sql = "select id, descricao from distrito";
            $result = $conn->query($sql);
  
  

            $msg = "<p class = 'optionboxsize'><center>Distrito</center></p><select id = 'selectdestrito2' class='form-control form-control-lg form-control-a optionboxsize'>";
  
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

  


            
        </td>
        <td>
          <?php 

include 'PHP/config.php';
  
  
            $sql = "select id, descricao from tipocama";
            $result = $conn->query($sql);
  
            $msg = "<p class = 'optionboxsize'><center>Tipo de cama</center></p><select id = 'selecttipocama2' class='form-control form-control-lg form-control-a optionboxsize'>";
  
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
        </td>
      </tr>
        <tr>
        <td>
          <p class = 'optionboxsize'><center>WC privado</center></p>
          <select class="form-control form-control-lg form-control-a optionboxsize"  id="selectcasadebanho">
                <option value="1">Sim</option>
                <option value="0">Não</option>
              </select>
        </td>
         <td>
          <p class = 'optionboxsize'><center>Internet</center></p>
          
          <select class="form-control form-control-lg form-control-a optionboxsize"  id="selectcinternet">
                <option value="1">Sim</option>
                <option value="0">Não</option>
              </select>
        </td>
        </tr>

      </table>
      <br><br>
      <input class="btn btn-b" type="button" onclick="procurarquarto()" value="Procurar">