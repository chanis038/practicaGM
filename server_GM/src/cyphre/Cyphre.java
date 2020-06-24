/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyphre;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author usuario
 */
public class Cyphre {
    
          Charset asciiCs;
          Mac sha256_HMAC;
          SecretKeySpec secret_key;
          byte[] mac_data;
           
           
        public String Generate(String shape_secret,String data) {
          String result = "";
           try {
           System.out.println("cifrando");
           asciiCs = Charset.forName("US-ASCII");
           sha256_HMAC = Mac.getInstance("HmacSHA256");
           secret_key = new javax.crypto.spec.SecretKeySpec(asciiCs.encode(shape_secret).array(),"HmacSHA256");
           sha256_HMAC.init(secret_key);
           mac_data = sha256_HMAC.doFinal(data.getBytes());
           result = DatatypeConverter.printHexBinary(mac_data);
         
           System.out.println("Result:[" + result + "]");
            
           }
           catch(InvalidKeyException e){
              System.out.println("error en el cifrador");  
           }
           catch(NoSuchAlgorithmException e){
              System.out.println("error en el cifrador"); 
           }
           return result;
         }
    
}
