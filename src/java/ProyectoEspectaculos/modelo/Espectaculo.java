/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoEspectaculos.modelo;

import java.sql.Date;

/**
 *
 * @author Pedro
 */
public class Espectaculo {

    int idEspectaculo;
    String nombre;
    String Descripcion;
    Date fecha;

    public Espectaculo() {
    }

    public int getIdEspectaculo() {
        return idEspectaculo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public void setIdEspectaculo(int idEspectaculo) {
        this.idEspectaculo = idEspectaculo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    
    
   
}