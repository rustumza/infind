/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import Entidades.indices.Indices;

/**
 *
 * @author rustu
 */
public class DTOIndices {
    
    private Indices ind;
    private double valor;
    
    private int normalidad;

    public int getNormalidad() {
        return normalidad;
    }

    public void setNormalidad(int normalidad) {
        this.normalidad = normalidad;
    }
    
    

    public Indices getInd() {
        return ind;
    }

    public void setInd(Indices ind) {
        this.ind = ind;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
