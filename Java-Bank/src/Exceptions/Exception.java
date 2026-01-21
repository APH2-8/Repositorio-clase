package Exceptions;

/**
 * Excepción genérica personalizada del sistema bancario.
 * Permite crear excepciones con mensajes personalizados para situaciones no
 * cubiertas por las excepciones específicas.
 * @version 1.0
 */
public class Exception extends java.lang.Exception {

    /**
     * Crea una nueva excepción con un mensaje personalizado.
     * @param message Descripción detallada del error ocurrido.
     */
    public Exception(String message) {
        super(message);
    }
}
