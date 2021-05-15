<br><br><br><br><br>
<div class="form-popup" id="myForm">

  <div id="logincontainer">

     <form class="form-container">

      <?php
            
            if (!isset($_SESSION["session"])) {
             echo ' <div id="formlogin">
   
    <label class="mobileuser" style="font-size:12px;" for="username"><b>Email</b></label>
    <input  class="mobileuser" style="font-size:12px;" type="email" placeholder="Insira o email" name="user" id="user" required>

    <label class="mobileuser" style="font-size:12px;" for="psw"><b>Insira a Password</b></label>
    <input class="mobileuser" style="font-size:12px;" type="password" placeholder="Insira a palavra-passe" name="password" id="password" required>


     <!--
     <input type="checkbox" checked="checked" name="remember" id="remember"> Lembrar login
     <br>
   -->

      <a href="#"> Recuperar password</a><br>
      <a href="registo_form.php">Criar conta</a>

      <input onclick="login()" type="button" name="submitformLogin" id="submitformLogin" class="btn" value="Login">

 </div>
     </div>
    <p id="output"></p>
    
  </form>';
            }else{

          echo '
      <div style="display: none" id="logoutcontainer">

     <form class="form-container"><br>

      <p><a href="perfil.php">Perfil</a><br></p>








      
      <p id="res2"><a  href="admin/dark/index.php">Gestão de casas</a><br></p>
     

      <p id="res"><a  href="admin/dark/Index_inquilino.php">Os meus quartos</a><br></p>
     
          


    

       
      

      <input onclick="logout()" type="button" name="submitformlogout" id="submitformlogout" class="btn" value="Logout">


    
    
  </form>

    

  </div>
';

            }




        ?>



       


</div> 

<!--END LOGIN FORM-->