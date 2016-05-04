/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer1;

import Student.Alumno;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class Bd {
    Connection con=null;
    String servidor="jdbc:mysql://localhost/";
    String bd;
    String user;
    String password;
    
    public Bd(String bd, String user, String password){
        this.bd=bd;
        this.user=user;
        this.password=password;
    }
    public boolean getConexion(){
        boolean estado = false;
        
        try {
            //Cargamos el driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Crear conexion a la BDD
            con = DriverManager.getConnection(servidor + bd, user, password);
            estado = true;
        } catch (ClassNotFoundException ex) { System.out.println("Class Not Found Exception: ");
        } catch (SQLException ex) { System.out.println("SQL Exception: " +ex.getLocalizedMessage());
        } catch (Exception ex) { System.out.println("Exception General: " +ex.getLocalizedMessage());
        }
        
        return estado;
    }
    
     private boolean compruebaCadena30(String cadena){
        return cadena.length()>0 && cadena.length() <=30;
    }
     
    public void alta(Alumno al){
        if(getConexion()){
            if(compruebaCadena30(al.getNombre())){
                //lo guardo en la Base de datos
                String cadena="INSERT INTO alumnos( Nombre, Nota1, Nota2, Nota3) VALUES ('" + al.getNombre()
                            + "',  '" + al.getNota(0) + " ', ' " + "',  '" + al.getNota(1) + " ', ' " + al.getNota(2) + "')";

                ejecutaUpdate(cadena);

            }else{
                System.out.println("el telefono tiene que tener entre 1 y 30 caracterres");
            }
        }
        
        cerrarConexion();
    }
    
    public int ejecutaUpdate(String statement) {
        int n = 0;
        try {
            Statement st = con.createStatement();
            System.out.println("Sentencia: " + statement);
            n = st.executeUpdate(statement); //Para que la ejecute.
            //st.close();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getLocalizedMessage());
        }
        
        return n;
    }
    
    public void cerrarConexion() {
        try {
            con.close();
        } catch(SQLException e) { System.out.println(e.getMessage());
        }
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
    
    public void cierraResultSet(ResultSet rs) {
        try {
            //cerramos el rs. porque garbage no puede eliminar el heap
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
    }
    
    public ArrayList listado(){
        //con el get string del rs hacer uno y guardarlo en un alumno
        // en el constructor del alumno nombre sera getstring(1) y asi guardar en un alumno y luego el alumno en un arraylist
        ArrayList <Alumno> listado = null;  
        if(getConexion()){
            ResultSet rs= null;
            rs=ejecutaConsulta("SELECT * from alumnos");
            
            try {
                while(rs.next()){
                    listado.add(new Alumno(rs.getString(1)));                    
                }
                
            } catch (SQLException ex) {
                System.out.println("Error al cargar datos");
            }
            cierraResultSet(rs);
            return listado;
        }
        return listado;
        
    }
    
    public ResultSet ejecutaConsulta(String consulta){
        Statement st=null;
        ResultSet rs =null;
        try {
            st=con.createStatement();
            rs=st.executeQuery(consulta);
            
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        }
        return rs;
    }
    
    
    /*
    PreparesStatement updateSales =con.prepareStatement("UPDATECOFFEES SET SALES =? WHERE COF_NAME LIKE ? ");
    updateSales.setInt(1; 75);
    updateSales.setString(2,"Colombia");
    updateSales.executeUpdate();
    */
    
    
    /*public int baja (String nombre){
    
    
    }*/
    
}
