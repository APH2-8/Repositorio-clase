package Person;

import Account.BankAccount;

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
    /**
     * Identificador único del empleado (inmutable).
     */
    final int employeeId;

    /**
     * Contador estático para generar IDs de usuarios.
     * Se incrementa con cada nuevo registro.
     */
    public static int id = 0;

    /**
     * Crea una nueva instancia de {@code Employee}.
     * 
     * @param id         Identificador del empleado.
     * @param name       Nombre completo del empleado.
     * @param password   Contraseña de acceso.
     * @param birthDate  Fecha de nacimiento en formato texto.
     * @param employeeId Identificador único numérico del empleado.
     */
    public Employee(String id, String name, String password, String birthDate, int employeeId) {
        super(name, password, birthDate);
        this.employeeId = employeeId;
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
        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        chackP=checkPassword(password);
        while (!checkP) {
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            chackP= checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);
        while (!checkD) {
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }
        id += 1;
        String newId = createId(id);
        User newUser = new User(name, password, birthdate, newId);
        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + newId);
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

        if (element1 > 32 || element1 < 0) {// check if the day is between 1 and 31
            return false;
        }
        if (element2 == 4 || element2 == 6 || element2 == 9 || element2 == 11) {// check if it is a 30-day month
            if (element1 > 30) {
                return false;
            }
        }
        if (element2 == 2) { // check if february
            if (element3 % 4 == 0) {
                if (element1 > 29) {// leap year
                    return false;
                }
            } else {
                if (element1 > 28) {// normal year
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

    /**
     * Crea una nueva cuenta bancaria.
     * @return Una nueva instancia de {@code BankAccount}, o {@code null} si aún no está implementado.
     */
    public BankAccount createBankAccount() {
        return null;
    }
}
