/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author rustu
 */
public class OrdenDeFabricacionExeption extends Exception{
     
    
    private final int codigo;
    private final String codigoArticuloQueNoHayStock;
    

    /**Recibe el código de error, para mostrar un mensaje de error
     *
     * @param codigo Indica el motivo del error:
     **
     **          default - "Error: en la orden de fabricacion"
     **          1 - "Hay ordenes de fabricación que aún no estan finalizadas";
     **          2 - "Hay ordenes de compra que aún no están finalizadas"     
     **          3 - "No hay stock suficiente del articulo " + codigoArticuloQueNoHayStock 
     **          4 - "Esta orden pertenece a otra orden de fabricacion que esta estado pendiente"
     **
     ** 
     */
    public OrdenDeFabricacionExeption(int codigo) {
        super();
        this.codigo = codigo;
        this.codigoArticuloQueNoHayStock="";
    }
    
    public OrdenDeFabricacionExeption(int codigo, String codigoArticulo) {
        super();
        this.codigo = codigo;
        this.codigoArticuloQueNoHayStock = codigoArticulo;
    }
    
    /**No recibe codigo de error, solo muestra el mensaje de error por default: "Error: no se ha encontrado ningun cliente"
     *
     */
    public OrdenDeFabricacionExeption(){
        super();
        codigo = 0;
        this.codigoArticuloQueNoHayStock="";
    }

    /**Devuelve el mensaje de error
     *
     * @return mensaje de error predefinido, tipo String
     */
    @Override
    public String getMessage() {
        if(codigo == 1){
            return "Hay ordenes de fabricación que aún no estan finalizadas";
        }else if(codigo == 2){
            return "Hay ordenes de compra que aún no están finalizadas";
        }else if(codigo == 3){
            return "No hay stock suficiente del articulo " + codigoArticuloQueNoHayStock ;
        }else if(codigo == 4){
            return "Esta orden pertenece a otra orden de fabricacion que esta estado pendiente";
        }else return "Error: en la orden de fabricacion";
    }

    /**Devuelve el código de error
     *
     * @return código de error, tipo int
     */
    public int getCodigo() {
        return codigo;
    }

}
