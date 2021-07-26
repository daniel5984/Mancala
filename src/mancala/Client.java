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
import mancala.infoRede;

/**
 *
 * @author DanielSilva
 */
public class Client {

    private Socket socket;
    private Connect conexao;

    public Client(Tabuleiro tabuleiro) throws IOException {
        System.out.println("A iniciar Client");
        socket = new Socket("localhost", 6666);
        conexao = new Connect(socket, tabuleiro);

    }

    public void objetoRecebido(Object obj) {
        if (obj instanceof infoRede) {
            infoRede info = (infoRede) obj;

            System.out.println("Objeto recebido no Client -> " + info.getNumeroBuraco());

        }
    }

    public void atualizaBuracos(int numeroBuraco) throws IOException {

        //conexao.enviarObjeto(new infoRede("Jogada", numeroBuraco));
    }

    public void enviarObject(Object obj) throws IOException {
        conexao.enviarObjeto(obj);
    }

    public void closeClient() throws IOException {
        conexao.close();
        socket.close();
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        conexao.setTabuleiro(tabuleiro);
    }

}
