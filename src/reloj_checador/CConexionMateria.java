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
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class CConexionMateria extends CConexion{
    
    public CConexionMateria()
    {
        
    }
    
    public int insertaMateria(CMateria c)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("INSERT INTO horarios.materia(nombre,num_horas_clase)\n"+
                                     "VALUES ('%s','%d');",
                                        c.getNombreMateria(),
                                        c.getNum_Horas_Clase()
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
    
    public int eliminaMateria(long idMat)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("DELETE FROM horarios.materia WHERE id_materia = %d ",idMat);
                                     
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
            //e.printStackTrace();
        }
        
        return res;
    }
    
    public int actualizaMateria(CMateria c, long idMat)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("UPDATE horarios.materia SET nombre = '%s', num_horas_clase = '%d' WHERE id_materia = %d",
                                        c.getNombreMateria(),
                                        c.getNum_Horas_Clase(), 
                                        idMat);
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
    
    public DefaultTableModel llenarTablaMaterias(JTable pTabla)
    {
        DefaultTableModel TModel = new DefaultTableModel();
        Statement stmt;
        ResultSet rs = null;
        String query = "SELECT* FROM horarios.materia";
        
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
