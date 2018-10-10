package uy.com.antel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/autenticar")
public class ServletAutenticar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("usuario");
        String pwd = req.getParameter("password");

        if(user.equals("admin") && pwd.equals("admin")){
            HttpSession session = req.getSession();
            session.setAttribute("usuario", "admin");
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(1*60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30*60);
            resp.addCookie(userName);
            //cuidado, siempre llama a GET
            resp.sendRedirect("/servlet-imm/paginas/opciones");
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out= resp.getWriter();
            out.println("<font color=red>Nombre de usuario o password incorrectos</font>");
            rd.include(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Se inició el servlet");
    }


}
