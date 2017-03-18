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
        
        while(rs.next()){
            System.out.println(rs.getString("usuario"));
            System.out.println(rs.getString("contrasenia"));
            String passSHA = DigestUtils.sha256Hex(pass);
            if(user.equals(rs.getString("usuario")) && passSHA.equals(rs.getString("contrasenia")))
                return true;
        }
        
        return false;
    
    }
    
   /* public static void main(String[] args) throws SQLException {
        Consultas coon = new Consultas();
        System.out.println(coon.Auntenticacion("Pedro", "pedro"));
    }*/
    
    
}
