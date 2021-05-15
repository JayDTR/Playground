<?php
session_set_cookie_params(3600,"/");
session_start();
	$op =$_GET["op"];
	
	
	
	 include 'config.php';

		
		
	
	if($op==1){ 
     
     $msg='';
        
        $NIF = $_GET["NIF"];
		$username = $_GET["username"];
		$psw = $_GET["psw"];
		$psw2 = $_GET["psw2"];
		$email = $_GET["email"];
		$telefone = $_GET["telefone"];
		$nasc = $_GET["nasc"];

           $validar = true;
           $validarregisto = true;
           

			$sql = "SELECT email FROM utilizador;";
     		$result = $conn->query($sql);

  			if ($result->num_rows > 0) {
      	while($row = $result->fetch_assoc()) {

                if ($email == $row["email"]) {
                	$validarregisto = false;
                	$validar = false;
                	break;
                }else{
             	$validarregisto = true;
				$validar = true;
                


                }
      		}
    		}
    		
                       /*
                    $msg .= $username . "<br>";
         			$msg .= $psw2 . "<br>";
         			$msg .= $psw . "<br>";
         			$msg .= $email . "<br>";
         			$msg .= $telefone . "<br>";
         			$msg .= $nasc . "<br>";

       		         */

			//registo

            $t=time();
            $uppercase = preg_match('@[A-Z]@', $psw);
			$lowercase = preg_match('@[a-z]@', $psw);
			$number    = preg_match('@[0-9]@', $psw);
			$specialChars = preg_match('@[_\^\w]@', $psw);
            

			if (!$uppercase || !$lowercase || !$number || !$specialChars ||strlen($psw) < 8) {
			$validar = false;
			$validarregisto = false;
			$msg .= "Palavra-pass deve ter pelo menos 8 caracteres e incluir pelo menos uma letra maiúscula, um número e um caractere especial.<br>";
			
		}else if (strlen($telefone) < 6) {
			$validar = false;
			$validarregisto = false;
			$msg .= "Numero de telefone invalido<br>";
			

		}else if ($nasc > date("Y-m-d",$t)) {
			$validar = false;
			$validarregisto = false;
		$msg .= "Data invalida<br>";

		}else if (!preg_match("/^[a-zA-Z ]*$/",$username)) {

			$validar = false;
			$validarregisto = false;
  		$msg .= " Nome de utilizador apenas letras e numeros permitidos<br>";
		}else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
  		$msg .= "Formato de email invalido<br>";
		}





		if ($validar == true) {
 
               

		// Check connection
		if($conn->connect_error) {
    	die("Connection failed: " . $conn->connect_error);
		}


		
		
		if ($psw!=$psw2) {
			alert("As Palavras passes não coincidem");
		}else{
			$sql = "INSERT INTO utilizador(nome,telefone,email,datanascimento,nif,idtipoutilizador,password) VALUES('$username',$telefone,'$email','$nasc','$NIF', 2,'$psw'); ";
		if ($conn->query($sql) === TRUE) {
			 $validarregisto = true;
			
		} else {
			$msg.= "Erro: " . $sql . "<br>" . $conn->error;
			 $validarregisto = false;
		}
		}
	
		}

		

			if ($validarregisto == false) {
			$msg .= "Não foi possivel registar";

		}else if ($validarregisto == true) {
			$msg.= "Registo adicionado com sucesso";
		}

		
		
		echo $msg;

		$conn->close();
	}
	  


	  else if ($op == 2) {

	  	$user = $_GET["user"];
		$psw = $_GET["psw"];

	  	$remember = $_GET["remember"];

        $msg='';
        $validar = "";

        if ($remember == "true") {


        		$_SESSION["check"] = "true";
        	
        }else{

        	$_SESSION["check"] = "false";
        }

		
		$_SESSION["password"] = $psw;


         // Check connection
		if($conn->connect_error) {
    	die("Connection failed: " . $conn->connect_error);
    	
                
		}        

			$sql = "SELECT id,email,nome,password FROM utilizador;";
     		$result = $conn->query($sql);

  			if ($result->num_rows > 0) {
      	while($row = $result->fetch_assoc()) {

                if ($user == $row["email"] && $psw == $row["password"]) {
                    
                    $_SESSION["id"] = $row["id"];
                	$_SESSION["login"] = $row["nome"];
                	$_SESSION["email"] = $row["email"];
                	$_SESSION["pass"] = $row["password"];
                	$validar = true;
                	break;
                }else{
             	
				$validar = false;
                


                }
      		}
    		}


            if ($validar == true) {
            	
            	$_SESSION["session"] = "true";
            	$msg .= $_SESSION["login"];
            	
                 
                 
                   
            }else{

            	$msg .= "Dados incorretos";
            	$_SESSION["session"] = "false";
            	
            	 
            }

          echo $msg;

	  	
	  }
	
	else if ($op == 3) {
		session_destroy();
		$msg = "Terminou sessão";
        
		echo $msg;
	}
          

	 else if ($op == 4) {
   
          
            	echo $_SESSION["session"];
 
	  }
	
	 else if ($op == 5) {
   
          echo $_SESSION["login"];
            	
 
	  }
     
    
   
     
?>