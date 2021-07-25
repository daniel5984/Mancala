/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mancala.Connect;
/**
 *
 * @author DanielSilva
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private Connect conexao;
   
    public Server() throws IOException {

        serverSocket = new ServerSocket(6666);
        socket = serverSocket.accept();
        conexao = new  Connect(socket);

    }

    public void closeServer() throws IOException {

        serverSocket.close();
    }
    
    

}
