package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class AutorizarTerminalUsuarioBean {
    private String terminal;
    private String usuario;

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void autorizarTerminalUsuario(){
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        String respuesta = aua.asignarTerminalUsuario(terminal,usuario);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                respuesta, "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
