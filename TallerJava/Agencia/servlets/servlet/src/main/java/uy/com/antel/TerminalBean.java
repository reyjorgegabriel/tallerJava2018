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
    private int idTicket;


    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

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

    public void anularTicket(){
        WebServiceServletService svc = new WebServiceServletService();
        WebServiceServlet wss = svc.getWebServiceServletPort();
        int numTicket = getIdTicket();
        ResultadoOperacionPosta ro = wss.anularTicket(numTicket,"SuperAgente86");

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Codigo: " + ro.getCodResultado() +
                                ", Mensaje: " + ro.getMsjResultado() +
                                ", Ticket: " + ro.getIdTicket() +
                                ", Importe: " + ro.getImporte(), "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);


    }


    public void enviarMensaje(){
        WebServiceServletService svc = new WebServiceServletService();
        WebServiceServlet wss = svc.getWebServiceServletPort();
        String stringEntrada = getEntrada();

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

        ResultadoOperacionPosta ro = wss.venderTicket(t,"SuperAgente86");
        System.out.println(ro.msjResultado);


        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "matrícula: " + stringEntrada + "\n ticket: " + ro.getIdTicket(),
                "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);


    }


    public void probarVenta(){
        System.out.println("TerminalBean.probarVenta()");
        AdminTicketsAgencia ata =AdminTicketsAgencia.getInstance();
        TicketTerminal ticketTerminal = new TicketTerminal();
        ticketTerminal.setMatricula(getEntrada());
        ticketTerminal.setMinutosEstacionamiento(190);

        Date ahora = new Date();
        Calendar cVenta = new GregorianCalendar();
        cVenta.setTime(ahora);
        ticketTerminal.setFechaVenta(cVenta);

        Calendar cInicio = new GregorianCalendar();
        cInicio.set(Calendar.YEAR, 2018);
        cInicio.set(Calendar.MONTH,Calendar.SEPTEMBER);
        cInicio.set(Calendar.DAY_OF_MONTH, 30);
        cInicio.set(Calendar.HOUR, 12);
        cInicio.set(Calendar.MINUTE, 20);
        cInicio.set(Calendar.SECOND, 00);
        ticketTerminal.setFechaInicio(cInicio);

        ResultadoOperacionPosta ro = ata.venderTicket(ticketTerminal,"terminal1");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Matrícula: " + ticketTerminal.getMatricula() +
                        " Codigo: " + ro.getCodResultado() +
                        ", Mensaje: " + ro.getMsjResultado() +
                        ", Ticket: " + ro.getIdTicket() +
                        ", Importe: " + ro.getImporte(), "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void probarAnulacion(){
        System.out.println("TerminalBean.probarAnulacion()");
        AdminTicketsAgencia ata =AdminTicketsAgencia.getInstance();
        ResultadoOperacionPosta ro = ata.anularTicket(getIdTicket());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Codigo: " + ro.getCodResultado() +
                        ", Mensaje: " + ro.getMsjResultado() +
                        ", Ticket: " + ro.getIdTicket() +
                        ", Importe: " + ro.getImporte(), "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
