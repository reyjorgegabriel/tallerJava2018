package uy.com.antel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TicketTerminal {

    private String matricula;
    private Calendar fechaInicio;
    private int minutosEstacionamiento;

    public TicketTerminal (String matricula, int minutos){

        this.matricula = matricula;

        minutosEstacionamiento = minutos;

        fechaInicio = Calendar.getInstance();
    }


    public TicketTerminal (String matricula, String fechaHora, int minutos){

        this.matricula = matricula;

        minutosEstacionamiento = minutos;

        try {
            DateFormat formateador ;
            Date fechaConHora;
            formateador = new SimpleDateFormat("dd/MM/yy hh:mm");
            fechaConHora = (Date)formateador.parse(fechaHora);
            fechaInicio = Calendar.getInstance();
            fechaInicio.setTime(fechaConHora);
            //System.out.println("Fecha ingresada " + fechaConHora );
        } catch (ParseException e) {
            System.out.println("Error de formato :" + e);
        }

    }


    public String toString() {

        Date datePublicacion =  fechaInicio.getTime();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy hh:mm");

        return "-Maticula: " + matricula + " -Fecha: " + sdf1.format(datePublicacion) + " -Minutos: " + minutosEstacionamiento;

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
        this.fechaInicio = fechaInicio;
    }

    public int getMinutosEstacionamiento() {
        return minutosEstacionamiento;
    }

    public void setMinutosEstacionamiento(int minutosEstacionamiento) {
        this.minutosEstacionamiento = minutosEstacionamiento;
    }

}


