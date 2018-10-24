package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class probarCredencialesBean {
    private String usuario;
    private String clave;
    private String terminal;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void probarCredenciales(){
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        Credenciales c = new Credenciales(usuario, clave, terminal);
        //String respuesta = aua.validarAcceso2(c);
        String propuesta = usuario + " -- " + clave + " -- " + terminal;
        String permiso = aua.validarAcceso(c) ? ": sí" : ": no" ;

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                propuesta + permiso, "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
