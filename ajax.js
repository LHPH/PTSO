var conexion;
var parametro;

function inicializar_xhr(){
    if(window.XMLHttpRequest){
           return new XMLHttpRequest();
        }
         else{
           return new ActiveXObject("Mcrosoft.XMLHTTP");
       }
}

function despliegaContenido(){
    if(conexion.readyState==4 && conexion.status==200){
            document.getElementById("mensaje").innerHTML=conexion.responseText
    }
}

function cargaContenido(url,metodo,funcion,mensaje){
      conexion=inicializar_xhr();
      if(conexion){
          conexion.onreadystatechange=function(){
               if(conexion.readyState==4 && conexion.status==200){
                         document.getElementById(mensaje).innerHTML=conexion.responseText;
    }
          };
          conexion.open("GET","oper?"+parametro,true);
          //conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          conexion.send(null);
          console.log("sssss");
      }
}

function enviarPeticion(param,mensaje){
    parametro=param;
    cargaContenido("oper","GET",despliegaContenido,mensaje);
}
