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
public class Silla {
    int     idEspectaculo;
    String  tipo;
    int     precio;
    int     numeroLibres;

    public Silla() {
    }

    public int getIdEspectaculo() {
        return idEspectaculo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public int getNumeroLibres() {
        return numeroLibres;
    }

    public void setIdEspectaculo(int idEspectaculo) {
        this.idEspectaculo = idEspectaculo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setNumeroLibres(int numeroLibres) {
        this.numeroLibres = numeroLibres;
    }
    
    

}
