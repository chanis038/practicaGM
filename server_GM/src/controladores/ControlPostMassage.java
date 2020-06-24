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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import org.json.simple.JSONObject;


import storage.StorageData;

/**
 *
 * @author usuario
 */
public class ControlPostMassage implements HttpHandler {

        @Override
         public void handle(HttpExchange request) throws IOException {
                 // parse request
            boolean authorized = false;
           Headers requesthaders=  request.getRequestHeaders();
           URI requestedUri = request.getRequestURI();
           GetParams params = new GetParams();
           JSONObject requestbody = params.getJson(request);
           ControlHeaders guard = new ControlHeaders();
            String Hroute ="";
          
           authorized=  guard.guardHeaders(requesthaders,requestedUri,requestbody,request);
           System.out.println("autorizado: "+authorized);
           if(authorized){
              Hroute = requesthaders.get("X-route").get(0);
           
            if(Hroute.split(" ")[0].toLowerCase().equals("post") ){
                     runRequest(requesthaders,requestbody,request);
                   }
                   else{
                    ControlGetMssgTag requestGet = new ControlGetMssgTag();
                    requestGet.runRequest(requestedUri,request);
                   }  
           }
  
         }
         
       
         public void handle(){}
         
         public void runRequest(Headers requesthaders,JSONObject requestbody,HttpExchange request) throws IOException{
   
                JSONObject jsonObject = null;
                String response = "ok";

               //obtencion de parametros del bodyrequest. (formato Json)
               jsonObject = requestbody;
                    
                if(jsonObject== null){ 
                      response = "{\"respuesta\":\"Error\"}";
                      request.sendResponseHeaders(500, response.length());
                }
                 // valida parametros               
                else if(jsonObject.get("msg")!= null && jsonObject.get("tags") != null){
                   int  result = StorageData.setDataMssg(jsonObject.get("msg").toString(),jsonObject.get("tags").toString());
                      response = "{\"id\":\""+String.valueOf(result)+"\"}";
                      request.sendResponseHeaders(200, response.length());
                      
                  
                 }
                 else{
                 response = "{\"respuesta\":\"Error en los parametros esperados\"}";
                 request.sendResponseHeaders(400, response.length());
                 }
                  //send response
                 OutputStream os = request.getResponseBody();
                 os.write(response.toString().getBytes());
                 os.close();   
         }
}
