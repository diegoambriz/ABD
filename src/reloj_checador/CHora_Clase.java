/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloj_checador;

/**
 *
 * @author diego
 */
public class CHora_Clase 
{
    private String hora_Inicio, hora_Fin;
    
    public CHora_Clase()
    {
        hora_Inicio = "";
        hora_Fin = "";
    }
    
    public CHora_Clase(String horaIn, String horaFin)
    {
        hora_Inicio = horaIn;
        hora_Fin = horaFin;
    }

    public String getHora_Inicio() 
    {
        return hora_Inicio;
    }

    public void setHora_Inicio(String hora_Inicio)
    {
        this.hora_Inicio = hora_Inicio;
    }

    public String getHora_Fin()
    {
        return hora_Fin;
    }

    public void setHora_Fin(String hora_Fin)
    {
        this.hora_Fin = hora_Fin;
    }
}
