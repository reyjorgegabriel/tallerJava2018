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


            ObjectInputStream SteamParaObjetos = new ObjectInputStream(socketRecepcion.getInputStream());


            PrintWriter escritura;
            escritura=new PrintWriter(socketRecepcion.getOutputStream(),true);

            int contadorIteraicones = 0;

            while(true) {

                System.out.println("Esperando lectura de clientes...");
                Object ObjetoPasado = SteamParaObjetos.readObject();

                if(ObjetoPasado instanceof TicketTerminal){
                    TicketTerminal ticket = (TicketTerminal) ObjetoPasado;

                    System.out.println("Objeto ticket recibido:: " + ticket.toString());

                    //Llamar a la función que devuelve el número de ticket.

                } else if (ObjetoPasado instanceof Integer){
                    Integer NroTicketAEliminar = (Integer) ObjetoPasado;

                    System.out.println("Numero de ticket recibido:: " + NroTicketAEliminar);

                }
                escritura.println(contadorIteraicones);

                contadorIteraicones++;

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Problemas en el Servidor para leer el objeto enviado");
            e.printStackTrace();
        }

// PARTE PRBADA PARA TRABAJR CON STRINGS (TRANSFERIR STRINGS DEL CLIENTE AL SERVIDOR
/*        try {
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
*/
    }
}
