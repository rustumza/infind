/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOCGI;
import DTOs.DTOGestionDeInventario;
import Entidades.MaestroDeArticulo;
import Entidades.MateriaPrima;
import Entidades.ProductoComponente;
import Entidades.ProductoIntermedio;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoGestionDeInventarios {

    MaestroDeArticulo articulo;
    List<MaestroDeArticulo> listaDeArticulos;
    
    public void setArticulo(MaestroDeArticulo articulo) {
        this.articulo = articulo;
    }

    public MaestroDeArticulo getArticulo(){
        return articulo;
    }
    
    public void calcularInventario(DTOGestionDeInventario dto) {
        
        
        articulo.setCostoDeAlmacenamiento(dto.getCostoDeAlmacenamiento());
        articulo.setCostoDePedido(dto.getCostoDePedido());
        articulo.setTipoInventario(dto.getTipoInventario());
        
        
        if(dto.getTipoInventario().equals("Fabricacion interna")){
        
            int tamanio = articulo.getDemanda().size();
            float demanda= (float)0;
            int contAux = 0;
            for (int i = tamanio-1; i > tamanio-4 & i>=0 ; i--) {
                demanda += articulo.getDemanda().get(i).getDemandaHistorica();
                contAux++;
            }
            demanda = demanda / contAux;
            float loteOptimo = (float)Math.sqrt(((2*dto.getCostoDePedido()*demanda)/(dto.getCostoDeAlmacenamiento()*(1-(demanda/dto.getTasaDeProduccion())))));
            articulo.setTamanioLoteEstandar(loteOptimo);
            int numeroDeCiclosDeFabricacionOptimo = (int)(demanda/loteOptimo);
            if(demanda%loteOptimo > 0){
                numeroDeCiclosDeFabricacionOptimo++;
            }
            articulo.setNumeroDeCiclosDeFabricacionOptimo(numeroDeCiclosDeFabricacionOptimo);
            int tiempoDeObtencio = (int)(250*loteOptimo/dto.getTasaDeProduccion());
            if((250*loteOptimo/dto.getTasaDeProduccion())%1>0){
                tiempoDeObtencio++;
            }
            articulo.setTiempoDeObtenecion(tiempoDeObtencio);
            articulo.setStockDeSeguridad((float)Math.sqrt(tiempoDeObtencio));
            
        }else if(dto.getTipoInventario().equals("Lote fijo")){
            
            int tamanio = articulo.getDemanda().size();
            float demanda= (float)0;
            int contAux = 0;
            for (int i = tamanio-1; i > tamanio-4 & i>=0 ; i--) {
                demanda += articulo.getDemanda().get(i).getDemandaHistorica();
                contAux++;
            }
            demanda = demanda / contAux;
            float loteOptimo = (float)Math.sqrt(((2*dto.getCostoDePedido()*demanda)/dto.getCostoDeAlmacenamiento()));
            articulo.setTamanioLoteEstandar(loteOptimo);
            float demandaDiaria = demanda / 28; //un periodo tiene 28 días
            
            articulo.setStockDeSeguridad((float)Math.sqrt(articulo.getTiempoDeObtenecion()));
            articulo.setPuntoDePedido(demandaDiaria * articulo.getTiempoDeObtenecion() + articulo.getStockDeSeguridad());
            
        }else if(dto.getTipoInventario().equals("Intervalo fijo")){
            
            int tamanio = articulo.getDemanda().size();
            float demanda= (float)0;
            int contAux = 0;
            for (int i = tamanio-1; i > tamanio-4 & i>=0 ; i--) {
                demanda += articulo.getDemanda().get(i).getDemandaHistorica();
                contAux++;
            }
            demanda = demanda / contAux;
            float loteOptimo = (float)Math.sqrt(((2*dto.getCostoDePedido()*demanda)/dto.getCostoDeAlmacenamiento()));
            articulo.setTamanioLoteEstandar(loteOptimo);            
            articulo.setStockDeSeguridad((float)Math.sqrt(articulo.getTiempoDeObtenecion()));
            articulo.setStockMaximoInventarioIntervaloFijo(dto.getCantidadMaximaDeStock());
        }
        
    }
    
    
    public float calculoDeCostoDeGestionDeInvetario(MaestroDeArticulo art){
        
        if(art.getDemanda() == null || art.getDemanda().isEmpty() ){
            return ((float)-1);
        }
        int tamanio = art.getDemanda().size();
        float demanda= (float)0;
            int contAux = 0;
            for (int i = tamanio-1; i > tamanio-4 & i>=0 ; i--) {
                demanda += art.getDemanda().get(i).getDemandaHistorica();
                contAux++;
            }
        demanda = demanda / contAux;
        float cGI = (float)0;
        if(art.getTipoInventario() == null){
            return ((float)-1);
        }
        if(art.getTipoInventario().equals("Fabricacion interna")){
            cGI = art.getCostoDeAlmacenamiento() * demanda / 2 + art.getCostoDePedido() * demanda/ art.getTamanioLoteEstandar();
        }else{
            cGI = art.getCostoEstandar() * demanda + art.getCostoDeAlmacenamiento() * demanda / 2 + art.getCostoDePedido() * demanda/ art.getTamanioLoteEstandar();
        }
        
        return cGI;
        
    }
    
    public List<DTOCGI> calculoDeCostoDeGestionDeInventariosDeTodosLosProductos(){
        
        List<DTOCGI> dtocgi = new ArrayList<DTOCGI>();
        
        List<MateriaPrima> listaMateriaPrima = buscarMateriasPrimas();
        
        
        for (MateriaPrima materiaPrima : listaMateriaPrima) {
            DTOCGI dto = new DTOCGI();
            dto.setArticulo(materiaPrima);
            dto.setCgi(calculoDeCostoDeGestionDeInvetario(materiaPrima));
            dtocgi.add(dto);
        }
        
        List<ProductoIntermedio> listaProductoIntermedios = buscarProductoIntermedios();
        
        
        for (ProductoIntermedio prodInt : listaProductoIntermedios) {
            DTOCGI dto = new DTOCGI();
            dto.setArticulo(prodInt);
            dto.setCgi(calculoDeCostoDeGestionDeInvetario(prodInt));
            dtocgi.add(dto);
        }
        
        List<ProductoComponente> listaProductoComponente = buscarProductosComponente();
        
        
        for (ProductoComponente prodComp : listaProductoComponente) {
            DTOCGI dto = new DTOCGI();
            dto.setArticulo(prodComp);
            dto.setCgi(calculoDeCostoDeGestionDeInvetario(prodComp));
            dtocgi.add(dto);
        }
        
        return dtocgi;
    }
 
    
    public List<MateriaPrima> buscarMateriasPrimas(){
        Criteria criterioMatPrims = Fachada.getInstancia().crearCriterioSinEliminado(MateriaPrima.class);
        return Fachada.getInstancia().buscar(MateriaPrima.class, criterioMatPrims);
    }
    
    public List<ProductoComponente> buscarProductosComponente(){
    
        Criteria criterioProdComp = Fachada.getInstancia().crearCriterioSinEliminado(ProductoComponente.class);
        return Fachada.getInstancia().buscar(ProductoComponente.class, criterioProdComp);
    }
    
    public List<ProductoIntermedio> buscarProductoIntermedios(){
        Criteria criterioProdInt = Fachada.getInstancia().crearCriterioSinEliminado(ProductoIntermedio.class);
        return Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioProdInt);
    }

    public float calcularCGIParaMateriaPrima(String codigo) {
        Criteria criterioMatPrims = Fachada.getInstancia().crearCriterioSinEliminado(MateriaPrima.class);
        criterioMatPrims.add(Restrictions.eq("codigo", codigo));
        List<MateriaPrima> lista = Fachada.getInstancia().buscar(MateriaPrima.class, criterioMatPrims);
        if(lista.isEmpty()){
            return ((float)-1.0);
        }else{
            return calculoDeCostoDeGestionDeInvetario(lista.get(0));
        }
    }
    
    public float calcularCGIParaProductoIntermedio(String codigo) {
        Criteria criterioProdInter = Fachada.getInstancia().crearCriterioSinEliminado(ProductoIntermedio.class);
        criterioProdInter.add(Restrictions.eq("codigo", codigo));
        List<ProductoIntermedio> lista = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioProdInter);
        if(lista.isEmpty()){
            return ((float)-1.0);
        }else{
            return calculoDeCostoDeGestionDeInvetario(lista.get(0));
        }
    }
    
    public float calcularCGIParaProductoComponente(String codigo) {
        Criteria criterioProdComp = Fachada.getInstancia().crearCriterioSinEliminado(ProductoComponente.class);
        criterioProdComp.add(Restrictions.eq("codigo", codigo));
        List<ProductoComponente> lista = Fachada.getInstancia().buscar(ProductoComponente.class, criterioProdComp);
        if(lista.isEmpty()){
            return ((float)-1.0);
        }else{
            return calculoDeCostoDeGestionDeInvetario(lista.get(0));
        }
    }
    
    public List<MaestroDeArticulo> buscarProductosPorDebajoDelPuntoDePedido(){
    
        List<MaestroDeArticulo> lista = new ArrayList<MaestroDeArticulo>(); 
        ExpertoStock expStock = new ExpertoStock();
        
        
        List<MateriaPrima> listaMateriaPrima = buscarMateriasPrimas();
        
        
        for (MateriaPrima materiaPrima : listaMateriaPrima) {
            if(materiaPrima.getPuntoDePedido() != 0){
                if(materiaPrima.getPuntoDePedido()>expStock.getStockDisponibleAFuturo(materiaPrima)){
                    lista.add(materiaPrima);
                }
            }
        }
        
        List<ProductoIntermedio> listaProductoIntermedios = buscarProductoIntermedios();
        
        for (ProductoIntermedio prodInt : listaProductoIntermedios) {
            if(prodInt.getPuntoDePedido() != 0){
                if(prodInt.getPuntoDePedido()>expStock.getStockDisponibleAFuturo(prodInt)){
                        lista.add(prodInt);
                }
            }
        }
        
        List<ProductoComponente> listaProductoComponente = buscarProductosComponente();
        
        
        for (ProductoComponente prodComp : listaProductoComponente) {
            if(prodComp.getPuntoDePedido() != 0){
                if(prodComp.getPuntoDePedido()>expStock.getStockDisponibleAFuturo(prodComp)){
                        lista.add(prodComp);
                }
            }
        }
    
        return lista;
    }

    public List<MaestroDeArticulo> buscarProductosPorDebajoDelStockDeSeguridad() {
        
        List<MaestroDeArticulo> lista = new ArrayList<MaestroDeArticulo>(); 
        ExpertoStock expStock = new ExpertoStock();
        
        
        List<MateriaPrima> listaMateriaPrima = buscarMateriasPrimas();
        
        
        for (MateriaPrima materiaPrima : listaMateriaPrima) {
            if(materiaPrima.getStockDeSeguridad() != 0){
                if(materiaPrima.getStockDeSeguridad()>expStock.getStockDisponibleAFuturo(materiaPrima)){
                    lista.add(materiaPrima);
                }
            }
        }
        
        List<ProductoIntermedio> listaProductoIntermedios = buscarProductoIntermedios();
        
        for (ProductoIntermedio prodInt : listaProductoIntermedios) {
            if(prodInt.getStockDeSeguridad() != 0){
                if(prodInt.getStockDeSeguridad()>expStock.getStockDisponibleAFuturo(prodInt)){
                        lista.add(prodInt);
                }
            }
        }
        
        List<ProductoComponente> listaProductoComponente = buscarProductosComponente();
        
        
        for (ProductoComponente prodComp : listaProductoComponente) {
            if(prodComp.getStockDeSeguridad() != 0){
                if(prodComp.getStockDeSeguridad()>expStock.getStockDisponibleAFuturo(prodComp)){
                        lista.add(prodComp);
                }
            }
        }
    
        return lista;
        
    }
    
}
