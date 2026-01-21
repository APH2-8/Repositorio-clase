package Exceptions;

/**
 * Excepción lanzada cuando se espera una entrada de tipo cadena de texto
 * pero se recibe un tipo de dato diferente o inválido.
 * 
 * @version 1.0
 */
public class InputStringException extends java.lang.Exception {
    /**
     * Crea una nueva instancia de la excepción con un mensaje predefinido.
     * El mensaje indica que se esperaba una cadena pero se recibió algo diferente.
     */
    public InputStringException() {
        super("Expected a string, received something different.");
    }
}
