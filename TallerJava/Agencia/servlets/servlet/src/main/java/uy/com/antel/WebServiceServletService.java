
package uy.com.antel;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.1-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WebServiceServletService", targetNamespace = "http://antel.com.uy/", wsdlLocation = "http://localhost:8080/servlet-imm/eco?wsdl")
public class WebServiceServletService
    extends Service
{

    private final static URL WEBSERVICESERVLETSERVICE_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICESERVLETSERVICE_EXCEPTION;
    private final static QName WEBSERVICESERVLETSERVICE_QNAME = new QName("http://antel.com.uy/", "WebServiceServletService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/servlet-imm/eco?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICESERVLETSERVICE_WSDL_LOCATION = url;
        WEBSERVICESERVLETSERVICE_EXCEPTION = e;
    }

    public WebServiceServletService() {
        super(__getWsdlLocation(), WEBSERVICESERVLETSERVICE_QNAME);
    }

    public WebServiceServletService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICESERVLETSERVICE_QNAME, features);
    }

    public WebServiceServletService(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICESERVLETSERVICE_QNAME);
    }

    public WebServiceServletService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICESERVLETSERVICE_QNAME, features);
    }

    public WebServiceServletService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceServletService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebServiceServlet
     */
    @WebEndpoint(name = "WebServiceServletPort")
    public WebServiceServlet getWebServiceServletPort() {
        return super.getPort(new QName("http://antel.com.uy/", "WebServiceServletPort"), WebServiceServlet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceServlet
     */
    @WebEndpoint(name = "WebServiceServletPort")
    public WebServiceServlet getWebServiceServletPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://antel.com.uy/", "WebServiceServletPort"), WebServiceServlet.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICESERVLETSERVICE_EXCEPTION!= null) {
            throw WEBSERVICESERVLETSERVICE_EXCEPTION;
        }
        return WEBSERVICESERVLETSERVICE_WSDL_LOCATION;
    }

}
