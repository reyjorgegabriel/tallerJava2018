package uy.com.antel;
import java.net.*;
import java.io.*;

public class mainServer {
    public static void main(String[] args){

        try {
            ServerSocket socket = new ServerSocket(1500);
            System.out.println("1");
            Socket socketRecepcion = null;
            socketRecepcion = socket.accept();
            BufferedReader lectura;
            lectura = new BufferedReader(new InputStreamReader(socketRecepcion.getInputStream()));


            PrintWriter escritura;
            escritura=new PrintWriter(socketRecepcion.getOutputStream(),true);




            while(true) {

                System.out.println("lectura");
                System.out.println("3");
                String s = lectura.readLine();
                System.out.println(s);
                //escribir algo
                escritura.println("Se recibió " + s);

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
