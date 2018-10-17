
package uy.com.antel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para resultadoOperacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resultadoOperacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codResultado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idTicket" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="msjResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoOperacion", propOrder = {
    "codResultado",
    "idTicket",
    "importe",
    "msjResultado"
})
public class ResultadoOperacion {

    protected int codResultado;
    protected long idTicket;
    protected int importe;
    protected String msjResultado;

    /**
     * Obtiene el valor de la propiedad codResultado.
     * 
     */
    public int getCodResultado() {
        return codResultado;
    }

    /**
     * Define el valor de la propiedad codResultado.
     * 
     */
    public void setCodResultado(int value) {
        this.codResultado = value;
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
     * Obtiene el valor de la propiedad importe.
     * 
     */
    public int getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     */
    public void setImporte(int value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad msjResultado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsjResultado() {
        return msjResultado;
    }

    /**
     * Define el valor de la propiedad msjResultado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsjResultado(String value) {
        this.msjResultado = value;
    }

}
