/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOCGI;

/**
 *
 * @author rustu
 */
public class ModeloTablaPantallaGestionDeInventariosParaTodosLosProductos extends ModeloTabla{

    public ModeloTablaPantallaGestionDeInventariosParaTodosLosProductos() {

        super("Código", "Nombre", "CGI");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        DTOCGI dto = (DTOCGI) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return dto.getArticulo().getCodigo();
            case 1:
                return dto.getArticulo().getNombre();
            case 2:
                if(dto.getCgi() == (float)-1){
                    return "";
                }else{
                    return dto.getCgi();
                }
            default:
                return "";
        }

    }

    
}
