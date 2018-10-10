package uy.com.antel;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class AdminTicketsIntendencia {

    //es un singleton
    private static AdminTicketsIntendencia ourInstance = new AdminTicketsIntendencia();

    public static AdminTicketsIntendencia getInstance() {
        return ourInstance;
    }

    private AdminTicketsIntendencia() {
    }


    public ResultadoOperacion altaTicket(Ticket t, String agencia, int importe) {
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

            PreparedStatement ps3 = conn.prepareStatement(sqlInsertTicket(t, idTicket, agencia, importe));
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
            ro.setMsjResultado("Ticket agregado con éxito");

        } catch (Exception e) {
            e.printStackTrace();
            ro.setCodResultado(-1);
            ro.setIdTicket(0);
            ro.setImporte(0);
            ro.setMsjResultado("Se produjo un error de acceso a la base de datos");
        }

         return ro;
    }


    public int calcularImporteTicket(Ticket t) {
        int importe = (t.getMinutos() * 3) / 2;
        return importe;
    }

    public ResultadoOperacion bajaTicket(int idTicket, String agencia){

        ResultadoOperacion ro = new ResultadoOperacion();
        ro.setIdTicket(idTicket);

        InitialContext initContext = null;
        try {
            System.out.println("baja ticket");
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS2");
            Connection conn = ds.getConnection();

            //verificar si existe el ticket
            //asumimos que solo hay un ticket por identificador
            PreparedStatement ps1 = conn.prepareStatement(sqlExisteTicket(idTicket));
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()){
                //el ticket existe
                //verificamos que pertenezca a la agencia
                if(agencia.equals(rs1.getString("agencia"))){
                   //pertenece a la agencia
                   //verificamos que no esté anulado
                   if(!rs1.getBoolean("anulado")){
                       //no está anulado
                       //actualizamos valor
                       PreparedStatement ps2 = conn.prepareStatement(sqlAnularTicket(idTicket));
                       ps2.executeUpdate();
                       ps2.close();
                       //resultado de la operación
                       ro.setImporte(rs1.getInt("importe"));
                       ro.setCodResultado(1);//
                       ro.setMsjResultado("Se anuló el ticket con éxito. Devolver $" +
                                            ro.getImporte() + ".");
                   }
                   else{
                       //está anulado.
                       ro.setImporte(0);
                       ro.setCodResultado(-3);//
                       ro.setMsjResultado("No se anuló el ticket. Ya estaba anulado.");
                   }
                }
                else{
                    //pertenece a otra agencia
                    ro.setImporte(0);
                    ro.setCodResultado(-2);//
                    ro.setMsjResultado("No se anuló el ticket. Pertenece a otra agencia.");
                }

            }
            else{
                //el ticket no existe
                ro.setImporte(0);
                ro.setCodResultado(-4);//
                ro.setMsjResultado("No se anuló el ticket. No existe un ticket con identificador " +
                                    ro.getIdTicket() + ".");
            }
            rs1.close();
            ps1.close();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();
            ro.setCodResultado(-1);
            ro.setIdTicket(idTicket);
            ro.setImporte(0);
            ro.setMsjResultado("Se produjo un error de acceso a la base de datos");
        }

        return ro;

    }

    public List<TicketIntendencia> listaTicketsIntendencia(){
        InitialContext initContext = null;
        List<TicketIntendencia> lista = new ArrayList<TicketIntendencia>();
        try {
            System.out.println("AP paso2");
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS2");
            Connection conn = ds.getConnection();

            PreparedStatement ps1 = conn.prepareStatement("select * from tickets");
            ResultSet rs1 = ps1.executeQuery();


            while(rs1.next()){
                TicketIntendencia ti = new TicketIntendencia();
                ti.setIdTicket(rs1.getInt("idTicket"));
                ti.setMatricula(rs1.getString("matricula"));
                ti.setAgencia(rs1.getString("agencia"));
                ti.setAnulado(rs1.getBoolean("anulado"));
                ti.setMinutos(rs1.getInt("minutos"));
                ti.setImporte(rs1.getInt("importe"));

                //procesamiento fechas
                Date fechaHoraInicio = rs1.getDate("fechaHoraInicio");
                GregorianCalendar gci = new GregorianCalendar();
                gci.setTime(fechaHoraInicio);
                ti.setFechaHoraInicio(gci);

                Date fechaHoraVenta = rs1.getDate("fechaHoraVenta");
                GregorianCalendar gcv = new GregorianCalendar();
                gcv.setTime(fechaHoraVenta);
                ti.setFechaHoraVenta(gcv);

                lista.add(ti);

            }
            rs1.close();
            ps1.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lista;
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
        String strFechaHoraInicio = sdf.format(ticket.getFechaHoraInicio().getTime());

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

    private String sqlExisteTicket(int idTicket){
        String salida = "select * from tickets where idTicket = " + idTicket;
        return salida;
    }

    private String sqlAnularTicket(int idTicket){
        String salida = "update tickets set anulado = 1 where idTicket = " + idTicket;
        return salida;
    }

}
