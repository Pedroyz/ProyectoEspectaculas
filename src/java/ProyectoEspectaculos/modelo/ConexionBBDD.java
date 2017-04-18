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
    
    public String USERNAME;
    public String PASSWORD;
    public String HOST;
    public String PORT;
    public String DATABASE;
    public String CLASSNAME;
    public String URL;
    
    public java.sql.Connection con;
 
    public ConexionBBDD() throws SQLException{
    
        this.USERNAME = "root";
        this.PASSWORD = "contraseniaroot";
        this.HOST = "localhost";
        this.PORT = "3306";
        this.DATABASE = "espectaculos";
        this.CLASSNAME = "com.mysql.jdbc.Driver";
        this.URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
        
        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch(ClassNotFoundException e){
            System.err.println("Error en la clase:" + e.toString());
        }catch(SQLException e){
            System.err.println("Error SQL: "+ e.toString());
        }
    
    }    
    
    
    public ConexionBBDD(String USERNAME, String PASSWORD, String HOST, String PORT, String DATABASE, String CLASSNAME, String URL) throws SQLException{
    
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.HOST = HOST;
        this.PORT = PORT;
        this.DATABASE = DATABASE;
        this.CLASSNAME = CLASSNAME;
        this.URL = URL;
        
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
        ConexionBBDD cone = new ConexionBBDD("root", "contraseniaroot", "localhost", "3306", "espectaculos", "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/espectaculos");
    }*/

}
