*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tso.modelo;

/**
 *
 * @author TOSHIBA
 */
public class Funciones {
    
    public static String [][] getMovimientos(String cad){
       // cad="[DES|2000|24-OCT-2015][RET|1000|10-ABR-2015][TRA|1245|11-MAY-2015]";
        int num=0;
        for(int i=0;i<cad.length();i++){
            if(cad.charAt(i)=='['){
                num++;
            }
        }
        String [][] resp = new String[num][3];
        String fec="";
        int index=0;
        for(int i=0;i<cad.length();i++){
            char c=cad.charAt(i);
            if(c!=']' && c!='['){
                fec=fec+c;
            }
            if(c==']'){
                String [] vec=getRespuestas(fec);
                resp[index][0]=vec[0];
                resp[index][1]=vec[1];
                resp[index][2]=vec[2];
                index++;
                fec="";
            }
        }
        return resp;
    }
    
    public static String [] getRespuestas(String cad){
       // cad="VER|10203|20304";
        String [] resp=cad.split("\\|");
        return resp;
    }
    
    public static void main(String...arg){
        String [][] m = getMovimientos("");
        for(int i=0;i<m.length;i++){
            System.out.println(m[i][0]);
            System.out.println(m[i][1]);
            System.out.println(m[i][2]);
        }
        
        String [] m2 = getRespuestas("");
        for(int i=0;i<m2.length;i++){
            System.out.println(m2[i]);
        }
        
    }
    
}
