/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;

/**
 *
 * @author Deybic Rojas
 */
public class Conexion {
    Connection con;
    public Connection getConnection(){
        String url = "jdbc:postgresql://localhost:5432/db_ejemplo";
        String user = "postgres";
        String pass = "admin";
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            
        }
        return con;
    }
}
