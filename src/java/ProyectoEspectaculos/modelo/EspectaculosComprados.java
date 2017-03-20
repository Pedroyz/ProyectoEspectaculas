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
public class EspectaculosComprados extends Espectaculo {

    String tipo;
    int precio;
    int numsillas;

    public EspectaculosComprados() {
        super();
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public int getNumsillas() {
        return numsillas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setNumsillas(int numsillas) {
        this.numsillas = numsillas;
    }

}
