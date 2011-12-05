/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.indices;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import persistencia.ObjetoPersitente;

/**
 *
 * @author edu
 */
@Entity
public class Indices extends ObjetoPersitente implements Serializable {
    
    public Indices(){
    
    }
    public Indices(String nom ,double min, double max){
        nombre = nom;
        minimo = min;
        maximo = max;
    
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean eliminado= false;
  
    private String nombre;
    private double minimo;
    private double maximo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double desde) {
        this.minimo = desde;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double hasta) {
        this.maximo = hasta;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indices)) {
            return false;
        }
        Indices other = (Indices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Indices[ id=" + id + " ]";
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(Long oid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
