package uy.com.antel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ServletServidorAgenciaATerminal", loadOnStartup = 1) //define como se va a acceder al servlet (parte de la url que lo identifica)
//loadOnStartup sirve para decirle al servidor que arranque al inicio del servidor o a demanda.
public class ServletServidorAgenciaATerminal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException, ServletException {

        System.out.println("  ---Se inicia el servlet para lanzar al servidor que atendera las conexiones de las terminales");

        resp.getWriter().println("<html>\n<body>\n");
        resp.getWriter().println("<br>Se detiene el servidor que atiende las conexiones a las terminales\n");
        resp.getWriter().println("</body>\n</html>");

        this.destroy();
    }


    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Destruyendo el servlet Servidor Agencia A Terminal");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("  ---Se inicia el servlet para lanzar al servidor que atendera las conexiones de las terminales");

        // Se instancia una clase que sera el servidor de los clientes de las terminales y se lanza un hilo aparte para eso.
        Runnable servidorDeClientes = new ServidorClientes();
        Thread hilo = new Thread(servidorDeClientes);
        hilo.start();

        System.out.println("   -Se ha iniciado el servidor que atendera las conexiones de las terminales.-");
    }
}
