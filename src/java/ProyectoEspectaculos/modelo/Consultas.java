/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoEspectaculos.modelo;


import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author Pedro
 */
public class Consultas extends ConexionBBDD{
    

    public Consultas() throws SQLException {
        super();
    
    }
    
    public boolean Auntenticacion (String user, String pass) throws SQLException{
        
        Statement st = con.createStatement();
        
        ResultSet rs = null;
        
        String consulta = "SELECT * FROM usuarios;";
        rs = st.executeQuery(consulta);
        
        String passSHA = DigestUtils.sha256Hex(pass);
        while(rs.next()){
            if(user.equals(rs.getString("usuario")) && passSHA.equals(rs.getString("contrasenia")))
                return true;
        }
        
        return false;
    
    }
    
    
    public boolean Registrarse (String user, String pass) throws SQLException{
        
        Statement st = con.createStatement();
        
        ResultSet rs = null;
        String consulta = "SELECT * FROM usuarios;";
        rs = st.executeQuery(consulta);
        
        
        while(rs.next()){
            if(user.equals(rs.getString("usuario"))){
                return false;
            }
        }
                       
        consulta = "INSERT INTO usuarios VALUES ( \'"+user+"\',\'"+DigestUtils.sha256Hex(pass)+"\');";
        int upd;
        upd = st.executeUpdate(consulta);
        
        if(upd != 1)
            return false;
        
        return true;
        
    }
    
    
    
    public boolean ExisteUser (String user) throws SQLException{
        
        Statement st = con.createStatement();
        
        ResultSet rs = null;
        
        String consulta = "SELECT * FROM usuarios;";
        rs = st.executeQuery(consulta);
        
        while(rs.next()){
            if(user.equals(rs.getString("usuario")))
                return true;
        }
        
        return false;
    
    }
   /* public static void main(String[] args) throws SQLException {
        Consultas coon = new Consultas();
        System.out.println(coon.Auntenticacion("Pedro", "pedro"));
    }*/
    
    
}
