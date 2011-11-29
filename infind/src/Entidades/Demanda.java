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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import persistencia.ObjetoPersitente;

/**
 *
 * @author diego
 */
@Entity
public class Demanda extends ObjetoPersitente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double demandaReal;
    private double demandaPronosticada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    private int periodo = 1;
    private double errorPromedio;
    private double demandaHistorica;
    @ManyToOne
    private MaestroDeArticulo articulo;

    public Long getId() {
        return id;
    }

    public double getDemandaHistorica() {
        return demandaHistorica;
    }

    public void setDemandaHistorica(double demandaHistorica) {
        this.demandaHistorica = demandaHistorica;
    }

    public double getDemandaPronosticada() {
        return demandaPronosticada;
    }

    public void setDemandaPronosticada(double demandaPronosticada) {
        this.demandaPronosticada = demandaPronosticada;
    }

    public double getDemandaReal() {
        return demandaReal;
    }

    public void setDemandaReal(double demandaReal) {
        this.demandaReal = demandaReal;
    }

    public double getErrorPromedio() {
        return errorPromedio;
    }

    public void setErrorPromedio(double errorPromedio) {
        this.errorPromedio = errorPromedio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public MaestroDeArticulo getArticulo() {
        return articulo;
    }

    public void setArticulo(MaestroDeArticulo articulo) {
        this.articulo = articulo;
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
        if (!(object instanceof Demanda)) {
            return false;
        }
        Demanda other = (Demanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Demanda[ id=" + id + " ]";
    }
}
