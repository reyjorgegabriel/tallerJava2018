package uy.com.antel;
import java.net.*;
import java.io.*;

public class ConexionCliente {

    private PrintWriter escritura;

    private BufferedReader lectura;


    public ConexionCliente() {

        try {
            Socket sock = new Socket("127.0.0.1", 1500);
            escritura = new PrintWriter(sock.getOutputStream(), true);
            lectura = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        } catch (IOException e) {
            System.out.println("Error intentando conectarse al socket 127.0.0.1 puerto 1500.\n");
            e.printStackTrace();
        }
    }


    public String enviarTicketAAgencia(String ticket) {

        escritura.println(ticket);

        //leer respuesta del servidor (de la agencia).
        try {
            return lectura.readLine();
        } catch (IOException e) {
            System.out.println("Error intentando enviar ticket a la agencia: " + ticket );
            e.printStackTrace();
            return "ERROR";
        }

    }

//Eliminar esta funcion.
/*    public static void main(String[] args){

        try {Socket sock = null;
            sock = new Socket("127.0.0.1",1500);

            PrintWriter escritura;
            BufferedReader teclado;
            String linea;
            teclado=new BufferedReader(new InputStreamReader(System.in));
            escritura=new PrintWriter(sock.getOutputStream(),true);


            BufferedReader lectura;
            lectura = new BufferedReader(new InputStreamReader(sock.getInputStream()));



            do {
                linea=teclado.readLine();
                escritura.println(linea);

                //leer respuesta
                String s = lectura.readLine();
                System.out.println(s);

            } while (linea.compareTo("#")!=0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
