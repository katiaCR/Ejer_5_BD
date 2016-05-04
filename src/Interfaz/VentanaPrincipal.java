/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Interfaz.VentanaListado;
import Interfaz.VentanaBaja;
import Interfaz.VentanaAlta;
import ejer1.DataBase;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaPrincipal extends JFrame implements ActionListener, WindowListener {
    JPanel contenedor;
    JButton botones[];
    DataBase db;
    
    public VentanaPrincipal(DataBase db) {
        //Creo el ArrayList nuevo
        this.db=db;
        this.setTitle("Alumnos");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300,300);
    }
    
    private void initComponents() {
        String textoBotones[]={"Alta Alumno", "Baja Alumno", "Listado", "Poner Notas", "Borrar Suspensos", "Fin"};
        
        botones=new JButton[textoBotones.length];
        //Utilizo todo el fondo del JFrame
        contenedor=(JPanel) this.getContentPane();
        contenedor.setBackground(Color.ORANGE);
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(textoBotones.length, 1, 5, 5));
        //Inicializo los objetos
       for (int x=0;x<textoBotones.length;x++) {
           botones[x]=new JButton();
           botones[x].setText(textoBotones[x]);
           botones[x].setActionCommand(Integer.toString(x));
           botones[x].addActionListener(this);
           contenedor.add(botones[x]);
       }
        //Atiendo a la ventana
       this.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(db.abrirConexion()) {
            switch (e.getActionCommand()) {
                case "0":
                    VentanaAlta v1 = new VentanaAlta(db);
                    break;
                case "1":
                    VentanaBaja vb1= new VentanaBaja(db);
                    break;
                case "2":
                    VentanaListado vl1= new VentanaListado(db.ejecutaConsulta("SELECT * from alumnos;"));
                default:
                     fin();
                
            }
        
        }
        
    }

    private void fin() {
        db.cerrarConexion();
        System.exit(0);
    }
    @Override
    public void windowOpened(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("cerrando");
        fin();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Cerrado");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
