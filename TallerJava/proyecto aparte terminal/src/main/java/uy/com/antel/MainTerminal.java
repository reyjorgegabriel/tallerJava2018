package uy.com.antel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class MainTerminal {

    public static void main(String[] arg) {

        String usuario;
        String contraseña;
        String comando;

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter salida = new PrintWriter(System.out, true);

        salida.println("\nIngrese usuario:");

        try {
            usuario = entrada.readLine();
        } catch (IOException e) {
            salida.println("\nNo se pudo leer el usuario correctamente:");
            //e.printStackTrace();
        }

        salida.println("\nIngrese la contraseña:");
        try {
            contraseña = entrada.readLine();
        } catch (IOException e) {
            salida.println("\nNo se pudo leer la contraseña correctamente:");
            //e.printStackTrace();
        }

        //FUNCION PARA VALIDAR USUARIO Y CONTRASEÑA CON LA AGENCIA


        salida.println("\nIngrese los comandos para venta y anulación de tickets:");

        String[] componentesComando = null;  //no se sabe el tamaño a priori asi que se pone en null.

        do {

            try {

                comando = entrada.readLine();

                componentesComando = comando.split(" "); //separador

                componentesComando[0] = componentesComando[0].toLowerCase();

                switch (componentesComando[0]) {

                    case "h": {
                        salida.println("Esta interfaz permite la venta y anulación de tickets de estacionamiento. \n\n" +
                                "Comandos permitidos\n" +
                                "H – despliega esta ayuda. \n" +
                                "T <Matrícula> <fecha y hora del inicio del estacionamiento, en formato dd/MM/yy hh:mm> < <minutos de estacionamiento> - crea un ticket de\n" +
                                "    estacionamiento para el vehículo con la matricula ingresada, en la fecha y hora indicadas y por los minutos pedidos\n" +
                                "Ta <Matrícula> <minutos de estacionamiento> - crea una ticket de estacionamiento para el vehículo con la\n   matricula ingresada por los minutos pedidos" +
                                " a partir de la fecha y hora actual (de venta del ticket).\n" +
                                "A <Numero de Ticket> - Anula el ticket con el numero ingresado. Debe corresponder a esta agencia. \n" +
                                "Observación: Si la fecha y la hora no están en el formato La fecha deben exactamente en formato dd/MM/yy hh:mm, no se procesará la solicitud.\n" +
                                "Q - Termina la ejecución del programa\n");
                    }
                    break;

                    case "t": {

                        if (componentesComando.length != 5) {
                            salida.println("Cantidad incorrecta de parámetros. Se espera una matrícula, fecha y hora del inicio del estacionamiento y los minutos de estacionamiento.");
                        } else {
                            String fechaYhora = componentesComando[2] + " " + componentesComando[3];
                            TicketTerminal ticketTerminal = new TicketTerminal(componentesComando[1], fechaYhora, Integer.parseInt(componentesComando[4]));
                            //LLAMAR A FUNCIONDE LA AGENCIA PARA PASARLE EL TICKET CREADO.
                            salida.println("Ticket agregado con exito con los datos:: " +  ticketTerminal.toString());
                        }
                    }
                    break;

                    case "ta": {

                        if (componentesComando.length != 3) {
                            salida.println("Cantidad incorrecta de parámetros. Se espera una matrícula y los minutos de estacionamiento. La fecha y hora se toman como las actuales.");
                        } else {
                            TicketTerminal ticketTerminal = new TicketTerminal(componentesComando[1], Integer.parseInt(componentesComando[2]));
                            //LLAMAR A FUNCIONDE LA AGENCIA PARA PASARLE EL TICKET CREADO.
                            salida.println("Ticket agregado con exito con los datos:: " +  ticketTerminal.toString());
                        }

                    }

                    break;

                    case "a": {

                        if (componentesComando.length != 2) {
                            salida.println("Cantidad incorrecta de parámetros. Se espera el numero de ticket para anular.");
                        } else {
                            //LLAMAR A FUNCION DE LA AGENCIA PARA ANULAR EL TICKET.
                            salida.println("Ticket " +  componentesComando[1]+ " anulado con exito.");

                        }
                    }

                    break;

                    case "q":

                    break;

                    default:
                        salida.println("Comando inexistente. Ingrese un nuevo comando. Para ver la ayuda ingrese comando H");
                    break;
                }

            } catch (IOException e) {
                salida.println("\nNo se pudo leer el comando correctamente");
                //e.printStackTrace();
            }

        } while (!componentesComando[0].equals("q"));

        salida.println("\nFin de ingreso de tickets de estacionamiento");

        //VER SI VA PROCESO DE DESCONECXION DEL USUARIO.
    }
}