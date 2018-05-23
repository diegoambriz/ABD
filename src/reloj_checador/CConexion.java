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

/**
 *
 * @author diego
 */
public class CConexion {
    
    private int conectado;
    private String url;
    private Connection con;
    private String nomDriver;
    private String nomBD;
    private String nomUsuario;
    private String passWord;
    private int puerto;
    
    public CConexion()
    {
        conectado = 0;
        this.nomDriver = "org.postgresql.Driver";
        this.nomBD = "Reloj_Checador";
        this.puerto = 5432;
        this.url = String.format("jdbc:postgresql://localhost:%d/%s", this.puerto, this.nomBD);
        this.nomUsuario = "postgres";
        this.passWord = "postgres";
        this.con = null;
        
        this.crearConexion();
    }
    
    public CConexion(String usu, String passw)
    {
        conectado = 0;
        this.nomDriver = "org.postgresql.Driver";
        this.nomBD = "Reloj_Checador";
        this.puerto = 5432;
        this.url = String.format("jdbc:postgresql://localhost:%d/%s", this.puerto, this.nomBD);
        this.nomUsuario = usu;
        this.passWord = passw;
        this.con = null;
        
        this.crearConexion();
    }
    
    private void crearConexion()
    {
        try
        {
            //Acceso al Driver
            Class.forName(this.nomDriver);
            this.con = DriverManager.getConnection(this.url, this.nomUsuario, this.passWord);
            
            if(this.con != null)
            {
                //System.out.println("CONECTADO");
                this.conectado = 1;
            }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public int ObtenerConexion()
    {
        return this.conectado;
    }

    public Connection getCon() {
        return con;
    }

    public int getConectado() {
        return conectado;
    }

    public void setConectado(int conectado) {
        this.conectado = conectado;
    }

    
}

