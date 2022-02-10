/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.Vista;

/**
 *
 * @author Deybic Rojas
 */
public class Controlador implements ActionListener{
    
    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador(Vista v){
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnOk.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
        listar(vista.jTable1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.jTable1);
        }
        if(e.getSource() == vista.btnGuardar){
            agregar();
            limpiarTabla();
            listar(vista.jTable1);
            limpiarCampos();
        }
        if (e.getSource() == vista.btnEditar) {
            editar();
        }
        if (e.getSource() == vista.btnOk) {
            if (vista.txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un usuario");
            }else{
                actualizar();
                limpiarTabla();
                listar(vista.jTable1);
                limpiarCampos();
            }
            
        }
        if(e.getSource() == vista.btnEliminar){
            eliminar();
            limpiarTabla();
            listar(vista.jTable1);
        }
        if(e.getSource() == vista.btnNuevo){
            limpiarCampos();
        }
    }
    
    public void eliminar(){
        int fila = vista.jTable1.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe selecionar un usuario");
        }else{
            int id = Integer.parseInt((String)vista.jTable1.getValueAt(fila, 0).toString());
            if (dao.eliminar(id) == 1) {
                JOptionPane.showMessageDialog(vista, "Usuario eliminado con éxito");
            }else{
                JOptionPane.showMessageDialog(vista, "Error al eliminar");
            }
        }
    }
    
    public void editar(){
        int fila = vista.jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showConfirmDialog(vista, "Debe seleccionar un usuario");
        }else{
            int id = Integer.parseInt((String)vista.jTable1.getValueAt(fila, 0).toString());
            String nombre = (String)vista.jTable1.getValueAt(fila, 1);
            String correo = (String)vista.jTable1.getValueAt(fila, 2);
            String telefono = (String)vista.jTable1.getValueAt(fila, 3);
            vista.txtId.setText(""+id);
            vista.txtNombre.setText(nombre);
            vista.txtEmail.setText(correo);
            vista.txtTelefono.setText(telefono);
        }
    }
    
    void limpiarCampos(){
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtEmail.setText("");
        vista.txtTelefono.setText("");
    }
    
    void limpiarTabla(){
        for (int i = 0; i < vista.jTable1.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    public void actualizar(){
        int id = Integer.parseInt((String)vista.txtId.getText());
        String nombre = vista.txtNombre.getText();
        String correo = vista.txtEmail.getText();
        String telefono = vista.txtTelefono.getText();
        
        p.setId(id);
        p.setNombres(nombre);
        p.setCorreo(correo);
        p.setTelefono(telefono);
       
        if(dao.actualizar(p) == 1){
            JOptionPane.showMessageDialog(vista, "Usuario actualizado con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al actualizar");
        }
    }
    
    public void agregar(){
        String nombre = vista.txtNombre.getText();
        String correo = vista.txtEmail.getText();
        String telefono = vista.txtTelefono.getText();
        
        p.setNombres(nombre);
        p.setCorreo(correo);
        p.setTelefono(telefono);
        int response = dao.agregar(p);
        if(response==1){
            JOptionPane.showMessageDialog(vista, "Usuario agregado con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al agregar");
        }
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        ArrayList<Persona>lista = dao.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombres();
            object[2] = lista.get(i).getCorreo();
            object[3] = lista.get(i).getTelefono();
            modelo.addRow(object);
        }
        vista.jTable1.setModel(modelo);
    }
    
}
