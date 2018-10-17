
package uy.com.antel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para ticket complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ticket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaHoraInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaHoraVenta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idTicket" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="matricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minutos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ticket", propOrder = {
    "fechaHoraInicio",
    "fechaHoraVenta",
    "idTicket",
    "matricula",
    "minutos"
})
public class Ticket {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraInicio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHoraVenta;
    protected long idTicket;
    protected String matricula;
    protected int minutos;

    /**
     * Obtiene el valor de la propiedad fechaHoraInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * Define el valor de la propiedad fechaHoraInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHoraInicio(XMLGregorianCalendar value) {
        this.fechaHoraInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHoraVenta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    /**
     * Define el valor de la propiedad fechaHoraVenta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHoraVenta(XMLGregorianCalendar value) {
        this.fechaHoraVenta = value;
    }

    /**
     * Obtiene el valor de la propiedad idTicket.
     * 
     */
    public long getIdTicket() {
        return idTicket;
    }

    /**
     * Define el valor de la propiedad idTicket.
     * 
     */
    public void setIdTicket(long value) {
        this.idTicket = value;
    }

    /**
     * Obtiene el valor de la propiedad matricula.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Define el valor de la propiedad matricula.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricula(String value) {
        this.matricula = value;
    }

    /**
     * Obtiene el valor de la propiedad minutos.
     * 
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Define el valor de la propiedad minutos.
     * 
     */
    public void setMinutos(int value) {
        this.minutos = value;
    }

}
