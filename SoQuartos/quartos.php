<!DOCTYPE html>
<html lang="en">
<?php

session_start();



  ?>
<head>
  <meta charset="utf-8">
  <title>Quartos</title>
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
                <option value="0">Não</option>
              </select>
            </div>
          </div>


                 <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="bedrooms">Internet</label>
              <select class="form-control form-control-lg form-control-a" id="Internettop">
                <option value="1">Sim</option>
                <option value="0">Não</option>
              </select>
            </div>
          </div>



          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="price">Limite de preço</label>
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
          <li class="nav-item active">
            <a class="nav-link" href="quartos.php">Quartos</a>
          </li>
        
          <li class="nav-item">
            <a class="nav-link" href="contactos.php">Contactos</a>
          </li>
          <li class="nav-item" style="display: none;">
            <a class="nav-link" href="Manutencao.php">Manutenção</a>
          </li>
        </ul>


       
      </div>
    <?php

               if (isset($_SESSION["session"])) {

            echo ' <p style="margin-top:3%" id="showuser">'.$_SESSION["login"].'</p>


       <button style="border-radius: 40%; border: none; background-color: white;" type="button" onclick="openForm()"><img id="foto_user"  width = "50px;" style="border-radius: 40%;"  src="'.$_SESSION["foto"].'"></button>';

       

          }else{

          echo ' <p id="showuser"></p>


       <button style="border-radius: 40%; border: none; background-color: white;" type="button" onclick="openForm()"><img id="foto_user"  width = "50px;" style="border-radius: 40%;"  src="img/login.PNG"></button>';

        

          }


          ?>

       
       
    <button onclick="Search()" type="button" class="btn btn-b-n navbar-toggle-box-collapse d-none d-md-block" data-toggle="collapse"
        data-target="#navbarTogglerDemo01" aria-expanded="false">
        <span class="fa fa-search" aria-hidden="true"></span>
      </button>
    </div>
  </nav>
  <!--/ Nav End /-->


  
   <!--/ Form login /-->




<?php 

 if (isset($_SESSION["session"])){

  include 'PHP/login_form2.php';
 }else{
 
 include 'PHP/login_form.php';

 }


 ?>

<!--END LOGIN FORM-->

  <!--/ Intro Single star /-->
  <section class="intro-single">
    <div class="container">
      <div class="row">
        <div class="col-md-12 col-lg-8">
          <div class="title-single-box">
            <h1 class="title-single">Quartos</h1>
          </div>
        </div>
        <div class="col-md-12 col-lg-4">
          <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
            <ol class="breadcrumb">
              <li class="breadcrumb-item">
                
              </li>
            </ol>
          </nav>
        </div>
      </div>
    </div>
  </section>
  <!--/ Intro Single End /-->

  <!--/ Property Grid Star /-->


<center>

      <div id="form54">

        <!-- class = "slider"-->


          <input  onchange="slider()" type="range" min="150" max="500" value="500" class="form-control form-control-lg form-control-a" id="slider">

          

        <div class="container2">
          
        </div>

        <div>
            Preço: <input class="form-control form-control-lg form-control-a" onkeyup="changeslider()" style="width: 95px;" min="150" max="500" type="Number" id="valor" value="500">
              </div>
<br>
 



               <?php  


              // Create the function, so you can use it
           function isMobile() {
              return preg_match("/(android|avantgo|blackberry|bolt|boost|cricket|docomo|fone|hiptop|mini|mobi|palm|phone|pie|tablet|up\.browser|up\.link|webos|wos)/i", $_SERVER["HTTP_USER_AGENT"]);
                 }
              // If the user is on a mobile device, redirect them
             if(isMobile()){
             include 'PHP/quarto_extension_mobile.php';
               }else{

            include 'PHP/quarto_extension.php';

               }

               ?>

        <br><br>

       </center>
   </div>
<section class="property-grid grid">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="grid-option">

   <p id="procquarto"></p>




      
          </div>
        </div>

        </div>
</div>



<div id="hidequarto">

<section class="property-grid grid">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="grid-option">

      
          </div>
        </div>

        <br><br>
      
<?php

          include 'PHP/procura_quartos.php';

    ?>

</div>
</div>

 <?php

include 'PHP/pages_quarto.php';



   ?>
  <!--/ Property Grid End /-->


 <!--/ footer Star /-->
 <?php 

include 'PHP/footer.php';

   ?>
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
