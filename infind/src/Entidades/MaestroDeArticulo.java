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
import javax.persistence.OneToOne;
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
    private String codigo;
    private float costoEstandar;
    private float costoUnitarioPorOmision;
    private String descripcion;
    private String estado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEntrarEnActividad;
    private String nombre;
    private String observacion;
    private float precioBase;
    private float tamanioLoteEstandar;
    private String tipo;
    private String ubicacionEnAlmacen;
    private String unidadDeMedida;
    @OneToOne
    private Stock stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getCostoEstandar() {
        return costoEstandar;
    }

    public void setCostoEstandar(float costoEstandar) {
        this.costoEstandar = costoEstandar;
    }

    public float getCostoUnitarioPorOmision() {
        return costoUnitarioPorOmision;
    }

    public void setCostoUnitarioPorOmision(float costoUnitarioPorOmision) {
        this.costoUnitarioPorOmision = costoUnitarioPorOmision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEntrarEnActividad() {
        return fechaEntrarEnActividad;
    }

    public void setFechaEntrarEnActividad(Date fechaEntrarEnActividad) {
        this.fechaEntrarEnActividad = fechaEntrarEnActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(float precioBase) {
        this.precioBase = precioBase;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public float getTamanioLoteEstandar() {
        return tamanioLoteEstandar;
    }

    public void setTamanioLoteEstandar(float tamanioLoteEstandar) {
        this.tamanioLoteEstandar = tamanioLoteEstandar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacionEnAlmacen() {
        return ubicacionEnAlmacen;
    }

    public void setUbicacionEnAlmacen(String ubicacionEnAlmacen) {
        this.ubicacionEnAlmacen = ubicacionEnAlmacen;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
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
