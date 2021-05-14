
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
        
        
        
        xmlhttp.open("GET", "PHP/registocasa.php?op=1&tipocasa=" + tipocasa + "&distrito=" + distrito  + "&selectinternet=" + selectinternet + "&morada=" + morada, true);
        
       
        xmlhttp.send();


 }
}

function registarquarto(){
var idcasa = document.getElementById("idcasa").innerHTML;
 var tipocama = document.getElementById("rtipocama").value;
 var price = document.getElementById("price").value;
 var file = document.getElementById("upload").files;
 var rwc = document.getElementById("rwc").value;
 var desc = document.getElementById("desc").value;

 if (tipocama == "" || price == "" || rwc == "" || rtipocama == "" ) {

    document.getElementById("registoquarto").innerHTML = "Preencha os espaços obrigatorios";


 }else{

  var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("registoquarto").innerHTML = this.responseText;
            uploadImg(file, 3);
            
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/registoquarto.php?op=1&tipocama=" + tipocama + "&price=" + price  + "&rwc=" + rwc + "&desc=" + desc + "&idcasa=" + idcasa, true);
        
       
        xmlhttp.send();


 }
}

function registoaluguer(){
var nifinq = document.getElementById("nifinq").value;
 var idquarto = document.getElementById("idquarto").value;
 var entrada = document.getElementById("entrada").value;
 var saida = document.getElementById("saida").value;


 if (nifinq == "" || entrada == "" || saida == "") {

    alert("Preencha Todos os Campos");

 }else{

  if (confirm("Deseja continuar?")) {
  

  var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
           alert(this.responseText);
            location.reload();
            
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/registoaluguer.php?op=1&nifinq=" + nifinq + "&idquarto=" + idquarto  + "&entrada=" + entrada + "&saida=" + saida, true);
        
       
        xmlhttp.send();

        } 


 }
}

function registardespesa(){
var idcasa = document.getElementById("idcasa").innerHTML;
 var tipodespesa = document.getElementById("rtipodespesa").value;
 var price = document.getElementById("price").value;
 var file = document.getElementById("upload").files;
 var mes = document.getElementById("rmes").value;
 var limite = document.getElementById("limite").value;

 if (price == "" || limite == "") {

    document.getElementById("registodespesa").innerHTML = "Preencha os espaços obrigatorios";


 }else{

  var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            uploadImg(file, 1);
            
          }
        };
         
        
         
        xmlhttp.open("GET", "PHP/registodespesa.php?op=1&tipodespesa=" + tipodespesa + "&price=" + price  + "&mes=" + mes + "&limite=" + limite + "&idcasa=" + idcasa, true);
        
       
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


//Chat

function start(){

enter();
var intervalo = setInterval(chat, 3000);
scroll();




}


function chat(){

  
var casa = document.getElementById("casa").value;


         var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("chat").innerHTML = this.responseText; 

          }
        };
        

        xmlhttp.open("GET", "PHP/chat.php?op=2&casa=" + casa, true);
        
       
        xmlhttp.send();

   
          
}


function scroll(){

  
var casa = document.getElementById("casa").value;


         var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("chat").innerHTML = this.responseText;

            var elmnt = document.getElementById("content");

              elmnt.scrollIntoView(false);


            
          }
        };
        

        xmlhttp.open("GET", "PHP/chat.php?op=2&casa=" + casa, true);
        
       
        xmlhttp.send();

   
          
}



function msg(){


var msg = document.getElementById("msg").value;
var casa = document.getElementById("casa").value;



 var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
          // alert(this.responseText);   
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/chat.php?op=1&msg=" + msg + "&casa=" + casa, true);
        
       
        xmlhttp.send();
        chat();
        scroll();
        var msg = document.getElementById("msg").value = "";

}


function enter(){

var input = document.getElementById("msg");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("enviar").click();
  }
});

}

 function uploadImgEditar(file, op,id){
    var url = "PHP/uploadeditar.php?op="+op+"&id="+id;
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

function editarcasa(){

 var tipocasa = document.getElementById("etipocasa").value;
 var file = document.getElementById("upload").files;
 var selectinternet = document.getElementById("einternet").value;
 var casa = document.getElementById("idcasa").value;

  var xmlhttp = new XMLHttpRequest();  
  
       
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("registocasa").innerHTML = this.responseText;
            uploadImgEditar(file, 1,casa);
             
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/editarcasa.php?casa="+ casa +"&tipocasa=" + tipocasa + "&selectinternet=" + selectinternet, true);
        
       
        xmlhttp.send();


 
}


function editarQuarto(){

 var tipocama = document.getElementById("etipocama").value;
 var price = document.getElementById("eprice").value;
 var file = document.getElementById("upload").files;
 var wc = document.getElementById("ewc").value;
 var desc = document.getElementById("edesc").value;
var quarto = document.getElementById("idquarto").value;


  var xmlhttp = new XMLHttpRequest();  
  
      
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("registoquarto").innerHTML = this.responseText;
            uploadImgEditar(file, 2,quarto);
            
          }
        };
        
        
        
        xmlhttp.open("GET", "PHP/editarquarto2.php?op=1&quarto="+quarto+"&tipocama=" + tipocama + "&price=" + price  + "&wc=" + wc + "&desc=" + desc, true);
        
       
        xmlhttp.send();


 
}




function update_estado_despesa(){

  var inputElement = document.createElement('input');

inputElement.type = "button"
inputElement.addEventListener('click', function(){
    gotoNode(result.name);
});

alert(inputElement);


}




setTimeout(start(), 1000);
setTimeout(chat(), 1000);
setTimeout(enter(), 1000);