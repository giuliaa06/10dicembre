package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
   public static void main(String[] args) throws IOException{

    ServerSocket ss = new ServerSocket(8080);

     
while(true){
    Socket s = ss.accept();
    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    DataOutputStream out = new DataOutputStream(s.getOutputStream());

    String firstLine = in.readLine();
    System.out.println(firstLine);
    String[] request = firstLine.split(" ");

    String method = request[0];
    String resource = request[1];
    String version = request[2];

    String header;
    do{
        header = in.readLine();
        System.out.println(header);

    }while (!header.isEmpty());

if(resource.equals("/index.html" )|| resource.equals("/")){

/* ------versione senza file-------
   // String respondeBody = "Ciao a tutti <b>belli e brutti <img src = 'smile.png' /></b> del Meucci";
    out.writeBytes("HTTP/1 404 Not found\n");
    out.writeBytes("Content-Type: text/html\n");
    out.writeBytes("Content-Length: " + respondeBody.length() + " \n");
    out.writeBytes("nome: giulia\n");
    out.writeBytes("\n");
   // out.writeBytes(respondeBody);
}
*/
// -----versione con file------
File file = new File("htdocs/index.html");
InputStream input = new FileInputStream(file);
out.writeBytes("HTTP/1.1 200 OK\n");
out.writeBytes("Content-length: " + file.length() + "\n");
out.writeBytes("\n");
byte[] buf = new byte[8912];
int n;
while ((n= input.read(buf)) != -1){
    out.write(buf, 0, n);
}
input.close();

}else {
    String msg = "File non trovato";
}

    
}
   }

}