package reloj_checador;

/**
 *
 * @author diego
 */
public class CHorario 
{
    private int idHoraClase;
    private int idGrupo;
    private int idMateria;
    private int idProfesor;
    private String diaSemana;
    
    public CHorario()
    {
        
    }
    
    public CHorario(int idHora, int idG, int idMat, int idProf, String diaS)
    {
        idHoraClase = idHora;
        idGrupo = idG;
        idMateria = idMat;
        idProfesor = idProf;
        diaSemana = diaS;
    }

    public int getIdHoraClase() 
    {
        return idHoraClase;
    }

    public void setIdHoraClase(int idHoraClase)
    {
        this.idHoraClase = idHoraClase;
    }

    public int getIdGrupo()
    {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo)
    {
        this.idGrupo = idGrupo;
    }

    public int getIdMateria()
    {
        return idMateria;
    }

    public void setIdMateria(int idMateria)
    {
        this.idMateria = idMateria;
    }

    public int getIdProfesor()
    {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor)
    {
        this.idProfesor = idProfesor;
    }

    public String getDiaSemana()
    {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana)
    {
        this.diaSemana = diaSemana;
    }
}
