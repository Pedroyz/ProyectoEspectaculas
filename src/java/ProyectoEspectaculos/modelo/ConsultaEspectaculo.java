/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoEspectaculos.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author Pedro
 */
public class ConsultaEspectaculo extends ConexionBBDD{

    public ConsultaEspectaculo() throws SQLException {
        super();
    }
    
    
    
    public LinkedList <Espectaculo> getEspectaculos() throws SQLException
    {
        LinkedList <Espectaculo> listaEspectaculos =  new LinkedList <>();
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String consulta = "SELECT * FROM espectaculo order by Fecha;";
            rs = st.executeQuery(consulta);
            while (rs.next())
            {
                Espectaculo espectaculo = new Espectaculo();
                espectaculo.setIdEspectaculo(rs.getInt("idespectaculo"));
                espectaculo.setNombre(rs.getString("Titulo"));
                espectaculo.setDescripcion(rs.getString("Descripcion"));
                espectaculo.setFecha(rs.getDate("Fecha"));
                listaEspectaculos.add(espectaculo);                
            }
            rs.close();
            st.close();
        }catch(SQLException e)
        {
            System.err.println("Error consulta sql");
        }
        return listaEspectaculos;
    }
    
}
