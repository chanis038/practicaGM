/*
 * To change this license requestader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open trequest template in trequest editor.
 */
package controladores;

import com.sun.net.httpserver.Headers;
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
           boolean authorized = false;
           Headers requesthaders=  request.getRequestHeaders();
           URI requestedUri = request.getRequestURI();

           ControlHeaders guard = new ControlHeaders();

          
           authorized=  guard.guardHeaders(requesthaders,requestedUri,null,request);
           System.out.println("autorizado: "+authorized);
           if(authorized){
                runRequest(requestedUri,request);  
           }

         }
        public void runRequest(URI requestedUri,HttpExchange request) throws IOException{
               
                 String response =getValuePath(requestedUri.toString());
                 
                 if(response.equals("E")){
                    response="{\"respuesta\":\" Pagina no encontrada.\"}";
                   request.sendResponseHeaders(404, response.length()); 
                 } 
                 else if(response.equals("{E}")){
                   response="{\"respuesta\":\" Error.\"}";
                   request.sendResponseHeaders(400, response.length());  
                 }
                 else{
                   request.sendResponseHeaders(200, response.length()); 
                 }
                 OutputStream os = request.getResponseBody();
                 os.write(response.toString().getBytes());
                 os.close();

               
         }
        public String getValuePath(String URI){
            System.out.println(URI);
              String values[]= URI.split("/");
              if(values.length > 3)
                  return "E@";
              else if ("messages".equals(values[1]))
                  return StorageData.getTag(values[2]);
              else if ("message".equals(values[1])){
                  try {
                    int id =Integer.parseInt(values[2]);
                    return StorageData.getId(id);  
                  } catch (Exception e) {
                     System.out.println("path value"+e);
                    }          
             }  
             return "{E}";
        }
    
}
