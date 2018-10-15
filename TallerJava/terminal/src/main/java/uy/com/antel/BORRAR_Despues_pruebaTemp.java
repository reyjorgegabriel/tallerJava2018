package uy.com.antel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BORRAR_Despues_pruebaTemp {
    public static void main(String[] argumentos) {

        System.out.println("+++Se inicia quiem lanzara las conexiones a los clientes de las terminales.+++");

        // Se instancia una clase que sera el servidor de los clientes de las terminales y se lanza un hilo aparte para eso.
        Runnable servidorDeClientes = new ServidorClientes();
        Thread hilo = new Thread(servidorDeClientes);
        hilo.start();

    }
}
