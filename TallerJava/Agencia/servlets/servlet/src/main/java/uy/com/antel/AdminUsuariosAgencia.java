package uy.com.antel;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminUsuariosAgencia {

    //es un singleton
    private static AdminUsuariosAgencia ourInstance = new AdminUsuariosAgencia();

    public static AdminUsuariosAgencia getInstance() {
        return ourInstance;
    }

    private AdminUsuariosAgencia() {
    }

    public String crearUsuario(String nombre, String clave){
        //TODO verificar que el nombre y la clave no son nulos
        String resultado;
        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            String existeUsuario = "select * from usuarios where usuario = ?";

            PreparedStatement ps = conn.prepareStatement(existeUsuario);
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                //El usuario no existe. Puede agregarse.
                String agregarUsuario = "insert into usuarios (usuario, clave) values (?, ?)";
                PreparedStatement ps1 = conn.prepareStatement(agregarUsuario);
                ps1.setString(1,nombre);
                ps1.setString(2,clave);
                ps1.executeUpdate();
                ps1.close();
                resultado = "Se agregó el usuario con éxito";
            }
            else
            {
                //Ya existe un usuario con ese nombre
                resultado = "Ya existe un usuario con ese nombre";
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            resultado = "Error de acceso a la base de datos";
            System.out.println(resultado);
        }
        return resultado;
    }

    public String eliminarUsuario(String nombre){
        //TODO verificar que el usuario no es nulo
        String resultado;
        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            String existeUsuario = "select * from usuarios where usuario = ?";

            PreparedStatement ps = conn.prepareStatement(existeUsuario);
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //El usuario existe. Puede eliminarse.

                //primero hay que borrar las autorizaciones del usuario
                int idUsuario = rs.getInt("idUsuario");
                String eliminarAutorizaciones = "delete from autorizaciones where idUsuario = ?";
                PreparedStatement ps1 = conn.prepareStatement(eliminarAutorizaciones);
                ps1.setInt(1,idUsuario);
                ps1.executeUpdate();
                ps1.close();

                //ahora borramos el usuario
                String eliminarUsuario = "delete from usuarios where usuario = ?";
                PreparedStatement ps2 = conn.prepareStatement(eliminarUsuario);
                ps2.setString(1,nombre);
                ps2.executeUpdate();
                ps2.close();

                resultado = "Se eliminó el usuario con éxito";
            }
            else
            {
                //No existe un usuario con ese nombre
                resultado = "No existe un usuario con ese nombre";
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            resultado = "Error de acceso a la base de datos";
            System.out.println(resultado);
        }
        return resultado;
    }

    public String cambiarClave(String usuario, String clave){
        //TODO verificar que usuario y clave no son nulos
        String resultado;
        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            String existeUsuario = "select * from usuarios where usuario = ?";

            PreparedStatement ps = conn.prepareStatement(existeUsuario);
            ps.setString(1,usuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //El usuario existe. Puede modificarse la clave.
                String modificarClave = "update usuarios set clave = ? where usuario = ?";
                PreparedStatement ps1 = conn.prepareStatement(modificarClave);
                ps1.setString(1,clave);
                ps1.setString(2,usuario);
                ps1.executeUpdate();
                ps1.close();
                resultado = "Se modificó la clave del usuario " + usuario +" con éxito";
            }
            else
            {
                //Ya existe un usuario con ese nombre
                resultado = "No existe un usuario con ese nombre";
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            resultado = "Error de acceso a la base de datos";
            System.out.println(resultado);
        }
        return resultado;
    }

    public List<String> obtenerListaUsuarios(){
        List<String> resultado = new ArrayList<String>();

        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();
            String consultaUsuario = "select * from usuarios";
            PreparedStatement ps = conn.prepareStatement(consultaUsuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String usuario = rs.getString("usuario");
                resultado.add(usuario);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error de acceso a la base de datos");
        }
        return resultado;
    }

    public String agregarTerminal(String terminal){
        //TODO verificar que el nombre y la clave no son nulos
        String resultado;
        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            String existeTerminal = "select * from terminales where terminal = ?";

            PreparedStatement ps = conn.prepareStatement(existeTerminal);
            ps.setString(1,terminal);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                //El terminal no existe. Puede agregarse.
                String agregarTerminal = "insert into terminales (terminal) values (?)";
                PreparedStatement ps1 = conn.prepareStatement(agregarTerminal);
                ps1.setString(1,terminal);
                ps1.executeUpdate();
                ps1.close();
                resultado = "Se agregó el usuario con éxito";
            }
            else
            {
                //Ya existe un terminal con ese nombre
                resultado = "Ya existe un terminal con ese nombre";
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            resultado = "Error de acceso a la base de datos";
            System.out.println(resultado);
        }
        return resultado;
    }

    public String eliminarTerminal(String terminal){
        //TODO verificar que el usuario no es nulo
        String resultado;
        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            String existeTerminal = "select * from terminales where terminal = ?";

            PreparedStatement ps = conn.prepareStatement(existeTerminal);
            ps.setString(1,terminal);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //El terminal existe. Puede eliminarse.

                //primero hay que borrar las autorizaciones del terminal
                int idTerminal = rs.getInt("idTerminal");
                String eliminarAutorizaciones = "delete from autorizaciones where idTerminal = ?";
                PreparedStatement ps1 = conn.prepareStatement(eliminarAutorizaciones);
                ps1.setInt(1,idTerminal);
                ps1.executeUpdate();
                ps1.close();

                //ahora borramos el terminal
                String eliminarTerminal = "delete from terminales where terminal = ?";
                PreparedStatement ps2 = conn.prepareStatement(eliminarTerminal);
                ps2.setString(1,terminal);
                ps2.executeUpdate();
                ps2.close();

                resultado = "Se eliminó el terminal con éxito";
            }
            else
            {
                //No existe un usuario con ese nombre
                resultado = "No existe un terminal con ese nombre";
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            resultado = "Error de acceso a la base de datos";
            System.out.println(resultado);
        }
        return resultado;

    }

    public List<String> obtenerListaTerminales(){
        List<String> resultado = new ArrayList<String>();

        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();
            String consultaTerminales = "select * from terminales";
            PreparedStatement ps = conn.prepareStatement(consultaTerminales);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String terminal = rs.getString("terminal");
                resultado.add(terminal);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error de acceso a la base de datos");
        }
        return resultado;
    }

    public List<String> obtenerListaAutorizados(String usuario){
        List<String> resultado = new ArrayList<String>();

        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();
            String consultaTerminales = "SELECT terminales.terminal FROM terminales INNER JOIN" +
                    " (usuarios INNER JOIN autorizaciones ON usuarios.IdUsuario = autorizaciones.idUsuario) " +
                    "ON terminales.IdTerminal = autorizaciones.idTerminal " +
                    "WHERE (((usuarios.usuario)=?))";
            PreparedStatement ps = conn.prepareStatement(consultaTerminales);
            ps.setString(1,usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String terminal = rs.getString("terminal");
                resultado.add(terminal);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error de acceso a la base de datos");
        }
        return resultado;
    }

    public boolean validarAcceso(Credenciales credenciales){
        //para probar, devuelve siempre verdadero
        return true;
    }

    public boolean validarAcceso2(Credenciales credenciales){

        System.out.println(credenciales.getUsuario());
        System.out.println(credenciales.getContraseña());
        System.out.println(credenciales.getTerminal());

        boolean salida = false;
        //acceso a la base de datos
        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            String usuarioAutorizado = "select * from usuarios where usuario = ? and clave = ?";

            PreparedStatement ps;
            ps = conn.prepareStatement(usuarioAutorizado);
            ps.setString(1, credenciales.getUsuario());
            ps.setString(2, credenciales.getContraseña());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //usuario y clave válidas
                salida = true;
            }
            rs.close();
            ps.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return salida;
    }



}
