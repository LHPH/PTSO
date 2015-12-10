*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tso.servlets;

import fi.tso.modelo.Cliente;
import fi.tso.modelo.Funciones;
import fi.tso.modelo.Servidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TOSHIBA
 */
public class OperacionHandlerServlet extends HttpServlet {
    
    
    private String TIPO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String operacion=request.getParameter("hide");
        
        HttpSession sesion = request.getSession();
        String nip=sesion.getAttribute("nip").toString();
        String tarjeta=sesion.getAttribute("numTarjeta").toString();
        Object otro = sesion.getAttribute("otro");
        System.out.println("NIP "+nip+" Tarjeta "+tarjeta+" otro "+otro);
        
        if(otro.equals("true")==true){
            TIPO="EXT";
        }
        else{
            TIPO="INT";
        }
         
        int op=Integer.parseInt(operacion);
        String mensaje=null;
        String cant=null;
        Object obj = request.getParameter("cant");
        if(obj!=null){
            cant=obj.toString();
        }
        String resp=null;
        String param=null;
        String [] valores;
        String [][] movimientos;
        PrintWriter out = response.getWriter();
        
        switch(op){
            case 0:
                response.sendRedirect("index.jsp");
                
                break;
            case 1:
                        param=TIPO+"|"+Servidor.CONSULTA+"|"+tarjeta+"|"+nip;
            try{
                        resp=Cliente.conexion(param);
                        valores=Funciones.getRespuestas(resp);
                        
                        mensaje="<table  style='border-collapse: separate;border-spacing: 5px 10px;'>";
                        mensaje=mensaje+"<tr>";
                        mensaje=mensaje+"<td><label style='color: black;font-weight: bold;'>No. Cuenta:</label></td>";
                        mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;'>"+valores[0]+"</label></td>";
                        mensaje=mensaje+"</tr>";

                        mensaje=mensaje+"<tr>";
                        mensaje=mensaje+"<td><label style='color: black;font-weight: bold;'>No. Tarjeta:</label></td>";
                        mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;'>"+tarjeta+"</label></td>";
                        mensaje=mensaje+"</tr>";

                        mensaje=mensaje+"<tr>";
                        mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Saldo:</label></td>";
                        mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>$"+valores[1]+"</label></td>";
                        mensaje=mensaje+"</tr></table>";

                        out.println(mensaje);
                    
                }
                catch(Exception e){
                    
                }
                
                out.close(); 
                break;
            case 2:
                //retirar
                param=TIPO+"|"+Servidor.RETIRO+"|"+tarjeta+"|"+nip+"|"+cant;
                try{
                    System.out.println(param);
                        resp=Cliente.conexion(param);
                        //resp="1234567|1000";
                        if(resp.equals("-1")==false){
                             valores=Funciones.getRespuestas(resp);
                        
                            mensaje="<table  style='border-collapse: separate;border-spacing: 5px 10px;'>";
                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>No. Cuenta:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>"+valores[0]+"</label></td>";
                            mensaje=mensaje+"</tr>";

                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Saldo:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>$"+valores[1]+"</label></td>";
                            mensaje=mensaje+"</tr>";

                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Cantidad Retirada:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>$"+cant+"</label></td>";
                            mensaje=mensaje+"</tr></table><br />";
                            
                            mensaje=mensaje+"<table style='border-collapse: separate;border-spacing: 5px 10px;'>";
                            mensaje=mensaje+"<tr><td>"+valores[2]+"</td><td><span class='label label-success'>$20</span></td></tr>";
                            mensaje=mensaje+"<tr><td>"+valores[3]+"</td><td><span class='label label-success'>$50</span></td></tr>";
                            mensaje=mensaje+"<tr><td>"+valores[4]+"</td><td><span class='label label-success'>$100</span></td></tr>";
                            mensaje=mensaje+"<tr><td>"+valores[5]+"</td><td><span class='label label-success'>$200</span></td></tr>";
                            mensaje=mensaje+"<tr><td>"+valores[6]+"</td><td><span class='label label-success'>$500</span></td></tr>";
                            mensaje=mensaje+"<tr><td>"+valores[7]+"</td><td><span class='label label-success'>$1000</span></td></tr>";
                            mensaje=mensaje+"</table><br />";
                            
                            
                            
                        }
                        else{
                            if(resp.equals("-1")==true){
                                mensaje="<div class='alert alert-danger' role='alert'>No posee suficiente saldo a retirar</div>";
                            }
                            if(resp.equals("-2")==true){
                                mensaje="<div class='alert alert-danger' role='alert'>No hay suficiente efectivo<br /> para realizar el retiro</div>";
                            }
                        }
                       
                        out.println(mensaje);
                        out.close(); 
                        
                }
                catch(Exception e){
                    
                }
                break;
            case 3:
                //transferir
                try{
                     String cuenta2=request.getParameter("cuenta2");
                    
                     param=TIPO+"|"+Servidor.TRANSFERIR+"|"+tarjeta+"|"+nip+"|"+cant+"|"+cuenta2;
                     System.out.println(param);
                     
                     resp=Cliente.conexion(param);
                     valores=Funciones.getRespuestas(resp);
                     if(resp.equals("-1")){
                          mensaje="<div class='alert alert-danger' role='alert'>No posee suficiente saldo<br />para la transferencia</div>";
                     }
                     else{
                         if(resp.equals("-2")){
                             mensaje="<div class='alert alert-danger' role='alert'>La cuenta para transferir saldo no existe</div>";
                         }
                         else{
                            mensaje="<table  style='border-collapse: separate;border-spacing: 5px 10px;'>";
                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Cuenta emisora:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>"+valores[0]+"</label></td>";
                            mensaje=mensaje+"</tr>";

                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Cuenta receptora:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>"+valores[1]+"</label></td>";
                            mensaje=mensaje+"</tr>";

                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Transferido:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>$"+cant+"</label></td>";
                            mensaje=mensaje+"</tr></table>";
                         }
                     }
                     out.println(mensaje);
                     out.close(); 
                }
                catch(Exception e){
                    
                }
                break;
            case 4:
                //movimientos
                param=TIPO+"|"+Servidor.MOVIMIENTOS+"|"+tarjeta+"|"+nip;
                
                try{
                    resp=Cliente.conexion(param);
                    movimientos=Funciones.getMovimientos(resp);
                    
                     mensaje="<table  style='border-collapse: separate;border-spacing: 5px 16px;'>";
                     mensaje=mensaje+"<tr><th>Movimiento</th><th>Cantidad</th><th>Fecha</th></tr>";
                     
                     for(int i=0;i<movimientos.length;i++){
                        mensaje=mensaje+"<tr>";
                        if(movimientos[i][1].equals("DEP")==true){
                            movimientos[i][1]="Deposito";
                        }
                        if(movimientos[i][1].equals("RET")==true){
                             movimientos[i][1]="Retiro";
                        }
                        if(movimientos[i][1].equals("TRA")==true){
                             movimientos[i][1]="Transferencia";
                        }
                        if(movimientos[i][1].equals("CIT")==true){
                             movimientos[i][1]="Compra Internet";
                        }
                        if(movimientos[i][1].equals("REC")==true){
                             movimientos[i][1]="Compra Tiempo Aire";
                        }
                        if(movimientos[i][1].equals("PCC")==true){
                             movimientos[i][1]="Retiro en Punto de Venta";
                        }
                        
                        mensaje=mensaje+"<td><label>"+movimientos[i][1]+"</label></td>";
                        mensaje=mensaje+"<td><label>"+"$"+movimientos[i][0]+"</label></td>";
                        mensaje=mensaje+"<td><label>"+movimientos[i][2]+"</label></td>";
                        mensaje=mensaje+"</tr>";
                     }
                     mensaje=mensaje+"</table>"; 
                     out.println(mensaje);
                }
                catch(Exception e){
                    
                }
                out.close(); 
                break;
            case 5:
                //Recarga de tiempo aire
                //retirar
                param=TIPO+"|"+Servidor.RECARGA+"|"+tarjeta+"|"+nip+"|"+cant;
                try{
                    //System.out.println(param);
                        resp=Cliente.conexion(param);
                        //resp="-1";
                        if(resp.equals("-1")==false){
                             valores=Funciones.getRespuestas(resp);
                             String telefono=request.getParameter("telefono");
                        
                            mensaje="<table  style='border-collapse: separate;border-spacing: 5px 10px;'>";
                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Telefono:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>"+telefono+"</label></td>";
                            mensaje=mensaje+"</tr>";

                            mensaje=mensaje+"<tr>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: bold;\'>Saldo Abonado:</label></td>";
                            mensaje=mensaje+"<td><label style='color: black;font-weight: lighter;\'>$"+cant+"</label></td>";
                            mensaje=mensaje+"</tr></table>";

                        }
                        else{
                            mensaje="<div class='alert alert-danger' role='alert'>No posee suficiente saldo para<br /> realizar estar transaccion</div>";
                        }
                       
                        out.println(mensaje);
                        out.close(); 
                        
                }
                catch(Exception e){
                    
                }
                
                break; 
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
