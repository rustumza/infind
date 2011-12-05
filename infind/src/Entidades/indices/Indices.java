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
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean eliminado= false;
    private double gradoDependencia;
    private double indiceLiquides;
    private double nivelCredito;
    private double queSeDebe;
    private double rotacionExterna;
    private double planSugerencias;
    private double rotacionInterna;
    private double nivelAusentismo;
    private double utilidadActivoTotal;
    private double proporcionCuotas;
    private double composicionVentas;
    private double eficienciaVendedor;
    private double carteraClientes;
    private double volumenCompra;
    private double nivelDescuento;
    private double credito;
    private double nivelRechazos;
    private double usoHP;
    private double deLasInversiones;
    private double capitalesPropios;
    private double stocks;
    private double nivelRotacion;
    private double deLasVentas;
    
            

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    

    public double getCapitalesPropios() {
        return capitalesPropios;
    }

    public void setCapitalesPropios(double capitalesPropios) {
        this.capitalesPropios = capitalesPropios;
    }

    public double getCarteraClientes() {
        return carteraClientes;
    }

    public void setCarteraClientes(double carteraClientes) {
        this.carteraClientes = carteraClientes;
    }

    public double getComposicionVentas() {
        return composicionVentas;
    }

    public void setComposicionVentas(double composicionVentas) {
        this.composicionVentas = composicionVentas;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getDeLasInversiones() {
        return deLasInversiones;
    }

    public void setDeLasInversiones(double deLasInversiones) {
        this.deLasInversiones = deLasInversiones;
    }

    public double getDeLasVentas() {
        return deLasVentas;
    }

    public void setDeLasVentas(double deLasVentas) {
        this.deLasVentas = deLasVentas;
    }

    public double getEficienciaVendedor() {
        return eficienciaVendedor;
    }

    public void setEficienciaVendedor(double eficienciaVendedor) {
        this.eficienciaVendedor = eficienciaVendedor;
    }

    public double getGradoDependencia() {
        return gradoDependencia;
    }

    public void setGradoDependencia(double gradoDependencia) {
        this.gradoDependencia = gradoDependencia;
    }

    public double getIndiceLiquides() {
        return indiceLiquides;
    }

    public void setIndiceLiquides(double indiceLiquides) {
        this.indiceLiquides = indiceLiquides;
    }

    public double getNivelAusentismo() {
        return nivelAusentismo;
    }

    public void setNivelAusentismo(double nivelAusentismo) {
        this.nivelAusentismo = nivelAusentismo;
    }

    public double getNivelCredito() {
        return nivelCredito;
    }

    public void setNivelCredito(double nivelCredito) {
        this.nivelCredito = nivelCredito;
    }

    public double getNivelDescuento() {
        return nivelDescuento;
    }

    public void setNivelDescuento(double nivelDescuento) {
        this.nivelDescuento = nivelDescuento;
    }

    public double getNivelRechazos() {
        return nivelRechazos;
    }

    public void setNivelRechazos(double nivelRechazos) {
        this.nivelRechazos = nivelRechazos;
    }

    public double getNivelRotacion() {
        return nivelRotacion;
    }

    public void setNivelRotacion(double nivelRotacion) {
        this.nivelRotacion = nivelRotacion;
    }

    public double getPlanSugerencias() {
        return planSugerencias;
    }

    public void setPlanSugerencias(double planSugerencias) {
        this.planSugerencias = planSugerencias;
    }

    public double getProporcionCuotas() {
        return proporcionCuotas;
    }

    public void setProporcionCuotas(double proporcionCuotas) {
        this.proporcionCuotas = proporcionCuotas;
    }

    public double getQueSeDebe() {
        return queSeDebe;
    }

    public void setQueSeDebe(double queSeDebe) {
        this.queSeDebe = queSeDebe;
    }

    public double getRotacionExterna() {
        return rotacionExterna;
    }

    public void setRotacionExterna(double rotacionExterna) {
        this.rotacionExterna = rotacionExterna;
    }

    public double getRotacionInterna() {
        return rotacionInterna;
    }

    public void setRotacionInterna(double rotacionInterna) {
        this.rotacionInterna = rotacionInterna;
    }

    public double getStocks() {
        return stocks;
    }

    public void setStocks(double stocks) {
        this.stocks = stocks;
    }

    public double getUsoHP() {
        return usoHP;
    }

    public void setUsoHP(double usoHP) {
        this.usoHP = usoHP;
    }

    public double getUtilidadActivoTotal() {
        return utilidadActivoTotal;
    }

    public void setUtilidadActivoTotal(double utilidadActivoTotal) {
        this.utilidadActivoTotal = utilidadActivoTotal;
    }

    public double getVolumenCompra() {
        return volumenCompra;
    }

    public void setVolumenCompra(double volumenCompra) {
        this.volumenCompra = volumenCompra;
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
    
}
