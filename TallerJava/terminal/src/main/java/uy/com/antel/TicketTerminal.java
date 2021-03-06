package uy.com.antel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TicketTerminal implements java.io.Serializable {

    private String matricula;

    private Calendar fechaVenta;
    private Calendar fechaInicio;
    private int minutosEstacionamiento;

    public TicketTerminal(){
        this.matricula = "";
        this.fechaVenta = Calendar.getInstance();
        this.fechaInicio = Calendar.getInstance();
        this.minutosEstacionamiento=0;
    }


    public TicketTerminal (String matricula, int minutos){

        this.matricula = matricula;

        minutosEstacionamiento = minutos;

        fechaVenta = Calendar.getInstance();

        fechaInicio = fechaVenta;
    }


    public TicketTerminal (String matricula, String fechaHora, int minutos) throws FormatoIncorrectoFechaException {

        this.matricula = matricula;

        minutosEstacionamiento = minutos;

        try {
            DateFormat formateador = new SimpleDateFormat("dd/MM/yy hh:mm");;
            Date fechaConHora = (Date)formateador.parse(fechaHora);
            fechaInicio = Calendar.getInstance();
            fechaInicio.setTime(fechaConHora);
        } catch (ParseException e) {
            System.out.println("\nError de formato de la fecha y hora ingresadas: " +  fechaHora);
            throw new FormatoIncorrectoFechaException();
        }

        fechaVenta = Calendar.getInstance();

    }


    public String toString() {

        Date dateVenta =  fechaVenta.getTime();

        Date dateInicio =  fechaInicio.getTime();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy hh:mm");

        return "-Maticula: " + matricula + " -Fecha Venta: " + sdf1.format(dateVenta) +
                " -Fecha Inicio estacionamiento: " + sdf1.format(dateInicio) + " -Minutos: " + minutosEstacionamiento;

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaVenta = fechaInicio;
    }

    public Calendar getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Calendar fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getMinutosEstacionamiento() {
        return minutosEstacionamiento;
    }

    public void setMinutosEstacionamiento(int minutosEstacionamiento) {
        this.minutosEstacionamiento = minutosEstacionamiento;
    }

}


