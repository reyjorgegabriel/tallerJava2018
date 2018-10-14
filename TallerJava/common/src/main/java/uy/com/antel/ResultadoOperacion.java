package uy.com.antel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultadoOperacion implements java.io.Serializable {

    //Jorge, tuve que poner serializadle esta clase, sino no lo podía enviarla por la conexión a la terminal.
    //También hago método toString para visualizar mas cómodo los datos.

    protected long idTicket;
    protected int importe;
    protected int codResultado;
    protected String msjResultado;

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }

    public String getMsjResultado() {
        return msjResultado;
    }

    public void setMsjResultado(String msjResultado) {
        this.msjResultado = msjResultado;
    }

    public String toString() {

        return "Objeto ResultadoOperacion:: -Identificador de ticket: " + idTicket + " -Importe: " + importe +
                " -Codigo Resultado: " + codResultado + " -Mensaje resultado: " + msjResultado;

    }




}
