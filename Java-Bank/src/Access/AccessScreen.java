package Access;
import Account.BankAccount;
import Account.CreditAccount;
import Account.DebitAccount;
import Account.Transaction;
import Features.Store;
import Person.Employee;
import Person.Manager;
import Utils.Database;
import Person.User;

import java.io.*;
import java.util.Scanner;

import java.util.ArrayList;

/**
 * Gestiona las pantallas de acceso y menús principales del sistema bancario.
 * Controla el flujo de registro, autenticación y navegación por las
 * funcionalidades disponibles.
 * @version 1.0
 * @see User
 */
public class AccessScreen {
    /**
     * Identificador del usuario actualmente en proceso de login.
     */
    String DNI = "";
    /**
     * Objeto para gestionar la conexión con la Base de Datos.
     */
    Database db = new Database();
    /**
     * Lista de usuarios registrados en el sistema.
     */

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Employee> employees = new ArrayList<Employee>();
    ArrayList<Manager> managers = new ArrayList<Manager>();
    public ArrayList<DebitAccount> debitAccounts = new ArrayList<DebitAccount>();
    public ArrayList<CreditAccount> creditAccounts = new ArrayList<CreditAccount>();
    public ArrayList<Transaction> historial = new  ArrayList<Transaction>();
        /*Importante: la serializacion hace que se guarden los archivos en otro array list,
        este tenia como objeto para guardar otra cosa ademas de n
         */

    /**
     * Muestra el menú principal del sistema bancario.
     * Permite al usuario crear cuenta, iniciar sesión o cerrar la aplicación.
     * El menú se ejecuta en bucle hasta que el usuario selecciona salir.
     */
    public void inicio() {
        /*try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Java-Bank/data/users.dat"));
            int longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                users.add((User) input.readObject());
            }
            input.close();
            //^ Users en el array ^

            input = new ObjectInputStream(new FileInputStream("Java-Bank/data/employees.dat"));
            longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                employees.add((Employee) input.readObject());
            }
            input.close();
            // ^ Employees en el array ^

            input = new ObjectInputStream(new FileInputStream("Java-Bank/data/managers.dat"));
            longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                managers.add((Manager) input.readObject());
            }
            input.close();
            // ^ Managers en el array ^

            input = new ObjectInputStream(new FileInputStream("Java-Bank/data/debitAccounts.dat"));
            longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                debitAccounts.add((DebitAccount) input.readObject());
            }
            input.close();

            input = new ObjectInputStream(new FileInputStream("Java-Bank/data/creditAccounts.dat"));
            longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                creditAccounts.add((CreditAccount) input.readObject());
            }
            input.close();
            // ^ Cuentas en el array ^

            input = new ObjectInputStream(new FileInputStream("Java-Bank/data/transactions.dat"));
            longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                historial.add((Transaction) input.readObject());
            }
            input.close();
            // ^ Historial en el array ^
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            System.err.println(e.getMessage());
        }
        */
        menu();
    }

    public void menu() {

        int option = 0;
        Scanner sc = new Scanner(System.in);
        while (option != 2) {
            System.out.println("¡Bienvenido a JavaBank!");
            System.out.println("1. Entrar");
            System.out.println("2. Salir de la aplicación");
            System.out.println("Por favor, selecciona una opción (1 o 2)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                   /* System.out.println(users);
                    System.out.println(employees);
                    System.out.println(managers);
                    System.out.println(debitAccounts);
                    System.out.println(creditAccounts);
                    System.out.println(historial);

                    */
                    login();
                    break;
                case 2:
                   /* try {
                        System.out.println(users);
                        System.out.println(employees);
                        System.out.println(managers);
                        System.out.println(creditAccounts);
                        System.out.println(debitAccounts);
                        System.out.println(historial);

                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/users.dat"));
                        output.writeInt(users.size());
                        for (int i = 0; i < users.size(); i++) {
                            output.writeObject(users.get(i));
                        }
                        output.close();

                        output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/employees.dat"));
                        output.writeInt(employees.size());
                        for (int i = 0; i < employees.size(); i++) {
                            output.writeObject(employees.get(i));
                        }
                        output.close();

                        output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/managers.dat"));
                        output.writeInt(managers.size());
                        for (int i = 0; i < managers.size(); i++) {
                            output.writeObject(managers.get(i));
                        }
                        output.close();

                        output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/debitAccounts.dat"));
                        output.writeInt(debitAccounts.size());
                        for (int i = 0; i < debitAccounts.size(); i++) {
                            output.writeObject(debitAccounts.get(i));
                        }
                        output.close();

                        output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/creditAccounts.dat"));
                        output.writeInt(creditAccounts.size());
                        for (int i = 0; i < creditAccounts.size(); i++) {
                            output.writeObject(creditAccounts.get(i));
                        }
                        output.close();

                        output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/transactions.dat"));
                        output.writeInt(historial.size());
                        for (int i = 0; i < historial.size(); i++) {
                            output.writeObject(historial.get(i));
                        }
                        output.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println("Cerrando la aplicación...");
                    System.exit(0);
            }
        }

    }

    /**
     * Muestra el menú de operaciones bancarias para un usuario autenticado.
     * Permite crear cuentas, realizar depósitos, retiros, transferencias y
     * recargas.
     *
     * @param currentUser Usuario actualmente autenticado en el sistema.
     */
    public void MenuUser(User currentUser) {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        while (option != 7) {
            System.out.println("Menú usuario");
            System.out.println("¡Bienvenido " + currentUser.name + "!");
            System.out.println("1. Ingresar");
            System.out.println("2. Retirar");
            System.out.println("3. Transferir");
            System.out.println("4. Recargar SIM");
            System.out.println("5. Ver cuentas");
            System.out.println("7. JavaStore");
            System.out.println("8. Salir");
            System.out.println("Por favor, selecciona una opción:  (1, 2, 3, 4, 5, 6 o 7)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                int eleccion=0;
                BankAccount currentAccount = null;
                System.out.println("Seleccione entre cuentas de debito o credito (1: debito) (2: credito)");
                eleccion = sc.nextInt();
                ArrayList<DebitAccount> currentUserDebitAccount = new ArrayList<>();
                currentUserDebitAccount.add(new DebitAccount("","","", "",""));
                ArrayList<CreditAccount> currentUserCreditAccount = new ArrayList<>();
                currentUserCreditAccount.add(new CreditAccount("","","",0,0,"",""));


                if (eleccion == 1) {
                    System.out.println("Seleccione el id de su cuenta de debito");

                    for (int i = 1; i < debitAccounts.size(); i++) {
                        if (debitAccounts.get(i).getIdPropietario().equals(currentUser.DNI)) {
                            currentUserDebitAccount.add(debitAccounts.get(i));
                        }
                    }
                    for (int i = 1; i < currentUserDebitAccount.size(); i++) {
                        System.out.println((i)+ "- " + currentUserDebitAccount.get(i).toString());
                    }
                    int eleccionDebito=-1;
                    eleccionDebito = sc.nextInt();
                    BankAccount currentUserDebit = currentUserDebitAccount.get(eleccionDebito);
                    System.out.println("Seleccione la cantidad a ingresar: ");
                    int amount = sc.nextInt();

                    currentUserDebitAccount.get(eleccionDebito).deposit(amount, currentUserDebit);

                }else if (eleccion == 2) {
                    System.out.println("Seleccione el id de su cuenta de credito: ");

                    for (int i = 1; i < creditAccounts.size(); i++) {
                        if (creditAccounts.get(i).getIdPropietario().equals(currentUser.DNI)) {
                            System.out.println(creditAccounts.get(i).toString());
                            currentUserCreditAccount.add(creditAccounts.get(i));

                        }
                    }
                    for (int i = 1; i < currentUserCreditAccount.size(); i++) {
                        System.out.println((i)+ "- " + currentUserCreditAccount.get(i).toString());
                    }
                    int eleccionCredito=-1;
                    eleccionCredito = sc.nextInt();
                    BankAccount currentUserCredit = currentUserCreditAccount.get(eleccionCredito);
                    System.out.println("Seleccione la cantidad a ingresar: ");
                    int amount = sc.nextInt();

                    currentUserCreditAccount.get(eleccionCredito).deposit(amount, currentUserCredit);
                }
                break;
                case 2:

                    break;
                case 3:
                    System.out.println("No implementado");
                    break;
                case 4:
                    System.out.println("No implementado");
                    break;
                case 5:
                    System.out.println("V--Cuentas de Débito--V");
                    for (int i = 0; i < currentUser.bankAccounts.size(); i++) {
                        if (currentUser.bankAccounts.get(i) instanceof DebitAccount) {
                            System.out.println(currentUser.bankAccounts.get(i).toString());
                        }
                    }
                    System.out.println("^-----^-----^-----^");
                    System.out.println(" ");
                    System.out.println("V--Cuentas de Crédito--V");
                    for (int i = 0; i < currentUser.bankAccounts.size(); i++) {
                        if (currentUser.bankAccounts.get(i) instanceof CreditAccount) {
                            System.out.println(currentUser.bankAccounts.get(i).toString());
                        }
                    }
                    System.out.println("^-----^-----^-----^");
                    break;
                case 6:
                    Store tienda = new Store(1000000000, "", "", 0.0, "", false);
                    tienda.StoreInicio();
                    break;
                case 7:
                    menu();
                    return;
            }
        }
    }

    public void menuManager(Manager currentManager) {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        while (option != 8) {
            System.out.println("Menú Manager");
            System.out.println("¡Bienvenido " + currentManager.name + "!");
            System.out.println("1. Crear cuenta bancaria");
            System.out.println("2. Ingresar");
            System.out.println("3. Retirar");
            System.out.println("4. Transferir dinero");
            System.out.println("5. Recargar SIM");
            System.out.println("6. Desbloquear usuario");
            System.out.println("7. Ver historial de cuentas");
            System.out.println("8. Salir");
            System.out.println("Por favor, selecciona una opción:  (1, 2, 3, 4, 5, 6, 7 o 8)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Indique el ID del usuario");
                    sc.nextLine();
                    DNI = sc.nextLine();

                    User currentUser = null;
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).DNI.equals(DNI)) {
                            System.out.println(users.get(i));
                            System.out.println("¿Es esta la ID del cliente? Sí (S) / No (N)");
                            String confirmacion;
                            confirmacion = sc.nextLine();
                            if (confirmacion.equalsIgnoreCase("S")) {
                                currentUser = users.get(i);
                                System.out.println("Usuario seleccionado");
                                System.out.println(users.get(i));
                                System.out.println(currentUser);
                            }
                            break;
                        }
                    }
                    if (currentUser == null) {
                        System.out.println("El DNI no existe");
                    } else {
                        System.out.println("Seleccione 1, 2 o 3: Crear cuenta de debito(1) o crédito(2), atrás (3)");
                        int opcionTarjeta = sc.nextInt();
                        if (opcionTarjeta == 1) {
                            DebitAccount nuevaBankAccountdebit = new DebitAccount("", "", "", "", currentUser.DNI);
                            DebitAccount cuentaDebitoNueva = nuevaBankAccountdebit.createDebitAccount(currentUser);
                            debitAccounts.add(cuentaDebitoNueva);
                            System.out.println(cuentaDebitoNueva);
                        }
                        if(opcionTarjeta == 2){
                            CreditAccount cuentaCreditoNueva = new CreditAccount("", "", "",  0.0, 0.0, "", currentUser.DNI);
                            cuentaCreditoNueva.createCreditAccount(currentUser);
                            creditAccounts.add(cuentaCreditoNueva);
                            System.out.println(cuentaCreditoNueva);
                        }

                        if (opcionTarjeta == 3) {
                            return;
                        }
                    }

                    //bankAccount  newBA = new bankAccount(dummyBankAccount.getEntity(), dummyBankAccount.getOffice(),  dummyBankAccount.calcDC(), null, null, null);
                    break;
                case 2:


                    login();
                    break;
                case 3:
                    return;
                case 4:
                    return;
                case 5:
                    return;
                case 6:
                    System.out.println("Desbloquear usuario: ");
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).active == false){
                            System.out.println(users.get(i).toString());
                        }
                    }
                    System.out.println("Seleccione el usuario que quiera desbloquear: ");
                    sc.nextLine();
                    String dni = sc.nextLine();
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).DNI.equals(dni)){
                            currentUser= users.get(i);
                            currentUser.active=true;
                            System.out.println("¡Usuario activado!");
                            break;
                        }
                    }
                    break;
                case 7:
                    System.out.println("Ingrese el ID del usuario del que desea ver el historial: ");
                    sc.nextLine();
                    for(int i = 0; i < historial.size(); i++) {
                        if (historial.get(i).idAsociado.equals(DNI)) {
                            System.out.println(historial.get(i).toString());
                        }
                    }
                case 8:
                    return;
                default:
                    System.out.println("Elija una opción del 1-7");
            }
        }
    }

    public void menuEmployee(Employee currentEmployee) {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        while (option != 7){
            System.out.println("Menu Empleado");
            System.out.println("¡Bienvenido " + currentEmployee.name + "!");
            System.out.println("1. Crear cuenta bancaria");
            System.out.println("2. Ingresar");
            System.out.println("3. Retirar");
            System.out.println("4. Transferir");
            System.out.println("5. Recargar SIM");
            System.out.println("6. Desbloquear usuario");
            System.out.println("7. Salir");
            System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5, 6 or 7)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Indique el id del user");
                    sc.nextLine();
                    DNI = sc.nextLine();

                    User currentUser = null;
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).DNI.equals(DNI)) {
                            System.out.println(users.get(i));
                            System.out.println("¿Es esta la id del cliente? Sí (S) / No (N)");
                            String confirmacion;
                            confirmacion = sc.nextLine();
                            if (confirmacion.equalsIgnoreCase("S")) {
                                currentUser = users.get(i);
                                System.out.println("Usuario seleccionado");
                                System.out.println(users.get(i));
                                System.out.println(currentUser);
                            }
                            break;
                        }
                    }
                    if (currentUser == null) {
                        System.out.println("El DNI no existe");
                    } else {
                        System.out.println("Seleccione 1, 2 o 3 :Crear cuenta de debito(1) o crédito(2), atras (3)");
                        int opcionTarjeta = sc.nextInt();
                        if (opcionTarjeta == 1) {
                            DebitAccount nuevaBankAccountdebit = new DebitAccount("", "", "", "", currentUser.DNI);
                            DebitAccount cuentaDebitoNueva = nuevaBankAccountdebit.createDebitAccount(currentUser);
                            debitAccounts.add(cuentaDebitoNueva);
                            System.out.println(cuentaDebitoNueva);
                        }
                        if(opcionTarjeta == 2){
                            CreditAccount cuentaCreditoNueva = new CreditAccount("", "", "",  0.0, 0.0, "", currentUser.DNI);
                            cuentaCreditoNueva.createCreditAccount(currentUser);
                            creditAccounts.add(cuentaCreditoNueva);
                            System.out.println(cuentaCreditoNueva);
                        }

                        if (opcionTarjeta == 3) {
                            return;
                        }
                    }

                    //bankAccount  newBA = new bankAccount(dummyBankAccount.getEntity(), dummyBankAccount.getOffice(),  dummyBankAccount.calcDC(), null, null, null);
                    break;
                case 2:


                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                  /*  System.out.println("V--Cuentas de Débito--V");
                    for (int i = 0; i < debitAccounts.size(); i++) {
                        System.out.println(debitAccounts.get(i).toString());
                    }
                    System.out.println("^-----^-----^-----^");
                    System.out.println(" ");
                    System.out.println("V--Cuentas de Crédito--V");
                    for (int i = 0; i < creditAccounts.size(); i++) {
                        System.out.println(creditAccounts.get(i).toString());
                    }
                    System.out.println("^-----^-----^-----^");*/ // Sirve para ver todas las cuentas, sin importar usuarios
                    break;
                case 6:
                    System.out.println("Desbloquear usuario: ");
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).active == false){
                            System.out.println(users.get(i).toString());
                        }
                    }
                    System.out.println("Seleccionar usuario para desbloquear: ");
                    sc.nextLine();
                    String dni = sc.nextLine();
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).DNI.equals(dni)){
                            users.get(i).active = true;
                        }
                    }
                    break;
                case 7:
                    return;
                case 8:
                    menu();
                default:
                    System.out.println("Error, seleccione una opción del 1 al 7");

            }
        }
    }

    /**
     * Gestiona el proceso de inicio de sesión de un usuario.
     * Solicita ID y contraseña, validando credenciales y estado de la cuenta.
     * Bloquea la cuenta tras 3 intentos fallidos de contraseña.
     * Si la cuenta está bloqueada, se informa al usuario que contacte con un
     * administrador.
     */

    public void login() {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        while (option != 4) {
            System.out.println("Por favor, seleccione su perfil:");
            System.out.println("1. Cliente");
            System.out.println("2. Manager");
            System.out.println("3. Empleado");
            System.out.println("4. Salir");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Por favor, ingrese su ID de usuario: ");
                    DNI = sc.nextLine();
                    int tries = 0;
                    boolean loginCorrecto = false;

                    while (tries < 3 && !loginCorrecto) {
                        System.out.println("Por favor, ingrese su contraseña: ");
                        String pass = sc.nextLine();
                        User currentUser = db.loginUser(DNI, pass);

                        if (currentUser == null) {
                            tries++;
                            System.out.println("Credenciales incorrectas. Intento " + tries + " de 3.");
                            if (tries == 3) {
                                System.out.println("Has agotado los intentos. Por seguridad, contacte con el banco.");
                            }
                        } else {
                            if (!currentUser.active) {
                                System.out.println("La cuenta asociada a este ID está bloqueada.\nContacte con el personal del banco");
                                tries = 3;
                            } else {
                                System.out.println("Inicio sesión completado. ¡Bienvenido " + currentUser.name + "!");

                                currentUser.bankAccounts = db.getUserAccounts(currentUser.DNI);

                                MenuUser(currentUser);
                                loginCorrecto = true;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Por favor, ingrese su ID de Manager (DNI): ");
                    DNI = sc.nextLine();
                    int triesManager = 0;
                    boolean loginManagerOk = false;

                    while (triesManager < 3 && !loginManagerOk) {
                        System.out.println("Por favor, ingrese su contraseña: ");
                        String pass = sc.nextLine();
                        Manager currentManager = db.loginManager(DNI, pass);

                        if (currentManager == null) {
                            triesManager++;
                            System.out.println("Credenciales incorrectas. Intento " + triesManager + " de 3.");
                            if (triesManager == 3) {
                                System.out.println("Cuenta bloqueada por seguridad.");
                            }
                        } else {
                            if (!currentManager.active) {
                                System.out.println("Esta cuenta de Manager está desactivada.");
                                triesManager = 3;
                            } else {
                                System.out.println("Inicio de sesión correcto.");
                                menuManager(currentManager);
                                loginManagerOk = true;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Por favor, ingrese su ID de empleado (DNI): ");
                    DNI = sc.nextLine();
                    int triesEmp = 0;
                    boolean loginEmpOk = false;
                    while (triesEmp < 3 && !loginEmpOk) {
                        System.out.println("Por favor, ingrese su contraseña: ");
                        String pass = sc.nextLine();
                        Employee currentEmployee = db.loginEmployee(DNI, pass);
                        if (currentEmployee == null) {
                            triesEmp++;
                            System.out.println("Credenciales incorrectas. Intento " + triesEmp + " de 3.");
                            if (triesEmp == 3) System.out.println("Cuenta bloqueada.");
                        } else {
                            if (!currentEmployee.active) {
                                System.out.println("Esta cuenta de Empleado está desactivada.");
                                triesEmp = 3;
                            } else {
                                System.out.println("Inicio de sesión correcto.");
                                menuEmployee(currentEmployee);
                                loginEmpOk = true;
                            }
                        }
                    }
                    break;
                case 4:
                    menu();
                    break;
            }
        }
    }
}