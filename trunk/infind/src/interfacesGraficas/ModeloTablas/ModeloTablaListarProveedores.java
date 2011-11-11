/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloTablas;

import Entidades.MateriaPrima;
import Entidades.Proveedor;



/**
 *
 * @author eduardo
 */
public class ModeloTablaListarProveedores extends ModeloTabla{



    public ModeloTablaListarProveedores() {

        super("Nombre", "Direccion", "Correo", "Telefono");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        Proveedor proveedor = (Proveedor) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return proveedor.getNombre();
            case 1:
                return proveedor.getDireccion();
            case 2:
                return proveedor.getCorreoElectronico();
            case 3:
                return proveedor.getTelefono();
            default:
                return "";
        }

    }



     public void quitarArticuloLista(Proveedor proveedor) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((Proveedor) getListaElementos().get(i)).getNombre().equals(proveedor.getNombre())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public Proveedor buscarProveedor (String nombre){
         for (Object proveedor : getListaElementos()) {
             if (((Proveedor) proveedor).getNombre().equals(nombre)) {
                 return (Proveedor) proveedor;
             }
        }
         return null;
     }

}
