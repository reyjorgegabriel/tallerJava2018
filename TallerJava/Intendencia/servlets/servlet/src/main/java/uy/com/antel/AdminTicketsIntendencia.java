package uy.com.antel;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AdminTicketsIntendencia {

    //es un singleton
    private static AdminTicketsIntendencia ourInstance = new AdminTicketsIntendencia();

    public static AdminTicketsIntendencia getInstance() {
        return ourInstance;
    }

    private AdminTicketsIntendencia() {
    }


    public ResultadoOperacion altaTicket(Ticket t, String agencia, int importe){
        ResultadoOperacion ro = new ResultadoOperacion();
        InitialContext initContext = null;
        try {
            System.out.println("AP paso2");
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS2");
            Connection conn = ds.getConnection();

            //obtener idTicket de la tabla de secuencias
            PreparedStatement ps1 = conn.prepareStatement(sqlGetSecuenciaTickets());
            ResultSet rs1 = ps1.executeQuery();

            //TODO ojo, estamos asumiendo que el resultset no está vacío
            rs1.next();
            int idTicket = rs1.getInt("ident");
            rs1.close();
            ps1.close();

            //agregamos el ticket a la tabla de tickets
            idTicket++;
            //salida = sqlInsertTicket(t,idTicket, agencia, importe);
            //System.out.println(salida);

            PreparedStatement ps3 = conn.prepareStatement(sqlInsertTicket(t,idTicket, agencia, importe));
            ps3.executeUpdate();
            ps3.close();

            //actualizamos la tabla de secuencias
            PreparedStatement ps5 = conn.prepareStatement(sqlSetSecuenciaTickets(idTicket));
            ps5.executeUpdate();
            ps5.close();

            //cerrar la conexión, los resultsets y las consultas ya están cerradas
            conn.close();

            ro.setCodResultado(0);
            ro.setIdTicket(idTicket);
            ro.setImporte(importe);
            ro.setMsjResultado("ticket agregado con éxito");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            ro.setCodResultado(0);
            ro.setIdTicket(0);
            ro.setImporte(0);
            ro.setMsjResultado("Se produjo una excepción");
        }

        return ro;
    }


    public int calcularImporteTicket(Ticket t){
        int importe = (t.getMinutos()*3)/2;
        return importe;
    }



    private String sqlGetSecuenciaTickets(){
        String p = "select ident from secuencias where tabla = 'tickets'";
        //System.out.println(p);
        return p;

    }

    private String sqlSetSecuenciaTickets(int id_ed){
        String p = "update secuencias set ident = " + id_ed + " where tabla = 'tickets'";
        //System.out.println(p);
        return p;
    }

    private String sqlInsertTicket(Ticket ticket, int idTicket, String agencia, int importe){

        //convertir los valores de Calendar al formato que requiere MySQL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strFechaHoraVenta = sdf.format(ticket.getFechaHoraVenta().getTime());
        String strFechaHoraInicio = sdf.format(ticket.getFechaHoraVenta().getTime());

        String p = "insert into tickets values (" +
                idTicket + ", '" + ticket.getMatricula() + "', '" + strFechaHoraVenta + "', '" +
                strFechaHoraInicio + "', " + ticket.getMinutos()  + ", '" + agencia + "', " + importe + ", FALSE)";
        return p;
        /*
        Orden de los campos en la tabla "tickets"
        idTicket
        matricula
        fechaHoraVenta
        fechaHoraInicio
        minutos
        agencia
        importe
        anulado
         */
    }

}




/*
    public String altaTicket(String matricula){

        //Ticket para probar la función
        Ticket t = new Ticket();
        t.setMatricula(matricula);
        t.setFechaHoraVenta(GregorianCalendar.getInstance());
        GregorianCalendar gcInicio = new GregorianCalendar();
        gcInicio.set(Calendar.YEAR, 2018);
        gcInicio.set(Calendar.MONTH,Calendar.SEPTEMBER);
        gcInicio.set(Calendar.DAY_OF_MONTH, 30);
        gcInicio.set(Calendar.HOUR, 12);
        gcInicio.set(Calendar.MINUTE, 20);
        gcInicio.set(Calendar.SECOND, 00);
        t.setFechaHoraInicio(gcInicio);
        t.setMinutos(190);

        //agencia para probar la función
        String agencia = "agencia1";
        //importe mockeado para probar la función
        int importe = 120;

        String salida;


        InitialContext initContext = null;
        try {
            System.out.println("AP paso2");
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS2");
            Connection conn = ds.getConnection();

            //obtener idTicket de la tabla de secuencias
            PreparedStatement ps1 = conn.prepareStatement(sqlGetSecuenciaTickets());
            ResultSet rs1 = ps1.executeQuery();

            //TODO ojo, estamos asumiendo que el resultset no está vacío
            rs1.next();
            int idTicket = rs1.getInt("ident");
            rs1.close();
            ps1.close();

            //agregamos el ticket a la tabla de tickets
            idTicket++;
            salida = sqlInsertTicket(t,idTicket, agencia, importe);
            System.out.println(salida);

            PreparedStatement ps3 = conn.prepareStatement(sqlInsertTicket(t,idTicket, agencia, importe));
            ps3.executeUpdate();
            ps3.close();

            //actualizamos la tabla de secuencias
            PreparedStatement ps5 = conn.prepareStatement(sqlSetSecuenciaTickets(idTicket));
            ps5.executeUpdate();
            ps5.close();




            //cerrar la conexión, los resultsets y las consultas ya están cerradas
            conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            salida = "Se produjo una excepción";
        }

        return salida;


    }

 */