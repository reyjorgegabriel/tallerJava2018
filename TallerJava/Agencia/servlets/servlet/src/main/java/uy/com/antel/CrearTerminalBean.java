package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CrearTerminalBean {
    private String nombre;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void crearTerminal(){
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        String respuesta = aua.agregarTerminal(nombre);
        //String respuesta = aua.crearUsuario(nombre,clave);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                respuesta, "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
