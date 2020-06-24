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
public class ControlPutCredencial implements HttpHandler {

            @Override
         public void handle(HttpExchange request) throws IOException {
                 // parse request
                 
                  JSONParser jsonParser = new JSONParser();
                  JSONObject jsonObject = null;
                  String response = "ok";
                  
                //obtencion de parametros del bodyrequest. (formato Json) 
                try {
                    jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(request.getRequestBody(), "utf-8"));
                } catch (ParseException ex) {
                    System.out.println("Error interno no se pudo obtener  el bodyrequest");
                      response = "{\"respuesta\":\" Error.\"}";
                      request.sendResponseHeaders(500, response.length());
                }
                 
                // valida parametros
                 if(jsonObject.get("key")!= null && jsonObject.get("shared_secret") != null){
                    //almacena datos 
                   boolean  result = StorageData.setData(jsonObject.get("key").toString(), jsonObject.get("shared_secret").toString());
                   
                   //verifica el resultado
                   if (result){
                       request.sendResponseHeaders(204, response.length());
                      }
                    else{
                         response = "{\"respuesta\":\" key ya existe\"}";
                         request.sendResponseHeaders(403, response.length());
                      }
                 }
                 else{
                 response = "{\"respuesta\":\" Error en los parametros esperados\"}";
                 request.sendResponseHeaders(400, response.length());
                 }

                  //envia respuesta
                 OutputStream os = request.getResponseBody();
                 os.write(response.toString().getBytes());
                 os.close();
         }

   


    
}
