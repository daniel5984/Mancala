/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import mancala.Tabuleiro;

/**
 * Esta Class é responsável pela enviar Objetos para o Client atraves da class
 * connect e também criar o socket necessário para fazer essa conexão
 * @author DanielSilva
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private Connect conexao;

   /**
     * O construtor inicializa o socket com o respetivo endereço IP neste caso é o localhost e a Porta
     * @param tabuleiro é uma instancia do tabuleiro que enviamos para a class Connect para depois chamar-mos metodos do tabuleiro.
     * @throws IOException "atira" uma exceção quando ocorre algum erro.
     */
    public Server(Tabuleiro tabuleiro) throws IOException {
        System.out.println("A iniciar Server");
        serverSocket = new ServerSocket(6666);
        socket = serverSocket.accept();
        conexao = new Connect(socket, tabuleiro);

    }

    /**
     * Fecha os inputs e outputs do na class Conexao e fecha os sockets
     * @throws IOException
     */
    public void closeServer() throws IOException {
        conexao.close();
        serverSocket.close();
    }

   /**
     * Metodo para enviar Objetos para o Client 
     * @param obj
     * @throws IOException ati
     */
    public void enviarObject(Object obj) throws IOException {
        conexao.enviarObjeto(obj);
    }

}





























/*
  public void objRecebido(Object obj) throws IOException {
        if (obj instanceof infoRede) {//Se recebeu um Packet do Cliente
            infoRede info = (infoRede) obj;
            System.out.println("Objeto recebido no server -> " + info.getNumeroBuraco());
            atualizaBuracos(info.getNumeroBuraco());
        }
    }
   public void setTabuleiro(Tabuleiro tabuleiro) {
        conexao.setTabuleiro(tabuleiro);
    }
 */
