/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MateriaPrima;
import Entidades.PedidoAProveedor;
import Entidades.ProductoComponente;
import Entidades.Proveedor;
import excepciones.StockExcepcion;
import expertos.ExpertoPedidoAproveedores;
import interfacesGraficas.ModeloTablas.ModeloTablaPedidoAProveedores;
import interfacesGraficas.PantallaEditarPedidoAProveedores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorEditarPedidoAProveedor {
    
    ControladorPantallaMadre controladorPantallaMadre;
    ExpertoPedidoAproveedores experto;   
    PantallaEditarPedidoAProveedores pantallaEditarPedidoAProveedor;
    
    public ControladorEditarPedidoAProveedor(ControladorPantallaMadre cont) {
        
        controladorPantallaMadre = cont;
        experto = new ExpertoPedidoAproveedores();        
        
    }
    
    public void iniciar(){
        pantallaEditarPedidoAProveedor = new PantallaEditarPedidoAProveedores(controladorPantallaMadre.getPantalla(), false, this);
        ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
        pantallaEditarPedidoAProveedor.getSoloSinConfirmar().setSelected(true);
        pantallaEditarPedidoAProveedor.getSeConcreto().setSelected(true);
        pantallaEditarPedidoAProveedor.getGuardar().setEnabled(true);
        pantallaEditarPedidoAProveedor.getEditar().setEnabled(false);
        pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
        List<PedidoAProveedor> Lista = experto.buscarPedidos(true);
        mod.setListaElementos(Lista);
        pantallaEditarPedidoAProveedor.getTablaDePedidos().setModel(mod);
        pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
        pantallaEditarPedidoAProveedor.setVisible(true);
        
       
    }

    /*
    public void buscarProducto() {
        
        String codigo = pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().getText();
        MaestroDeArticulo articulo = null;
        List<Proveedor> listaDeProveedores = null;
        if(!codigo.equals("")){
            
            if(((String)pantallaEditarPedidoAProveedor.getTipoProductoListBox().getSelectedItem()).equals("Materia prima")){
                articulo = experto.buscarMateriaPrima(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                    return;
                }
                listaDeProveedores= ((MateriaPrima)articulo).getProveedores();

            }else{
                articulo = experto.buscarProductoComponente(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                    return;
                }
                listaDeProveedores= ((ProductoComponente)articulo).getProveedores();
            }
            
            if(listaDeProveedores == null){
                JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "El articulo no tiene proveedores", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                return;
            }
            pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(listaDeProveedores.toArray()));
            if(((String)pantallaEditarPedidoAProveedor.getTipoProductoListBox().getSelectedItem()).equals("Materia prima")){
                pantallaEditarPedidoAProveedor.getProveedorListBox().setSelectedItem(((MateriaPrima)articulo).getProveedorPredeterminado());
            }else{
                pantallaEditarPedidoAProveedor.getProveedorListBox().setSelectedItem(((ProductoComponente)articulo).getProveedor());
            }
            pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText(articulo.getNombre());
            pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText(String.valueOf(articulo.getTamanioLoteEstandar()));
            pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText(String.valueOf(articulo.getTiempoDeObtenecion()));
            pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText(articulo.getUnidadDeMedida());
            pantallaEditarPedidoAProveedor.getBucarProducto().setEnabled(false);
            pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setEnabled(false);
            
        }

        
    }
     * 
     */

    public void cargarPedidoSeleccionado() {
        
        int detalleSeleccionado = pantallaEditarPedidoAProveedor.getTablaDePedidos().getSelectedRow();
        pantallaEditarPedidoAProveedor.getQuitar().setEnabled(true);
        if(pantallaEditarPedidoAProveedor.getSoloSinConfirmar().isSelected()){
            pantallaEditarPedidoAProveedor.getEditar().setEnabled(true);
            pantallaEditarPedidoAProveedor.getQuitar().setEnabled(true);
            pantallaEditarPedidoAProveedor.getSeConcreto().setEnabled(true);
        }else{
            pantallaEditarPedidoAProveedor.getEditar().setEnabled(false);
            pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
            pantallaEditarPedidoAProveedor.getSeConcreto().setEnabled(false);
        }
        
        PedidoAProveedor pedido = experto.getListaDePedidos().get(detalleSeleccionado);
        if(pedido.getArticulo().getClass().equals(MateriaPrima.class)){
            pantallaEditarPedidoAProveedor.getTipoProductoListBox().setSelectedItem("Materia prima");
            List<Proveedor> listaProveedor = ((MateriaPrima)pedido.getArticulo()).getProveedores();
            pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(listaProveedor.toArray()));
            pantallaEditarPedidoAProveedor.getProveedorListBox().setSelectedItem(pedido.getProveedor());
        }else{
            pantallaEditarPedidoAProveedor.getTipoProductoListBox().setSelectedItem("Producto componente");
            List<Proveedor> listaProveedor = ((ProductoComponente)pedido.getArticulo()).getProveedores();
            pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(listaProveedor.toArray()));
            pantallaEditarPedidoAProveedor.getProveedorListBox().setSelectedItem(((ProductoComponente)pedido.getArticulo()).getProveedor());
            pantallaEditarPedidoAProveedor.getProveedorListBox().setSelectedItem(pedido.getProveedor());
        }
        pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setText(pedido.getArticulo().getCodigo());
        pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().setText(String.valueOf((int)pedido.getCantidad()/pedido.getArticulo().getTamanioLoteEstandar()));
        pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText(pedido.getArticulo().getNombre());
        pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText(String.valueOf(pedido.getArticulo().getTamanioLoteEstandar()));
        pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText(String.valueOf(pedido.getArticulo().getTiempoDeObtenecion()));
        pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText(pedido.getArticulo().getUnidadDeMedida());
        pantallaEditarPedidoAProveedor.getSeConcreto().setSelected(pedido.isEstaConcretado());
        
        
    }

    /*public void agregar() {
        try{
            String cantidadString = pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().getText();
            if(!pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().getText().equals("")){
                List<PedidoAProveedor> lista = experto.generarPedidoAProveedores(pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().getText(), Integer.parseInt(cantidadString),(Proveedor)pantallaEditarPedidoAProveedor.getProveedorListBox().getSelectedItem());
                ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
                mod.setListaElementos(lista);
                pantallaEditarPedidoAProveedor.getTablaDePedidos().setModel(mod);
                pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
                pantallaEditarPedidoAProveedor.getEditar().setEnabled(false);
                pantallaEditarPedidoAProveedor.getAgregar().setEnabled(true);
                pantallaEditarPedidoAProveedor.getBucarProducto().setEnabled(true);
                pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setEnabled(true);
                pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setText("");
                pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
                pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText("");
                pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
                pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
                pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText("");
                pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
            }else{
                JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "No ha seleccionado un producto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
            
            }
        
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "La cantidad ingresada no es válida", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().requestFocus();
        }
    }
     * */
    

    public void cancelar() {
        
            ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
            mod.setListaElementos(experto.getListaDePedidos());
            pantallaEditarPedidoAProveedor.getTablaDePedidos().setModel(mod);
            pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setText("");
            pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
            pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText("");
            pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
            pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
            pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText("");
            pantallaEditarPedidoAProveedor.getSeConcreto().setSelected(false);
            pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
            pantallaEditarPedidoAProveedor.getSeConcreto().setSelected(false);
            pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
            pantallaEditarPedidoAProveedor.getEditar().setEnabled(false);
        
    }

    public void editar() {
        try{
            int seleccionado = pantallaEditarPedidoAProveedor.getTablaDePedidos().getSelectedRow();
            List<PedidoAProveedor> lista = experto.editar(seleccionado, Integer.parseInt(pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().getText()), (Proveedor)pantallaEditarPedidoAProveedor.getProveedorListBox().getSelectedItem(), pantallaEditarPedidoAProveedor.getSeConcreto().isSelected());
            ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
            mod.setListaElementos(lista);
            pantallaEditarPedidoAProveedor.getTablaDePedidos().setModel(mod);
            pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
            pantallaEditarPedidoAProveedor.getEditar().setEnabled(false);
            pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setText("");
            pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
            pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText("");
            pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
            pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
            pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText("");
            pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
            pantallaEditarPedidoAProveedor.getSeConcreto().setSelected(false);
            pantallaEditarPedidoAProveedor.getSeConcreto().setEnabled(true);
            
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "La cantidad ingresada no es válida", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().requestFocus();
        }
        
        
    }
        

    public void quitar() {
        int seleccionado = pantallaEditarPedidoAProveedor.getTablaDePedidos().getSelectedRow();
        List<PedidoAProveedor> lista = experto.quitar(seleccionado);
        ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
        mod.setListaElementos(lista);
        pantallaEditarPedidoAProveedor.getTablaDePedidos().setModel(mod);
        pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
        pantallaEditarPedidoAProveedor.getEditar().setEnabled(false);
        pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setText("");
        pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
        pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText("");
        pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
        pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
        pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText("");
        pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
        pantallaEditarPedidoAProveedor.getSeConcreto().setSelected(false);
        pantallaEditarPedidoAProveedor.getSeConcreto().setEnabled(true);
            
    }

    public void guardar() {
        try {
            experto.confirmarPedido();
            JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "Pedidos guardados con éxito", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            
            ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
            mod.setListaElementos(new ArrayList<PedidoAProveedor>());
            pantallaEditarPedidoAProveedor.getTablaDePedidos().setModel(mod);
            pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
            pantallaEditarPedidoAProveedor.getEditar().setEnabled(false);
            pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setText("");
            pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
            pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText("");
            pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
            pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
            pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText("");
            pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
            pantallaEditarPedidoAProveedor.getSeConcreto().setSelected(false);
            pantallaEditarPedidoAProveedor.getSeConcreto().setEnabled(true);
        } catch (StockExcepcion ex) {
            JOptionPane.showMessageDialog(pantallaEditarPedidoAProveedor, "Error al confirmar el pedido", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void soloSinConfirmar() {
        boolean soloSinConfirmar = pantallaEditarPedidoAProveedor.getSoloSinConfirmar().isSelected();
        List<PedidoAProveedor> lista = experto.buscarPedidos(soloSinConfirmar);
        ModeloTablaPedidoAProveedores mod = new ModeloTablaPedidoAProveedores();
        mod.setListaElementos(lista);
        if(soloSinConfirmar){
            pantallaEditarPedidoAProveedor.getSeConcreto().setEnabled(true);
            pantallaEditarPedidoAProveedor.getGuardar().setEnabled(true);
        }else{
            pantallaEditarPedidoAProveedor.getSeConcreto().setEnabled(false);
            pantallaEditarPedidoAProveedor.getGuardar().setEnabled(false);
        }
        pantallaEditarPedidoAProveedor.getQuitar().setEnabled(false);
        pantallaEditarPedidoAProveedor.getEditar().setEnabled(false); 
        pantallaEditarPedidoAProveedor.getTablaDePedidos().setModel(mod);
        pantallaEditarPedidoAProveedor.getCodigoProductoTextBox().setText("");
        pantallaEditarPedidoAProveedor.getProductoSeleccionadoTextBox().setText("");
        pantallaEditarPedidoAProveedor.getLoteEstandarTextBox().setText("");
        pantallaEditarPedidoAProveedor.getTiempoDeDemoraTextBox().setText("");
        pantallaEditarPedidoAProveedor.getCantidadDeLotesTextBox().setText("");
        pantallaEditarPedidoAProveedor.getUnidadDeMedida().setText("");
        pantallaEditarPedidoAProveedor.getProveedorListBox().setModel(new DefaultComboBoxModel(new ArrayList().toArray()));
        
    }
    
}
