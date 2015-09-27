package com.jheto.webserver;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Hashtable;

/***
 * This a base to create customs WebServers
 * 
 * @author Jheto Xekri
 * @license LGPL
 * @see https://github.com/JhetoX/JavaWebServer
 */
public class WebServer {

    private WebServer(){}
    
    public static WebServer getInstance(IWebServer callback, int port){
        WebServer ws = null;
        if(callback != null && port > 0){
            ws = new WebServer();
            ws.callback = callback;
            ws.port = port;
        }
        return ws;
    }
    
    private IWebServer callback = null;
    private int port = 0;
    
    private static String RETURN = "\r\n";
    
    private ServerSocket ss = null;
    
    public void stop() {
        try{
            ss.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void start() {
        try {
            ss = new ServerSocket(port);
            while(true){
                try{
                    Socket s = ss.accept();
                    InputStream is = s.getInputStream();
                    InputStreamReader r = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(r);
                    Hashtable<String,String>[] headers = readHeaders(br);
                    Hashtable<String,String> headersBoundary = null;
                    Hashtable<String,String> request = headers[1];
                    Hashtable<String,String> dataPOST = null;
                    Hashtable<String,String> dataGET = parseRequest(request);
                    byte[] dataBoundary = null;
                    String ContentType = getContentType(headers[0]);
                    if(ContentType != null && ContentType.length()>0){
                        String ProtocolMethod = request.get("Protocol-Method");
                        if(ProtocolMethod.equals("POST")){
                            if(ContentType.toLowerCase().contains("multipart/form-data")){
                                String boundary = getBoundary(headers[0]);
                                if(boundary.length()>0){
                                    headersBoundary = readHeadersBoundary(br, headers[0]);
                                    dataBoundary = readDataBoundary(is, headers[0]);
                                }
                            }
                            else dataPOST = readDataPost(br, headers[0]);
                        }
                    }
                    
                    if(headers != null && request != null) receiveRequest(s, request, headers[0], dataGET, dataPOST, headersBoundary, dataBoundary);
                    else finishQuery(s);
                    
                }catch(Exception e){}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //ok
    private Hashtable<String,String>[] readHeaders(BufferedReader br){
        Hashtable<String,String> headers = new Hashtable<String,String>();
        Hashtable<String,String> request = new Hashtable<String,String>();
        try{
            StringBuilder rawHeaders = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line != null && line.length()>0) rawHeaders.append(line + CRLF);
                else break;
            }
            String[] lines = rawHeaders.toString().trim().split(CRLF);
            
            try{
                String[] requestLine = lines[0].split(" ");
                request.put("Protocol-Method", requestLine[0].trim());
                request.put("Protocol-Request", requestLine[1].trim());
                request.put("Protocol-Version", requestLine[2].trim());
            }catch(Exception e){}
            
            for(int i=1; i<lines.length; i++){
                String param = lines[i];
                if(param != null && param.indexOf(":") != -1){
                    int index = param.indexOf(":");
                    if(index != -1){
                        String key = param.substring(0, index).trim();
                        String value = param.substring(index + 1).trim();
                        if(key != null && key != value) headers.put(key, value);
                    }
                }
                else break;
            }
        }catch(Exception e){}
        return new Hashtable[]{headers, request};
    }
    
    //ok
    private String getContentType(Hashtable<String,String> headers){
        String type = null;
        try{
            Enumeration keys = headers.keys();
            while(keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                String keyLow = key.toLowerCase();
                if(keyLow.equals("content-type")){
                    type = headers.get(key);
                    break;
                }
            }
        }catch(Exception e){
            type = null;
        }
        return type;
    }
    
    //ok
    private long getContentLength(Hashtable<String,String> headers){
        long size = 0;
        try{
            Enumeration keys = headers.keys();
            while(keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                String keyLow = key.toLowerCase();
                if(keyLow.equals("content-length")){
                    size = Long.parseLong(headers.get(key));
                    break;
                }
            }
        }catch(Exception e){
            size = 0;
        }
        return size;
    }
    
    //ok
    private String getBoundary(Hashtable<String,String> headers){
        String boundary = null;
        try{
            long ContentLength = getContentLength(headers);
            String ContentType = getContentType(headers);
            if(ContentLength >0){
                if(ContentType.contains("multipart/form-data")){
                    String contentType = ContentType.replace("multipart/form-data;", "").trim();
                    boundary = contentType.replace("boundary=", "").trim();
                }
            }
        }catch(Exception e){}
        return boundary;
    }
    
    private final static String CRLF = "\r\n";
    
    //ok
    private Hashtable<String,String> readDataPost(BufferedReader br, Hashtable<String,String> headers){
        Hashtable<String,String> vars = new Hashtable<String,String>();
        try{
            StringBuilder rawHeaders = new StringBuilder();
            String line = null;
            while (true) {
                line = br.readLine();
                if(line != null && line.length()>0) rawHeaders.append(line + CRLF);
                else break;
            }
            
            long ContentLength = getContentLength(headers);
            String ContentType = getContentType(headers);
            
            if(ContentLength > 0 && rawHeaders.length()>0){
                if(ContentType.equals("application/json")){
                    String json = rawHeaders.substring(1).trim();
                    vars.put("json", json);
                }
                else if(ContentType.equals("application/x-www-form-urlencoded")){
                    String[] lines = rawHeaders.toString().split("&");
                    if(lines != null && lines.length>0){
                        for(int j=0; j<lines.length; j++){
                            int index = lines[j].indexOf("=");
                            if(index != -1){
                                String key = lines[j].substring(0, index).trim();
                                String value = "";
                                    try{
                                        value = lines[j].substring(index +1).trim();
                                    }catch(Exception e){}
                                value = URLDecoder.decode(value, "UTF-8");
                                vars.put(key, value);
                            }
                        }
                    }
                }    
            }
        }catch(Exception e){}
        return vars;
    }
    
    //ok
    private Hashtable<String,String> parseRequest(Hashtable<String,String> request){
        Hashtable<String,String> vars = new Hashtable<String,String>();
        try{
            String path = request.get("Protocol-Request");
            int index = path.indexOf("?");
            if(index != -1){
                path = path.substring(index + 1);
                String[] lines = path.split("&");
                if(lines != null && lines.length>0){
                    for(int i=0; i<lines.length; i++){
                        index = lines[i].indexOf("=");
                        if(index != -1){
                            String key = lines[i].substring(0, index);
                            String value = "";
                            try{
                                value = lines[i].substring(index + 1).trim();
                            }catch(Exception e){}
                            value = URLDecoder.decode(value, "UTF-8");
                            vars.put(key, value);
                        }
                    }
                }
            }
        }catch(Exception e){}
        return vars;
    }
    
    //ok
    private Hashtable<String,String> readHeadersBoundary(BufferedReader br, Hashtable<String,String> headers){
        Hashtable<String,String> boundaryParams = new Hashtable<String,String>();
        try{
            String boundary = getBoundary(headers);
            StringBuilder rawHeaders = new StringBuilder();
            String line = null;
            boolean appendLines = false;
            while (true) {
                line = br.readLine();
                if(line != null && line.length()>0) {
                    boolean isHeaderBoundary = (line.startsWith("--" + boundary))? true:false;
                    if(isHeaderBoundary) appendLines = true;
                    else if(appendLines) rawHeaders.append(line + CRLF);
                }
                else break;
            }
            String[] lines = rawHeaders.toString().trim().split(CRLF);
            for(int i=0; i<lines.length; i++){
                String param = lines[i];
                if(param != null && param.indexOf(":") != -1){
                    int index = param.indexOf(":");
                    if(index != -1){
                        String key = param.substring(0, index).trim();
                        String value = "";
                        try{
                            value = param.substring(index + 1).trim();
                        }catch(Exception e){}
                        if(key != null && key != value) boundaryParams.put(key, value);
                    }
                }
                else break;
            }
        }catch(Exception e){}
        return boundaryParams;
    }
    
    private byte[] readDataBoundary(InputStream is, Hashtable<String,String> headers){
        ByteArrayOutputStream binaryFile = new ByteArrayOutputStream();
        try{
            String boundary = getBoundary(headers);
            byte[] bytes = new byte[1024];
            int numRead = 0;
            while (true){
                numRead = is.read(bytes);
                String line = new String(bytes, 0, numRead);
                if(line != null && !line.contains(boundary)){
                    binaryFile.write(bytes, 0, numRead);
                    System.out.println(line);
                }
                else break;
            }
        }catch(Exception e){}
        return binaryFile.toByteArray();
    }
    
    public void sendResponse(Socket s, int code, String message, String content){
        try{
            content = (content != null && content.length()>0)? content:"";
            message = (message != null && message.length()>0)? message:"";
            OutputStream os = s.getOutputStream();
            String response = "";
            response += "HTTP/1.1 " + code+ " " + message + RETURN;
            response += "Server: (Linux/Android)" + RETURN;
            response += "Connection: close" + RETURN + RETURN;
            response += content;
            os.write(response.getBytes());
            os.close();
            s.close();
        }catch(Exception e){}
    } 
    
    private void receiveRequest(Socket s, 
            Hashtable<String,String> request, Hashtable<String,String> headers,  
            Hashtable<String,String> dataGET, Hashtable<String,String> dataPOST,
            Hashtable<String,String> headersBoundary, byte[] dataBoundary) {
        try{
            if(callback != null) callback.receiveRequest(this, s, request, headers, dataGET, dataPOST, headersBoundary, dataBoundary);
        }catch(Exception e){}
    }
    
    private void finishQuery(Socket s) {
        try{
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        IWebServer callback = new IWebServer(){
            @Override
            public void receiveRequest(WebServer ws, Socket s, Hashtable<String, String> request, Hashtable<String, String> headers, Hashtable<String, String> paramsGET, Hashtable<String, String> paramsPOST, Hashtable<String, String> paramsBoundary, byte[] dataBoundary) {
                String content = "<html><title>Phising Site</title><body>This can have a clone site for phising.</body></html>";
                //content = "<html><body>404 ERROR</body></html>";
                //ws.sendResponse(s, ResponseStatus.CODE_NOT_FOUND, ResponseStatus.MESSAGE_NOT_FOUND, content);
                ws.sendResponse(s, ResponseStatus.CODE_OK, ResponseStatus.MESSAGE_OK, content);
                ws.stop();
            }
        };
        
        WebServer ws = WebServer.getInstance(callback, 1234);
        ws.start();
    }
}
