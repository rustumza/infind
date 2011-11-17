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
    
    private boolean inicioDeManejoDeStock;
    
    public ExpertoStock(){
        inicioDeManejoDeStock = false;
    }
    
    public void iniciarManejoDeStock(){
        Conexion.getInstancia().iniciarTX();
        inicioDeManejoDeStock = true;
    }
    
    public void finalizarManejoDeStock(){
        Conexion.getInstancia().confirmarTx();
        inicioDeManejoDeStock = false;
    }
    
    public void cancelarManejoDeStock(){
        Conexion.getInstancia().deshacerTx();
        inicioDeManejoDeStock = false;
    }
    
    public void agregarStockReal(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
        if(!inicioDeManejoDeStock){
            throw new StockExcepcion(4);
        }
        Stock stock = articulo.getStock();
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
    }
    
    public void agregarStockPorLlegar(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
    
        if(!inicioDeManejoDeStock){
            throw new StockExcepcion(4);
        }
        Stock stock = articulo.getStock();
        stock.setCantidadPorEntrar(stock.getCantidadPorEntrar() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void reservarStock(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
    
        if(!inicioDeManejoDeStock){
            throw new StockExcepcion(4);
        }
        Stock stock = articulo.getStock();
        if(((stock.getCantidadFisicaReal() + stock.getCantidadPorEntrar())-stock.getCantidadReservada()) < cantidad){
            throw new StockExcepcion(3);
        }
        stock.setCantidadReservada(stock.getCantidadReservada() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void restarStock(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
    
        if(!inicioDeManejoDeStock){
            throw new StockExcepcion(4);
        }
        Stock stock = articulo.getStock();
        if(stock.getCantidadFisicaReal() < cantidad){
            throw new StockExcepcion(2);
        }
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() - cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
        
    }
    
    public void cambiarStockPorLlegarPorStockReal(MaestroDeArticulo articulo, float cantidad) throws StockExcepcion{
        
        if(!inicioDeManejoDeStock){
            throw new StockExcepcion(4);
        }
        Stock stock = articulo.getStock();
        if(stock.getCantidadPorEntrar()< cantidad){
            throw new StockExcepcion(1);
        }
        stock.setCantidadPorEntrar(stock.getCantidadPorEntrar() - cantidad);
        stock.setCantidadFisicaReal(stock.getCantidadFisicaReal() + cantidad);
        Fachada.getInstancia().guardarSinTranasaccion(stock);
    }
    
}
