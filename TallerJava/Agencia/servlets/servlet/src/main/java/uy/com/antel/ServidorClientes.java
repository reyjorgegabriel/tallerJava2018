package uy.com.antel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorClientes implements Runnable {

    public void run() {

        try {
            ServerSocket socketServidor = new ServerSocket(1501);

            System.out.println("Se inicia el servidor para recibir conexiones de todas las terminales.\n");

            int CantidadTerminales = 0;

            while (true) {

                Socket socketClienteTerminalIndividual = new Socket();

                // Se espera y acepta un nueva conecxion a una terminal.
                socketClienteTerminalIndividual = socketServidor.accept();

                CantidadTerminales++;

                System.out.println("////Se inicia la conecxion con la terminal numero " + CantidadTerminales + "///");

                // Se instancia una clase para atender a la terminal cliente y se lanza en
                // un hilo aparte para eso.
                Runnable nuevaTerminalCliente = new ServidorClienteIndividual(socketClienteTerminalIndividual);
                Thread hilo = new Thread(nuevaTerminalCliente);
                hilo.start();
            }

        } catch (IOException e) {
            System.out.println("Clase ServidorClientes:: ERROR: Problemas en el Servidor para leer/escribir objeto.");
            e.printStackTrace();
        }


    }
}
