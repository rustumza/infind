/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOPromedioPonderadoMovil;

/**
 *
 * @author rustu
 */
public class ModeloTablaPromedioPonderadoMovil extends ModeloTabla{

       public ModeloTablaPromedioPonderadoMovil() {

        super("Periodo", "valor", "valor real", "error");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        DTOPromedioPonderadoMovil dto = (DTOPromedioPonderadoMovil) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return dto.getDemanda().getPeriodo();
            case 1:
                return dto.getDemandaPronosticada();
            case 2:
                if(dto.getDemanda().getDemandaHistorica()== (float)0){
                    return "";
                }else{
                    return dto.getDemanda().getDemandaHistorica();
                }
            case 3:
                if(dto.getError() == (float)0){
                    return "";
                }else{
                    return dto.getError();
                }
            default:
                return "";
        }

    }

    
}
