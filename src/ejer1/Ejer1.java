/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer1;

import Interfaz.VentanaPrincipal;

/**
 *
 * @author Alumno2
 */
public class Ejer1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Nos conectamos a la base de datos
        //todo: pasar todo esto para conectarse por entorno grafico
        String servidor = "jdbc:mysql://localhost/";
        String bd = "centro";
        String user = "user";
        String password = "1234";
        DataBase db = new DataBase(bd, user, password, servidor);
        
        if(db.abrirConexion()) {
            System.out.println("NOS HEMOS CONECTADO!!!!!");
            VentanaPrincipal v1= new VentanaPrincipal(db);
            
        }
        else{
            System.out.println("No se ha podido conectar");
        }
        
        db.cerrarConexion();
        
        
        
    }
}
