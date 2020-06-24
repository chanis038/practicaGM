/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

/**
 *
 * @author usuario
 */
public class facade {
    String key = "";
    String shared_secret ="";

    public String getKey() {
        return key;
    }

    public String getShared_secret() {
        return shared_secret;
    }
    
    facade(String key,String shared_secret){
        this.key = key;
        this.shared_secret = shared_secret;
    }
    
}
