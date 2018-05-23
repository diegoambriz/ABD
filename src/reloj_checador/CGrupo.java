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
public class CGrupo {
    
    private String ciclo_Escolar, grupo;
    private int semestre;
    
    public CGrupo()
    {
        ciclo_Escolar = "";
        grupo = "";
        semestre = 0;
    }
    
    public CGrupo(String unCiclo, int unSem, String unGrupo)
    {
        ciclo_Escolar = unCiclo;
        semestre = unSem;
        grupo = unGrupo;
    }

    public String getCiclo_Escolar() 
    {
        return ciclo_Escolar;
    }

    public void setCiclo_Escolar(String ciclo_Escolar) 
    {
        this.ciclo_Escolar = ciclo_Escolar;
    }

    public String getGrupo()
    {
        return grupo;
    }

    public void setGrupo(String grupo)
    {
        this.grupo = grupo;
    }

    public int getSemestre() 
    {
        return semestre;
    }

    public void setSemestre(int semestre)
    {
        this.semestre = semestre;
    }
}
