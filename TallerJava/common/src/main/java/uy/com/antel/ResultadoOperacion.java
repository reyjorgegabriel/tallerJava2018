package uy.com.antel;

public class ResultadoOperacion {
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




}
