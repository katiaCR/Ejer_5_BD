/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import ejer1.DataBase;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alumno2
 */
public class VentanaBaja extends JFrame implements ActionListener{
    
    JPanel contenedor;
    JButton botonBorrar;
    JTextField enombre, etelefono;
    JLabel etiquetaNombre, etiquetaTelefono;
    String servidor, bdd, usuario, pwd, nombre, telefono;
    DataBase db;
    
    String[] tituloIntrinseco = {"Servidor", "BDD", "Usuario", "Password"};
    String[] tituloBoton = {"Conectar", "Cancelar"};
    JButton[] boton = new JButton[tituloBoton.length];
    
    public VentanaBaja(DataBase db) {
        this.db=db;
        this.setTitle("¡Base de Datos!"); //fixme: Cambiar el titulo de la ventana para cada programa.
        this.setSize(400, 500); //width, height.
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null); //Invoca la ventana en el centro.
        iniCampos();
    }
    
    /**
     * Inicializacion de todos los botones de la ventana base.
     */
    private void iniCampos() {
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(4, 2, 5, 5));
        //Inicializo los objetos
        etiquetaNombre = new JLabel("Nombre: ");
        enombre = new JTextField();
        etiquetaTelefono = new JLabel("Telefono: ");
        etelefono = new JTextField();
        botonBorrar = new JButton("Borrar");
        botonBorrar.addActionListener(this);
        botonBorrar.setActionCommand("borrar");
        //los pongo en el contendor
        contenedor.add(etiquetaNombre);
        contenedor.add(enombre);
        contenedor.add(etiquetaTelefono);
        contenedor.add(etelefono);
        contenedor.add(botonBorrar);
        
        botonBorrar= new JButton("Alta");
        botonBorrar.addActionListener(this);
        botonBorrar.setActionCommand("alta");
        
    }
    
    private void baja(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch(e.getActionCommand()) {
            case "alta": //Llamada a trabajo.
                baja();
                db.recorreResultado(db.ejecutaConsulta("SELECT * from alumnos"));
                limpiaPantalla();
                break;
            default:
                fin();
        }
    }
    private void limpiaPantalla() {
        enombre.setText(null);
        etelefono.setText(null);
    }
    private void fin(){
    db.cerrarConexion();
    System.exit(0);
    }
}
