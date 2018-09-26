
package uy.com.antel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.com.antel package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Eco_QNAME = new QName("http://antel.com.uy/", "eco");
    private final static QName _EcoResponse_QNAME = new QName("http://antel.com.uy/", "ecoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.com.antel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Eco }
     * 
     */
    public Eco createEco() {
        return new Eco();
    }

    /**
     * Create an instance of {@link EcoResponse }
     * 
     */
    public EcoResponse createEcoResponse() {
        return new EcoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Eco }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Eco }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "eco")
    public JAXBElement<Eco> createEco(Eco value) {
        return new JAXBElement<Eco>(_Eco_QNAME, Eco.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EcoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EcoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "ecoResponse")
    public JAXBElement<EcoResponse> createEcoResponse(EcoResponse value) {
        return new JAXBElement<EcoResponse>(_EcoResponse_QNAME, EcoResponse.class, null, value);
    }

}
