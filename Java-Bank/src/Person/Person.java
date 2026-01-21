package Person;

/**
 * Clase abstracta que representa a una persona dentro del sistema bancario.
 * Define el contrato base para todas las personas (usuarios y empleados) que
 * interactúan con el sistema.
 * Gestiona la información personal básica, credenciales y estado de actividad.
 *
 * @version 1.0
 * @see User
 * @see Employee
 */
public abstract class Person {

    /**
     * Nombre completo de la persona.
     */
    public String name = "";

    /**
     * Fecha de nacimiento en formato texto (dd/mm/yyyy).
     */
    public String birthDate = "";

    /**
     * Contraseña de acceso al sistema.
     */
    public String password = "";

    /**
     * Indica si la persona está activa en el sistema.
     */
    public boolean active = true;

    /**
     * Constructor base para crear una instancia de persona.
     * @param name      Nombre completo de la persona.
     * @param password  Contraseña de acceso.
     * @param birthDate Fecha de nacimiento en formato texto.
     */
    public Person(String name, String password, String birthDate) {
    }

    /**
     * Inicia el proceso de registro de una nueva persona en el sistema.
     * Las subclases deben implementar la lógica específica de registro según su
     * tipo.
     * @return Una nueva instancia de la persona registrada.
     */
    abstract Person register();

    /**
     * Valida si una contraseña cumple con las políticas de seguridad establecidas.
     * Las subclases definen los criterios específicos de validación.
     * @param password La contraseña a validar.
     * @return {@code true} si la contraseña es válida; {@code false} en caso
     *         contrario.
     */
    abstract boolean checkPassword(String password);

    /**
     * Valida si una fecha tiene el formato correcto y es lógicamente válida.
     * Las subclases implementan las reglas de validación específicas.
     * @param date La fecha a validar en formato texto.
     * @return {@code true} si la fecha es válida; {@code false} en caso contrario.
     */
    abstract boolean checkDate(String date);
}
