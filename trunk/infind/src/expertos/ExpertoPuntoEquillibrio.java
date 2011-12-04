/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOPuntoEquilibrio;
import Entidades.CostoVariable;
import Entidades.CostosFijos;
import Entidades.Demanda;
import Entidades.MaestroDeArticulo;
import Entidades.ProductoFinal;
import excepciones.ExpertoPuntoEquilibrioException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoPuntoEquillibrio extends Experto{
    

     public List<CostoVariable> buscarCostosVariables(MaestroDeArticulo productoIntermedioBase) throws ExpertoPuntoEquilibrioException{
        
        List<CostoVariable> costosEncontrados = null;
        Criteria criterioCostosVariables = Fachada.getInstancia().crearCriterioSinEliminado(CostoVariable.class);
        criterioCostosVariables.add(Restrictions.eq("articulo", productoIntermedioBase));
        costosEncontrados = Fachada.getInstancia().buscar(CostoVariable.class, criterioCostosVariables);

        return costosEncontrados;
    }
     
     public List<ProductoFinal> buscarProductoFinal(){
        List<ProductoFinal> productoFinalEncontrados = null;
        Criteria criterioFinalIntermedio = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioFinalIntermedio.add(Restrictions.eq("eliminado", false));
        productoFinalEncontrados = Fachada.getInstancia().buscar(ProductoFinal.class, criterioFinalIntermedio);

        return productoFinalEncontrados;
        
        
    }
     
     public List<MaestroDeArticulo> buscarProducto(){
        List<MaestroDeArticulo> productoFinalEncontrados = null;
        Criteria criterioFinalIntermedio = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioFinalIntermedio.add(Restrictions.eq("eliminado", false));
        productoFinalEncontrados = Fachada.getInstancia().buscar(ProductoFinal.class, criterioFinalIntermedio);

        return productoFinalEncontrados;
        
        
    }
     
     public List<CostoVariable> buscarCostosVariablesProdEstandar(MaestroDeArticulo productoIntermedioBase)  throws ExpertoPuntoEquilibrioException{
        List<CostoVariable> costosEncontrados = null;
        Criteria criterioCostosVariables = Fachada.getInstancia().crearCriterioSinEliminado(CostoVariable.class);
        criterioCostosVariables.add(Restrictions.eq("eliminado", false));
        criterioCostosVariables.add(Restrictions.eq("articulo", productoIntermedioBase));
        costosEncontrados = Fachada.getInstancia().buscar(CostoVariable.class, criterioCostosVariables);

        return costosEncontrados;
    
    }
     
     public MaestroDeArticulo buscarProductoEstandar() {
        List<MaestroDeArticulo> productoEstandarEncontrados = null;
        Criteria criterioProductoEstandar = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeArticulo.class);
        criterioProductoEstandar.add(Restrictions.eq("eliminado", false));
        criterioProductoEstandar.add(Restrictions.eq("codigo", "4.1.1"));
        productoEstandarEncontrados = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterioProductoEstandar);

        return productoEstandarEncontrados.get(0);
    }

    public List<DTOPuntoEquilibrio> calcularFilaDeTabla(int volumen, ProductoFinal producto, double costoVariable) throws ExpertoPuntoEquilibrioException {
       List<DTOPuntoEquilibrio> dtosADevolver = new ArrayList<DTOPuntoEquilibrio>();
       double totalCostosFijos = 0.0;
       double totalCostosVariables =costoVariable;
       double costoTotal = 0.0;
       double ingresoXVenta = 0.0;
        List<Integer> volumenes = new ArrayList<Integer>();
        volumen = volumen - 100;
        for (int i = 0; i < 100; i++) {
            volumen = volumen + 100;
            volumenes.add(volumen);
        }
        //costos fijos: los busco en la base de datos y los sumo
        List<CostosFijos> costosFijosDevueltos = buscarCostosFijos();
        for (CostosFijos costosFijos : costosFijosDevueltos) {
            totalCostosFijos = totalCostosFijos + costosFijos.getCosto();
        }
        
        
        
        //costo total: costo variable mas costo fijo
         
               
         
        // lleno los dto (cada dto es una fila)
         for (int i = 0; i < volumenes.size(); i++) {
             DTOPuntoEquilibrio dtoPuntoEquilibrio = new DTOPuntoEquilibrio();
             dtoPuntoEquilibrio.setVolumen(String.valueOf(volumenes.get(i)));
             dtoPuntoEquilibrio.setCostoFijo(String.valueOf(totalCostosFijos));
             dtoPuntoEquilibrio.setCostoVariable(String.valueOf((totalCostosVariables * volumenes.get(i))/producto.getTamanioLoteEstandar()));
             dtoPuntoEquilibrio.setCostoTotales(String.valueOf(((totalCostosVariables * volumenes.get(i))/producto.getTamanioLoteEstandar()) + totalCostosFijos));
             //ingreso por venta: demanda x precio base del producto
             dtoPuntoEquilibrio.setIngresoPorVenta(String.valueOf(producto.getPrecioBase() * volumenes.get(i)));
             //precio: es el precio base
             dtoPuntoEquilibrio.setPrecio(String.valueOf(producto.getPrecioBase()));
             //cv/unidad: es el costo variable sobre la demanda
             dtoPuntoEquilibrio.setCvUnidad(String.valueOf(totalCostosVariables / volumenes.get(i)));
             // faltaria calcular el Punto de equilibrio(cuando el ingreso por venta corta al costo total, el valor de volumen que da , es el punto de equilibrio)
             dtoPuntoEquilibrio.setPuntoEquilibrio(String.valueOf(totalCostosFijos / (producto.getPrecioBase()-(totalCostosVariables/500))));
             dtosADevolver.add(dtoPuntoEquilibrio);
             
             
             
        }
         
        
        return  dtosADevolver;
        
    }
    
    public List<CostosFijos> buscarCostosFijos(){
        List<CostosFijos> costosFijosEcontrados = null;
        Criteria criterioCostosFijos = Fachada.getInstancia().crearCriterioSinEliminado(CostosFijos.class);
        criterioCostosFijos.add(Restrictions.eq("eliminado", false));
        costosFijosEcontrados = Fachada.getInstancia().buscar(CostosFijos.class, criterioCostosFijos);

        return costosFijosEcontrados;
        
        
    }

    public void calcularCostosVariables() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public ProductoFinal buscarProductoFinal(ProductoFinal productoFinal) {
        List<ProductoFinal> productoFinalEncontrado = null;
        Criteria criterioFinalIntermedio = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        criterioFinalIntermedio.add(Restrictions.eq("eliminado", false));
        criterioFinalIntermedio.add(Restrictions.eq("nombre", productoFinal.getNombre()));
        productoFinalEncontrado = Fachada.getInstancia().buscar(ProductoFinal.class, criterioFinalIntermedio);

        return productoFinalEncontrado.get(0);
        
    }
    
    
}
    

