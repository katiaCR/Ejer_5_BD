/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno2
 */
public class DataBase {
    String bd;
    String login;
    String password;
    String servidorMySQL;
    Connection conexion;
    
    public DataBase(String bd, String login, String password, String servidorMySQL) {
        this.bd = bd;
        this.login = login;
        this.password = password;
        this.servidorMySQL = servidorMySQL;
    }
    
    public boolean abrirConexion() {
        boolean estado = false;
        
        try {
            //Cargamos el driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Crear conexion a la BDD
            conexion = DriverManager.getConnection(servidorMySQL + bd, login, password);
            estado = true;
        } catch (ClassNotFoundException ex) { System.out.println("Class Not Found Exception: ");
        } catch (SQLException ex) { System.out.println("SQL Exception: " +ex.getLocalizedMessage());
        } catch (Exception ex) { System.out.println("Exception General: " +ex.getLocalizedMessage());
        }
        
        return estado;
    }
    
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch(SQLException e) { System.out.println(e.getMessage());
        }
    }
    
    public int ejecutaUpdate(String statement) {
        int n = 0;
        
        try {
            Statement st = conexion.createStatement();
            System.out.println("Sentencia: " +statement);
            n = st.executeUpdate(statement); //Para que la ejecute.
        } catch (SQLException ex) {
            System.out.println("Exception: " +ex.getLocalizedMessage());
        }
        
        return n;
    }
    
    public ResultSet ejecutaConsulta(String consulta){
        Statement st=null;
        ResultSet rs =null;
        try {
            st=conexion.createStatement();
            rs=st.executeQuery(consulta);
            
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        }
        return rs;
    }
    
    public void recorreResultado(ResultSet rs){
        try{
            while(rs.next()){
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }
        }catch(SQLException ex){
            System.out.println("Error sql: " + ex.getMessage());
        }
    }
    
    
}
