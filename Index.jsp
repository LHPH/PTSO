<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Cajero ATM</title>
        <link  rel="stylesheet" type="text/css" href="resources/css/nip.css">
        <link  rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
        <script type="text/javascript" src="resources/js/nip.js" ></script>
    </head>
    
    <body style="background-image: url('resources/img/fondo.png')" >
        
        <div id="sss" align="center">
            <div class="panel panel-info" align="center" style="width:600px;margin-top: 100px;padding-bottom: 20px;">
            <div class="panel-heading">Cajero ATM</div>
            
             <form name="form" method="post" action="vec">
                 <br />
                 <br />
                    <label>Numero de Tarjeta</label><br />
                    <input type="text" name="numTarjeta" id="form:tarjeta" class="form-control" style="width:300px;" />
                    <br />
                    <br />
                    <label>NIP</label>
                    <br />
                    <input type="text" readonly="true" class="form-control" style="width:300px;" name="nip" id="form:entrada" />
                    <br />
                    <table  >
                                <tr>
                                    <td><input type="button" value="1" onclick="ingresar(1)" class="btn seg" /></td>
                                    <td><input type="button" value="2" onclick="ingresar(2)" class="btn seg" /></td>
                                    <td><input type="button" value="3" onclick="ingresar(3)" class="btn seg" /></td>
                                </tr>

                                <tr>
                                    <td><input type="button" value="4" onclick="ingresar(4)" class="btn seg" /></td>
                                    <td><input type="button" value="5" onclick="ingresar(5)" class="btn seg" /></td>
                                    <td><input type="button" value="6" onclick="ingresar(6)" class="btn seg" /></td>
                                </tr>

                                <tr>
                                    <td><input type="button" value="7" onclick="ingresar(7)" class="btn seg" /></td>
                                    <td><input type="button" value="8" onclick="ingresar(8)" class="btn seg" /></td>
                                    <td><input type="button" value="9" onclick="ingresar(9)" class="btn seg" /></td>
                                </tr>

                                <tr>
                                    <td><input type="button" value="<-" onclick="borrar()" class="btn seg"/></td>
                                    <td><input type="button" value="0" onclick="ingresar(0)" class="btn seg"/></td>
                                    <td><input type="submit" value="Continuar" class="btn seg"/></td>
                                </tr>
                    </table>
            
        </form>
             <%
                 String msg="";
                 Object s = session.getAttribute("no_user");
                 if(s!=null){
                     msg="<br /></br /><div class='alert alert-danger' style='width:290px;' role='alert'>"
                             + "Error de autentificacion.<br />Verifique su NIP y su Numero de Tarjeta<br /> sean correctos</div>";
                 }
                 session.removeAttribute("no_user");
                 
                 s = request.getParameter("logout");
                 if(s!=null){
                     session.removeAttribute("nip");
                     session.removeAttribute("numTarjeta");
                     session.removeAttribute("otro");
                 }
                 
                 
                 %>
                 <%= msg %>
       </div>
            
      </div>  
    </body>
    
</html>
