package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@RequestScoped
public class CambiarClaveUsuarioBean {
    private String nombre;
    private String clave;
    private List<String> listaNombres;

    public CambiarClaveUsuarioBean() {
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        listaNombres = aua.obtenerListaUsuarios();
    }

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
    public List<String> getListaNombres() {
        return listaNombres;
    }

    public void setListaNombres(List<String> listaNombres) {
        this.listaNombres = listaNombres;
    }

    public void cambiarClaveUsuario(){
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        //TODO verificar que usuario y clave no son nulos
        String mensaje = aua.cambiarClave(nombre,clave);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                mensaje, "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
