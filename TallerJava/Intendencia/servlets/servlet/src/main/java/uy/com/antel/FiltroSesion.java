package uy.com.antel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "FiltroSesion", urlPatterns =  {"/paginas/*"})
public class FiltroSesion implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("contexto.log: FiltroSesion inicializado");
        System.out.println("filtro inicializado");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("\n\ncontexto.log: recurso solicitado = " + uri);

        System.out.println("\n\nURL solicitada:" + uri);

        //Se solicita la sesión. Con el parámetro "false", si no hay una sesión,
        //se devuelve "null"
        HttpSession session = req.getSession(false);

        if(session == null){
            System.out.println("\n\nsesión nula\n\n");
            if(uri.equals("/servlet-imm/eco")){
                System.out.println("pedido de acceso a webservice, no se verifica validez de la sesión\n");
                chain.doFilter(request, response);
            }
            else{
                System.out.println("sesión no válida, se redirecciona a login\n");
                res.sendRedirect("/servlet-imm/index.html");
            }
        }
        else
        {
            System.out.println("\n\nsesión no nula, ID: " + session.getId() + "\n\n");
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
        //close any resources here
    }

}