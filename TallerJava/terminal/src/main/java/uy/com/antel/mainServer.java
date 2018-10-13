package uy.com.antel;
import java.net.*;
import java.io.*;

public class mainServer {
    public static void main(String[] args){

        try {
            ServerSocket socket = new ServerSocket(1500);

            System.out.println("Se inicia el servidor para recibir conexiones de la terminal.");
            Socket socketRecepcion = null;
            socketRecepcion = socket.accept();
            BufferedReader lectura;
            lectura = new BufferedReader(new InputStreamReader(socketRecepcion.getInputStream()));
            String usuario;
            String contraseña;
            String terminal;

            ResultadoOperacion Respuesta;

            ObjectInputStream StreamParaObjetosLectura = new ObjectInputStream(socketRecepcion.getInputStream());

            PrintWriter escritura;
            escritura=new PrintWriter(socketRecepcion.getOutputStream(),true);

            //Objeto para usar para enviar objeto de respuesta.
            ObjectOutputStream SrteamParaObjetosRespuesta = new ObjectOutputStream(socketRecepcion.getOutputStream());

            System.out.println("Esperando lectura de usuario y terminal cliente...");

            Object ObjetoRecibido = StreamParaObjetosLectura.readObject();

            if ( ! (ObjetoRecibido instanceof Credenciales)){
                System.out.println("No se ha recibido credencial del usuario para conectarse.");
                escritura.println("Error!!");
                return;
            }

            SrteamParaObjetosRespuesta.writeObject("Recibido");

            System.out.println("VER SI SE LLEGO ACA");

            Credenciales datosUsuario = (Credenciales) ObjetoRecibido;

            usuario = datosUsuario.getUsuario();

            contraseña = datosUsuario.getContraseña();

            terminal = datosUsuario.getTerminal();

            System.out.println("Usuario " + usuario + " recibido.");

            System.out.println("Contraseña usada: " + contraseña + ".");

            System.out.println("Terminal donde se conecta: " + terminal + ".");

            //PONERO CODIGO PARA VALIDAR EL USUARIO.

            System.out.println("El usuario " + usuario + "se ha conectado con exito");

            int contadorIteraicones = 0;

            while(true) {

                System.out.println("Esperando lectura tickets del usuario " + usuario + "...");
                ObjetoRecibido = StreamParaObjetosLectura.readObject();

                if(ObjetoRecibido instanceof TicketTerminal){
                    TicketTerminal ticket = (TicketTerminal) ObjetoRecibido;

                    System.out.println("Objeto ticket recibido:: " + ticket.toString());

                    //Llamar a la función que devuelve el número de ticket.

                } else if (ObjetoRecibido instanceof Integer){
                    Integer NroTicketAEliminar = (Integer) ObjetoRecibido;

                    System.out.println("Numero de ticket recibido:: " + NroTicketAEliminar);

                    //Llamar eliminacion de ticket.

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
