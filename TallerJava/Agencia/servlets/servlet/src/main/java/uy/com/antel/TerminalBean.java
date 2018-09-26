package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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

    }

}
