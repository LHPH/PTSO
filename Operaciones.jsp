<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cajero ATM</title>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/operaciones.css" />
        <script type="text/javascript" src="resources/js/operaciones.js" ></script>
        <script type="text/javascript" src="resources/js/ajax.js" ></script>
    </head>
    <body style="background-image: url('resources/img/fondo.png');overflow:hidden">
        
        <form id="oper" name="form:oper" method="post">
            
            <div id="ssss" align="center">
                <div class="panel panel-info" style="width:400px;margin-top: 100px;padding-bottom: 20px;" >
                  <div class="panel-heading" >Operaciones</div>
                  <%@include file="includes/formConsulta.jspf" %>
                  <%@include file="includes/formRetiro.jspf" %>
                  <%@include file="includes/formTransf.jspf" %>
                  <%@include file="includes/formMovimientos.jspf" %>
                  <%@include file="includes/formRecarga.jspf" %>
                  <%@include file="includes/recibo.jspf" %>
                  <br />
                  <table style=" border-collapse: separate;border-spacing: 7px;">
                     <tr><td><input type="button" value="Consultar Saldo" onclick="mostrarSald()" class="btn seg" /></td></tr>
                     <tr><td><input type="button" value="Retirar Efectivo" onclick="mostrarRet()" class="btn seg" /></td></tr>
                     <tr><td><input type="button" value="Transferir" onclick="transfCuenta()" class="btn seg" /></td></tr>
                     <tr><td><input type="button" value="Compra Tiempo Aire" onclick="mostrarTiempo()" class="btn seg"/></td></tr>
                     <tr><td><input type="button" value="Movimientos" onclick="mostrarMov()" class="btn seg" /></td></tr>
                     <tr><td><input type="hidden" id="val"  /></td></tr>
                  </table>
                  <br />
                  <input type="button" value="Salir" onclick="enviar(0)" class="btn seg2" />
                
              </div>
            </div>
        </form>
    </body>
</html>
