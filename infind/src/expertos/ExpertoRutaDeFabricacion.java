/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOCentro;
import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.Herramientas;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.MaestroDeRutaDeFabricacion;
import Entidades.MateriaPrima;
import excepciones.ExpertoExceptionRutaFabricacion;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoRutaDeFabricacion extends Experto {
    
    List<EtapaDeRutaDeFabricacion> etapaEnEspera = new ArrayList<EtapaDeRutaDeFabricacion>();
    DetalleDeArticuloEnEtapaDeFabricacion detalleArticuloGuardado = new DetalleDeArticuloEnEtapaDeFabricacion();
    
    

    public MaestroDeCentroDeTrabajo buscarCentros(DTOCentro dtoCentro) throws ExpertoExceptionRutaFabricacion {

        List<MaestroDeCentroDeTrabajo> centroEncontrado = null;
        MaestroDeCentroDeTrabajo centroDevuelto = null;

        if (dtoCentro == null) {

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(MaestroDeCentroDeTrabajo.class);
            if (dtoCentro.getCodigoCentro() != null) {
                criterioCentro.add(Restrictions.like("codigo", dtoCentro.getCodigoCentro()));
            }

            if (dtoCentro.getNombreCentro() != null) {
                criterioCentro.add(Restrictions.like("nombreCentro", dtoCentro.getNombreCentro()));
            }

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, criterioCentro);


        }
        if (centroEncontrado.isEmpty()) {
            throw new ExpertoExceptionRutaFabricacion("No se encontró Centro de Trabajo para los datos ingresados");

        }

        centroDevuelto = centroEncontrado.get(0);
        return centroDevuelto;
    }

    public List<MateriaPrima> buscarMateriasPrimas() {

        List<MateriaPrima> listaMateriaPrima = null;
        Criteria criterioMatPrim = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterioMatPrim);
        return listaMateriaPrima;

    }
    
    public void guardarEtapaRutaFabricacion(EtapaDeRutaDeFabricacion nuevaEtapa){
        etapaEnEspera.add(nuevaEtapa);
        
    }
    
    public List<EtapaDeRutaDeFabricacion> devolverEtapasAGuardar(){
        
        return etapaEnEspera;
        
    }

    public void guardarRutaDeFabricacion(MaestroDeRutaDeFabricacion rutaNueva) {
        
        Fachada.getInstancia().guardar(rutaNueva);
        
    }

    public void persistirEtapaRutaFabricacion(EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion) {
        Fachada.getInstancia().guardar(etapaDeRutaDeFabricacion);
    }

    public void persistirDetalleArticuloEnEtapaFabricacion(DetalleDeArticuloEnEtapaDeFabricacion detalleArticulo) {
        Fachada.getInstancia().guardar(detalleArticulo);
    }
    
    public DetalleDeArticuloEnEtapaDeFabricacion devolverDetalleArticulo(DetalleDeArticuloEnEtapaDeFabricacion detalle){
        List<DetalleDeArticuloEnEtapaDeFabricacion> llistaDetalleArticulo = null;
        
        Criteria criterioDetalleArticulo = Fachada.getInstancia().crearCriterio(DetalleDeArticuloEnEtapaDeFabricacion.class);
        criterioDetalleArticulo.add(Restrictions.eq("numero",detalle.getNumero() ));
        llistaDetalleArticulo = Fachada.getInstancia().buscar(DetalleDeArticuloEnEtapaDeFabricacion.class, criterioDetalleArticulo);    
        
        return llistaDetalleArticulo.get(0);
        
    }

    
}
