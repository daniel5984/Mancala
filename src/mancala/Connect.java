/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author DanielSilva
 */
public class Connect {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Connect(Socket socket) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        
        
        
    }
    
    
    
    public void EnviarObjeto(Object obj) throws IOException{
        outputStream.writeObject(obj);
        
    }
}
