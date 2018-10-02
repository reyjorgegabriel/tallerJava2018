
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

    private final static QName _AltaTicket_QNAME = new QName("http://antel.com.uy/", "altaTicket");
    private final static QName _AltaTicketResponse_QNAME = new QName("http://antel.com.uy/", "altaTicketResponse");
    private final static QName _AnularTicket_QNAME = new QName("http://antel.com.uy/", "anularTicket");
    private final static QName _AnularTicketResponse_QNAME = new QName("http://antel.com.uy/", "anularTicketResponse");
    private final static QName _Eco_QNAME = new QName("http://antel.com.uy/", "eco");
    private final static QName _EcoResponse_QNAME = new QName("http://antel.com.uy/", "ecoResponse");
    private final static QName _VenderTicket_QNAME = new QName("http://antel.com.uy/", "venderTicket");
    private final static QName _VenderTicketResponse_QNAME = new QName("http://antel.com.uy/", "venderTicketResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.com.antel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AltaTicket }
     * 
     */
    public AltaTicket createAltaTicket() {
        return new AltaTicket();
    }

    /**
     * Create an instance of {@link AltaTicketResponse }
     * 
     */
    public AltaTicketResponse createAltaTicketResponse() {
        return new AltaTicketResponse();
    }

    /**
     * Create an instance of {@link AnularTicket }
     * 
     */
    public AnularTicket createAnularTicket() {
        return new AnularTicket();
    }

    /**
     * Create an instance of {@link AnularTicketResponse }
     * 
     */
    public AnularTicketResponse createAnularTicketResponse() {
        return new AnularTicketResponse();
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
     * Create an instance of {@link VenderTicket }
     * 
     */
    public VenderTicket createVenderTicket() {
        return new VenderTicket();
    }

    /**
     * Create an instance of {@link VenderTicketResponse }
     * 
     */
    public VenderTicketResponse createVenderTicketResponse() {
        return new VenderTicketResponse();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link ResultadoOperacion }
     * 
     */
    public ResultadoOperacion createResultadoOperacion() {
        return new ResultadoOperacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaTicket }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "altaTicket")
    public JAXBElement<AltaTicket> createAltaTicket(AltaTicket value) {
        return new JAXBElement<AltaTicket>(_AltaTicket_QNAME, AltaTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "altaTicketResponse")
    public JAXBElement<AltaTicketResponse> createAltaTicketResponse(AltaTicketResponse value) {
        return new JAXBElement<AltaTicketResponse>(_AltaTicketResponse_QNAME, AltaTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnularTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AnularTicket }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "anularTicket")
    public JAXBElement<AnularTicket> createAnularTicket(AnularTicket value) {
        return new JAXBElement<AnularTicket>(_AnularTicket_QNAME, AnularTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnularTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AnularTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "anularTicketResponse")
    public JAXBElement<AnularTicketResponse> createAnularTicketResponse(AnularTicketResponse value) {
        return new JAXBElement<AnularTicketResponse>(_AnularTicketResponse_QNAME, AnularTicketResponse.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VenderTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VenderTicket }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "venderTicket")
    public JAXBElement<VenderTicket> createVenderTicket(VenderTicket value) {
        return new JAXBElement<VenderTicket>(_VenderTicket_QNAME, VenderTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VenderTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VenderTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://antel.com.uy/", name = "venderTicketResponse")
    public JAXBElement<VenderTicketResponse> createVenderTicketResponse(VenderTicketResponse value) {
        return new JAXBElement<VenderTicketResponse>(_VenderTicketResponse_QNAME, VenderTicketResponse.class, null, value);
    }

}
