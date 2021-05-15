<?php 

include 'PHP/config.php';

 $msg = "";
$utilizador = $_SESSION['id'];

  $sql = "SELECT id, nome, telefone, email,datanascimento,nif,password,foto FROM utilizador where  utilizador.id=".$utilizador.";";

        

        $res_data = mysqli_query($conn,$sql);
        while($row = mysqli_fetch_array($res_data)){

$msg .= '<center><div> 
    <div>
      <form>
        <div>
          <div class="col-md-6 mb-2">
            <div class="form-group">
            <label for="first_name"><h4>Nome</h4></label><br>
            <input type="text" style="width:400px"  name="nome" id="nome_perfil" value="'.$row['nome'].'"> 
            </div>
          </div>

          <div class="col-md-6 mb-2">
            <div class="form-group">
             <label for="phone"><h4>Telemovel</h4></label><br>
            <input type="text" style="width:400px" class="form-control" name="phone" id="phone_perfil" value="'.$row['telefone'].'">
            </div>
          </div>
                 <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="mobile"><h4>NIF</h4></label><br>
              <input type="text" style="width:400px" class="form-control" name="BI" id="BI_perfil" value="'.$row['nif'].'">
            </div>
          </div>

                  <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="password"><h4>Password</h4></label><br> 
              <input type="password" style="width:400px" class="form-control" name="password" id="password_perfil" value="'.$row['password'].'">
            </div>
          </div>

                  <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="password2"><h4>Confirmar password</h4></label><br>
              <input type="password" style="width:400px" class="form-control" name="password2" id="password2_perfil" value="'.$row['password'].'">
            </div>
          </div>

        </div>
      </form>
    </div>
  </div></center>';
}

  echo $msg;
  

  ?> 