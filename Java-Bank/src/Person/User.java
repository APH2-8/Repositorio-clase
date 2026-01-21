package Person;

import Account.BankAccount;

import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa a un usuario dentro del sistema bancario.
 * Esta clase es responsable de mantener la información personal del usuario,
 * sus credenciales de acceso y la lista de cuentas bancarias asociadas
 * ({@code BankAccount}).
 * Además, gestiona el estado de actividad del usuario en el sistema.
 *
 * @version 1.0
 * @see Person
 * @see Account.BankAccount
 */
public class User extends Person {
    public String id = "";
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    /**
     * Crea una nueva instancia de {@code User} con la información básica
     * proporcionada. Inicializa al usuario como activo por defecto y asigna su identificador
     * único.
     *
     * @param name      Nombre completo del usuario.
     * @param password  Contraseña inicial del usuario.
     * @param birthDate Fecha de nacimiento en formato texto.
     * @param id        Identificador único del usuario.
     */
    public User(String name, String password, String birthDate, String id) {
        super(name, password, birthDate);
        this.active = true;
        this.id = id;
    }

    /**
     * Inicia el proceso de registro de un nuevo usuario.
     * Este método solicita al usuario que introduzca su nombre, contraseña y fecha
     * de nacimiento.
     * Realiza validaciones en bucle hasta que la contraseña y la fecha cumplan con
     * los requisitos de seguridad y formato.
     * @return Una nueva instancia de {@code User} con los datos validados y
     * confirmados.
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
        checkP = checkPassword(password);
        while (!checkP) {
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            checkP = checkPassword(password);
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
        id = id + 1;
        User newUser = new User(name, password, birthdate, id);
        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + id);
        return newUser;
    }

    /**
     * Valida si una fecha proporcionada en formato texto correcta.
     * Verifica el formato "dd/mm/yyyy" y aplica reglas de negocio para:
     * <ul>
     * <li>Días válidos según el mes (30 o 31 días).</li>
     * <li>Manejo de años bisiestos para febrero (29 días).</li>
     * <li>Rango de años permitido (desde 1900 hasta el año actual).</li>
     * </ul>
     * @param date La fecha en formato "dd/mm/yyyy" a validar.
     * @return {@code true} si la fecha es válida y existe en el calendario;
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean checkDate(String date) {

        String[] myArray = date.split("/");
        int element1 = Integer.parseInt(myArray[0]);
        int element2 = Integer.parseInt(myArray[1]);
        int element3 = Integer.parseInt(myArray[2]);
        int year = Year.now().getValue();

        if (element1 > 32 || element1 < 0) { // check if the day is between 1 and 31
            return false;
        }
        if (element2 == 4 || element2 == 6 || element2 == 9 || element2 == 11) { // check if it is a 30-day month
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
     * Verifica si una contraseña cumple con las políticas de seguridad estrictas.
     * La contraseña debe satisfacer las siguientes condiciones:
     * <ul>
     * <li>Al menos 8 caracteres de longitud.</li>
     * <li>Contener al menos un dígito.</li>
     * <li>Contener al menos una letra minúscula y una mayúscula.</li>
     * <li>Contener al menos un carácter especial (no alfanumérico).</li>
     * <li>No contener espacios en blanco.</li>
     * </ul>
     * Utiliza una expresión regular compleja para validar estos requisitos en una
     * sola evaluación.
     *
     * @param password La contraseña candidata a validar.
     * @return {@code true} si la contraseña es segura; {@code false} si falla
     *         alguna condición.
     */
    @Override
    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{8,}";
        if (password.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

}
