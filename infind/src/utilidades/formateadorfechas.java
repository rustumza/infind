/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author eduardo
 */
public class formateadorfechas {

    private static SimpleDateFormat format_dd_MM_yyyy = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat formatMySQLyyyyMMdd = new  SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat format_yyyy_MM_dd = new SimpleDateFormat("yyyy/MM/dd");


    /**
     * @return the format_dd_MM_yyyy
     */
    public static SimpleDateFormat getFormat_dd_MM_yyyy() {
        return format_dd_MM_yyyy;
    }

    public static SimpleDateFormat getFormat_yyyy_MM_dd() {
        return format_yyyy_MM_dd;
    }

    /**
     * @return the formatMySQLyyyyMMdd
     */
    public static SimpleDateFormat getFormatMySQLyyyyMMdd() {
        return formatMySQLyyyyMMdd;
    }

    /**
     * Regresa una fecha para un String dado
     * @param strFecha
     * puede ser en formato dd/MM/yyyy, yyyy/MM/dd, dd-MM-yyyy, yyyy-MM-dd
     * @return regresa la fecha ingresada como String en Date
     * @throws ParseException 
     */
    public static Date StringAFecha(String strFecha) throws ParseException {

        Date nuevaFecha = null;

        if (strFecha.split("-").length != 1) {//la fecha ingresada tiene separador "-"
            if (strFecha.split("-")[0].length() == 4) {//la fecha ingresada tiene formato yyyy-MM-dd
                nuevaFecha = formatMySQLyyyyMMdd.parse(strFecha);
            }else{//la fecha ingresada tiene formato dd-MM-yyyy
                nuevaFecha = format_dd_MM_yyyy.parse(strFecha);
            }
        } else if (strFecha.split("/").length !=1) {//la fecha ingresada tiene separador "/"
            if(strFecha.split("/")[0].length() == 4){//la fecha ingresada tiene formatoo yyyy/MM/dd
                nuevaFecha = format_yyyy_MM_dd.parse(strFecha);
            }else{//la fecha ingresada tiene formato dd/MM/yyyy
                nuevaFecha = format_dd_MM_yyyy.parse(strFecha);
            }
        }

        return nuevaFecha;
    }

    public static String formatearAMySql(Date fecha){

        String fechaFormateada = formatMySQLyyyyMMdd.format(fecha);

        return  fechaFormateada;

    }

    /**
     * Compara 2 fechas ingresadas
     * @return Regresa -1 si fecha1 es  mayor a fecha2. Regresa 1 si fecha2 es mayor a fecha1. Regresa 0 si son iguales
     * 
     * 
     */
    public static int compararFechas(Date fecha1, Date fecha2){
        int resultado = 0;
        if(fecha1.getYear() < fecha2.getYear()){
            resultado = 1;
        }else if(fecha1.getYear() > fecha2.getYear()){
            resultado = -1;
        }else if(fecha1.getYear() == fecha2.getYear()){
            if(fecha1.getMonth() < fecha2.getMonth()){
                resultado = 1;
            }else if(fecha1.getMonth() > fecha2.getMonth()){
                resultado = -1;
            }else if(fecha1.getMonth() == fecha2.getMonth()){
                if(fecha1.getDay() < fecha2.getDay()){
                    resultado = 1;
                }else if(fecha1.getDay() > fecha2.getDay()){
                    resultado = -1;
                }else if(fecha1.getDay() == fecha2.getDay()){
                    resultado = 0;
                }
            }
        }
        return resultado;
    }
    
    
    public static String fechaAStringDDMMAAAA(Date fecha){
        if(fecha == null)
            return "";
        else
            return format_dd_MM_yyyy.format(fecha);
    }
}


