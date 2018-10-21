package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CrearUsuarioBean {
    private String nombre;
    private String clave;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void crearUsuario(){
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        String respuesta = aua.crearUsuario(nombre,clave);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                respuesta, "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
