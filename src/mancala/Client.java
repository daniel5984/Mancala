/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DanielSilva
 */
public class Client {

    private Socket socket;

    public Client() throws IOException {

        socket = new Socket("localhost", 6666);

    }

    public void closeClient() throws IOException {
        socket.close();
    }

}
