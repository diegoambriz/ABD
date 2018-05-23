package reloj_checador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class CConexionHorario extends CConexion
{
    public CConexionHorario()
    {
        
    }
    
    public int insertaHorario(CHorario h)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("INSERT INTO horarios.horario(id_hora_clase,id_grupo,id_materia,id_profesor, dia_semana)\n"+
                                     "VALUES ('%d','%d','%d','%d','%s');",
                                        h.getIdHoraClase(),
                                        h.getIdGrupo(),
                                        h.getIdMateria(),
                                        h.getIdProfesor(),
                                        h.getDiaSemana()
                                        );
        try 
        {
            stmt = this.getCon().createStatement();
            res = stmt.executeUpdate(query);
            stmt.execute("END");
            stmt.close();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            //System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        
        return res;
    }
    
    public int eliminaHorario(long idHor)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("DELETE FROM horarios.horario WHERE id_horario = %d ",idHor);
                                     
        try 
        {
            stmt = this.getCon().createStatement();
            res = stmt.executeUpdate(query);
            stmt.execute("END");
            stmt.close();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            //System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        
        return res;
    }
    
    public int actualizaHorario(CHorario h, long idHor)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("UPDATE horarios.horario SET id_hora_clase = '%d', id_grupo = '%d', id_materia = '%d', id_profesor = '%d', dia_semana = '%s' WHERE id_Profesor = %d",
                                        h.getIdHoraClase(),
                                        h.getIdGrupo(),
                                        h.getIdMateria(),
                                        h.getIdProfesor(),
                                        h.getDiaSemana(), 
                                        idHor);
        try 
        {
            stmt = this.getCon().createStatement();
            res = stmt.executeUpdate(query);
            stmt.execute("END");
            stmt.close();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            //System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        
        return res;
    }
    
    public DefaultTableModel llenarTablaHorario(JTable pTabla)
    {
        DefaultTableModel TModel = new DefaultTableModel();
        Statement stmt;
        ResultSet rs = null;
        
        String query = "SELECT H.Id_Horario, CAST(Hora_Inicio AS VARCHAR(10)) || ' - ' || CAST(Hora_Fin AS VARCHAR(10)) AS HoraClase, G.Ciclo_Escolar || '/' || CAST(G.Semestre AS VARCHAR(2)) || G.Grupo AS Grupo, M.Nombre AS Materia, P.Nombre AS Profesor, H.Dia_Semana ";
               query += "FROM Horarios.Horario H INNER JOIN Horarios.Hora_Clase HC ON HC.Id_Hora_Clase = H.Id_Hora_Clase INNER JOIN Horarios.Grupo G ON G.Id_Grupo = H.Id_Grupo INNER JOIN Horarios.Materia M ON M.Id_Materia = H.Id_Materia INNER JOIN Horarios.Profesor P ON P.Id_Profesor = H.Id_Profesor";
        
        this.limpiarJTable(pTabla);
        
        try
        {
            stmt = this.getCon().createStatement();
            rs = stmt.executeQuery(query);
            pTabla.setModel(TModel);
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            for(int i = 1; i<= cantidadColumnas;i++)
            {
                TModel.addColumn(rsMd.getColumnLabel(i));
            }
            while(rs.next())
            {
                Object[] fila = new Object[cantidadColumnas];
                for(int i = 0; i<cantidadColumnas;i++)
                {
                    fila[i] = rs.getObject(i+1);
                }
                TModel.addRow(fila);
            }
            rs.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return TModel;
    }
    
    private void limpiarJTable(JTable pTabla)
    {
        DefaultTableModel m1 = (DefaultTableModel)pTabla.getModel();
        
        pTabla.setModel(new DefaultTableModel());
    }
}
