package edu.duke.ece651.team13.server;

import edu.duke.ece651.team13.shared.V1Map;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private final int portNum;
    private ServerSocket serverSocket;

    public Server(int portNum){
        this.portNum = portNum;
        buildServer();
    }

    public void buildServer(){
        try {
            this.serverSocket = new ServerSocket(portNum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMesgTo(Object mesg, Socket clientSocket){
        try {
            BufferedOutputStream clientBufferedStream = new BufferedOutputStream(clientSocket.getOutputStream());
            ObjectOutputStream clientObjectStream = new ObjectOutputStream(clientBufferedStream);
            clientObjectStream.writeObject(mesg);
            clientObjectStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMapToClient(){
        try{
            Socket clientSocket = this.serverSocket.accept();
            V1Map map = new V1Map(1);
            sendMesgTo(map, clientSocket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}