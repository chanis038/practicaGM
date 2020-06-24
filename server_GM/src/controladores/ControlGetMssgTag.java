/*
 * To change this license requestader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open trequest template in trequest editor.
 */
package controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import storage.StorageData;
/**
 *
 * @author usuario
 */
public class ControlGetMssgTag implements HttpHandler {

         @Override
         public void handle(HttpExchange request) throws IOException {
                 // parse request
                URI requestedUri = request.getRequestURI();
                 String response =getValuePath(requestedUri.toString());
                 
                 if(!"E@".equals(response)){
                   request.sendResponseHeaders(200, response.length());  
                 } 
                 else{
                   response="{\"respuesta\":\" Pagina no encontrada.\"}";
                   request.sendResponseHeaders(404, response.length());   
                 }
                 OutputStream os = request.getResponseBody();
                 os.write(response.toString().getBytes());
                 os.close();

         }
       
        public String getValuePath(String URI){
              String values[]= URI.split("/");
              if(values.length > 2)
                  return "E@";
              else if ("messages".equals(values[0]))
                  return StorageData.getTag(values[1]);
              else if ("message".equals(values[0])){
                  try {
                    return StorageData.getId(Integer.parseInt(values[1]));  
                  } catch (Exception e) {
                     System.out.println(e);
                    }          
             }  
             return "{}";
        }
    
}
