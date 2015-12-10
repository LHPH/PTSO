function enviar(i){
    var form=document.getElementById("oper");
    var pagina="index.jsp?logout=true";
    form.action=pagina;
    form.submit();
}

function mostrarRet(){
    var form = document.getElementById("form_ret");
    form.style.display='block';
}

function operRet(cant){
   limpiarRecibo();
   //var cant=document.getElementById("saldo_ret").value;
   enviarPeticion("hide=2&cant="+cant,"popupRecibo");
   ocultarRet();
   var form = document.getElementById("recibo");
   form.style.display='block';
}

function ocultarRet(){
    var form = document.getElementById("form_ret");
    form.style.display='none';
}

function mostrarSald(){
   enviarPeticion("hide=1","popupCons");
   var form = document.getElementById("form_cons");
    form.style.display='block';
}

function ocultarSald(){
    var form = document.getElementById("form_cons");
    form.style.display='none';
}

function transfCuenta(){
    var form = document.getElementById("form_trans");
    form.style.display='block';
}

function operTrans(){
   limpiarRecibo();
   var cuenta2 = document.getElementById("numCuenta2").value;
   var cant = document.getElementById("saldo_tra").value;
   if(cuenta2!='' && cant!=''){
       if(isNaN(cant)==false){
           enviarPeticion("hide=3&cant="+cant+"&cuenta2="+cuenta2,"popupRecibo");
           document.getElementById("numCuenta2").value="";
           document.getElementById("saldo_tra").value="";
           ocultarTrans();
           var form = document.getElementById("recibo");
           form.style.display='block';
       }
       else{
           alert("El campo de cuenta debe ser numerico");
       }
   }
   else{
       alert("No ha ingresado todos los campos");
   }
   
}

function ocultarTrans(){
    var form = document.getElementById("form_trans");
    form.style.display='none';
}

function mostrarMov(){
    enviarPeticion("hide=4","popupMov");
    var form = document.getElementById("form_mov");
    form.style.display='block';
}

function ocultarMov(){
    var form = document.getElementById("form_mov");
    form.style.display='none';
}

function mostrarTiempo(){
    var form = document.getElementById("form_rec");
     form.style.display='block';
}

function ocultarTiempo(){
     var form = document.getElementById("form_rec");
     form.style.display='none';
}

function operTiempo(){
   limpiarRecibo();
   var cant=document.getElementById("saldo_rec").value;
   var tel=document.getElementById("telefono").value;
   if(cant!='' && tel!=''){
       if(tel.length<10){
           alert("El telefono no tiene 10 digitos");
       }
       else{
           if(isNaN(cant)==false && isNaN(tel)==false){
               enviarPeticion("hide=5&cant="+cant+"&telefono="+tel,"popupRecibo");
               document.getElementById("saldo_rec").value="";
               document.getElementById("telefono").value="";
               ocultarTiempo();
               var form = document.getElementById("recibo");
               form.style.display='block';
           }
           else{
               alert("Los campos deben contener solo numeros");
           }
       }
      
   }
   else{
       alert("No ha completado los dos campos");
   }
   
}

function ocultarRecibo(){
    var form = document.getElementById("recibo");
    form.style.display='none';
}

function limpiarRecibo(){
    document.getElementById("popupRecibo").innerHTML="";
}

