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
public class CConexionGrupo extends CConexion {
    
    public CConexionGrupo()
    {
        
    }
    
    public int insertaGrupo(CGrupo g)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("INSERT INTO horarios.grupo(ciclo_escolar,semestre,grupo)\n"+
                                     "VALUES ('%s','%d','%s');",
                                        g.getCiclo_Escolar(),
                                        g.getSemestre(),
                                        g.getGrupo()
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
    
    public int eliminaGrupo(long idGrupo)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("DELETE FROM horarios.grupo WHERE id_grupo = %d ",idGrupo);
                                     
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
    
    public int actualizaGrupo(CGrupo g, long idGrupo)
    {
        int res = 0;
        Statement stmt;
        ResultSet rs;
        String query = String.format("UPDATE horarios.grupo SET ciclo_escolar = '%s', semestre = '%d', grupo = '%s' WHERE id_grupo = %d",
                                        g.getCiclo_Escolar(),
                                        g.getSemestre(),
                                        g.getGrupo(),
                                        idGrupo);
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
    
    public DefaultTableModel llenarTablaGrupos(JTable pTabla)
    {
        DefaultTableModel TModel = new DefaultTableModel();
        Statement stmt;
        ResultSet rs = null;
        String query = "SELECT* FROM horarios.grupo";
        
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
