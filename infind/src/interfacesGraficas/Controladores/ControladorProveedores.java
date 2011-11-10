/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MateriaPrima;
import Entidades.ProductoComponente;
import Entidades.Proveedor;
import expertos.ExpertoProveedor;
import interfacesGraficas.ModeloComboYListas.ModeloJListListaMateriaPrima;
import interfacesGraficas.ModeloComboYListas.ModeloJListListaProductoComponente;
import interfacesGraficas.PantallaCrearProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorProveedores {
    
    ControladorPantallaMadre contPantallaMadre;
    PantallaCrearProveedor pantallaCrearProveedor;
    ExpertoProveedor experto;
    
    public ControladorProveedores(ControladorPantallaMadre cont) {
        contPantallaMadre = cont;
        experto = new ExpertoProveedor();
    }
    
    
    
    
    public void crearProveedor(){
        
        pantallaCrearProveedor = new PantallaCrearProveedor(contPantallaMadre.getPantalla(), false, this);
        List<MateriaPrima> listaMatPrim = experto.buscarMateriasPrimas();
        pantallaCrearProveedor.getListaTodasLasMateriasPrimas().setModel(new ModeloJListListaMateriaPrima(listaMatPrim));
        pantallaCrearProveedor.getListaMateriasPrimasSeleccionadas().setModel(new ModeloJListListaMateriaPrima(new ArrayList<MateriaPrima>()));
        List<ProductoComponente> listaProdComp = experto.buscarProductosComponentes();
        pantallaCrearProveedor.getListaTodosLosProductosComponentes().setModel(new ModeloJListListaProductoComponente(listaProdComp));
        pantallaCrearProveedor.getListaProductosComponenteSeleccionados().setModel(new ModeloJListListaProductoComponente(new ArrayList<ProductoComponente>()));
        pantallaCrearProveedor.setVisible(true);
        
    }

    public void agregarMatriaPrima() {
        int seleccionado = pantallaCrearProveedor.getListaTodasLasMateriasPrimas().getSelectedIndex();
        MateriaPrima materiaPrimaSeleccionada = ((ModeloJListListaMateriaPrima)pantallaCrearProveedor.getListaTodasLasMateriasPrimas().getModel()).getElementAt(seleccionado);
        List<MateriaPrima> listaMatPrims = ((ModeloJListListaMateriaPrima)pantallaCrearProveedor.getListaMateriasPrimasSeleccionadas().getModel()).getMateriasPrimas();
        if(!listaMatPrims.contains(materiaPrimaSeleccionada)){
            listaMatPrims.add(materiaPrimaSeleccionada);
        }
        pantallaCrearProveedor.getListaMateriasPrimasSeleccionadas().setModel(new ModeloJListListaMateriaPrima(listaMatPrims));
    }
    
    public void sacarMateriaPrima(){
        //TODO: hacer metodo
        System.out.println("sacar materia prima");
        System.out.println("metodo no implementado!!");    
        
    }
    
    
    
    public void guardar() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(pantallaCrearProveedor.getNombreTextBox().getText());
        proveedor.setTelefono(Integer.valueOf(pantallaCrearProveedor.getTelefonoTextBox().getText()));
        proveedor.setDireccion(pantallaCrearProveedor.getDireccionTextBox().getText());
        proveedor.setCorreoElectronico(pantallaCrearProveedor.getCorreoElectronicoTextbox().getText());
        proveedor.setMateriasPrimas(new ArrayList<MateriaPrima>());
        proveedor.setProductosComponentes(new ArrayList<ProductoComponente>());
        List<MateriaPrima> listaDeMateriasPrimas = ((ModeloJListListaMateriaPrima)pantallaCrearProveedor.getListaMateriasPrimasSeleccionadas().getModel()).getMateriasPrimas();
        for (MateriaPrima materiaPrima : listaDeMateriasPrimas) {
            proveedor.addMateriaPrima(materiaPrima);
        }
        List<ProductoComponente> listaDeProdcutosComponente = ((ModeloJListListaProductoComponente)pantallaCrearProveedor.getListaProductosComponenteSeleccionados().getModel()).getProductosComponentes();
        for (ProductoComponente productoComponente : listaDeProdcutosComponente) {
            proveedor.addProductoComponente(productoComponente);
        }
        experto.guardar(proveedor);
        JOptionPane.showMessageDialog(pantallaCrearProveedor, "Proveedor guardado!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void agregarProductoComponente() {
        
        int seleccionado = pantallaCrearProveedor.getListaTodosLosProductosComponentes().getSelectedIndex();
        ProductoComponente productoComponenteSeleccionado = ((ModeloJListListaProductoComponente)pantallaCrearProveedor.getListaTodosLosProductosComponentes().getModel()).getElementAt(seleccionado);
        List<ProductoComponente> listaProdComp = ((ModeloJListListaProductoComponente)pantallaCrearProveedor.getListaProductosComponenteSeleccionados().getModel()).getProductosComponentes();
        if(!listaProdComp.contains(productoComponenteSeleccionado)){
            listaProdComp.add(productoComponenteSeleccionado);
        }
        pantallaCrearProveedor.getListaProductosComponenteSeleccionados().setModel(new ModeloJListListaProductoComponente(listaProdComp));
    }

    public void sacarProductoComponente() {
        //TODO: hacer este metodo
        System.out.println("sacar producto componente");
        System.out.println("metodo no implementado!!"); 
    }
    
    
}
