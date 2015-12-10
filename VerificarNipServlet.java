/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tso.servlets;

import fi.tso.modelo.Cliente;
import fi.tso.modelo.Servidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TOSHIBA
 */
public class VerificarNipServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String nip=request.getParameter("nip");
            String tarjeta=request.getParameter("numTarjeta");
            
            boolean ind=false;
            
            String msg="INT|"+Servidor.VERIFICAR+"|"+tarjeta+"|"+nip;
            try{
                String resp=Cliente.conexion(msg);
                //String resp="1";
                HttpSession sesion = request.getSession();
                if(resp.equals("1")==true){
                
                    sesion.setAttribute("nip",nip);
                    sesion.setAttribute("numTarjeta",tarjeta);
                    sesion.setAttribute("otro","falso");
                    response.sendRedirect("Operaciones.jsp");
                }
                else{
                    
                    if(resp.equals("2")==true){
                        sesion.setAttribute("nip",nip);
                        sesion.setAttribute("numTarjeta",tarjeta);
                        sesion.setAttribute("otro","true");
                        response.sendRedirect("Operaciones.jsp");
                    }
                    else{
                        sesion.setAttribute("no_user","true");
                        response.sendRedirect("index.jsp");
                    }
                }
            }catch(Exception e){
                   
            }
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
        processRequest(request, response);
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
