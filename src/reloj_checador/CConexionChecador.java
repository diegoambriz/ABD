/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author diego
 */
public class CConexionChecador extends CConexion
{
    public CConexionChecador()
    {
        
    }
    
    public int insertaAsistencia(CChecador ch)
    {
        String f;
        DateTimeFormatter forma = DateTimeFormatter.ofPattern("HH:mm:ss",Locale.US);
        //f = forma.format(ch.getHora());
        
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("INSERT INTO controlpagos.registro_asistencia(id_horario,fecha,hora,firma)\n"+
                                     "VALUES ('%d','%s','%s','%s');",
                                        ch.getIdHorario(),
                                        ch.getFecha(),
                                        ch.getHora(),
                                        ch.getFirma()
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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return res;
    }
    
    public DefaultTableModel llenarTablaAsistencia(JTable pTabla)
    {
        DefaultTableModel TModel = new DefaultTableModel();
        Statement stmt;
        ResultSet rs = null;
        
        /*String query = "SELECT H.Id_Horario, CAST(Hora_Inicio AS VARCHAR(10)) || ' - ' || CAST(Hora_Fin AS VARCHAR(10)) AS HoraClase, G.Ciclo_Escolar || '/' || CAST(G.Semestre AS VARCHAR(2)) || G.Grupo AS Grupo, M.Nombre AS Materia, P.Nombre AS Profesor, H.Dia_Semana ";
               query += "FROM Horarios.Horario H INNER JOIN Horarios.Hora_Clase HC ON HC.Id_Hora_Clase = H.Id_Hora_Clase INNER JOIN Horarios.Grupo G ON G.Id_Grupo = H.Id_Grupo INNER JOIN Horarios.Materia M ON M.Id_Materia = H.Id_Materia INNER JOIN Horarios.Profesor P ON P.Id_Profesor = H.Id_Profesor";
        */
        String query = "SELECT * FROM controlpagos.registro_asistencia";
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
