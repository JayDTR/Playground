(function ($) {
  "use strict";
  
  // Preloader
  $(window).on('load', function () {
    if ($('#preloader').length) {
      $('#preloader').delay(100).fadeOut('slow', function () {
        $(this).remove();
      });
    }
  });

  // Back to top button
  $(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
      $('.back-to-top').fadeIn('slow');
    } else {
      $('.back-to-top').fadeOut('slow');
    }
  });
  $('.back-to-top').click(function(){
    $('html, body').animate({scrollTop : 0},1500, 'easeInOutExpo');
    return false;
  });
  
	var nav = $('nav');
	var navHeight = nav.outerHeight();

	/*--/ ScrollReveal /Easy scroll animations for web and mobile browsers /--*/
	window.sr = ScrollReveal();
	sr.reveal('.foo', { duration: 1000, delay: 15 });

	/*--/ Carousel owl /--*/
	$('#carousel').owlCarousel({
		loop: true,
		margin: -1,
		items: 1,
		nav: true,
		navText: ['<i class="ion-ios-arrow-back" aria-hidden="true"></i>', '<i class="ion-ios-arrow-forward" aria-hidden="true"></i>'],
		autoplay: true,
		autoplayTimeout: 3000,
		autoplayHoverPause: true
	});

	/*--/ Animate Carousel /--*/
	$('.intro-carousel').on('translate.owl.carousel', function () {
		$('.intro-content .intro-title').removeClass('zoomIn animated').hide();
		$('.intro-content .intro-price').removeClass('fadeInUp animated').hide();
		$('.intro-content .intro-title-top, .intro-content .spacial').removeClass('fadeIn animated').hide();
	});

	$('.intro-carousel').on('translated.owl.carousel', function () {
		$('.intro-content .intro-title').addClass('zoomIn animated').show();
		$('.intro-content .intro-price').addClass('fadeInUp animated').show();
		$('.intro-content .intro-title-top, .intro-content .spacial').addClass('fadeIn animated').show();
	});

	/*--/ Navbar Collapse /--*/
	$('.navbar-toggle-box-collapse').on('click', function () {
		$('body').removeClass('box-collapse-closed').addClass('box-collapse-open');
	});
	$('.close-box-collapse, .click-closed').on('click', function () {
		$('body').removeClass('box-collapse-open').addClass('box-collapse-closed');
		$('.menu-list ul').slideUp(700);
	});

	/*--/ Navbar Menu Reduce /--*/
	$(window).trigger('scroll');
	$(window).bind('scroll', function () {
		var pixels = 50;
		var top = 1200;
		if ($(window).scrollTop() > pixels) {
			$('.navbar-default').addClass('navbar-reduce');
			$('.navbar-default').removeClass('navbar-trans');
		} else {
			$('.navbar-default').addClass('navbar-trans');
			$('.navbar-default').removeClass('navbar-reduce');
		}
		if ($(window).scrollTop() > top) {
			$('.scrolltop-mf').fadeIn(1000, "easeInOutExpo");
		} else {
			$('.scrolltop-mf').fadeOut(1000, "easeInOutExpo");
		}
	});

	/*--/ Property owl /--*/
	$('#property-carousel').owlCarousel({
		loop: true,
		margin: 30,
		responsive: {
			0: {
				items: 1,
			},
			769: {
				items: 2,
			},
			992: {
				items: 3,
			}
		}
	});

	/*--/ Property owl owl /--*/
	$('#property-single-carousel').owlCarousel({
		loop: true,
		margin: 0,  
		nav: true,
		navText: ['<i class="ion-ios-arrow-back" aria-hidden="true"></i>', '<i class="ion-ios-arrow-forward" aria-hidden="true"></i>'],
		responsive: {
			0: {
				items: 1,
			}
		}
	});

	/*--/ News owl /--*/
	$('#new-carousel').owlCarousel({
		loop: true,
		margin: 30,
		responsive: {
			0: {  
				items: 1,
			},
			769: {
				items: 2,
			},
			992: {
				items: 3,
			}
		}
	});

	/*--/ Testimonials owl /--*/
	$('#testimonial-carousel').owlCarousel({
		margin: 0,
		autoplay: true,
		nav: true,
		animateOut: 'fadeOut',
		animateIn: 'fadeInUp',
		navText: ['<i class="ion-ios-arrow-back" aria-hidden="true"></i>', '<i class="ion-ios-arrow-forward" aria-hidden="true"></i>'],
		autoplayTimeout: 4000,
		autoplayHoverPause: true,
		responsive: {
			0: {
				items: 1,
			}
		}
	});

})(jQuery);



/* Login */


function openForm(){

  
 var obj = document.getElementById("myForm").style;
  

if (obj.display == "block") {

          
     obj.display = "none";


}else{

      obj.display = "block";
      enter();

}



}




/*slider*/


function slider(){

var slider = document.getElementById("slider").value;

document.getElementById("valor").value = slider;



}




function changeslider(){

var valor = document.getElementById("valor").value;

if (valor >= 150 && valor <=500) {
  //alert(valor);

document.getElementById("slider").value = valor;

}


}


function slidertop(){

var slider = document.getElementById("slidertop").value;


document.getElementById("valortop").value = slider;



}




function changeslidertop(){

var valor = document.getElementById("valortop").value;

if (valor >= 150 && valor <=500) {
  //alert(valor);

document.getElementById("slidertop").value = valor;


}


}
  



/*perfil*/



function readURL(){

  var inputX;

  var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.avatar').attr('src', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
            var inputX = input.files[0];
        }
    }
    

    $(".file-upload").on('change', function(){
        

				readURL(this);


    }

    );


   
}


/*registo*/


function registo(){

  
    var NIF = document.getElementById("NIF").value;
    var nBI = document.getElementById("nBI").value;
  	var username = document.getElementById("username").value;
	var psw = document.getElementById("psw").value;
	var psw2 = document.getElementById("psw2").value;
	var email = document.getElementById("email").value;
	var telefone = document.getElementById("telefone").value;
	var nasc = document.getElementById("datanasc").value;


  alert("shit");

  alert(nBI);
	
    

   var xmlhttp = new XMLHttpRequest();


        
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("registomsg").innerHTML = this.responseText;
          }
        };
        
        
        xmlhttp.open("GET", "PHP/user.php?op=1&NIF=" + NIF +"&username=" + username + "&psw=" + psw + "&psw2=" + psw2 + "&email=" + email + "&telefone=" + telefone + "&nasc=" + nasc + "&nBI=" + nBI, true);
       // xmlhttp.open("GET", "PHP/user.php?op=2&username=" + username + "&psw=" + psw  + "&remember=" + remember, true);
        
       
        xmlhttp.send();

          
        
      }

   
        
      
       var user = "";

      function login(){

  
       
  	user = document.getElementById("user").value;
	var psw = document.getElementById("password").value;
    var remember = "";
 
   var xmlhttp = new XMLHttpRequest();
 
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {

             var x =  this.responseText;

             if (x == "Dados incorretos") {

             	document.getElementById("showuser").innerHTML = "";
              alert(this.responseText);
             }else{
              
              
             	document.getElementById("showuser").innerHTML = "<br>" + this.responseText + "";
              document.getElementById("output").innerHTML = "";
              
              location.reload(); 
             }


            
            
          }
        };
        
        
        xmlhttp.open("GET", "PHP/user.php?op=2&user=" + user + "&psw=" + psw  + "&remember=" + remember, true);
        
       
        xmlhttp.send();



          openForm();
        
         
		        
      }



  

        function validardata(){
  
     
      var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; 
			var yyyy = today.getFullYear();
 			
			today = yyyy+'-'+mm+'-'+dd;

			document.getElementById("datanasc").setAttribute("max", today);
      

      }



      function logout(){

      	var username = "";
		      var psw = "";



   var xmlhttp = new XMLHttpRequest();


        
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            
            document.getElementById("showuser").innerHTML = "";
            window.location.replace("index.php", true);
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/user.php?op=3&username=" + username + "&psw=" + psw, true);
        
       
        xmlhttp.send();
        openForm();




       
        
      }






function procurarquarto(){


  

  var valor = document.getElementById("valor").value;
  

  var selectdestrito2 = document.getElementById("selectdestrito2").value;
  

  var selecttipocama2 = document.getElementById("selecttipocama2").value;
  

  var selectcasadebanho = document.getElementById("selectcasadebanho").value;
  

  var selectcinternet = document.getElementById("selectcinternet").value;

        
    window.location.replace("procura_quartos.php?op=1&valor=" + valor + "&selectdestrito=" + selectdestrito2 + "&selectquartos=" + selecttipocama2 + "&selectcasadebanho=" + selectcasadebanho + "&selectcinternet=" + selectcinternet, true);
        

}


function procurarquarto2(){

  var valor = document.getElementById("valortop").value;

  var selectdestrito2 = document.getElementById("destritotop").value;

  var selecttipocama2 = document.getElementById("tipocamatop").value;

  var selectcasadebanho = document.getElementById("wctop").value;

  var selectcinternet = document.getElementById("Internettop").value;

    window.location.replace("procura_quartos.php?op=1&valor=" + valor + "&selectdestrito=" + selectdestrito2 + "&selectquartos=" + selecttipocama2 + "&selectcasadebanho=" + selectcasadebanho + "&selectcinternet=" + selectcinternet, true);
        

}




function registarcasa(){

 var tipocasa = document.getElementById("rtipocasa").value;
 var distrito = document.getElementById("rdistritocasa").value;
 var file = document.getElementById("upload").files;
 var selectinternet = document.getElementById("rinternet").value;
 var morada = document.getElementById("rmorada").value;

 if (tipocasa == "" || distrito == "" || selectinternet == "" || morada == "" ) {

    document.getElementById("registocasa").innerHTML = "Preencha os espaços obrigatorios";


 }else{

  var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("registocasa").innerHTML = this.responseText;   
            uploadImg(file, 2);
            
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/registocasa.php?op=1&tipocasa=" + tipocasa + "&distrito=" + distrito + "&fotocasa=" + file + "&selectinternet=" + selectinternet + "&morada=" + morada, true);
        
       
        xmlhttp.send();


 }

 

}

function uploadImg(file, op){
    var url = "PHP/upload.php?op="+op;
    var xhr = new XMLHttpRequest();
    var fd = new FormData();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var res = JSON.parse(xhr.responseText);
            if(parseInt(res['val']) == 1){
               location.reload();
                alert(res['msg']);
                
            }else{
                alert(res['msg']);
            }            
        }
    };
    fd.append('file0', file[0]);
    //fd.append('file', file);
    xhr.send(fd);

    
  }

  


function upload_profil(){

 var file = document.getElementById("upload").files;
 var nome = document.getElementById("nome_perfil").value;
 var telefone = document.getElementById("phone_perfil").value;
 var BI = document.getElementById("BI_perfil").value;
 var psw = document.getElementById("password_perfil").value;
 var psw2 = document.getElementById("password2_perfil").value;



 if (nome == "" || telefone == "" || BI == "" || psw == "" || psw2 == "") {

    document.getElementById("save_profil").innerHTML = "Preencha os espaços obrigatorios";


 }else if (psw != psw2) {

  document.getElementById("save_profil").innerHTML = "Palavras-passes não coincidem";

 }

 else{

  var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("save_profil").innerHTML = this.responseText;
            uploadImg(file, 1);
            user_img();


            
            
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/update_profil.php?nome=" + nome  + "&telefone=" + telefone + "&BI=" + BI + "&psw=" + psw + "&psw2=" + psw2, true);
        
       
        xmlhttp.send();

        


 }



}


function enter(){


var input = document.getElementById("user");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("submitformLogin").click();
  }
});


var input2 = document.getElementById("password");
input2.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("submitformLogin").click();
  }
});

}


setTimeout(enter(), 1000);

