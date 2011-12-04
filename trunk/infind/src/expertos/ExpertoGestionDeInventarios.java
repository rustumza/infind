/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOGestionDeInventario;
import Entidades.MaestroDeArticulo;

/**
 *
 * @author rustu
 */
public class ExpertoGestionDeInventarios {

    MaestroDeArticulo articulo;
    
    public void setArticulo(MaestroDeArticulo articulo) {
        this.articulo = articulo;
    }

    public MaestroDeArticulo getArticulo(){
        return articulo;
    }
    
    public void calcularInventario(DTOGestionDeInventario dto) {
        
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
        articulo.setCostoDeAlmacenamiento(dto.getCostoDeAlmacenamiento());
        articulo.setCostoDePedido(dto.getCostoDePedido());
        articulo.setTipoInventario(dto.getTipoInventario());
        float puntoDePedido = 0;
        float demandaDiaria = demanda / 28; //un periodo tiene 28 días
        float stockDeSeguridad = 0;
        
        if(dto.getTipoInventario().equals("Fabricacion interna")){
        
        }else if(dto.getTipoInventario().equals("Lote fijo")){
            stockDeSeguridad = (float)Math.sqrt(articulo.getTiempoDeObtenecion());
            
        }else if(dto.getTipoInventario().equals("Intervalo fijo")){
        
            
        }
        
    
    }
    
}
