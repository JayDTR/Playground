            <?php 
              // Create the function, so you can use it
           function isMobile() {
              return preg_match("/(android|avantgo|blackberry|bolt|boost|cricket|docomo|fone|hiptop|mini|mobi|palm|phone|pie|tablet|up\.browser|up\.link|webos|wos)/i", $_SERVER["HTTP_USER_AGENT"]);
                 }
              // If the user is on a mobile device, redirect them
             if(isMobile()){
             include 'PHP/perfil_mobile.php';
            
               }else{

            include 'PHP/perfil.php';

               }
 ?>