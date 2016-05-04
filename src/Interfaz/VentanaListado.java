/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Alumno
 */
public class VentanaListado extends JFrame{
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    JPanel contenedor;
    JTable table;
    DefaultTableModel modelo;
    ResultSet rs;
    
    public VentanaListado(ResultSet rs) {
        this.rs= rs;
        this.setTitle("Listado");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300,300);
    }
    
    public void initComponents() {
        contenedor = (JPanel) this.getContentPane();
        modelo = new DefaultTableModel();

        table = new JTable(modelo);
         JScrollPane scrollPane = new JScrollPane(table);

        contenedor.add(scrollPane, BorderLayout.CENTER);
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        
        muestraFilas();
    }
    
    public void muestraFilas(){
        String fila[] = new String[4];
        try {
            while (rs.next()) {
            fila[0] = rs.getString(2);
            fila[1] = rs.getString(3);
            fila[2] = rs.getString(4);
            fila[3] = rs.getString(5);
            modelo.addRow(fila);
        }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
    

}
