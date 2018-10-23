package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@RequestScoped
public class EliminarTerminalBean {
    private String nombre;
    private List<String> listaNombres;

    public EliminarTerminalBean() {
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        listaNombres = aua.obtenerListaTerminales();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getListaNombres() {
        return listaNombres;
    }

    public void setListaNombres(List<String> listaNombres) {
        this.listaNombres = listaNombres;
    }

    public void eliminarTerminal(){
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        String mensaje = aua.eliminarTerminal(nombre);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                mensaje, "respuesta:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
