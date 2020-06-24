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
class FacadeMassg {
      private int id;
      private String mssg;
      private String tag;
       
      FacadeMassg( int id, String mssg, String tag){
       this.id= id;
       this.mssg=mssg;
       this.tag= tag;
      } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
     
}
