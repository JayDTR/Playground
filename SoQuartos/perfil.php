<!DOCTYPE html>
<?php 
session_start();
 ?>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Perfil</title>
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

              include 'PHP/config.php';
  
  
            $sql = "select id, descricao from distrito";
            $result = $conn->query($sql);
  
  

            $msg = "<select id = 'destritotop' class='form-control form-control-lg form-control-a'>";
  
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
            </div>
          </div>
          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label for="bedrooms">Tipo de cama</label>
               <?php 

              include 'PHP/config.php';
  
  
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
            <a class="nav-link" href="index-user.php">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="sobre-user.php">Sobre</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="quartos-user.php">Quartos</a>
          </li>
        
          <li class="nav-item">
            <a class="nav-link" href="contactos-user.php">Contactos</a>
          </li>
       
        </ul>


       
      </div>
        <?php 
     echo ' <p style="margin-top:1%" id="showuser">'.$_SESSION["login"].'<br></p>


      

            <button style="border-radius: 40%; border: none; background-color: white;" type="button" onclick="openForm()"><img id="foto_user"  width = "50px;" style="border-radius: 40%;"  src="'.$_SESSION["foto"].'"></button>';

       ?>


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
include 'PHP/login_form2.php';
 ?>

<!--END LOGIN FORM-->

<!--Perfil-->


<div class="container bootstrap snippet">
    <div class="row">
      <div class="col-sm-10"></div>
     
    </div>
    <div class="row">
      <div class="col-sm-3"><!--left col-->
              
<br><br><br>
      <div class="text-center">
        <?php 
include 'PHP/perfil2.php';
 ?>
     <!--   
    <form action="" method="post" enctype="multipart/form-data">
       <input id="newfoto" onclick="readURL()" type="file" name="fileToUpload" id="fileToUpload">
       <input type="submit" value="Upload Image" name="submit">
    </form>
-->

<br><label for="fname">Insira uma foto</label>
    <input type="file" id="upload">

      
<!--
        <img id="perfilimg" src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
        <h6>Mudar foto</h6>
        <input onclick="readURL()" accept="image/*" id="file" type="file" class="text-center center-block file-upload">


      -->
          <p id="text"></p>
      </div><br>

          
        </div><!--/col-3-->
      <div class="col-sm-9">      
          <div class="tab-content">
            <div class="tab-pane active" id="home">
                <hr>
                  
                      <?php 
include 'PHP/perfil.php';
 ?>

                      <div class="form-group">
                           <div class="col-xs-12">
                                <br>
                                <button onclick="upload_profil()" class="btn btn-lg btn-success"> Guardar</button>
                                
                            </div>
                      </div>
                
              
              <hr>
              
             </div>
        
               
              </div><!--/tab-pane-->
          </div><!--/tab-content-->

        </div><!--/col-9-->
    </div><!--/row-->

<p id="save_profil"></p>


<!-- End Perfil -->






 <!--/ footer Star /-->
  <footer>
  <!--/ Nav Star /-->

 <div style="height: 60px;">
            <a class="navbtext"  href="index.php">Home</a>&nbsp;
            <a class="navbtext"  href="sobre.php">Sobre</a>&nbsp;
            <a class="navbtext"  href="quartos.php">Quartos</a>&nbsp;
            <a class="navbtext" href="contactos.php">Contactos</a>&nbsp;
            <a class="navbtext" id="Adicionarcasa2" style="display: none;" href="Admin/Dark/Adicionarcasa.php">Adicionar casa</a>&nbsp;
            <a  style="display: none;" href="Manutencao.php">Manutenção</a>&nbsp;
</div>
  
  <!--/ Nav End /-->



    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="copyright-footer">
            <p class="copyright color-text-a">
              &copy; Copyright
              <span class="color-a">SoQuartos</span> All Rights Reserved.
            </p>
          </div>
        </div>
      </div>
    </div>
  </footer>
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
