package uy.com.antel;


import java.util.Calendar;

public class Ticket {
    protected long idTicket;
    protected String matricula;
    protected Calendar fechaHoraVenta;
    protected Calendar fechaHoraInicio;
    protected int minutos;

    public Calendar getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(Calendar fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public Calendar getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Calendar fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
