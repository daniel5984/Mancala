/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mancala.Mancala;
import mancala.Tabuleiro;

/**
 * Esta Class é responsavel por enviar e receber os objects , contem um thread que fica 
 * a espera para ler os objetos que são enviados através do inputStream
 * @author DanielSilva
 */
public class Connect implements Runnable {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean aCorrer;
    private Tabuleiro tabuleiro;

    /**
     * Construtor da class connect que inicia as streams de output e input e incia o thread que fica à espera de de receber objects
     * @param socket o socket necessário para criar as streams
     * @param tabuleiro instancia de tabuleiro para executar metodos 
     * @throws IOException "atira" uma exceção
     */
    public Connect(Socket socket, Tabuleiro tabuleiro) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        this.tabuleiro = tabuleiro;
        new Thread(this).start();

    }

    /**
     * Este metodo é chamada tanto no client como no server para enviar ficheiros do server para client e vice-versa
     * @param obj é o object que queremos enviar 
     * @throws IOException "atira" exceção
     */
    public void enviarObjeto(Object obj) throws IOException {
        outputStream.reset();
        outputStream.writeObject(obj);
        outputStream.flush();
    
    }

    
     /**
     *Esta metodo é uma override da class Runnable que implementa um thread que fica a 
     * espera de objects e trata de gerenciar os objetos que recebe
     */
    @Override
    public void run() {
        aCorrer = true;
        while (aCorrer) {
            try {
                Object obj = inputStream.readObject();
                if (obj != null) {

                    if (obj instanceof infoRedeLadoServer) {
                        infoRedeLadoServer infoServer = (infoRedeLadoServer) obj;
                        // System.out.println("Recebemos um packet com do lado do Server  -> " + infoServer.getTipo() + " Buraco -> " + infoServer.getNumeroBuraco()); //Works
                        // System.out.println("Thread -> " + Thread.currentThread().getName());
                        // System.out.println("Objeto recebido do server -> " + infoServer.getNumeroBuraco());
                        this.tabuleiro.processarTurno(infoServer.getIDBuraco());
                    } else if (obj instanceof infoRedeLadoClient) {
                        infoRedeLadoClient infoClient = (infoRedeLadoClient) obj;
                        //System.out.println("Recebemos um packet com do lado do Client  -> " + infoClient.getTipo() + " Buraco -> " + infoClient.getNumeroBuraco()); //Works
                        //System.out.println("Thread -> " + Thread.currentThread().getName());
                        //System.out.println("Objeto recebido do client -> " + infoClient.getNumeroBuraco());
                        this.tabuleiro.processarTurno(infoClient.getIDBuraco());
                    }

                    if (obj instanceof String && !Mancala.getInfo().isServer()) { //Sou servidor quero 
                        //Mancala.getInfo().setJogador1((String) obj);
                        tabuleiro.atualizarLabelStatus((String) obj, 1);
                        //System.out.println("Mudar o nome do Client jogador 1  para  -> " + (String) obj);
                    } else if (obj instanceof String && Mancala.getInfo().isServer() == true) {

                        //Mancala.getInfo().setJogador2((String) obj);
                        tabuleiro.atualizarLabelStatus((String) obj, 2);
                        //System.out.println("Mudar o nome do Servidor jogador 2  para  -> " + (String) obj);
                    }
                }

            } catch (EOFException | SocketException ex) {
                aCorrer = false;

            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                System.out.println("Causa -> " + e.getCause().getClass());
            }

        }
    }

    /**
     * Quando queremos por exemplo reniciar executamos para parar os input e output streams
     * @throws IOException
     */
    public void close() throws IOException {
        aCorrer = false;
        inputStream.close();
        outputStream.close();
    }
}






























/*
  public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }
 */
