package uy.com.antel;

import java.io.*;
import java.net.Socket;

public class ServidorClienteIndividual implements Runnable {

    private Socket socketRecepcion;

    public ServidorClienteIndividual(Socket socketAAtender) {

        socketRecepcion = socketAAtender;
    }

    //@Override
    public void run() {
        try {
            //ServerSocket socket = new ServerSocket(1500);

            System.out.println("--Se inicia el servidor para atender a una nueva terminal que quiere conectarse.");

            String usuario;
            String contraseña;
            String terminal;

            ResultadoOperacionPosta respuesta = new ResultadoOperacionPosta();

            ObjectInputStream StreamParaObjetosLectura = new ObjectInputStream(socketRecepcion.getInputStream());

            //Objeto para usar para enviar objeto de respuesta.
            ObjectOutputStream SrteamParaObjetosRespuesta = new ObjectOutputStream(socketRecepcion.getOutputStream());

            AdminUsuariosAgencia controlAcceso = AdminUsuariosAgencia.getInstance();

            Object ObjetoRecibido;

            boolean AccesoPermitido = false;

            do {

                System.out.println("Esperando lectura de usuario y terminal cliente...");

                ObjetoRecibido = StreamParaObjetosLectura.readObject();

                if (ObjetoRecibido instanceof String) {
                    if (((String) ObjetoRecibido).equals("salida")) {
                        System.out.println("     \\\\\\--- Usuario ha desistido de conectarse. ---\\\\\\");

                        //Se envia mensaje a cliente que se cierra el servidor.
                        //No es ecesario hacer socket.close(), ni socketRecepcion.close(), ni lectura.close(), ni StreamParaObjetosLectura.close();
                        //alcanzando enviar mensaje de salida al cliente y haciendo return.
                        SrteamParaObjetosRespuesta.writeObject("salida");
                        return;
                    } else {
                        System.out.println("     \\\\\\---Se reusa la conexion por envio string incorrecto.---\\\\\\");

                        //Se envia mensaje a cliente que se cierra el servidor.
                        //No es ecesario hacer socket.close(), ni socketRecepcion.close(), ni lectura.close(), ni StreamParaObjetosLectura.close();
                        //alcanzando enviar mensaje de salida al cliente y haciendo return.
                        SrteamParaObjetosRespuesta.writeObject("salida");
                        return;
                    }
                } else if (!(ObjetoRecibido instanceof Credenciales)) {
                    System.out.println("Error!!: No se ha recibido credencial del usuario para conectarse.");
                    SrteamParaObjetosRespuesta.writeObject("ERROR!!");
                    return;
                }

                Credenciales datosUsuario = (Credenciales) ObjetoRecibido;

                usuario = datosUsuario.getUsuario();

                contraseña = datosUsuario.getContraseña();

                terminal = datosUsuario.getTerminal();

                // Sustitur esto por la llamada al objeto de Jorge.
                if (controlAcceso.validarAcceso(datosUsuario)) {

                    System.out.println("Conexión establecida con exito.");

                    SrteamParaObjetosRespuesta.writeObject("Acceso correcto");

                    AccesoPermitido = true;

                } else {

                    System.out.println("Acceso incorrecto: Usuario: " + usuario + ". Contraseña: " + contraseña);

                    SrteamParaObjetosRespuesta.writeObject("Acceso incorrecto");
                }


            } while (! AccesoPermitido);

            ///PONER CODIGO PARA VALIDAR ACCESO A LA AGENCIA.

            System.out.println("Datos de usuario conectado:");

            System.out.println("        Usuario: " + usuario);

            System.out.println("        Contraseña: " + contraseña);

            System.out.println("        Terminal donde se conecta: " + terminal);

            int contadorIteraicones = 0;

            AdminTicketsAgencia administrador = AdminTicketsAgencia.getInstance();

            while (true) {

                //System.out.println("\nEsperando lectura tickets del usuario " + usuario + "...");
                ObjetoRecibido = StreamParaObjetosLectura.readObject();

                System.out.println(" Transacción usuario " + usuario + ":");

                if (ObjetoRecibido instanceof TicketTerminal) {
                    TicketTerminal ticket = (TicketTerminal) ObjetoRecibido;

                    System.out.println("    Ticket recibido:: " + ticket.toString());

                    //ATENCION: SI NO SE INICIALIZA LA VARIABLE CON UN NEW SIEMPRE VIAJA EL MISMO VALOR A LA TERMINAL POR LA CONEXION,
                    //AUNQUE EN ESTA CLASE SE VEA CORRECTAMENTE EL VALOR ACTUALIZADO.
                    //CONSULTAR POQUE SE DA ESTO, YA QUE NO DEBERIA PASAR. QUIZAS SEA UN PROBLEMA INTERNO DE LAS CLASES
                    //QUE IMPREMENTAN LA CONEXION.
                    respuesta = new ResultadoOperacionPosta();

                    //Llama a la función que agrega el tikcket y devuelve su número.
                    respuesta = administrador.venderTicket(ticket,terminal);

                } else if (ObjetoRecibido instanceof Integer) {
                    Integer NroTicketAEliminar = (Integer) ObjetoRecibido;

                    System.out.println("    Numero de ticket recibido para eliminar:: " + NroTicketAEliminar);

                    //ATENCION: SI NO SE INICIALIZA LA VARIABLE CON UN NEW SIEMPRE VIAJA EL MISMO VALOR A LA TERMINAL POR LA CONEXION,
                    //AUNQUE EN ESTA CLASE SE VEA CORRECTAMENTE EL VALOR ACTUALIZADO.
                    //CONSULTAR POQUE SE DA ESTO, YA QUE NO DEBERIA PASAR. QUIZAS SEA UN PROBLEMA INTERNO DE LAS CLASES
                    //QUE IMPREMENTAN LA CONEXION.
                    respuesta = new ResultadoOperacionPosta();

                    //Llama a funcion para eliminación de ticket.
                    respuesta = administrador.anularTicket(NroTicketAEliminar);

                } else if (ObjetoRecibido instanceof String) {
                    String mensaje = (String) ObjetoRecibido;
                    //Si se envia el texto salida entonces hay que salir del servidor.
                    if (mensaje.equals("salida")) {
                        System.out.println("     ---El usuario " + usuario + " ha finalizado la conexión.---");

                        //Se envia mensaje a cliente que se cierra el servidor.
                        //No es ecesario hacer socket.close(), ni socketRecepcion.close(), ni lectura.close(), ni StreamParaObjetosLectura.close();
                        //alcanzando enviar mensaje de salida al cliente y haciendo return.
                        SrteamParaObjetosRespuesta.writeObject("salida");

                        return;
                    }
                }

                SrteamParaObjetosRespuesta.writeObject(respuesta);

                System.out.println("   Respuesta enviada a la terminal: " + respuesta.toString());

                System.out.println("   Iteracion numero: " + contadorIteraicones);

                contadorIteraicones++;

            }
        } catch (IOException e) {
            System.out.println("ERROR: Problemas en el Servidor para leer/escribir objeto.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Problemas clase no encontrada en el Servidor para leer el objeto enviado.");
            e.printStackTrace();
        }

    }
}
