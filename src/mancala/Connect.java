/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DanielSilva
 */
public class Connect implements Runnable {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean aCorrer;
    private Tabuleiro tabuleiro;

    public Connect(Socket socket, Tabuleiro tabuleiro) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        this.tabuleiro = tabuleiro;
        new Thread(this).start();

    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void enviarObjeto(Object obj) throws IOException {
        outputStream.reset();
        outputStream.writeObject(obj);
        outputStream.flush();
        if (obj == null) {
            System.out.println("Null linha 44 em Connect -> ");
        }

    }

    @Override
    public void run() {
        aCorrer = true;
        while (aCorrer) {
            try {
                Object obj = inputStream.readObject();
                if (obj != null) {

                    if (obj instanceof infoRedeLadoServer) {
                        infoRedeLadoServer infoServer = (infoRedeLadoServer) obj;
                        System.out.println("Recebemos um packet com do lado do Server  -> " + infoServer.getTipo() + " Buraco -> " + infoServer.getNumeroBuraco()); //Works
                        System.out.println("Thread -> " + Thread.currentThread().getName());
                        System.out.println("Objeto recebido do server -> " + infoServer.getNumeroBuraco());
                        this.tabuleiro.processarTurno(infoServer.getIDBuraco());
                    } else if (obj instanceof infoRedeLadoClient) {
                        infoRedeLadoClient infoClient = (infoRedeLadoClient) obj;
                        System.out.println("Recebemos um packet com do lado do Client  -> " + infoClient.getTipo() + " Buraco -> " + infoClient.getNumeroBuraco()); //Works
                        System.out.println("Thread -> " + Thread.currentThread().getName());
                        System.out.println("Objeto recebido do client -> " + infoClient.getNumeroBuraco());
                        this.tabuleiro.processarTurno(infoClient.getIDBuraco());
                    }

                    if (obj instanceof String && !Mancala.getInfo().isServer()) { //Sou servidor quero 
                        //Mancala.getInfo().setJogador1((String) obj);
                        tabuleiro.atualizarLabelStatus((String) obj, 1);
                        System.out.println("Mudar o nome do Client jogador 1  para  -> " + (String) obj);
                    } else if (obj instanceof String && Mancala.getInfo().isServer() == true) {

                        //Mancala.getInfo().setJogador2((String) obj);
                        tabuleiro.atualizarLabelStatus((String) obj, 2);
                        System.out.println("Mudar o nome do Servidor jogador 2  para  -> " + (String) obj);
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

    public void close() throws IOException {
        aCorrer = false;
        inputStream.close();
        outputStream.close();
    }
}
