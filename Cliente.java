/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tso.modelo;

import java.io.*;
import java.net.*;

public class Cliente {
    
    private static String direccion="192.168.1.82";
    private static int puerto=32003;
    
    public static void main(String[] args) throws IOException {


        String hostName = direccion;
        int portNumber = puerto;
        Socket firstSocket = new Socket(hostName, portNumber);
        PrintWriter out =new PrintWriter(firstSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Empezando a leer");
        while ((userInput = stdIn.readLine()) != null) 
        {
            System.out.println("Leyendo");
            char [] mensaje = new char[256];
        
            out.write(userInput);
            out.flush();
           
            in.read(mensaje);
            String m = "";
	    
            for(int i=0;i<mensaje.length;i++){
                
                if(mensaje[i]!=' ' && mensaje[i]!=0){
                      //System.out.println("+"+mensaje[i]+"+"+(int)mensaje[i]);
		      m=m+mensaje[i];
                }
            }
            System.out.println("Servidor dice "+m);
        }

        in.close();
        stdIn.close();
        firstSocket.close();
    }
    
    public static String conexion(String param) throws Exception{
        String resp=null;
        
        String hostName = direccion;
        int portNumber = puerto;
        
        Socket firstSocket = new Socket(hostName, portNumber);
        PrintWriter out =new PrintWriter(firstSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
     
            System.out.println("Leyendo");
            char [] mensaje = new char[256];
        
            out.write(param);
            out.flush();
           
            in.read(mensaje);
            String m = "";
	    
            for(int i=0;i<mensaje.length;i++){
                if(mensaje[i]!=' ' && mensaje[i]!=0){
                      //System.out.println("+"+mensaje[i]+"+"+(int)mensaje[i]);
		      m=m+mensaje[i];
                }
            }
            resp=m;

        in.close();
        firstSocket.close();
        
        return resp;
    }
}
