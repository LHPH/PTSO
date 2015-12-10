function ingresar(i){
    var entrada=document.getElementById("form:entrada");
    var valor=entrada.value;
    if(valor==null){
        valor="";
    }
    if(valor.length<4){
        entrada.value=valor+i;
    }
}

function borrar(){
    var entrada=document.getElementById("form:entrada");
    var valor=entrada.value;
    if(valor==null){
        valor="";
    }
    if(valor!=""){
        var aux="";
        var i=0;
        for(i=0;i<valor.length-1;i++){
            aux=aux+valor.charAt(i);
        }
        valor=aux;
        entrada.value=valor;
    }
    
}
