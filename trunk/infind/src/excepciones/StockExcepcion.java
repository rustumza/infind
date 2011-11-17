/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package excepciones;

/**
 *
 * @author rustu
 */
public class StockExcepcion extends Exception{


    private final int codigo;

    /**Recibe el código de error, para mostrar un mensaje de error
     *
     * @param codigo Indica el motivo del error:
     **
     **          0 - "Error: no existe el stock"
     **          1 - "Stock por llegar menor que el stock que entra"
     **          2 - "Stock insuficiente"
     **          3 - "Stock disponible insuficiente"
     **          4 - "No se ha iniciado el manejo de stock"
     **
     ** 
     */
    public StockExcepcion(int codigo) {
        super();
        this.codigo = codigo;
    }
    /**No recibe codigo de error, solo muestra el mensaje de error por default: "Error: no se ha encontrado ningun cliente"
     *
     */
    public StockExcepcion(){
        super();
        codigo = 0;
    }

    /**Devuelve el mensaje de error
     *
     * @return mensaje de error predefinido, tipo String
     */
    @Override
    public String getMessage() {
        if(codigo == 1){
            return "Stock por llegar menor que el stock que entra";
        }else if(codigo == 2){
            return "Stock insuficiente";
        }else if(codigo == 3){
            return "Stock disponible insuficiente";
        }else if(codigo == 4){
            return "No se ha iniciado el manejo de stock";            
        }else return "Error: no existe el stock";
    }

    /**Devuelve el código de error
     *
     * @return código de error, tipo int
     */
    public int getCodigo() {
        return codigo;
    }


}
