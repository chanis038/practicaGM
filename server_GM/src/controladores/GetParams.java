/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author usuario
 */
public class GetParams {

    public GetParams() {
    }
    
    
       public JSONObject getJson(HttpExchange request) throws IOException{
                  JSONParser jsonParser = new JSONParser();
                  JSONObject jsonObject = null;                 
                 //obtencion de parametros del bodyrequest. (formato Json) 
                 InputStreamReader requestbody= new InputStreamReader(request.getRequestBody(), "utf-8");
                try {
                   //requestbody.reset();
                   jsonObject = (JSONObject)jsonParser.parse(requestbody);
                   jsonParser.reset(requestbody);
                  
                } catch (ParseException ex) {
                   System.out.println("Error interno no se pudo obtener  el bodyrequest" +ex);
                }
                return jsonObject;
         }
       
         public String getValuePath(URI requestedUri){
               String values[]= requestedUri.toString().split("/");
              if(values.length > 3)
                  return "E";
              else
                  return values[2];
        }
    
}
