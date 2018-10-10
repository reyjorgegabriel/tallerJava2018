package uy.com.antel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = "/paginas/listartickets")
public class ServletListaTicketsIntendencia extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        AdminTicketsIntendencia ati = AdminTicketsIntendencia.getInstance();
        List<TicketIntendencia> listaTickets = ati.listaTicketsIntendencia();

        resp.getWriter().println("<html>\n<body>\n<table border=\"1\">");
        resp.getWriter().println("<tr><td>ID</td><td>MATRICULA</td>" +
                "<td>INICIO</td><td>MINUTOS</td>" +
                "<td>AGENCIA</td><td>VENTA</td><td>IMPORTE</td><td>ANULADO</td></tr>");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


        for(TicketIntendencia ti: listaTickets){

            resp.getWriter().print("<tr>");
            resp.getWriter().print("<td>" + ti.getIdTicket() + "</td>");
            resp.getWriter().print("<td>" + ti.getMatricula() + "</td>");

            String strFechaHoraInicio = sdf.format(ti.getFechaHoraInicio().getTime());
            resp.getWriter().print("<td>" + strFechaHoraInicio + "</td>");
            resp.getWriter().print("<td>" + ti.getMinutos() + "</td>");

            resp.getWriter().print("<td>" + ti.getAgencia() + "</td>");

            String strFechaHoraVenta = sdf.format(ti.getFechaHoraVenta().getTime());
            resp.getWriter().print("<td>" + strFechaHoraVenta + "</td>");
            resp.getWriter().print("<td>" + ti.getImporte() + "</td>");

            //System.out.println("##################" + ti.getFechaHoraVenta().getTime());
            //System.out.println("$$$$$$$$$$$$$$$$$$" + ti.getFechaHoraVenta());

            resp.getWriter().print("<td>" + ti.isAnulado() + "</td>");
            resp.getWriter().println("</tr>");
        }

        resp.getWriter().println("</table>\n</body>\n</html>");

    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Se inició el servlet");
    }
}
