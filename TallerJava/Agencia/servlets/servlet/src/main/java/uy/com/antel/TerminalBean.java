package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import java.util.List;


@ManagedBean
@RequestScoped

public class TerminalBean {
    private String entrada;
    private String mensaje;
    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void enviarMensaje(){
        WebServiceServletService svc = new WebServiceServletService();
        WebServiceServlet wss = svc.getWebServiceServletPort();
        String stringEntrada = getEntrada();
        String salida = wss.eco(stringEntrada);
        System.out.println("---------------------cliente webservice-------------------------");
        System.out.println("Se envió: " + stringEntrada);
        System.out.println("Se recibió: " + salida);
        System.out.println("-----------------------------------------------------------------");

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "entrada: " + entrada + "\n salida: " + salida,
                "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);



        Ticket t = new Ticket();
        t.setMatricula(stringEntrada);

        //
        Date ahora = new Date();
        GregorianCalendar gcVenta = new GregorianCalendar();
        gcVenta.setTime(ahora);
        XMLGregorianCalendar xmlGcVenta = null;
        try{
            xmlGcVenta =DatatypeFactory.newInstance().newXMLGregorianCalendar(gcVenta);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        t.setFechaHoraVenta(xmlGcVenta);


        GregorianCalendar gcInicio = new GregorianCalendar();
        gcInicio.set(Calendar.YEAR, 2018);
        gcInicio.set(Calendar.MONTH,Calendar.SEPTEMBER);
        gcInicio.set(Calendar.DAY_OF_MONTH, 30);
        gcInicio.set(Calendar.HOUR, 12);
        gcInicio.set(Calendar.MINUTE, 20);
        gcInicio.set(Calendar.SECOND, 00);

        XMLGregorianCalendar xmlGcInicio = null;
        try{
            xmlGcInicio =DatatypeFactory.newInstance().newXMLGregorianCalendar(gcInicio);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        t.setFechaHoraInicio(xmlGcInicio);
        t.setMinutos(190);

        ResultadoOperacion ro = wss.venderTicket(t,"SuperAgente86");
        System.out.println(ro.msjResultado);



        /*
        //Check out this code
        // Create Date Object /
        Date date = new Date();
        XMLGregorianCalendar xmlDate = null;
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);

        try{
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        }
        catch(Exception e){
            e.printStackTrace();
        }
         */


    }

}
