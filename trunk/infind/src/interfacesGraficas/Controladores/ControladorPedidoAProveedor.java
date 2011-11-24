/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeArticulo;
import Entidades.MateriaPrima;
import Entidades.PedidoAProveedor;
import Entidades.ProductoComponente;
import Entidades.Proveedor;
import expertos.ExpertoPedidoAproveedores;
import interfacesGraficas.ModeloTablas.ModeloTablaPedidoAProveedores;
import interfacesGraficas.PantallaCrearPedidoAProveedores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
        ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
        mod.setListaElementos(new ArrayList<PedidoAProveedor>());
        pantallaCrearPedidoAProveedor.getTablaDePedidos().setModel(mod);
        pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
        pantallaCrearPedidoAProveedor.setVisible(true);
    }

    public void buscarProducto() {
        
        String codigo = pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().getText();
        MaestroDeArticulo articulo = null;
        List<Proveedor> listaDeProveedores = null;
        if(!codigo.equals("")){
            
            if(((String)pantallaCrearPedidoAProveedor.getTipoProductoListBox().getSelectedItem()).equals("Materia prima")){
                articulo = experto.buscarMateriaPrima(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                    return;
                }
                listaDeProveedores= ((MateriaPrima)articulo).getProveedores();

            }else{
                articulo = experto.buscarProductoComponente(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                    return;
                }
                listaDeProveedores= ((ProductoComponente)articulo).getProveedores();
            }
            
            if(listaDeProveedores == null){
                JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "El articulo no tiene proveedores", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                return;
            }
            pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(listaDeProveedores.toArray()));
            if(((String)pantallaCrearPedidoAProveedor.getTipoProductoListBox().getSelectedItem()).equals("Materia prima")){
                pantallaCrearPedidoAProveedor.getProveedorListBox().setSelectedItem(((MateriaPrima)articulo).getProveedorPredeterminado());
            }else{
                pantallaCrearPedidoAProveedor.getProveedorListBox().setSelectedItem(((ProductoComponente)articulo).getProveedor());
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
        if(pedido.getArticulo().getTipo().equals("Materia prima")){
            pantallaCrearPedidoAProveedor.getTipoProductoListBox().setSelectedItem("Materia prima");
            List<Proveedor> listaProveedor = ((MateriaPrima)pedido.getArticulo()).getProveedores();
            pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(listaProveedor.toArray()));
            pantallaCrearPedidoAProveedor.getProveedorListBox().setSelectedItem(((MateriaPrima)pedido.getArticulo()).getProveedorPredeterminado());
        }else{
            pantallaCrearPedidoAProveedor.getTipoProductoListBox().setSelectedItem("Producto componente");
            List<Proveedor> listaProveedor = ((ProductoComponente)pedido.getArticulo()).getProveedores();
            pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(listaProveedor.toArray()));
            pantallaCrearPedidoAProveedor.getProveedorListBox().setSelectedItem(((ProductoComponente)pedido.getArticulo()).getProveedor());
            pantallaCrearPedidoAProveedor.getProveedorListBox().setSelectedItem(((ProductoComponente)pedido.getArticulo()).getProveedor());
        }
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
            if(!pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().getText().equals("")){
                if(pantallaCrearPedidoAProveedor.getProveedorListBox().getSelectedItem() == null){
                    JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "No ha seleccionado un proveedor", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantallaCrearPedidoAProveedor.getProveedorListBox().requestFocus();
                    return;
                }
                List<PedidoAProveedor> lista = experto.generarPedidoAProveedores(pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().getText(), Integer.parseInt(cantidadString),(Proveedor)pantallaCrearPedidoAProveedor.getProveedorListBox().getSelectedItem());
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
                pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
            }else{
                JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "No ha seleccionado un producto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
            
            }
        
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
            pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
        
    }

    public void editar() {
        try{
            int seleccionado = pantallaCrearPedidoAProveedor.getTablaDePedidos().getSelectedRow();
            List<PedidoAProveedor> lista = experto.editar(seleccionado, Integer.parseInt(pantallaCrearPedidoAProveedor.getCantidadDeLotesTextBox().getText()), (Proveedor)pantallaCrearPedidoAProveedor.getProveedorListBox().getSelectedItem(), false);
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
            pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
            
            
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
        pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
            
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
        pantallaCrearPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
    }
    
}
