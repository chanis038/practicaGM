/*
 * To change this license requestader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open trequest template in trequest editor.
 */
package controladores;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import cyphre.Cyphre;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import org.json.simple.JSONObject;
import storage.*;


/**
 *
 * @author usuario
 */
public class ControlHeaders {

    /**
     *
     * @param requestaders
     * @param requestedUri
     * @param requestbody
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public boolean guardHeaders( Headers requestaders, URI requestedUri,JSONObject requestbody, HttpExchange request)throws IOException{
              
                boolean sendresponse = false;
                boolean authorized = false;
                String response ="inautorizado";
                 String Hkey= "";
                 String Hroute = "";
                 String Hsingnature= "";
                 GetParams params = new GetParams();
                 //optencion de los headers
                 try{
                 Hkey= requestaders.get("X-key").get(0);
                 Hroute = requestaders.get("X-route").get(0);
                 Hsingnature= requestaders.get("X-signature").get(0);
                 }
                 catch(Exception e){
                     System.out.println("error no se pudo optener el header.");
                 }
                 // se verifica que vengan los 3 headers
                 if( "".equals(Hkey) || "".equals(Hroute) || "".equals(Hsingnature)){
                     sendresponse = true;
                     response= "inautorizado, no headers";
                 }
                 else{
                    //se optiene la key y shape_secret
                 facade keyShape= StorageData.getKey(Hkey);
                  //se verifica que exista la key 
                 if(keyShape ==null){
                   response= "inautorizado, key no existe";
                   sendresponse= true;
                 }
                 else{
                    // se obtiene los valores para realizar la validacion HASH 
                   ArrayList<String> values = new ArrayList<String>();
                   String valuesort="";
                   boolean first= true;
                                    
                   values.add(Hroute);
                   if(Hroute.split(" ")[0].toLowerCase().equals("post") ){
                     JSONObject paramsValue = requestbody;
                     values.add("msg/"+paramsValue.get("msg"));
                     values.add("tags/"+paramsValue.get("tags"));
                   }
                   else{
                       System.out.println(requestedUri);
                    String value = params.getValuePath(requestedUri);
                    values.add(value);
                   }
                   
                   //se ordena la lista y se crea el string para optener el valor HASH
                   Collections.sort(values);
                    for (String value : values) {
                      if(first){
                          first= false;
                          valuesort = valuesort + value;
                      }
                      else
                        valuesort = valuesort +";"+ value;
                   }
                    
                    System.out.println(valuesort);
                    System.out.println(keyShape.getShared_secret());
                    
                   // se otiene y se compara el valor HASH 
                   Cyphre cyphrer = new Cyphre();
                   String code = cyphrer.Generate(keyShape.getShared_secret(), valuesort);
                   System.out.println(valuesort);
                   
                   if(code.equals(Hsingnature.toUpperCase())){
                       authorized = true;
                   }
                   else{
                    sendresponse = true;  
                   }
                   
                 }  
                 }
                

                 if(sendresponse){
                        request.sendResponseHeaders(403, response.length());
                        OutputStream os = request.getResponseBody();
                        os.write(response.toString().getBytes());
                        os.close(); 
                 }
                      
                 
                 return authorized;
                 
         }

   

}
