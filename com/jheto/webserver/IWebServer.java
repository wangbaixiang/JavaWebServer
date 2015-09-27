package com.jheto.webserver;

import java.net.Socket;
import java.util.Hashtable;

/***
 * This a base to create customs WebServers
 * 
 * @author Jheto Xekri
 * @license LGPL
 * @see https://github.com/JhetoX/JavaWebServer
 */
public interface IWebServer {
    
    public void receiveRequest(WebServer ws, Socket s, 
            Hashtable<String,String> request, Hashtable<String,String> headers,
            Hashtable<String,String> paramsGET, Hashtable<String,String> paramsPOST,
            Hashtable<String,String> paramsBoundary, byte[] dataBoundary);
    
}
