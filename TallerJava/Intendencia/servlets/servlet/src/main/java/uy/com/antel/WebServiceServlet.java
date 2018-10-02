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
    public String altaTicket(String prueba){
        return "está andando " + prueba;

    }

    @WebMethod
    public ResultadoOperacion venderTicket(Ticket t, String agencia){
        AdminTicketsIntendencia ati = AdminTicketsIntendencia.getInstance();
        int importe = ati.calcularImporteTicket(t);
        ResultadoOperacion ro = ati.altaTicket(t, agencia,importe);
        return ro;
    }

    @WebMethod
    public ResultadoOperacion anularTicket(Ticket t, String agencia){
        ResultadoOperacion ro = new ResultadoOperacion();
        return ro;
    }

}
