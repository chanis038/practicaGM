/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_core;


import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import controladores.*;
import Cors.Cors;
import java.net.URI;
/**
 *
 * @author usuario
 */
public class core{
   int port = 8080;
    HttpServer serverMG ;
    InetSocketAddress socket;
    
    public core() throws IOException{
        socket = new InetSocketAddress(port);
        HttpServer serverMG = HttpServer.create(socket, 0);
        System.out.println("serverMG iniciado en el puerto" + port);
        
        serverMG.createContext("/", new RaizHttp());
       //serverMG.createContext("/Header", new ControlHeaders());
        serverMG.createContext("/credencial", new ControlPutCredencial());
        serverMG.createContext("/message", new ControlPostMassage());
        serverMG.createContext("/messages", new ControlGetMssgTag());
        serverMG.setExecutor(null);
        serverMG.start();
        }

}
