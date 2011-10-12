/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class MaestroDeArticulo extends ObjetoPersitente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private char categoria;
    private String condigo;
    private float costoEstandar;
    private float costoUnitarioPorOmision;
    private String descripcion;
    private String estado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeVencimiento;
    private String nombre;
    private String observacion;
    private float precioBase;
    private float tamanioLoteEstandar;
    private String tipo;
    private String ubicacionEnAlmacen;
    private String unidadDeMedida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof MaestroDeArticulo)) {
            return false;
        }
        MaestroDeArticulo other = (MaestroDeArticulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.MaestroDeArticulo[ id=" + id + " ]";
    }
}
