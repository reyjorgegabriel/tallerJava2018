package uy.com.antel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainTerminal {

    public static void main(String[] arg) {

        String usuario = null;
        String contraseña = null;
        String comando;


        ResultadoOperacionPosta RespuestaObjeto;

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter salida = new PrintWriter(System.out, true);

        //Creo la clase para poder conectarse con la agencia.
        ConexionCliente conecxionAgencia = new ConexionCliente();

        boolean usuarioValidado = false;

        do {
            salida.println("\nIngrese usuario:");

            try {
                usuario = entrada.readLine();
            } catch (IOException e) {
                salida.println("\nNo se pudo leer el usuario correctamente.");
                //e.printStackTrace();
            }

            salida.println("\nIngrese la contraseña:");
            try {
                contraseña = entrada.readLine();
            } catch (IOException e) {
                salida.println("\nNo se pudo leer la contraseña correctamente:");
                //e.printStackTrace();
            }

            //VER DE OBTENER EL NOMBRE DE LA TERMINAL SE PONE HARDCODEADO AHORA.
            //String NombTerminal = "La Terminal";


            //PRUEBA LECTURA DEL NOMBRE DEL TERMINAL
            String NombTerminal = null;
            salida.println("\nIngrese nombre del terminal:");
            try {
                NombTerminal = entrada.readLine();
            } catch (IOException e) {
                salida.println("\nNo se pudo leer el nomre del terminal correctamente:");
                //e.printStackTrace();
            }



            Credenciales infoUsuario = new Credenciales(usuario, contraseña, NombTerminal);

            if (((String) conecxionAgencia.enviarDatoAAgencia(infoUsuario)).equals("Acceso incorrecto")) {
                salida.println("\n ----- ACCESO INCORRECTO ----- \n");

                salida.println("Si desea cerrar la terminal ingrese \"salida\".");
                salida.println("\nSi desea intentar con otro usuario y/o contraseña ingrese \"continuar\" u otro valor.");

                String opcionUsuario = "";

                try {
                    opcionUsuario = entrada.readLine();
                } catch (IOException e) {
                    salida.println("\nNo se pudo leer correctamente lo ingresado.");
                    //e.printStackTrace();
                }

                if (opcionUsuario.equals("salida")) {
                    salida.println("\nSaliendo de la terminal a instancias del usuario.\n");
                    conecxionAgencia.enviarDatoAAgencia("salida");
                    return;
                }

            } else {
                usuarioValidado = true;
            }

        } while (! usuarioValidado);

        salida.println("\nAcceso correcto.");

        salida.println("\n    ---Inicio de interfaz de ingreso de comandos para venta y anulación de tickets. ---");

        String[] componentesComando = null;  //no se sabe el tamaño a priori asi que se pone en null.

        do {

            try {

                salida.println("\nIngrese comando: ");

                comando = entrada.readLine();

                componentesComando = comando.split(" "); //separador. Podria ponerse \\s en vez de espacio.

                componentesComando[0] = componentesComando[0].toLowerCase();

                switch (componentesComando[0]) {

                    case "h": {
                        salida.println("Esta interfaz permite la venta y anulación de tickets de estacionamiento.\n" +
                                "Comandos permitidos\n" +
                                "H – despliega esta ayuda. \n" +
                                "T <Matrícula> <fecha y hora del inicio del estacionamiento, en formato dd/MM/yy hh:mm> < <minutos de estacionamiento> - crea un ticket de\n" +
                                "    estacionamiento para el vehículo con la matricula ingresada, en la fecha y hora indicadas y por los minutos pedidos\n" +
                                "Ta <Matrícula> <minutos de estacionamiento> - crea una ticket de estacionamiento para el vehículo con la\n   matricula ingresada por los minutos pedidos" +
                                " a partir de la fecha y hora actual (de venta del ticket).\n" +
                                "A <Numero de Ticket> - Anula el ticket con el numero ingresado. Debe corresponder a esta agencia. \n" +
                                "Observación: Si la fecha y la hora no están en el formato La fecha deben exactamente en formato dd/MM/yy hh:mm, no se procesará la solicitud.\n" +
                                "Q - Termina la ejecución del programa.");
                    }
                    break;

                    case "t": {

                        if (componentesComando.length != 5) {
                            salida.println("Cantidad incorrecta de parámetros. Se espera una matrícula, fecha y hora del inicio del estacionamiento y los minutos de estacionamiento.");
                        } else {
                            String fechaYhora = componentesComando[2] + " " + componentesComando[3];

                            int minutos;
                            try{
                                minutos = Integer.parseInt(componentesComando[4]);
                                TicketTerminal ticketTerminal = new TicketTerminal(componentesComando[1], fechaYhora, minutos);

                                RespuestaObjeto = (ResultadoOperacionPosta) conecxionAgencia.enviarDatoAAgencia(ticketTerminal);

                                salida.println("-Respuesta de la agencia: " + RespuestaObjeto.toString());

                                if ( RespuestaObjeto.codResultado >= 0){
                                    salida.println("    Ticket agregado con exito Nro: " + RespuestaObjeto.getIdTicket());
                                    salida.println("    Importe: " + RespuestaObjeto.getImporte());
                                    salida.println("    Datos estacionamiento:: " +  ticketTerminal.toString());

                                } else {
                                    salida.println("  ERROR!! EL TICKET NO PUDO SER AGREGADO");
                                    salida.println("  Codigo de error: " + RespuestaObjeto.getCodResultado());
                                    salida.println("  Mensaje de error: " + RespuestaObjeto.getMsjResultado());
                                    salida.println("  Datos ticket que no pudo ser creado:: " +  ticketTerminal.toString());

                                }

                            } catch (FormatoIncorrectoFechaException e) {
                                salida.println("Por favor, ingrese un formato de fecha y hora correctos. Solo se acepta dd/MM/yy hh:mm.");
                                //e.printStackTrace();
                            } catch(NumberFormatException ex) {
                                salida.println("Los minutos ingresados \"" + componentesComando[4] +
                                        "\" no son un valor entero. Ingrese un entero e inténtelo nuevmente.");
                            }
                        }
                    }
                    break;

                    case "ta": {

                        if (componentesComando.length != 3) {
                            salida.println("Cantidad incorrecta de parámetros. Se espera una matrícula y los minutos de estacionamiento. La fecha y hora se toman como las actuales.");
                        } else {
                            int minutos;
                            try {
                                minutos = Integer.parseInt(componentesComando[2]);
                                TicketTerminal ticketTerminal = new TicketTerminal(componentesComando[1], minutos);

                                RespuestaObjeto = (ResultadoOperacionPosta) conecxionAgencia.enviarDatoAAgencia(ticketTerminal);

                                salida.println("-Respuesta de la agencia: " + RespuestaObjeto.toString());

                                if ( RespuestaObjeto.codResultado >= 0) {
                                    salida.println("    Ticket agregado con exito Nro: " + RespuestaObjeto.getIdTicket());
                                    salida.println("    Importe: " + RespuestaObjeto.getImporte());
                                    salida.println("    Datos estacionamiento:: " +  ticketTerminal.toString());
                                } else {
                                    salida.println("  ERROR!! EL TICKET NO PUDO SER AGREGADO");
                                    salida.println("  Codigo de error: " + RespuestaObjeto.getCodResultado());
                                    salida.println("  Mensaje de error: " + RespuestaObjeto.getMsjResultado());
                                    salida.println("  Datos ticket que no pudo ser creado:: " +  ticketTerminal.toString());
                                }

                            } catch(NumberFormatException ex) {
                                salida.println("Los minutos ingresados " + componentesComando[2] +
                                        " no son un valor entero. Ingrese un entero e inténtelo nuevmente.");
                            }
                        }

                    }

                    break;

                    case "a": {

                        if (componentesComando.length != 2) {
                            salida.println("Cantidad incorrecta de parámetros. Se espera el numero de ticket para anular.");
                        } else {

                            try {
                                Integer NroTicket = Integer.parseInt(componentesComando[1]);

                                salida.println("Eliminando ticket " + NroTicket + "...");

                                RespuestaObjeto = (ResultadoOperacionPosta) conecxionAgencia.enviarDatoAAgencia(NroTicket);

                                salida.println("-Respuesta de la agencia: " + RespuestaObjeto.toString());

                                if ( RespuestaObjeto.codResultado >= 0) {
                                    salida.println("    Ticket " + componentesComando[1] + " anulado con exito.");
                                    salida.println("    Ticket numero Nro: " + RespuestaObjeto.getIdTicket());
                                    salida.println("    Importe: " + RespuestaObjeto.getImporte());
                                } else {
                                    salida.println("  ERROR!! El Ticket " + componentesComando[1] + " no pudo ser anulado. Intente nuevamente o ingrese otro ticket.");
                                    salida.println("  Codigo de error: " + RespuestaObjeto.getCodResultado());
                                    salida.println("  Mensaje de error: " + RespuestaObjeto.getMsjResultado());
                                }

                            } catch(NumberFormatException ex) {
                                salida.println("El numero de ticket ingresado (" + componentesComando[1] +
                                        ") no es un valor entero. Ingrese un entero e inténtelo nuevmente.");
                            }
                        }
                    }

                    break;

                    case "q":

                    break;

                    default:
                        salida.println("Comando " + componentesComando[0] + " inexistente. Ingrese un nuevo comando. Para ver la ayuda ingrese comando H.");
                    break;
                }

            } catch (IOException e) {
                salida.println("\n Error: No se pudo leer el comando correctamente.");
                //e.printStackTrace();
            }

        } while (!componentesComando[0].equals("q"));

        conecxionAgencia.enviarDatoAAgencia("salida");

        salida.println("\n  ---Fin de ingreso de tickets de estacionamiento.---");

        //VER SI VA PROCESO DE DESCONECXION DEL USUARIO.
    }
}