package uy.com.antel;

public class FormatoIncorrectoFechaException extends Throwable {

    public FormatoIncorrectoFechaException(){

        //System.out.println("Formato de fecha utilizado incorrecto");
    };

    public FormatoIncorrectoFechaException(String s) {

        super(s);
        //System.out.println("Se llama al metodo con parametro string " + s);
    }
}



