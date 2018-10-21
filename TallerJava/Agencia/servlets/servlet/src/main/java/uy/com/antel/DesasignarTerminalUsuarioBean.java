package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.util.List;

@ManagedBean
@RequestScoped
public class DesasignarTerminalUsuarioBean {
    private String usuarioSeleccionado;
    private List<String> listaUsuarios;
    private String terminalSeleccionado;
    private List<String> listaTerminales;


    public String getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(String usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<String> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getTerminalSeleccionado() {
        return terminalSeleccionado;
    }

    public void setTerminalSeleccionado(String terminalSeleccionado) {
        this.terminalSeleccionado = terminalSeleccionado;
    }

    public List<String> getListaTerminales() {
        return listaTerminales;
    }

    public void setListaTerminales(List<String> listaTerminales) {
        this.listaTerminales = listaTerminales;
    }

    public DesasignarTerminalUsuarioBean() {
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        listaUsuarios = aua.obtenerListaUsuarios();
        if(listaUsuarios.size() > 0){
            listaTerminales = aua.obtenerListaAutorizados(listaUsuarios.get(0));

            System.out.println("soy el constructor del bean size > 0");
        }
        else{
            System.out.println("soy el constructor del bean size <= 0");
        }

    }

    public void usuarioCambioSeleccion(ValueChangeEvent event){
        this.usuarioSeleccionado = event.getNewValue().toString();
        System.out.println("cambio de selección: " + usuarioSeleccionado);
        AdminUsuariosAgencia aua = AdminUsuariosAgencia.getInstance();
        this.setListaTerminales(aua.obtenerListaAutorizados(usuarioSeleccionado));
        System.out.println("imprimo lista terminales");
        for(String e: listaTerminales){
            System.out.println(e);
        }
        System.out.println("-------------------------");

        if(listaTerminales.size()>0){
            this.terminalSeleccionado = listaTerminales.get(0);
            System.out.println("+++++++++++" + this.terminalSeleccionado + "++++++++++++");
        }

    }

}
