/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class MaestroDeCentroDeTrabajo extends ObjetoPersitente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado = false;
    private String codigo;
    private String nombreCentro;
    private String descripcion;
    @ManyToMany //(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Operario> operarios;
    @ManyToMany //(fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Herramientas> herramientas;
    @ManyToMany //(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Maquina> maquinas;

    public void addHerramientas(Herramientas herramientas) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaHerramientas(herramientas)) {
            getHerramientas().add(herramientas);
            if (herramientas.getMaestroCentroTrabajo() != null) {
                herramientas.getMaestroCentroTrabajo().get(0).getHerramientas().remove(herramientas);
            }
            herramientas.getMaestroCentroTrabajo().add(this);

        }
    }

    private boolean estaEnLaListaHerramientas(Herramientas herramienta) {
        for (Herramientas herra : herramientas) {
            if (herramienta == herra) {
                return true;
            }
        }
        return false;
    }

    public void addMaquinas(Maquina maquina) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaMaquina(maquina)) {
            getMaquinas().add(maquina);
            if (maquina.getMaestroCentroTrabajo() != null) {
                maquina.getMaestroCentroTrabajo().get(0).getMaquinas().remove(maquina);
            }
            maquina.getMaestroCentroTrabajo().add(this);

        }
    }

    private boolean estaEnLaListaMaquina(Maquina maquina) {
        for (Maquina maqu : maquinas) {
            if (maquina == maqu) {
                return true;
            }
        }
        return false;
    }

    public void addOperario(Operario operario) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaOperario(operario)) {

            getOperario().add(operario);
            if (operario.getCentroDeTrabajo() != null) {
                operario.getCentroDeTrabajo().get(0).getOperario().remove(operario);
            }
            operario.getCentroDeTrabajo().add(this);

        }
    }

    private boolean estaEnLaListaOperario(Operario operario) {
        for (Operario opera : operarios) {
            if (operario == opera) {
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public List<Operario> getOperario() {
        return operarios;
    }

    public List<Herramientas> getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(List<Herramientas> herramientas) {
        this.herramientas = herramientas;
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    public void setOperario(List<Operario> operario) {
        this.operarios = operario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
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
        if (!(object instanceof MaestroDeCentroDeTrabajo)) {
            return false;
        }
        MaestroDeCentroDeTrabajo other = (MaestroDeCentroDeTrabajo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.MaestroDeCentroDeTrabajo[ id=" + id + " ]";
    }
}
