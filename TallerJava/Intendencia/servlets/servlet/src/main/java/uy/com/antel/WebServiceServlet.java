package uy.com.antel;
import javax.jws.*;

@WebService
public class WebServiceServlet {

    @WebMethod
    public String eco(String entrada){
        String mensaje = "El webservice de Intendencia recibió: " + entrada;
        System.out.println("---------------------servidor webservice-------------------------");
        System.out.println(mensaje);
        System.out.println("-----------------------------------------------------------------");

        return mensaje;
    }


    @WebMethod
    public ResultadoOperacionPosta venderTicket(Ticket t, String agencia){
        AdminTicketsIntendencia ati = AdminTicketsIntendencia.getInstance();
        int importe = ati.calcularImporteTicket(t);
        ResultadoOperacionPosta ro = ati.altaTicket(t, agencia,importe);
        return ro;
    }

    @WebMethod
    public ResultadoOperacionPosta anularTicket(int idTicket, String agencia){
        AdminTicketsIntendencia ati = AdminTicketsIntendencia.getInstance();
        ResultadoOperacionPosta ro = ati.bajaTicket(idTicket,agencia);
        System.out.println("Se pidio anular el ticket " + ro.getIdTicket());
        System.out.println(ro.getCodResultado());
        System.out.println(ro.getMsjResultado());
        System.out.println(ro.getIdTicket());
        System.out.println(ro.getImporte());


        return ro;
    }
}
