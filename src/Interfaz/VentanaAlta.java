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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alumno2
 */
public class VentanaAlta extends JFrame implements ActionListener, WindowListener {
    
    JPanel contenedor;
    JButton botonAlta, botonFin;
    JTextField enombre, etelefono;
    JLabel etiquetaNombre, etiquetaTelefono;
    DataBase db;
    
    public VentanaAlta(DataBase db) {
        this.db=db;
        this.setTitle("Alta Alumno");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(400, 300);
    }
    
    /**
     * Inicializacion de todos los botones de la ventana base.
     */
    private void initComponents() {
        contenedor = (JPanel) this.getContentPane();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(3, 2, 5, 5));
        //Inicializo los objetos
        etiquetaNombre = new JLabel("Nombre: ");
        enombre = new JTextField();
        etiquetaTelefono = new JLabel("Telefono: ");
        etelefono = new JTextField();
        botonAlta = new JButton("Alta");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("alta");
        botonFin = new JButton("Borrar");
        botonFin.addActionListener(this);
        botonFin.setActionCommand("borrar");
        
        //los pongo en el contendor
        contenedor.add(etiquetaNombre);
        contenedor.add(enombre);
        contenedor.add(etiquetaTelefono);
        contenedor.add(etelefono);
        contenedor.add(botonAlta);
        contenedor.add(botonFin);
        botonAlta = new JButton("Alta");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("alta");
        botonFin = new JButton("Borrar");
        botonFin.addActionListener(this);
        botonFin.setActionCommand("borrar");
        
    }
    
    private void alta(){
        if(compruebaCadena20(enombre.getText())){
            if(compruebaCadena20(etelefono.getText())){
                //lo guardo en la Base de datos
                String cadena="INSERT INTO alumnos(nombre, telefono) VALUES ('" + enombre.getText() 
                        + "',  '" + etelefono.getText() + "')";
                db.ejecutaUpdate(cadena);
                
            }else{
                ventanaError("el telefono tiene que tener entre 1 y 20 caracterres");
            }
        }else{
                ventanaError("el telefono tiene que tener entre 1 y 20 caracterres");
            }
            limpiaPantalla();
    }
    
    private boolean compruebaCadena20(String cadena){
        return cadena.length()>0 && cadena.length() <=20;
    }
    
    private void fin(){
        db.cerrarConexion();
        System.exit(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch(e.getActionCommand()) {
            case "alta": //Llamada a trabajo.
                alta();
                db.recorreResultado(db.ejecutaConsulta("SELECT * from alumnos"));
                VentanaListado vL =new VentanaListado(db.ejecutaConsulta("SELECT * from alumnos"));
                break;
            default:
                fin();
        }
    }
    /**
     * @return the contenedor
     */
    public JPanel getContenedor() {
        return contenedor;
    }

    /**
     * @param contenedor the contenedor to set
     */
    public void setContenedor(JPanel contenedor) {
        this.contenedor = contenedor;
    }

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(this, cadena, "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiaPantalla() {
        enombre.setText(null);
        etelefono.setText(null);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
