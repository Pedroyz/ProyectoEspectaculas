/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoEspectaculos.modelo;

/**
 *
 * @author Pedro
 */

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;



public class ConexionBBDD {
    
    public static final String USERNAME = "root";
    public static final String PASSWORD = "contraseniaroot";
    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "espectaculos";
    public static final String CLASSNAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
    
    public java.sql.Connection con;
    
    public ConexionBBDD() throws SQLException{
    
        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch(ClassNotFoundException e){
            System.err.println("Error en la clase:" + e.toString());
        }catch(SQLException e){
            System.err.println("Error SQL: "+ e.toString());
        }
    
    }
    /*
    public static void main(String[] args) throws SQLException {
        ConexionBBDD cone = new ConexionBBDD();
    }
*/
}
