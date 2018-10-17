package uy.com.antel;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class AdminTicketsAgencia {
    //es un singleton
    private static AdminTicketsAgencia ourInstance = new AdminTicketsAgencia();

    //nombre de la agencia
    private static String nombreAgencia = "agencia1";


    public static AdminTicketsAgencia getInstance() {
        return ourInstance;
    }

    private AdminTicketsAgencia() {
    }


    public ResultadoOperacionPosta venderTicket(TicketTerminal ticketTerminal, String terminal){

        //conectarse al webservice
        WebServiceServletService svc = new WebServiceServletService();
        WebServiceServlet wss = svc.getWebServiceServletPort();

        //preparar ticket para el webservice, es XML
        Ticket ticketAgencia = new Ticket();

        ticketAgencia.setMatricula(ticketTerminal.getMatricula());
        ticketAgencia.setMinutos(ticketTerminal.getMinutosEstacionamiento());

        //convertir las horas a XML
        //hora de inicio
        XMLGregorianCalendar xmlGcInicio = null;
        try{
            xmlGcInicio =DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    (GregorianCalendar) ticketTerminal.getFechaInicio());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ticketAgencia.setFechaHoraInicio(xmlGcInicio);

        //hora de venta
        XMLGregorianCalendar xmlGcVenta = null;
        try{
            xmlGcVenta =DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    (GregorianCalendar) ticketTerminal.getFechaVenta());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ticketAgencia.setFechaHoraVenta(xmlGcVenta);

        ResultadoOperacionPosta ro = wss.venderTicket(ticketAgencia,nombreAgencia);

        if(ro.getCodResultado() == 0){
            //la intendencia vendió el ticket. Lo guardamos en la base de la agencia
            InitialContext initContext = null;
            try{
                initContext = new InitialContext();
                DataSource ds;
                ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
                Connection conn = ds.getConnection();

                PreparedStatement ps3 = conn.prepareStatement(sqlInsertTicket(ticketAgencia, (int)ro.getIdTicket(),
                        terminal, ro.getImporte()));
                ps3.executeUpdate();
                ps3.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return ro;
    }

    public ResultadoOperacionPosta anularTicket(int idTicket){
        //consultar la base de datos de la agencia
        System.out.println("AdminTicketsAgencia.anularTicket()");
        ResultadoOperacionPosta ro = new ResultadoOperacionPosta();
        ro.setIdTicket(idTicket);

        InitialContext initContext;
        try {
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            //verificar si existe el ticket
            //asumimos que solo hay un ticket por identificador
            String consultaExisteTicket = "select * from tickets where idTicket = ?";
            PreparedStatement ps1 = conn.prepareStatement(consultaExisteTicket);
            ps1.setInt(1,idTicket);
            ResultSet rs1 = ps1.executeQuery();
            //verificamos si el ticket existe en la agencia o no
            if(rs1.next()) {
                //el ticket existe
                //verificamos que no esté anulado
                if(!rs1.getBoolean("anulado")){
                    //ticket no anulado
                    //
                    WebServiceServletService svc = new WebServiceServletService();
                    WebServiceServlet wss = svc.getWebServiceServletPort();
                    ro = wss.anularTicket(idTicket,nombreAgencia);

                    if (ro.getCodResultado() == 1){
                        //se pudo anular el ticket en la intendencia, actualizamos la base de datos
                        //de la agencia
                        String anularTicket = "update tickets set anulado = TRUE where idTicket = ?";
                        PreparedStatement ps2 = conn.prepareStatement(anularTicket);
                        ps2.setInt(1,idTicket);
                        ps2.executeUpdate();
                        ps2.close();
                        //el resultado de la operación ya está dado por la intendencia
                    }
                    else{
                        //no se pudo anular el ticket en la intendencia
                        //no hacer nada
                        //el resultado de la operación ya está dado por la intendencia
                    }

                }
                else{
                    //el ticket existe, pero ya fue anulado
                    ro.setImporte(0);
                    ro.setCodResultado(-3);
                    ro.setMsjResultado("No se anuló el ticket. Ya estaba anulado");

                }

            }
            else{
                //el ticket no existe, no es de la agencia
                ro.setImporte(0);
                ro.setCodResultado(-2);
                ro.setMsjResultado("El ticket no pertenece a esta agencia");

            }
            rs1.close();
            ps1.close();
            conn.close();
        }catch (Exception e){
            //errores en la conexión a la base de datos
            e.printStackTrace();
            ro.setImporte(0);
            ro.setCodResultado(-1);
            ro.setMsjResultado("Se produjo un error de acceso a la base de datos de la agencia");
        }

        return ro;

    }

    private String sqlInsertTicket(Ticket ticket, int idTicket, String terminal, int importe){

        //convertir los valores de Calendar al formato que requiere MySQL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //No hay caso. Este es el ticket del web service. No reconoce getTime del XML

        String strFechaHoraVenta = sdf.format(ticket.getFechaHoraVenta().toGregorianCalendar().getTime());


        String p = "insert into tickets values (" +
                idTicket + ", '" + ticket.getMatricula() + "', '" + strFechaHoraVenta + "', '" +
                terminal + "', " +  importe + ", FALSE)";

        System.out.println(p);
        return p;
        /*
        Orden de los campos en la tabla "tickets"
        idTicket       | int(11)
        matricula      | varchar(10)
        fechaHoraVenta | datetime
        terminal       | varchar(20)
        importe        | decimal(10,2)
        anulado        | tinyint(1)
         */
    }

}
