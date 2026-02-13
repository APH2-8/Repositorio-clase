package Person;

import Account.BankAccount;

import java.io.Serializable;
import java.time.Year;
import java.util.Scanner;

/**
 * Representa a un empleado del sistema bancario.
 * Los empleados tienen privilegios para registrar nuevos usuarios en el sistema
 * y gestionar la creación de cuentas bancarias.
 * @version 1.0
 * @see Person
 * @see User
 */
public class Employee extends Person {
    private static final long serialVersionUID = 22L;
    int employeeID;
    public int id;
    public boolean active;

    public Employee(String DNI, String name, String password, String birthDate, int employeeID) {
        super(DNI, name, password, birthDate);
        this.employeeID = employeeID;
        this.active=true;
    }

    @Override
    public String toString() {
        return " DNI: " + this.DNI + ", Nombre: " + this.name + ", Contraseña: " + this.password + ", Fecha de cumpleaños: " + this.birthDate + ", ID Administrativo: " + this.employeeID + ", Cuenta activa: " + this.active;
    }

    /**
     * Registra un nuevo usuario en el sistema a través de interacción por consola.
     * Solicita nombre, contraseña y fecha de nacimiento, validando cada dato.
     * Genera automáticamente un ID único para el nuevo usuario.
     * 
     * @return Una nueva instancia de {@code User} con los datos validados.
     * @see #createId(int)
     * @see #checkPassword(String)
     * @see #checkDate(String)
     */
    @Override
    public User register() {
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP = false, checkD = false;
        System.out.println("Introduzca nombre y apellidos: ");
        name = sc.nextLine();

        System.out.println("Introduzca su contraseña: ");
        password = sc.nextLine();
        checkP=checkPassword(password);
        while (!checkP) {
            System.out.println("Contraseña con formato incorrecto.");
            System.out.println("La contraseña debe contener:");
            System.out.println("* 8 dígitos");
            System.out.println("* 1 mayúscula");
            System.out.println("* 1 minúscula");
            System.out.println("* 1 número");
            System.out.println("* 1 caracter especial");
            password = sc.nextLine();
            checkP= checkPassword(password);
        }

        System.out.println("Introduzca su fecha de nacimiento (dd/mm/yyyy): ");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);
        while (!checkD) {
            System.out.println("Fecha incorrecta, inténtelo de nuevo");
            System.out.println("Recuente usar el siguiente formato: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }
        id += 1;
        String newId = createId(id);
        User newUser = new User(name, password, birthdate, newId);
        System.out.println("El resgistro se ha completado");
        System.out.println("Tus datos: ");
        System.out.println("Nombre: " + name);
        System.out.println("Fecha de nacimiento: " + birthdate);
        System.out.println("Contraseña: " + password);
        System.out.println("ID: " + newId);
        return newUser;
    }


    /**
     * Valida si una contraseña cumple con políticas de seguridad.
     * Requiere caracteres especiales específicos: {@code @#$%^&+=}
     * La contraseña debe contener:
     * <ul>
     * <li>Al menos 8 caracteres de longitud.</li>
     * <li>Al menos un dígito.</li>
     * <li>Al menos una letra minúscula y una mayúscula.</li>
     * <li>Al menos uno de estos caracteres especiales: @#$%^&+=</li>
     * <li>Sin espacios en blanco.</li>
     * </ul>
     * 
     * @param password La contraseña candidata a validar.
     * @return {@code true} si la contraseña es segura; {@code false} en caso
     *         contrario.
     */
    @Override
    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (password.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si una fecha proporcionada es correcta.
     * Acepta múltiples separadores (coma, punto, espacio) además de barra.
     * Verifica el formato y aplica las mismas reglas de negocio que {@code User}:
     * <ul>
     * <li>Días válidos según el mes (30 o 31 días).</li>
     * <li>Manejo de años bisiestos para febrero (29 días).</li>
     * <li>Rango de años permitido (desde 1900 hasta el año actual).</li>
     * </ul>
     * 
     * @param date La fecha a validar en formato "dd[separador]mm[separador]yyyy".
     * @return {@code true} si la fecha es válida; {@code false} en caso contrario.
     */
    @Override
    public boolean checkDate(String date) {
        String regex = "[,\\.\\s]";
        String[] myArray = date.split(regex);
        int element1 = Integer.parseInt(myArray[0]);
        int element2 = Integer.parseInt(myArray[1]);
        int element3 = Integer.parseInt(myArray[2]);
        int year = Year.now().getValue();

        if (element1 > 32 || element1 < 0) { // check if the day is between 1 and 31
            return false;
        }
        if (element2 == 4 || element2 == 6 || element2 == 9 || element2 == 11) {// check if it is a 30-day month
            if (element1 > 30) {
                return false;
            }
        }
        if (element2 == 2) {
            if (element3 % 4 == 0) {
                if (element1 > 29) {
                    return false;
                }
            } else {
                if (element1 > 28) {
                    return false;
                }
            }
        }
        if (element3 < 1900 || element3 > year) {
            return false;
        }
        return true;
    }

    /**
     * Genera un identificador de usuario formateado con ceros a la izquierda.
     * El ID resultante tiene una longitud fija de 8 caracteres.
     * @param id Número de identificación base.
     * @return Cadena de texto con el ID formateado (8 caracteres).
     */
    public String createId(int id) {
        String newId = "";
        for (int i = String.valueOf(id).length(); i < 8; i++) {
            newId = "0" + newId;
        }
        return newId;
    }
}
