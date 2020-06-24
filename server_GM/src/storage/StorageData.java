/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class StorageData {
    
    private static  HashMap<String,facade> data;
    private static  HashMap<Integer,FacadeMassg> messages;
    private static int id= 0;           
    
    
    public static void setData() {
        StorageData.data = new HashMap<String,facade>();
    }

    public static void setMessages() {
        StorageData.messages = new HashMap<Integer,FacadeMassg>();
    }

    public static void setId(int id) {
        StorageData.id = id;
    }
    
    //**********funciones para manejar keys- ****************//
    public static boolean setData(String key, String secret){
        if(data.get(key) ==  null){
            data.put(key,new facade(key,secret));
            return true;
        }
        return false;
    }
     public static facade validateKey(String key){
        return  data.get(key);
    }
    
   //**********funciones para manejar los messages ***********//
     
    public static Integer setDataMssg(String mssg, String tag){
          id+=1;
         messages.put(id,new FacadeMassg(id,mssg,tag));
         return id;

    }
    
   public static String getId(Integer id){
        FacadeMassg mssg = messages.get(id);
        return "{\"id\":\""+mssg.getId()+"\",\"message\":\""+mssg.getMssg()+"\",\"message\":\""+mssg.getTag()+"\"}";
    }
   
    public static String getTag(String tag){
        String result ="[";
        boolean first=true;
        
        //busqueda de mensagges con el mismo tag
        for (Map.Entry<Integer, FacadeMassg> entry : messages.entrySet()) {
            
            //verficacion si el mensaje tiene el tag
            if(entry.getValue().getTag() == null ? tag == null : entry.getValue().getTag().equals(tag)){
                if(first)
                    first = false;
                else
                    result= result+",";
                
                result=result+"{\"id\":\""+entry.getValue().getId()+"\",\"message\":\""+entry.getValue().getMssg()+"\",\"message\":\""+entry.getValue().getTag()+"\"}"; 
            }       
        }
       
        return result+="]";  
    }
    
  

      
}
