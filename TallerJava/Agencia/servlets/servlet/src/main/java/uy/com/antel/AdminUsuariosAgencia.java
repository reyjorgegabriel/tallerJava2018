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
                String eliminarUsuario = "delete from usuarios where usuario = ?";
                PreparedStatement ps1 = conn.prepareStatement(eliminarUsuario);
                ps1.setString(1,nombre);
                ps1.executeUpdate();
                ps1.close();
                resultado = "Se eliminó el usuario con éxito";
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


    public boolean validarAcceso(Credenciales credenciales){
        //para probar, devuelve siempre verdadero
        System.out.println(credenciales.getUsuario());
        System.out.println(credenciales.getContraseña());
        System.out.println(credenciales.getTerminal());
        //acceso a la base de datos
        InitialContext initContext;
        try{
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS1");
            Connection conn = ds.getConnection();

            PreparedStatement ps;

            conn.close();
            /*PreparedStatement ps3 = conn.prepareStatement(sqlInsertTicket(ticketAgencia, (int)ro.getIdTicket(),
                    terminal, ro.getImporte()));
            ps3.executeUpdate();
            ps3.close();*/

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }






}
