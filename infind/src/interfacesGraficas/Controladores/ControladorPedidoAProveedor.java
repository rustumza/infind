/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeArticulo;
import Entidades.PedidoAProveedor;
import expertos.ExpertoPedidoAproveedores;
import interfacesGraficas.ModeloTablas.ModeloTablaPedidoAProveedores;
import interfacesGraficas.PantallaCrearPedidoAProveedores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorPedidoAProveedor {
    
    ControladorPantallaMadre controladorPantallaMadre;
    ExpertoPedidoAproveedores experto;   
    PantallaCrearPedidoAProveedores pantallaCrearPedidoAProveedor;
    
    public ControladorPedidoAProveedor(ControladorPantallaMadre cont) {
        
        controladorPantallaMadre = cont;
        experto = new ExpertoPedidoAproveedores();        
        
    }
    
    public void iniciar(){
        pantallaCrearPedidoAProveedor = new PantallaCrearPedidoAProveedores(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearPedidoAProveedor.setVisible(true);
    }

    public void buscarProducto() {
        
        String codigo = pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().getText();
        MaestroDeArticulo articulo = null;
        if(!codigo.equals("")){
            
            if(((String)pantallaCrearPedidoAProveedor.getTipoProductoListBox().getSelectedItem()).equals("Materia prima")){
                articulo = experto.buscarMateriaPrima(codigo);

            }else{
                articulo = experto.buscarProductoComponente(codigo);
            }
            if(articulo == null){
                JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                return;
            }
            pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText(articulo.getNombre());
            pantallaCrearPedidoAProveedor.getLoteEstandarTextBox().setText(String.valueOf(articulo.getTamanioLoteEstandar()));
            pantallaCrearPedidoAProveedor.getTiempoDeDemoraTextBox().setText(String.valueOf(articulo.getTiempoDeObtenecion()));
            pantallaCrearPedidoAProveedor.getUnidadDeMedida().setText(articulo.getUnidadDeMedida());
            pantallaCrearPedidoAProveedor.getBucarProducto().setEnabled(false);
            pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setEnabled(false);
        }

        
    }

    public void cargarPedidoSeleccionado() {
        
        int detalleSeleccionado = pantallaCrearPedidoAProveedor.getTablaDePedidos().getSelectedRow();
        pantallaCrearPedidoAProveedor.getQuitar().setEnabled(true);
        pantallaCrearPedidoAProveedor.getEditar().setEnabled(true);
        pantallaCrearPedidoAProveedor.getAgregar().setEnabled(false);
        PedidoAProveedor pedido = experto.getListaDePedidos().get(detalleSeleccionado);
        pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setText(pedido.getArticulo().getCodigo());
        pantallaCrearPedidoAProveedor.getBucarProducto().setEnabled(false);
        pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setEnabled(false);
        pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().setText(String.valueOf(pedido.getCantidad()));
        pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText(pedido.getArticulo().getNombre());
        pantallaCrearPedidoAProveedor.getLoteEstandarTextBox().setText(String.valueOf(pedido.getArticulo().getTamanioLoteEstandar()));
        pantallaCrearPedidoAProveedor.getTiempoDeDemoraLabel().setText(String.valueOf(pedido.getArticulo().getTiempoDeObtenecion()));
        pantallaCrearPedidoAProveedor.getUnidadDeMedida().setText(pedido.getArticulo().getUnidadDeMedida());
        
    }

    public void agregar() {
        try{
            String cantidadString = pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().getText();
            List<PedidoAProveedor> lista = experto.generarPedidoAProveedores(pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().getText(), Integer.parseInt(cantidadString));
            ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
            mod.setListaElementos(lista);
            pantallaCrearPedidoAProveedor.getTablaDePedidos().setModel(mod);
            pantallaCrearPedidoAProveedor.getQuitar().setEnabled(false);
            pantallaCrearPedidoAProveedor.getEditar().setEnabled(false);
            pantallaCrearPedidoAProveedor.getAgregar().setEnabled(true);
            pantallaCrearPedidoAProveedor.getBucarProducto().setEnabled(true);
            pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setEnabled(true);
            pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setText("");
            pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
            pantallaCrearPedidoAProveedor.getLoteEstandarTextBox().setText("");
            pantallaCrearPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
            pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
            pantallaCrearPedidoAProveedor.getUnidadDeMedida().setText("");
        
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "La cantidad ingresada no es válida", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().requestFocus();
        }
    }

    public void cancelar() {
        
            ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
            mod.setListaElementos(experto.getListaDePedidos());
            pantallaCrearPedidoAProveedor.getTablaDePedidos().setModel(mod);
            pantallaCrearPedidoAProveedor.getQuitar().setEnabled(false);
            pantallaCrearPedidoAProveedor.getEditar().setEnabled(false);
            pantallaCrearPedidoAProveedor.getAgregar().setEnabled(true);
            pantallaCrearPedidoAProveedor.getBucarProducto().setEnabled(true);
            pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setEnabled(true);
            pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setText("");
            pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
            pantallaCrearPedidoAProveedor.getLoteEstandarTextBox().setText("");
            pantallaCrearPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
            pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
            pantallaCrearPedidoAProveedor.getUnidadDeMedida().setText("");
        
    }

    public void editar() {
        try{
            int seleccionado = pantallaCrearPedidoAProveedor.getTablaDePedidos().getSelectedRow();
            List<PedidoAProveedor> lista = experto.editar(seleccionado, Integer.parseInt(pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().getText()));
            ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
            mod.setListaElementos(lista);
            pantallaCrearPedidoAProveedor.getTablaDePedidos().setModel(mod);
            pantallaCrearPedidoAProveedor.getQuitar().setEnabled(false);
            pantallaCrearPedidoAProveedor.getEditar().setEnabled(false);
            pantallaCrearPedidoAProveedor.getAgregar().setEnabled(true);
            pantallaCrearPedidoAProveedor.getBucarProducto().setEnabled(true);
            pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setEnabled(true);
            pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setText("");
            pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
            pantallaCrearPedidoAProveedor.getLoteEstandarTextBox().setText("");
            pantallaCrearPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
            pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
            pantallaCrearPedidoAProveedor.getUnidadDeMedida().setText("");
            
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "La cantidad ingresada no es válida", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().requestFocus();
        }
        
        
    }
        

    public void quitar() {
        int seleccionado = pantallaCrearPedidoAProveedor.getTablaDePedidos().getSelectedRow();
        List<PedidoAProveedor> lista = experto.quitar(seleccionado);
        ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
        mod.setListaElementos(lista);
        pantallaCrearPedidoAProveedor.getTablaDePedidos().setModel(mod);
        pantallaCrearPedidoAProveedor.getQuitar().setEnabled(false);
        pantallaCrearPedidoAProveedor.getEditar().setEnabled(false);
        pantallaCrearPedidoAProveedor.getAgregar().setEnabled(true);
        pantallaCrearPedidoAProveedor.getBucarProducto().setEnabled(true);
        pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setEnabled(true);
        pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setText("");
        pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
        pantallaCrearPedidoAProveedor.getLoteEstandarTextBox().setText("");
        pantallaCrearPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
        pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
        pantallaCrearPedidoAProveedor.getUnidadDeMedida().setText("");
            
    }

    public void guardar() {
        experto.asentarPedidos();
        JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "Pedidos guardados con éxito", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
        
        ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
        mod.setListaElementos(new ArrayList<PedidoAProveedor>());
        pantallaCrearPedidoAProveedor.getTablaDePedidos().setModel(mod);
        pantallaCrearPedidoAProveedor.getQuitar().setEnabled(false);
        pantallaCrearPedidoAProveedor.getEditar().setEnabled(false);
        pantallaCrearPedidoAProveedor.getAgregar().setEnabled(true);
        pantallaCrearPedidoAProveedor.getBucarProducto().setEnabled(true);
        pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setEnabled(true);
        pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().setText("");
        pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
        pantallaCrearPedidoAProveedor.getLoteEstandarTextBox().setText("");
        pantallaCrearPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
        pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
        pantallaCrearPedidoAProveedor.getUnidadDeMedida().setText("");
    }
    
}
