<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>SoQuartos</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">

  <!-- =======================================================
    Theme Name: EstateAgency
    Theme URL: https://bootstrapmade.com/real-estate-agency-bootstrap-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>


  <div class="click-closed"></div>
    <!--/ Form Search Star /-->
  <div class="box-collapse">
    <div class="title-box-d">
      <h3 class="title-d">Procura de quartos</h3>
    </div>
    <span class="close-box-collapse right-boxed ion-ios-close"></span>
    <div class="box-collapse-wrap form">
      <form class="form-a">
        <div class="row">
          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="city">Distrito</label>
                       <?php 
                       
              include 'PHP/distrito.php';

           ?>  
            </div>
          </div>
          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="bedrooms">Tipo de cama</label>
               <?php 

              include 'PHP/tipocama.php';

           ?>
               
            </div>
          </div>

          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="bathrooms">Casa de banho privada</label>
              <select class="form-control form-control-lg form-control-a" id="wctop">
                <option value="1">Sim</option>
                <option value="0">N??o</option>
              </select>
            </div>
          </div>


                 <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="bedrooms">Internet</label>
              <select class="form-control form-control-lg form-control-a" id="Internettop">
                <option value="1">Sim</option>
                <option value="0">N??o</option>
              </select>
            </div>
          </div>



          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="price">Limite de pre??o</label>
              <!--
              <input  onchange="slidertop()" type="range" min="150" max="500" value="50" class="form-control form-control-lg form-control-a" id="slidertop">
            <input class="form-control form-control-lg form-control-a" onkeyup="changeslidertop()" style="width: 95px;" min="150" max="500" type="Number" id="valortop" value="0">
          -->
          <input class="form-control form-control-lg form-control-a" onkeyup="changeslidertop()" style="width: 95px;" min="150" max="500" type="Number" id="valortop" value="500">
            </div>
          </div>
          <div class="col-md-12">
            <input type="button" onclick="procurarquarto2()" class="btn btn-b" value="Procurar">
          </div>
        </div>
      </form>
    </div>
  </div>
  <!--/ Form Search End /-->

     <!--/ Nav Star /-->
  <nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
    <div class="container">
      <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarDefault"
        aria-controls="navbarDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span></span>
        <span></span>
        <span></span>
      </button>
      <a class="navbar-brand text-brand" href="index.php"><img class="logosize" height="50px" width="120px" src="upload/logo.png"></a>
      <button  type="button" class="btn btn-link nav-search navbar-toggle-box-collapse d-md-none" data-toggle="collapse"
        data-target="#navbarTogglerDemo01" aria-expanded="false">
        <span class="fa fa-search" aria-hidden="true"></span>
      </button>
      <div class="navbar-collapse collapse justify-content-center" id="navbarDefault">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="index.php">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="sobre.php">Sobre</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="quartos.php">Quartos</a>
          </li>
        
          <li class="nav-item">
            <a class="nav-link" href="contactos.php">Contactos</a>
          </li>
      
          <li class="nav-item" style="display: none;">
            <a class="nav-link" href="Manutencao.php">Manuten????o</a>
          </li>
          
        </ul>


       
      </div>
      <p id="showuser"></p>

       <button style="border-radius: 40%; border: none; background-color: white;" type="button" onclick="openForm()"><img id="foto_user"  width = "50px;" style="border-radius: 40%;"  src="img/login.PNG"></button>

       &nbsp;
       
    <button onclick="Search()" type="button" class="btn btn-b-n navbar-toggle-box-collapse d-none d-md-block" data-toggle="collapse"
        data-target="#navbarTogglerDemo01" aria-expanded="false">
        <span class="fa fa-search" aria-hidden="true"></span>
      </button>
    </div>
  </nav>
  <!--/ Nav End /-->


 <!--/ Form login /-->

<?php 
include 'PHP/login_form.php';
 ?>

<!--END LOGIN FORM-->

<center>
               <form class="form-container">
              
                   <div class="form-group">
                          
                       <div class="col-xs-6">
                          <label class="mobileuser"  for="username"><b>Nome</b></label>
                        <input  class="mobileuser"  minlength="4" type="text" placeholder="Insira o nome de utilizador" name="username" id="username" required>
                       </div>
                   </div>
          
                      <div class="form-group">
                          <div class="col-xs-6">
                             <label class="mobileuser"  for="psw"><b>Insira a Password</b></label>
                            <input class="mobileuser"  minlength="8" type="password" placeholder="Insira a palavra-passe" name="psw" id="psw" required>
                          </div>
                      </div>
          
                      <div class="form-group">
                          <div class="col-xs-6">
                            <label class="mobileuser"  for="psw"><b>Insira novamente a Password</b></label>
                          <input class="mobileuser"  minlength="8" type="password" placeholder="Insira novamente a Password" name="psw2" id="psw2" required>
                          </div>
                      </div>
                      <div class="form-group">
                          
                          <div class="col-xs-6">
                          <label class="mobileuser"  for="email"><b>Email</b></label>
                          <input class="mobileuser"  type="email" placeholder="Enter Email" name="email" id="email" required>
                          </div>
                      </div>
                        <div class="form-group">       
                      <div class="col-xs-6">
                        <label class="mobileuser"  for="NIF"><b>Numero do NIF</b></label>
                   <input  class="mobileuser"  minlength="8" type="Number" placeholder="Insira o seu NIF" name="NIF" id="NIF" required>
                      </div>
                      <div class="col-xs-6">
                        <label class="mobileuser"  for="nBI"><b>Numero do Cartao de Cidadao</b></label>
                          <input  class="mobileuser"  minlength="8" type="Number" placeholder="Insira o numero do seu CC/BI" name="nBI" id="nBI" required>
                        </div>
                    </div>
                      <div class="form-group">
                          
                          <div class="col-xs-6">
                          <label class="mobileuser"  for="telefone"><b>Telefone</b></label>
                          <input class="mobileuser"  minlength="6" type="Number" placeholder="Insira o numero de telefone" name="telefone" id="telefone" required>
                          </div>
                          <label class="mobileuser"  for="nasc"><b>Data de nascimento</b></label>
                           <div class="form-row show-inputbtns">
                          <input class="mobileuser" onclick="validardata()" style="font-size:12px;" min="1900-01-01" max="0000-00-00" id="datanasc" name="datanasc" type="date" required/>
                        </div>
                          </div>
                      </div>
                    </form>
                      <input onclick="registo()" type="button" name="submitform" id="submitform" class="btn" value="Registo"><br><br>


              <p id="registomsg"></p>
 </center>




  <?php 

include 'PHP/footer.php';

   ?>

    <!--/ footer Star /-->
  
  <!--/ Footer End /-->

  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
  <div id="preloader"></div>

  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/jquery/jquery-migrate.min.js"></script>
  <script src="lib/popper/popper.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script src="lib/easing/easing.min.js"></script>
  <script src="lib/owlcarousel/owl.carousel.min.js"></script>
  <script src="lib/scrollreveal/scrollreveal.min.js"></script>
  <!-- Contact Form JavaScript File -->
  <script src="contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>

</body>
</html>
