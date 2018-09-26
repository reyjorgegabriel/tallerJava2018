package uy.com.antel;
import javax.jws.*;

@WebService
public class WebServiceServlet {

    @WebMethod
    public String eco(String entrada){
        String mensaje = "El webservice de Intendencia recibió: " + entrada;
        System.out.println("---------------------servidor webservice-------------------------");
        System.out.println(mensaje);
        System.out.println("-----------------------------------------------------------------");

        String retorno;
        try {
            switch (entrada.charAt(0)) {
                case 'I': {
                    String s = entrada.substring(1);
                    if(s.equals("")){
                        retorno = "E";
                    }
                    else{
                        retorno = "I000001";
                    }

                }
                break;
                case 'A': {
                    String s = entrada.substring(1);
                    if(s.equals("")){
                        retorno = "E";
                    }
                    else{
                    retorno = "A" + s;
                    }
                }
                break;
                default: {
                    retorno = "E";
                }
            }
        }
        catch(Exception e){
            retorno = "E";
        }


        return retorno;
    }

    @WebMethod
    public String altaTicket(String prueba){
        return "está andando " + prueba;

    }
}
