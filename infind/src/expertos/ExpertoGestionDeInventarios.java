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
        for (int i = tamanio-1; i > tamanio-4 & i>=0 ; i--) {
            demanda += articulo.getDemanda().get(i).getDemandaHistorica();
        }        
        float loteOptimo = (float)Math.sqrt(((2*dto.getCostoDePedido()*demanda)/dto.getCostoDeAlmacenamiento()));
        articulo.setTamanioLoteEstandar(loteOptimo);
        articulo.setCostoDeAlmacenamiento(dto.getCostoDeAlmacenamiento());
        articulo.setCostoDePedido(dto.getCostoDePedido());
        articulo.setTipoInventario(dto.getTipoInventario());
    }
    
}
