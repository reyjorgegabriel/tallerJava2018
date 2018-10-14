package uy.com.antel;
import java.net.*;
import java.io.*;

public class ConexionCliente {

    private PrintWriter escritura;

    private BufferedReader lectura;

    private ObjectOutputStream SteamParaObjetos;

    private ObjectInputStream StreamParaObjetosLectura;


    public ConexionCliente() {

        try {
            Socket sock = new Socket("127.0.0.1", 1500);
            escritura = new PrintWriter(sock.getOutputStream(), true);
            lectura = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            SteamParaObjetos = new ObjectOutputStream(sock.getOutputStream());

            StreamParaObjetosLectura = new ObjectInputStream(sock.getInputStream());

        } catch (IOException e) {
            System.out.println("Error intentando conectarse al socket 127.0.0.1 puerto 1500.\n");
            e.printStackTrace();
        }
    }


    public Object enviarDatoAAgencia(Object Informacion) {

        try {
            SteamParaObjetos.writeObject(Informacion);
        } catch (IOException e) {
            System.out.println("Error intentando enviar datos a la agencia. Valor objeto intentado enviar: " + Informacion.toString());
            e.printStackTrace();
        }

        //leer respuesta del servidor (de la agencia).
        try {
            Object objeto = StreamParaObjetosLectura.readObject();
            return objeto;
        } catch (IOException e) {
            System.out.println("Error I/O intentando recibir respuesta de agencia. Valor objeto enviado: " + Informacion.toString() );
            e.printStackTrace();
            return "ERROR";
        } catch (ClassNotFoundException e) {
            System.out.println("Error clase no encontrada intentando recibir respuesta de agencia. Valor objeto enviado: " + Informacion.toString() );
            e.printStackTrace();
            return "ERROR";
        }

    }

    public String enviarDatoAAgenciaStringRespuestaString(String dato) {

        escritura.println(dato);

        //leer respuesta del servidor (de la agencia).
        try {
            return lectura.readLine();
        } catch (IOException e) {
            System.out.println("Error intentando enviar datos a la agencia: " + dato );
            e.printStackTrace();
            return "ERROR";
        }

    }

    public String enviarDatoAAgenciaRescuepstaString(Object Informacion) {

        try {
            SteamParaObjetos.writeObject(Informacion);
        } catch (IOException e) {
            System.out.println("Error intentando enviar datos a la agencia. Valor objeto intentado enviar: " + Informacion.toString());
            e.printStackTrace();
        }

        //leer respuesta del servidor (de la agencia).
        try {
            return lectura.readLine();
        } catch (IOException e) {
            System.out.println("Error intentando recibir respuesta de agencia. Valor objeto enviado: " + Informacion.toString()  );
            e.printStackTrace();
            return "ERROR";
        }

    }
}
