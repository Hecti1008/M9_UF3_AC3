/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activitat3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Hector
 */
public class AC3 {
    @SuppressWarnings("rawtypes")
        public static void main (String[] args) throws IOException{
            String urlLoc = args[0];
            int numCamps = Integer.parseInt(args[1]);
            String text = args[2];
            
                try{
                    String cadena;
                    
                    URL url = new URL(urlLoc);
                    URLConnection conexio = url.openConnection();
                    
                    System.out.println("=======================");
                    System.out.println("Adreça, Data i Contingut");
                    System.out.println("Adreça [getURL]" + conexio.getURL());
                    
                    Date data = new Date(conexio.getLastModified());
                    System.out.println("Data ulrima modificacio [getLastModified()]" + data);
                    System.out.println("Tipus de contingut [getContentType()]" + conexio.getContentType());
                    
                    System.out.println("==========================");
                    System.out.println("Tots els camps de capçelera amb getHeaderFields(): ");
                    
                    Map campsCapçelera = conexio.getHeaderFields();
                    Iterator it = campsCapçelera.entrySet().iterator();
                    
                    while (it.hasNext()) {
                        Map.Entry map = (Map.Entry) it.next();
                        System.out.println(map.getKey() + ":" + map.getValue());
                    }
                    
                    System.out.println("===========================");
                    System.out.println("Camps" + numCamps);
                    
                    for (int i=1; i <= numCamps; i++) {
                        System.out.println("getHeaderField(" + i + ")=>" + conexio.getHeaderField(i));
                    }
                    
                    System.out.println("=============================");
                    
                    System.out.println("Contingut de [url.getFile()]: " + url.getFile());
                    BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
                    
                    while ((cadena = pagina.readLine()) != null) {
                        if(cadena.contains(text.subSequence(0, text.length()-1))) {
                            System.out.println(cadena);
                        }
                    }
                }
                catch (MalformedURLException e) { e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
        }
}
