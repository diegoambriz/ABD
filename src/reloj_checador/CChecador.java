/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj_checador;
import java.util.Date;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.sql.Time; 

/**
 *
 * @author diego
 */
public class CChecador 
{
    private int idHorario;
    private LocalDate fecha;
    private LocalTime hora;
    private String firma;
    
    public CChecador()
    {
        idHorario = 0;
        fecha = null;
        hora = null;
        firma = "";
    }
    
    public CChecador(int idH, LocalDate fec, LocalTime hor, String fir)
    {
        idHorario = idH;
        fecha = fec;
        hora = hor;
        firma = fir;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

   
}
