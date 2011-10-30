/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.ProductoComponente;

public class ModeloTablaListarProductosComponentes extends ModeloTabla {

    public ModeloTablaListarProductosComponentes() {

        super("Código", "Nombre", "Categoria", "Ubicacion en almacén");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getListaElementos() == null) {
            return null;
        }
        ProductoComponente pc = (ProductoComponente) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pc.getCodigo();
            case 1:
                return pc.getNombre();
            case 2:
                return pc.getCategoria();
            case 3:
                return pc.getUbicacionEnAlmacen();
            default:
                return "";
        }

    }

    public void quitarArticuloLista(ProductoComponente pc) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((ProductoComponente) getListaElementos().get(i)).getCodigo().equals(pc.getCodigo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }

    public ProductoComponente buscarProductoComponente(String nombre) {
        for (Object pc : getListaElementos()) {
            if (((ProductoComponente) pc).getNombre().equals(nombre)) {
                return (ProductoComponente) pc;
            }
        }
        return null;
    }
}
