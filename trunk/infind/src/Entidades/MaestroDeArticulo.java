/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Tipo", discriminatorType = DiscriminatorType.STRING)
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
    private int tiempoDeObtenecion;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Demanda> demanda;
    @OneToOne
    private Stock stock;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CostoVariable> costosVariables;
    private float costoDePedido;
    private float costoDeAlmacenamiento;
    private String tipoInventario;
    private float puntoDePedido;
    private float stockDeSeguridad;
    private float stockMaximoInventarioIntervaloFijo;
    private float tasaDeProduccion;
    private float numeroDeCiclosDeFabricacionOptimo;
    
    public Long getId() {
        return id;
    }

    public List<Demanda> getDemanda() {
        return demanda;
    }

    public List<CostoVariable> getCostosVariables() {
        return costosVariables;
    }

    public void setCostosVariables(List<CostoVariable> costosVariables) {
        this.costosVariables = costosVariables;
    }

    public void setDemanda(List<Demanda> demanda) {
        this.demanda = demanda;
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

    public int getTiempoDeObtenecion() {
        return tiempoDeObtenecion;
    }

    public void setTiempoDeObtenecion(int tiempoDeObtenecion) {
        this.tiempoDeObtenecion = tiempoDeObtenecion;
    }

    public float getCostoDeAlmacenamiento() {
        return costoDeAlmacenamiento;
    }

    public void setCostoDeAlmacenamiento(float costoDeAlmacenamiento) {
        this.costoDeAlmacenamiento = costoDeAlmacenamiento;
    }

    public float getCostoDePedido() {
        return costoDePedido;
    }

    public void setCostoDePedido(float costoDePedido) {
        this.costoDePedido = costoDePedido;
    }

    public String getTipoInventario() {
        return tipoInventario;
    }

    public void setTipoInventario(String tipoInventario) {
        this.tipoInventario = tipoInventario;
    }

    public float getPuntoDePedido() {
        return puntoDePedido;
    }

    public void setPuntoDePedido(float puntoDePedido) {
        this.puntoDePedido = puntoDePedido;
    }

    public float getStockDeSeguridad() {
        return stockDeSeguridad;
    }

    public void setStockDeSeguridad(float stockDeSeguridad) {
        this.stockDeSeguridad = stockDeSeguridad;
    }

    public float getStockMaximoInventarioIntervaloFijo() {
        return stockMaximoInventarioIntervaloFijo;
    }

    public void setStockMaximoInventarioIntervaloFijo(float stockMaximoInventarioIntervaloFijo) {
        this.stockMaximoInventarioIntervaloFijo = stockMaximoInventarioIntervaloFijo;
    }

    public float getTasaDeProduccion() {
        return tasaDeProduccion;
    }

    public void setTasaDeProduccion(float tasaDeProduccion) {
        this.tasaDeProduccion = tasaDeProduccion;
    }

    public float getNumeroDeCiclosDeFabricacionOptimo() {
        return numeroDeCiclosDeFabricacionOptimo;
    }

    public void setNumeroDeCiclosDeFabricacionOptimo(float numeroDeCiclosDeFabricacionOptimo) {
        this.numeroDeCiclosDeFabricacionOptimo = numeroDeCiclosDeFabricacionOptimo;
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

    public void addDemanda(Demanda demanda) {
        getDemanda().add(demanda);
//        if (demanda.getArticulo() != null) {
//            demanda.getArticulo().getDemanda().remove(demanda);
//        }
        demanda.setArticulo(this);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
