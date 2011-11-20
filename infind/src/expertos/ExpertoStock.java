/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MaestroDeArticulo;
import Entidades.Stock;
import excepciones.StockExcepcion;
import persistencia.Conexion;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoStock {
    
    
    
    public ExpertoStock(){
    
    }
    
    public void iniciarManejoDeStock(){
        Conexion.getInstancia().iniciarTX();
    
    }
    
    public void finalizarManejoDeStock(){
        Conexion.getInstancia().confirmarTx();
    
    }
    
    public void cancelarManejoDeStock(){
        Conexion.getInstancia().deshacerTx();
    
    }
    
    public void agregarStockReal(MaestroDeArticulo articulo, float cantidad){
    
        Stock stock = articulo.getStock();
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
    }
    
    public void agregarStockPorLlegar(MaestroDeArticulo articulo, float cantidad){
    
    
        Stock stock = articulo.getStock();
        stock.setCantidadPorEntrar(stock.getCantidadPorEntrar() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void reservarStock(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
    
    
        Stock stock = articulo.getStock();
        if(((stock.getCantidadFisicaReal() + stock.getCantidadPorEntrar())-stock.getCantidadReservada()) < cantidad){
            throw new StockExcepcion(3);
        }
        stock.setCantidadReservada(stock.getCantidadReservada() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void restarStock(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
    
    
        Stock stock = articulo.getStock();
        if(stock.getCantidadFisicaReal() < cantidad){
            throw new StockExcepcion(2);
        }
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() - cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void cambiarStockPorLlegarPorStockReal(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
        
    
        Stock stock = articulo.getStock();
        if(stock.getCantidadPorEntrar()< cantidad){
            throw new StockExcepcion(1);
        }
        stock.setCantidadPorEntrar(stock.getCantidadPorEntrar() - cantidad);
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
    }
    
}
