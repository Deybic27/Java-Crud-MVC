/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Deybic Rojas
 */
public class PersonaDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public ArrayList listar(){
        ArrayList<Persona>datos = new ArrayList<>();
        String sql = "select * from persona order by id asc";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                datos.add(p);
            }
        } catch (Exception e) {
        } 
        return datos;
    }
    
    public int agregar(Persona p){
        String sql = "INSERT INTO persona (nombre,correo,telefono) VALUES (?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelefono());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
    
    public int actualizar(Persona p){
        int resultado = 0;
        String sql = "update persona set nombre = ?, correo = ?, telefono = ? where id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelefono());
            ps.setInt(4, p.getId());
            resultado = ps.executeUpdate();
            if (resultado == 1) {
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
        }
        return resultado;
    }
    
    public int eliminar(int id){
        int resultado = 0;
        String sql = "DELETE FROM persona WHERE id = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
        }
        return resultado;
    }
}
