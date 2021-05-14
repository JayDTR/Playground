<br><br><br><br>
<div class="form-popup" id="myForm">

  <div id="logincontainer">

     <form class="form-container">


     </div>
   
    
  </form>

      <div id="logoutcontainer">

     <form class="form-container"><br>

      <p><a href="perfil.php">Perfil</a><br></p>




<?php 

 include 'config.php';

 $id = $_SESSION["id"];


 $sql = "SELECT DISTINCT idsenhorio from casa where idsenhorio = $id";
 $result = $conn->query($sql);

 if ($result->num_rows > 0) {

    while($row = $result->fetch_assoc()) {
           
      echo '<p><a href="admin/dark/index.php">Gestão de casas</a></p>';
  }

  }else{

    echo '<p><a href="admin/dark/adicionarcasa.php">Adicionar casa</a></p>';


  }


  $sql = "SELECT DISTINCT idinquilino from aluguer where idinquilino = $id";
 $result = $conn->query($sql);

 if ($result->num_rows > 0) {

    while($row = $result->fetch_assoc()) {

     echo '<p id="res"><a  href="admin/dark/Index_inquilino.php">Os meus quartos</a><br></p>';

    }
  }
    

 ?>



      
      
     

    
     
          


    

       
      

      <input onclick="logout()" type="button" name="submitformlogout" id="submitformlogout" class="btn" value="Logout">


    
    
  </form>

    

  </div>

       


</div> 

<!--END LOGIN FORM-->