/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import java.io.IOException;
import java.net.Socket;
import mancala.Tabuleiro;

/**
 * Esta Class é responsável pela enviar Objetos para o server atraves da class
 * connect e também criar o socket necessário para fazer essa conexão
 *
 * @author DanielSilva
 */
public class Client {

    private Socket socket;
    private Connect conexao;

    /**
     * O construtor inicializa o socket com o respetivo endereço IP neste caso é o localhost e a Porta
     * @param tabuleiro é uma instancia do tabuleiro que enviamos para a class Connect para depois chamar-mos metodos do tabuleiro.
     * @throws IOException "atira" uma exceção quando ocorre algum erro.
     */
    public Client(Tabuleiro tabuleiro) throws IOException {
        System.out.println("A iniciar Client");
        socket = new Socket("localhost", 6666);
        conexao = new Connect(socket, tabuleiro);

    }

    /**
     * Metodo para enviar Objetos para o Server 
     * @param obj
     * @throws IOException ati
     */
    public void enviarObject(Object obj) throws IOException {
        conexao.enviarObjeto(obj);
    }

    /**
     * Metodo para fechar o socket e a conexão
     * @throws IOException "atira" uma exceção quando ocorre algum erro.
     */
    public void closeClient() throws IOException {
        conexao.close();
        socket.close();
    }

}































/*
    public void objetoRecebido(Object obj) {
        if (obj instanceof infoRede) {
            infoRede info = (infoRede) obj;

            System.out.println("Objeto recebido no Client -> " + info.getNumeroBuraco());

        }
    }

 public void atualizaBuracos(int numeroBuraco) throws IOException {

        //conexao.enviarObjeto(new infoRede("Jogada", numeroBuraco));
    }

   public void setTabuleiro(Tabuleiro tabuleiro) {
        conexao.setTabuleiro(tabuleiro);
    }
 */
