package uy.com.antel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(urlPatterns = "/paginas/opciones")
public class ServletOpciones extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.getWriter().println("<html>\n<body>");
        resp.getWriter().println("<h2>Intendencia</h2>");
        //prueba de cookies
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for (Cookie cookie: cookies) {
                resp.getWriter().println("<P> cookie name" + cookie.getName() + "</p>");
                resp.getWriter().println("<P> cookie value" + cookie.getValue() + "</p>");
            }
        }
        //prueba de sesión
        HttpSession session = req.getSession(false);
        if(session != null){

            Enumeration e = (Enumeration) (session.getAttributeNames());

            while ( e.hasMoreElements())
            {
                Object tring;
                if((tring = e.nextElement())!=null)
                {
                    resp.getWriter().println("<p>"+ (String) tring + " -- " + session.getValue((String) tring) + "</p>" );
                }

            }



        }
        else{
            resp.getWriter().println("<p>sesión no válida</p>");
        }


        resp.getWriter().println("<form action=\"listartickets\">");
        resp.getWriter().println("<input type=\"submit\" value=\"ver lista de tickets\" />");
        resp.getWriter().println("</form>");
        resp.getWriter().println("</body>\n</html>");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Se inició el servlet");
    }





}
