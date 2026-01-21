package Exceptions;

/**
 * Excepción lanzada cuando se espera una entrada numérica
 * pero se recibe un tipo de dato diferente o no convertible a número.
 * @version 1.0
 */
public class InputNumberException extends java.lang.Exception {
    /**
     * Crea una nueva instancia de la excepción con un mensaje predefinido.
     * El mensaje indica que se esperaba un número pero se recibió algo diferente.
     */
    public InputNumberException() {
        super("Expected a number, received something different.");
    }
}
