package Access;
import Account.BankAccount;
import Account.CreditAccount;
import Account.DebitAccount;
import Person.Employee;
import Person.Manager;
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
     * Lista de usuarios registrados en el sistema.
     */
    /**
     * Scanner para lectura de entrada del usuario.
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Identificador del usuario actualmente en proceso de login.
     */
    String DNI = "";


    /**
     * Lista de usuarios registrados en el sistema.
     */

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Employee> employees = new ArrayList<Employee>();
    ArrayList<Manager> managers = new ArrayList<Manager>();
    public ArrayList<DebitAccount> genAccounts = new ArrayList<DebitAccount>();
        /*Importante: la serializacion hace que se guarden los archivos en otro array list,
        este tenia como objeto para guardar otra cosa ademas de n
         */
    /**
     * Usuario dummy utilizado para acceder a métodos de registro.
     */
    User dummyUser = new User("", "", "", "");
    Employee dummyEmployee = new Employee("", "", "", "", 0);
    Manager dummyManager = new Manager("", "", "", "", 0);

    /**
     * Muestra el menú principal del sistema bancario.
     * Permite al usuario crear cuenta, iniciar sesión o cerrar la aplicación.
     * El menú se ejecuta en bucle hasta que el usuario selecciona salir.
     */
    public void menu() {
        try {
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

            input = new ObjectInputStream(new FileInputStream("Java-Bank/data/accounts.dat"));
            longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                genAccounts.add((BankAccount) input.readObject());
            }      /*IMPORTANTE*/
            input.close();
            // ^ Cuentas en el array ^
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            System.err.println(e.getMessage());
        }
        int option = 0;
        while (option != 2) {
            /*De momento he dejado el menu de Incio sin variaciones, lo suyo es modificarlo para solo dejar hacerl login, y cuando se corrobore
             * que se trata de un employee o manager, deja hacer cuentas de banco */
            /*Es por lo que los menus son un copia y pega del menu users*/
            System.out.println("Welcome to JavaBank ");
            System.out.println("1. Log In");
            System.out.println("2. Close Application");
            System.out.println("Please enter your numbered choice (1 or 2)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    /*Esto queda eliminado de momento*/
                    /*User newUser = dummyUser.register();
                    users.add(newUser);*/

                    System.out.println(users);
                    System.out.println(employees);
                    System.out.println(managers);
                    login();
                    break;
                case 2:
                    try {
                        System.out.println(users);
                        System.out.println(employees);
                        System.out.println(managers);
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

                        output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/accounts.dat"));
                        output.writeInt(genAccounts.size());
                        for (int i = 0; i < genAccounts.size(); i++) {
                            output.writeObject(genAccounts.get(i));
                        }
                        output.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
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
        while (option != 6) {
            System.out.println("Menu User");
            System.out.println("Welcome " + currentUser.name);
            System.out.println("1. Make a deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Money");
            System.out.println("4. Recharge SIM card");
            System.out.println("5. View accounts");
            System.out.println("6. Log Out");
            System.out.println("Please enter your numbered choice (1, 2, 3, 4 or 5)");
            option = sc.nextInt();
            switch (option) {
                case 1:
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
                    System.out.println("Hola");
                    for(int i = 0; i < genAccounts.size(); i++) {
                        System.out.println(genAccounts.get(i).toString());
                    }
                    break;
                case 6:
                    menu();
                    return;
            }
        }
    }

    public void menuManager(Manager currentManager) {
        int option = 0;
        while (option != 7) {
            System.out.println("Menu Manager");
            System.out.println("Welcome " + currentManager.name);
            System.out.println("1. Create BankAccount");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. Unlock User");
            System.out.println("7. Log Out");
            System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5, 6 or 7)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Indique el id del user");
                    sc.nextLine();
                    DNI = sc.nextLine();

                    User currentUser = null;
                    for(int i=0; i<users.size(); i++) {
                        if(users.get(i).DNI.equals(DNI)){
                            System.out.println(users.get(i));
                            System.out.println("¿Es esta la id del cliente? Sí (S) / No (N)");
                            String confirmacion;
                            confirmacion = sc.nextLine();
                            if(confirmacion.equalsIgnoreCase("S")){
                                currentUser = users.get(i);
                            }
                            break;
                        }
                    }
                    if (currentUser == null) {
                        System.out.println("El DNI no existe");
                    }else{
                        System.out.println("Seleccione 1, 2 o 3 :Crear cuenta de debito(1) o crédito(2), atras (3)");
                        int opcionTarjeta = sc.nextInt();
                        if(opcionTarjeta == 1){
                            DebitAccount nuevaBankAccountdebit = new DebitAccount("", "", "", "", currentUser);
                            nuevaBankAccountdebit.createDebitAccount(currentUser);
                            genAccounts.add(nuevaBankAccountdebit);
                            /*HASTA AQUI FUNCIONA, SE NECESITA crear BankAccount*/
                        }
                        /*
                        if(opcionTarjeta == 2){
                            CreditAccount nuevaBankCredit = new CreditAccount("", "", "",  0.0, 0.0, "", currentUser);
                            nuevaBankCredit.createCreditAccount(currentUser);
                            System.out.println("Seleccione el límite de credito:");
                            genAccounts.add(nuevaBankCredit);
                        }

                         */
                        if(opcionTarjeta == 3){
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
                    break;
                case 6:
                    System.out.println("Unlock User:");
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).active == false){
                            System.out.println(users.get(i).toString());
                        }
                    }
                    System.out.println("Select User to unlock:");
                    String dni = sc.nextLine();
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).DNI.equals(dni)){
                            users.get(i).active = true;
                        }
                    }
                    break;
                case 7:
                    menu();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void menuEmployee(Employee currentEmployee) {
        int option = 0;

        while (option != 7) {
            System.out.println("Menu Employee");
            System.out.println("Welcome " + currentEmployee.name);
            System.out.println("1. Create BankAccount");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. Unlock User");
            System.out.println("7. Log Out");
            System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5 or 6)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Indique el id del user");
                    sc.nextLine();
                    DNI = sc.nextLine();

                    User currentUser = null;
                    for(int i=0; i<users.size(); i++) {
                        if(users.get(i).DNI.equals(DNI)){
                            System.out.println(users.get(i));
                            System.out.println("¿Es esta la id del cliente? Sí (S) / No (N)");
                            String confirmacion;
                            confirmacion = sc.nextLine();
                            if(confirmacion.equalsIgnoreCase("S")){
                                currentUser = users.get(i);
                            }
                            break;
                        }
                    }
                    if (currentUser == null) {
                        System.out.println("El DNI no existe");
                    }else{
                        System.out.println("Seleccione 1, 2 o 3 :Crear cuenta de debito(1) o crédito(2), atras (3)");
                        int opcionTarjeta = sc.nextInt();
                        if(opcionTarjeta == 1){
                            DebitAccount nuevaBankAccountdebit = new DebitAccount("", "", "", "", currentUser);
                            nuevaBankAccountdebit.createDebitAccount(currentUser);
                            genAccounts.add(nuevaBankAccountdebit);
                            /*HASTA AQUI FUNCIONA, SE NECESITA crear BankAccount*/
                        }
                        /*
                        if(opcionTarjeta == 2){
                            CreditAccount nuevaBankCredit = new CreditAccount("", "", "",  0.0, 0.0, "", currentUser);
                            nuevaBankCredit.createCreditAccount(currentUser);
                            System.out.println("Seleccione el límite de credito:");
                            genAccounts.add(nuevaBankCredit);
                        }

                         */
                        if(opcionTarjeta == 3){
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
                    break;
                case 6:
                    System.out.println("Unlock User:");
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).active == false){
                            System.out.println(users.get(i).toString());
                        }
                    }
                    System.out.println("Select User to unlock:");
                    String dni = sc.nextLine();
                    for(int i = 0; i < users.size(); i++) {
                        if(users.get(i).DNI.equals(dni)){
                            users.get(i).active = true;
                        }
                    }
                    break;
                case 7:
                    menu();
                    break;
                default:
                    System.out.println("Invalid choice");

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
        while (option != 4) {
            System.out.println("Please select your profile:");
            System.out.println("1. User");
            System.out.println("2. Manager");
            System.out.println("3. Employee");
            System.out.println("4. Log Out");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Please enter user id: ");

                    DNI = sc.nextLine();
                    User currentUser = null;
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).DNI.equals(DNI)) {
                            currentUser = users.get(i);
                        }
                    }
                    if (currentUser == null) {
                        System.out.println("Stated id is not found, please enter a valid id");
                        return;
                    } else {
                        if (!currentUser.active) {
                            System.out.println("The account associated with this id is blocked.\n Contact a system admin for more information.");
                        } else {
                            int tries = 0;
                            while (tries != 3) {
                                System.out.println("Please enter password: ");
                                String pass = sc.nextLine();
                                if (pass.equals(currentUser.password)) {
                                    System.out.println("You have successfully logged in");
                                    MenuUser(currentUser);
                                } else {
                                    System.out.println("Wrong password, please try again");
                                    tries++;
                                    if (tries == 3) {
                                        System.out.println("You have failed to log in, you account has been blocked.\n Please contact a system admin to resolve this issue.");
                                        currentUser.active = false;
                                    }
                                }
                            }

                        }

                    }
                    break;
                case 2:
                    System.out.println("Please enter manager id: ");

                    DNI = sc.nextLine();
                    Manager currentManager = null;
                    for (int i = 0; i < managers.size(); i++) {
                        if (managers.get(i).DNI.equals(DNI)) {
                            currentManager = managers.get(i);
                        }
                    }
                    if (currentManager == null) {
                        System.out.println("Stated id is not found, please enter a valid id");
                        return;
                    } else {
                        if (!currentManager.active) {
                            System.out.println("The account associated with this id is blocked.\n Contact a system admin for more information.");
                        } else {
                            int tries = 0;
                            while (tries != 3) {
                                System.out.println("Please enter password: ");
                                String pass = sc.nextLine();
                                if (pass.equals(currentManager.password)) {
                                    System.out.println("You have successfully logged in");
                                    menuManager(currentManager);
                                } else {
                                    System.out.println("Wrong password, please try again");
                                    tries++;
                                    if (tries == 3) {
                                        System.out.println("You have failed to log in, you account has been blocked.\n Please contact a system admin to resolve this issue.");
                                        currentManager.active = false;
                                    }
                                }
                            }

                        }

                    }
                    break;
                case 3:
                    System.out.println("Please enter employee id: ");
                    DNI = sc.nextLine();
                    Employee currentEmployee = null;
                    for (int i = 0; i < employees.size(); i++) {
                        if (employees.get(i).DNI.equals(DNI)) {
                            currentEmployee = employees.get(i);
                        }
                    }
                    if (currentEmployee == null) {
                        System.out.println("Stated id is not found, please enter a valid id");
                        return;
                    } else {
                        if (!currentEmployee.active) {
                            System.out.println("The account associated with this id is blocked.\n Contact a system admin for more information.");
                        } else {
                            int tries = 0;
                            while (tries != 3) {
                                System.out.println("Please enter password: ");
                                String pass = sc.nextLine();
                                if (pass.equals(currentEmployee.password)) {
                                    System.out.println("You have successfully logged in");
                                    menuEmployee(currentEmployee);
                                } else {
                                    System.out.println("Wrong password, please try again");
                                    tries++;
                                    if (tries == 3) {
                                        System.out.println("You have failed to log in, you account has been blocked.\n Please contact a system admin to resolve this issue.");
                                        currentEmployee.active = false;
                                    }
                                }
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