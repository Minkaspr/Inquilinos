package util;

import java.util.ArrayList;
import java.util.List;

public class DeString {

    /**
     * Convierte una cadena en un entero.
     *
     * @param s la cadena a convertir
     * @return el entero resultante, o null si la cadena no puede ser convertida
     * en un entero
     */
    public static Integer aInteger(String s) {
        Integer result = null;
        if (s != null) {
            try {
                result = Integer.valueOf(s);
            } catch (NumberFormatException e) {
                // Si la cadena no puede ser convertida en un entero, se devuelve null
            }
        }
        return result;
    }

    /**
     * Convierte una cadena en un doble.
     *
     * @param s la cadena a convertir
     * @return el doble resultante, o null si la cadena no puede ser convertida
     * en un doble
     */
    public static Double aDouble(String s) {
        Double result = null;
        if (s != null) {
            try {
                result = Double.valueOf(s);
            } catch (NumberFormatException e) {
                // Si la cadena no puede ser convertida en un doble, se devuelve null
            }
        }
        return result;
    }

    /**
     * Convierte una cadena de identificadores separados por comas en una lista
     * de enteros.
     *
     * @param _ids la cadena de identificadores separados por comas
     * @return una lista de enteros, o null si alguno de los identificadores no
     * puede ser convertido en un entero
     */
    public static List<Integer> ids(String _ids) {
        // Creamos una nueva lista vacía para almacenar los enteros resultantes
        List<Integer> list = new ArrayList<>();

        // Verificamos si la cadena de entrada no es nula
        if (_ids != null) {
            // Dividimos la cadena en un arreglo de cadenas, utilizando la coma como separador
            String[] v_ids = _ids.split(",");

            // Recorremos el arreglo de cadenas resultante
            for (String v_id : v_ids) {
                // Convertimos cada cadena en un entero utilizando el método aInteger
                Integer id = aInteger(v_id);

                // Si la cadena pudo ser convertida en un entero, agregamos el entero a la lista
                if (id != null) {
                    list.add(id);
                } else {
                    // Si la cadena no pudo ser convertida en un entero, asignamos null a la lista y salimos del bucle
                    list = null;
                    break;
                }
            }
        }

        // Devolvemos la lista resultante
        return list;
    }

    /**
     * Convierte un arreglo de cadenas en un arreglo de enteros.
     *
     * @param s el arreglo de cadenas a convertir
     * @return un arreglo de enteros, o null si alguna de las cadenas no puede
     * ser convertida en un entero
     */
    public static Integer[] aIntegerL(String[] s) {
        // Creamos una variable para almacenar el resultado
        Integer[] result = null;

        // Verificamos si el arreglo de entrada no es nulo y tiene al menos un elemento
        if ((s != null) && (s.length > 0)) {
            // Creamos un nuevo arreglo de enteros del mismo tamaño que el arreglo de entrada
            result = new Integer[s.length];

            // Recorremos el arreglo de entrada
            for (int i = 0; i < result.length; i++) {
                try {
                    // Convertimos cada cadena en un entero utilizando el método valueOf de la clase Integer
                    result[i] = Integer.valueOf(s[i]);
                } catch (NumberFormatException ex) {
                    // Si alguna cadena no puede ser convertida en un entero, asignamos null al resultado y salimos del bucle
                    result = null;
                    break;
                }
            }
        }

        // Devolvemos el resultado
        return result;
    }

    /**
     * Convierte un arreglo de cadenas en un arreglo de dobles.
     *
     * @param s el arreglo de cadenas a convertir
     * @return un arreglo de dobles, o null si alguna de las cadenas no puede
     * ser convertida en un doble
     */
    public static Double[] aDoubleL(String[] s) {
        // Creamos una variable para almacenar el resultado
        Double[] result = null;

        // Verificamos si el arreglo de entrada no es nulo y tiene al menos un elemento
        if ((s != null) && (s.length > 0)) {
            // Creamos un nuevo arreglo de dobles del mismo tamaño que el arreglo de entrada
            result = new Double[s.length];

            // Recorremos el arreglo de entrada
            for (int i = 0; i < result.length; i++) {
                try {
                    // Convertimos cada cadena en un doble utilizando el método valueOf de la clase Double
                    result[i] = Double.valueOf(s[i]);
                } catch (NumberFormatException ex) {
                    // Si alguna cadena no puede ser convertida en un doble, asignamos null al resultado y salimos del bucle
                    result = null;
                    break;
                }
            }
        }

        // Devolvemos el resultado
        return result;
    }
}
