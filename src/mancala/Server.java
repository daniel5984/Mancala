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

    public Server(Tabuleiro tabuleiro) throws IOException {
        System.out.println("A iniciar Server");
        serverSocket = new ServerSocket(6666);
        socket = serverSocket.accept();
        conexao = new Connect(socket, tabuleiro);

    }

    public void objRecebido(Object obj) throws IOException {
        if (obj instanceof infoRede) {//Se recebeu um Packet do Cliente
            infoRede info = (infoRede) obj;
            System.out.println("Objeto recebido no server -> " + info.getNumeroBuraco());
            atualizaBuracos(info.getNumeroBuraco());
        }
    }

    public void closeServer() throws IOException {
        conexao.close();
        serverSocket.close();
    }

    public void atualizaBuracos(int numeroBuraco) throws IOException {

        //conexao.enviarObjeto(new infoRede("Jogada", numeroBuraco));
    }

    public void enviarObject(Object obj) throws IOException {
        conexao.enviarObjeto(obj);
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        conexao.setTabuleiro(tabuleiro);
    }
}
