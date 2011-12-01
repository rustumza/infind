/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.CostoVariable;
import Entidades.CostosFijos;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.ProductoFinal;
import excepciones.ExpertoCostosVariablesException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author edu
 */
public class ExpertoABMCostosVariables extends Experto {

    public List<MaestroDeArticulo> buscarMaestroArticulo() throws ExpertoCostosVariablesException{

        List<MaestroDeArticulo> maestroArticuloEncontrados = null;

        Criteria criterioMaestroArticulo = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeArticulo.class);
        criterioMaestroArticulo.add(Restrictions.eq("eliminado", false));
        maestroArticuloEncontrados = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterioMaestroArticulo);

        return maestroArticuloEncontrados;
    }

    public List<MaestroDeCentroDeTrabajo> buscarCentroDeTrabajo()  throws ExpertoCostosVariablesException{

        List<MaestroDeCentroDeTrabajo> maestroCentroTrabajoEncontrados = null;

        Criteria criterioMaestroCentroTrabajo = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeCentroDeTrabajo.class);
        criterioMaestroCentroTrabajo.add(Restrictions.eq("eliminado", false));
        maestroCentroTrabajoEncontrados = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, criterioMaestroCentroTrabajo);

        return maestroCentroTrabajoEncontrados;
    }

    
    
    public List<CostoVariable> buscarCostosVariables(MaestroDeArticulo productoIntermedioBase) throws ExpertoCostosVariablesException{
        
        List<CostoVariable> costosEncontrados = null;
        Criteria criterioCostosVariables = Fachada.getInstancia().crearCriterioSinEliminado(CostoVariable.class);
        criterioCostosVariables.add(Restrictions.eq("eliminado", false));
        criterioCostosVariables.add(Restrictions.eq("MaestroDeArticulo", productoIntermedioBase));
        costosEncontrados = Fachada.getInstancia().buscar(CostoVariable.class, criterioCostosVariables);

        return costosEncontrados;
    }

    public MaestroDeArticulo buscarProductoIntermedioBase() {
        List<MaestroDeArticulo> productoIntermedioEncontrados = null;
        Criteria criterioProductoIntermedio = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeArticulo.class);
        criterioProductoIntermedio.add(Restrictions.eq("eliminado", false));
        criterioProductoIntermedio.add(Restrictions.eq("codigo", "3.1.1"));
        productoIntermedioEncontrados = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterioProductoIntermedio);

        return productoIntermedioEncontrados.get(0);
    }
    
    public List<MaestroDeArticulo> buscarProductoFinal(){
        List<MaestroDeArticulo> productoFinalEncontrados = null;
        Criteria criterioFinalIntermedio = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioFinalIntermedio.add(Restrictions.eq("eliminado", false));
        productoFinalEncontrados = Fachada.getInstancia().buscar(ProductoFinal.class, criterioFinalIntermedio);

        return productoFinalEncontrados;
        
        
    }

    public void guardarCostoVariable(CostoVariable nuevoCosto) {
         Fachada.getInstancia().guardar(nuevoCosto);
    }
}

