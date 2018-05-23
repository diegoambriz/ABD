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

public class CConexionHora_Clase extends CConexion
{ 
    public CConexionHora_Clase()
    {
        
    }
    
    public int insertaHora_Clase(CHora_Clase cl)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("INSERT INTO horarios.hora_clase(hora_inicio,hora_fin)\n"+
                                     "VALUES ('%s','%s');",
                                        cl.getHora_Inicio(),
                                        cl.getHora_Fin()
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
    
    public int eliminaHora_Clase(long idHoraC)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("DELETE FROM horarios.hora_clase WHERE id_hora_clase = %d ",idHoraC);
                                     
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
    
    public int actualizaHora_Clase(CHora_Clase cl, long idHoraC)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("UPDATE horarios.hora_clase SET hora_inicio = '%s', hora_fin = '%s' WHERE id_hora_clase = %d",
                                        cl.getHora_Inicio(),
                                        cl.getHora_Fin(), 
                                        idHoraC);
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
    
    public DefaultTableModel llenarTablaHoraClase(JTable pTabla)
    {
        DefaultTableModel TModel = new DefaultTableModel();
        Statement stmt;
        ResultSet rs = null;
        String query = "SELECT* FROM horarios.hora_clase";
        
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
