package uy.com.antel;

public class Credenciales implements java.io.Serializable {

    private String usuario;

    private String contraseña;

    private String terminal;

    public Credenciales(String usu, String pass, String nombTerminal) {

        usuario = usu;

        contraseña = pass;

        terminal = nombTerminal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
