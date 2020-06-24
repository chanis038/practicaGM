/*
 * To change this license requestader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open trequest template in trequest editor.
 */
package controladores;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import storage.StorageData;

/**
 *
 * @author usuario
 */
public class ControlPostMassage implements HttpHandler {

        @Override
         public void handle(HttpExchange request) throws IOException {
                 // parse request
                 ControlHeaders guard = new ControlHeaders();
                 guard.guardHeaders(request);
                 
                 
          
         }

    
         public void runRequest(HttpExchange request) throws IOException{
                  JSONParser jsonParser = new JSONParser();
                  JSONObject jsonObject = null;
                  String response = "ok";
                  
                 //obtencion de parametros del bodyrequest. (formato Json) 
                try {
                    jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(request.getRequestBody(), "utf-8"));
                } catch (ParseException ex) {
                    System.out.println("Error interno no se pudo obtener  el bodyrequest");
                      response = "{\"respuesta\":\"Error\"}";
                      request.sendResponseHeaders(500, response.length());
                }
                
                 // valida parametros               
                 if(jsonObject.get("msg")!= null && jsonObject.get("tags") != null){
                   boolean  result = StorageData.setData(jsonObject.get("msg").toString(),jsonObject.get("tags").toString());
                    if (result){
                      response = "{\"respuesta\":\"Mensaje guardado correctamente.\"}";
                      request.sendResponseHeaders(200, response.length());
                      }
                  
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
