<?php

$URL = $_GET["URL"];

if (isset($_FILES["file"])) {

     $name_file = $_FILES["file"]["name"];
     $tmp_name = $_FILES["file"]["temp_name"];
     $local_image = "uploaded/";
     $upload = move_uploaded_file($temp_name, $local_image.$name_file);

     if ($upload) {
         echo "uploaded this file" . $name_file;
     	
     }
	
}


?>
