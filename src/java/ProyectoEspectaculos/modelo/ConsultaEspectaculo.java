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
public class ConsultaEspectaculo extends ConexionBBDD {

    public ConsultaEspectaculo() throws SQLException {
        super();
    }

    public LinkedList<Espectaculo> getEspectaculos() throws SQLException {
        LinkedList<Espectaculo> listaEspectaculos = new LinkedList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String consulta = "SELECT * FROM espectaculo \n" +
                                "where idespectaculo in (SELECT espectaculoId FROM silla where usuarioId is null)\n" +
                                "order by Fecha";
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                Espectaculo espectaculo = new Espectaculo();
                espectaculo.setIdEspectaculo(rs.getInt("idespectaculo"));
                espectaculo.setNombre(rs.getString("Titulo"));
                espectaculo.setDescripcion(rs.getString("Descripcion"));
                espectaculo.setFecha(rs.getDate("Fecha"));
                listaEspectaculos.add(espectaculo);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Error consulta sql");
        }
        return listaEspectaculos;
    }

    public LinkedList<Silla> getSillas(int idespectaculo) throws SQLException {

        LinkedList<Silla> listaSillas = new LinkedList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String consulta = "SELECT tipo, COUNT(*) as numSillas, precio "
                    + "FROM silla where usuarioId IS NULL and espectaculoId = " + idespectaculo + " group by espectaculoId, tipo, precio;";
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                Silla silla = new Silla();
                silla.setIdEspectaculo(idespectaculo);
                silla.setNumeroLibres(rs.getInt("numSillas"));
                silla.setPrecio(rs.getInt("precio"));
                silla.setTipo(rs.getString("tipo"));
                listaSillas.add(silla);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Error consulta sql");
        }
        return listaSillas;
    }

    public boolean comprarSillas(int idespectaculo, String tipoSilla, int cantidad, String usuario) throws SQLException {

        try {
            Statement st = con.createStatement();
            ResultSet rs = null;

            if(cantidad <=0 )
                return false;
            //primero seleccinamos las sillas que vamos a comprar
            String consulta = "SELECT idSilla FROM espectaculos.silla where usuarioId IS NULL and espectaculoId = " + idespectaculo + " AND tipo = \"" + tipoSilla + "\""
                    + "limit " + cantidad + ";";
            rs = st.executeQuery(consulta);

            if (rs.next()) {

                String update = "UPDATE silla SET usuarioId = \"" + usuario + "\" where usuarioId IS NULL and idSilla in ( ";
                String sillas = String.valueOf(rs.getInt("idSilla"));

                while (rs.next()) {
                    sillas = sillas.concat(", " + rs.getInt("idSilla"));
                }

                update = update.concat(sillas + ")");

                int numCambios = st.executeUpdate(update);

                //Si por alguna razon no se ha comprado bién, decimos que se elimine la compra del usuario
                if (numCambios != cantidad) {

                    update = "UPDATE silla SET usuarioId = NULL where usuarioId = \"" + usuario + "\" and idSilla in ( " + sillas + ")";
                    numCambios = st.executeUpdate(update);

                    return false;
                }

                rs.close();
                st.close();
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error consulta sql");
            return false;
        }
        return true;
    }

    public LinkedList<EspectaculosComprados> getEspectaculosComprados(String user) throws SQLException {
        LinkedList<EspectaculosComprados> listaEspectaculos = new LinkedList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            String consulta = "SELECT idespectaculo, Titulo, Descripcion, Fecha, tipo, precio, count(idSilla) as numSillas FROM espectaculo " +
            "inner join silla on espectaculo.idespectaculo = silla.espectaculoId " +
            "where usuarioId = \""+user+"\" " +
            "group by idespectaculo, Titulo, Descripcion, Fecha, tipo, precio;";
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                EspectaculosComprados espectaculo = new EspectaculosComprados();
                espectaculo.setIdEspectaculo(rs.getInt("idespectaculo"));
                espectaculo.setNombre(rs.getString("Titulo"));
                espectaculo.setDescripcion(rs.getString("Descripcion"));
                espectaculo.setFecha(rs.getDate("Fecha"));
                espectaculo.setPrecio(rs.getInt("precio"));
                espectaculo.setTipo(rs.getString("tipo"));
                espectaculo.setNumsillas(rs.getInt("numSillas"));
                
                listaEspectaculos.add(espectaculo);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Error consulta sql");
        }
        return listaEspectaculos;
    }
    
    public boolean borrarSillas(int idespectaculo, String tipoSilla, String usuario) throws SQLException {

        try {
            Statement st = con.createStatement();
            ResultSet rs = null;
            int numCambios = 0;

            if(idespectaculo <=0  || tipoSilla == null || tipoSilla.length() == 0 || usuario == null || usuario.length() == 0)
                return false;
            //primero seleccinamos las sillas que vamos a comprar
            String consulta = "UPDATE silla SET usuarioId = NULL where usuarioId = \"" + usuario + "\" and tipo =  \"" + tipoSilla + "\"  and espectaculoId = " + idespectaculo;
            numCambios = st.executeUpdate(consulta);
            
            if(numCambios <= 0)
                return false;

        } catch (SQLException e) {
            System.err.println("Error consulta sql");
            return false;
        }
        return true;
    }
       

  /*  public static void main (String argv []) throws SQLException{
    
        ConsultaEspectaculo con = new ConsultaEspectaculo();
        System.out.println(con.borrarSillas(1, "PREMIUM", "pedro"));
    
    
    }*/
    
}
