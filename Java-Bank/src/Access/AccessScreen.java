package Access;
import Account.BankAccount;
import Account.CreditAccount;
import Account.DebitAccount;
import Account.Transaction;
import Features.Store;
import Features.Tarjeta; ///NUEVO
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
    public ArrayList<Tarjeta> tarjetas = new ArrayList<>();


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

            input = new ObjectInputStream(new FileInputStream("Java-Bank/data/storeProducts.dat"));  ///NUEVO
            longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                tarjetas.add((Tarjeta) input.readObject());
            }
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

                        output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/transactions.dat"));  ///NUEVO
                        output.writeInt(tarjetas.size());
                        for (int i = 0; i < tarjetas.size(); i++) {
                            output.writeObject(tarjetas.get(i));
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
            System.out.println("6. JavaStore");
            System.out.println("7. Salir");
            System.out.println("Por favor, selecciona una opción:  (1, 2, 3, 4, 5, 6 o 7)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Seleccione tipo de cuenta (1: Débito) (2: Crédito)");
                    int eleccion = sc.nextInt();
                    ArrayList<BankAccount> misCuentas = new ArrayList<>();
                    for (BankAccount acc : currentUser.bankAccounts) {
                        if (eleccion == 1 && acc instanceof DebitAccount) {
                            misCuentas.add(acc);
                        } else if (eleccion == 2 && acc instanceof CreditAccount) {
                            misCuentas.add(acc);
                        }
                    }
                    if (misCuentas.isEmpty()) {
                        System.out.println("No tienes cuentas de este tipo.");
                        break;
                    }
                    System.out.println("Seleccione el número de la cuenta:");
                    for (int i = 0; i < misCuentas.size(); i++) {
                        System.out.println((i + 1) + "- " + misCuentas.get(i).toString());
                    }
                    int indexCuenta = sc.nextInt() - 1;
                    if (indexCuenta >= 0 && indexCuenta < misCuentas.size()) {
                        BankAccount cuentaElegida = misCuentas.get(indexCuenta);
                        System.out.println("Introduzca la cantidad a ingresar: ");
                        int cantidad = sc.nextInt();
                        cuentaElegida.deposit(cantidad, cuentaElegida);
                        db.updateSaldo(cuentaElegida.getAccNumber(), cuentaElegida.getBalance());
                        db.registrarTransaccion(currentUser.DNI, "INGRESO", cantidad, "Ingreso en la cuenta " + cuentaElegida.getAccNumber());
                        System.out.println("Ingreso realizado con éxito.");
                    } else {
                        System.out.println("Número de cuenta incorrecto.");
                    }
                    break;
                case 2:
                    System.out.println("--- Retirar Dinero ---");
                    System.out.println("Seleccione tipo de cuenta (1: Débito) (2: Crédito)");
                    int eleccionRetiro = sc.nextInt();
                    ArrayList<BankAccount> misCuentasRetiro = new ArrayList<>();
                    for (BankAccount acc : currentUser.bankAccounts) {
                        if (eleccionRetiro == 1 && acc instanceof DebitAccount) {
                            misCuentasRetiro.add(acc);
                        } else if (eleccionRetiro == 2 && acc instanceof CreditAccount) {
                            misCuentasRetiro.add(acc);
                        }
                    }
                    if (misCuentasRetiro.isEmpty()) {
                        System.out.println("No tienes cuentas de este tipo.");
                        break;
                    }
                    System.out.println("Seleccione el número de la cuenta desde la que desea retirar:");
                    for (int i = 0; i < misCuentasRetiro.size(); i++) {
                        System.out.println((i + 1) + "- " + misCuentasRetiro.get(i).toString());
                    }
                    int indexRetiro = sc.nextInt() - 1;
                    if (indexRetiro >= 0 && indexRetiro < misCuentasRetiro.size()) {
                        BankAccount cuentaElegida = misCuentasRetiro.get(indexRetiro);

                        System.out.println("Introduzca la cantidad a retirar: ");
                        double cantidad = sc.nextDouble();
                        if (cuentaElegida.getBalance() >= cantidad) {
                            cuentaElegida.setBalance(cuentaElegida.getBalance() - cantidad);
                            db.updateSaldo(cuentaElegida.getAccNumber(), cuentaElegida.getBalance());
                            db.registrarTransaccion(currentUser.DNI, "RETIRO", cantidad, "Retiro de la cuenta " + cuentaElegida.getAccNumber());
                            System.out.println("Retiro de " + cantidad + "€ realizado con éxito");
                        } else {
                            System.out.println("Operación denegada: Saldo insuficiente en la cuenta.");
                        }
                    } else {
                        System.out.println("Número de cuenta incorrecto.");
                    }
                    break;
                case 3:
                    System.out.println("--- Transferir Dinero ---");
                    if (currentUser.bankAccounts.isEmpty()) {
                        System.out.println("No tienes cuentas para hacer transferencias.");
                        break;
                    }
                    System.out.println("Seleccione el número de la cuenta de ORIGEN:");
                    for (int i = 0; i < currentUser.bankAccounts.size(); i++) {
                        System.out.println((i + 1) + "- " + currentUser.bankAccounts.get(i).toString());
                    }
                    int indexOrigen = sc.nextInt() - 1;
                    sc.nextLine();
                    if (indexOrigen >= 0 && indexOrigen < currentUser.bankAccounts.size()) {
                        BankAccount cuentaOrigen = currentUser.bankAccounts.get(indexOrigen);
                        System.out.println("Introduzca el número de cuenta de DESTINO (ej. CUENTA-ALE):");
                        String cuentaDestino = sc.nextLine();
                        if (cuentaOrigen.getAccNumber().equals(cuentaDestino)) {
                            System.out.println("No puedes transferirte dinero a tu misma cuenta.");
                            break;
                        }
                        System.out.println("Introduzca la cantidad a transferir: ");
                        double cantidadTrans = sc.nextDouble();
                        if (cantidadTrans > 0 && cuentaOrigen.getBalance() >= cantidadTrans) {
                            boolean exito = db.realizarTransferencia(cuentaOrigen.getAccNumber(), cuentaDestino, cantidadTrans);
                            if (exito) {
                                cuentaOrigen.setBalance(cuentaOrigen.getBalance() - cantidadTrans);
                                db.registrarTransaccion(currentUser.DNI, "TRANSFERENCIA", cantidadTrans, "Transferencia enviada a " + cuentaDestino);
                                System.out.println("Transferencia de " + cantidadTrans + "€ realizada con éxito");
                            } else {
                                System.out.println("Transferencia fallida. Revise que el número de cuenta de destino exista.");
                            }
                        } else {
                            System.out.println("Operación denegada: Saldo insuficiente o cantidad no válida.");
                        }
                    } else {
                        System.out.println("Número de cuenta incorrecto.");
                    }
                    break;
                case 4:
                    System.out.println("--- Recargar Móvil  ---");
                    if (currentUser.bankAccounts.isEmpty()) {
                        System.out.println("No tienes cuentas para pagar la recarga.");
                        break;
                    }
                    System.out.println("Seleccione el número de la cuenta con la que desea pagar:");
                    for (int i = 0; i < currentUser.bankAccounts.size(); i++) {
                        System.out.println((i + 1) + "- " + currentUser.bankAccounts.get(i).toString());
                    }
                    int indexCuentaSim = sc.nextInt() - 1;
                    sc.nextLine();
                    if (indexCuentaSim >= 0 && indexCuentaSim < currentUser.bankAccounts.size()) {
                        BankAccount cuentaPago = currentUser.bankAccounts.get(indexCuentaSim);
                        System.out.println("Introduzca el número de teléfono a recargar:");
                        String numeroTelefono = sc.nextLine();
                        System.out.println("Introduzca la cantidad a recargar: ");
                        double importeRecarga = sc.nextDouble();
                        if (importeRecarga > 0 && cuentaPago.getBalance() >= importeRecarga) {
                            cuentaPago.setBalance(cuentaPago.getBalance() - importeRecarga);
                            db.updateSaldo(cuentaPago.getAccNumber(), cuentaPago.getBalance());
                            db.registrarTransaccion(currentUser.DNI, "RECARGA SIM", importeRecarga, "Recarga al número " + numeroTelefono);
                            System.out.println("Recarga de " + importeRecarga + "€ al número " + numeroTelefono + " realizada con éxito");
                        } else {
                            System.out.println("Operación denegada: Saldo insuficiente o cantidad no válida.");
                        }
                    } else {
                        System.out.println("Número de cuenta incorrecto.");
                    }
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
                    tienda.StoreInicio(tarjetas, currentUser);
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
            System.out.println("8. Ver todas las cuentas del banco");
            System.out.println("9. Salir");
            System.out.println("Por favor, selecciona una opción:  (1 al 9)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Indique el DNI del usuario:");
                    sc.nextLine();
                    DNI = sc.nextLine();
                    User currentUser = db.getUser(DNI);
                    if (currentUser == null) {
                        System.out.println("El DNI no existe en la base de datos.");
                    } else {
                        System.out.println("Usuario encontrado: " + currentUser.name + " | DNI: " + currentUser.DNI);
                        System.out.println("¿Es este el cliente correcto? Sí (S) / No (N)");
                        String confirmacion = sc.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                            System.out.println("Seleccione tipo de cuenta a crear:");
                            System.out.println("1. Débito\n2. Crédito\n3. Cancelar");
                            int opcionTarjeta = sc.nextInt();
                            if (opcionTarjeta == 1) {
                                DebitAccount nuevaCuenta = new DebitAccount("", "", "", "", currentUser.DNI);
                                DebitAccount cuentaCreada = nuevaCuenta.createDebitAccount(currentUser);
                                db.saveAccount(cuentaCreada, "DEBITO");
                                System.out.println("Cuenta de Débito creada y guardada en la Nube.");
                            } else if (opcionTarjeta == 2) {
                                CreditAccount nuevaCuenta = new CreditAccount("", "", "", 0.0, 0.0, "", currentUser.DNI);
                                CreditAccount cuentaCreada = nuevaCuenta.createCreditAccount(currentUser);
                                db.saveAccount(cuentaCreada, "CREDITO");
                                System.out.println("Cuenta de Crédito creada y guardada en la Nube.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("--- Ingresar dinero a Cliente ---");
                    System.out.println("Indique el DNI del cliente: ");
                    sc.nextLine();
                    String dniIngreso = sc.nextLine();
                    User clienteIngreso = db.getUser(dniIngreso);
                    if (clienteIngreso == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteIngreso.bankAccounts = db.getUserAccounts(clienteIngreso.DNI);
                        if (clienteIngreso.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas bancarias.");
                        } else {
                            System.out.println("Cuentas de " + clienteIngreso.name + ":");
                            for (int i = 0; i < clienteIngreso.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteIngreso.bankAccounts.get(i).toString());
                            }
                            System.out.println("Seleccione el número de cuenta:");
                            int indexCuenta = sc.nextInt() - 1;
                            if (indexCuenta >= 0 && indexCuenta < clienteIngreso.bankAccounts.size()) {
                                BankAccount cuentaElegida = clienteIngreso.bankAccounts.get(indexCuenta);
                                System.out.println("Introduzca la cantidad a ingresar: ");
                                int cantidad = sc.nextInt();
                                cuentaElegida.deposit(cantidad, cuentaElegida);
                                db.updateSaldo(cuentaElegida.getAccNumber(), cuentaElegida.getBalance());
                                db.registrarTransaccion(clienteIngreso.DNI, "INGRESO", cantidad, "Ingreso realizado por personal del banco");
                                System.out.println("Ingreso realizado con éxito en la cuenta de " + clienteIngreso.name + "");
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("--- Retirar dinero ---");
                    System.out.println("Indique el DNI del cliente: ");
                    sc.nextLine();
                    String dniRetiro = sc.nextLine();
                    User clienteRetiro = db.getUser(dniRetiro);
                    if (clienteRetiro == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteRetiro.bankAccounts = db.getUserAccounts(clienteRetiro.DNI);
                        if (clienteRetiro.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas bancarias.");
                        } else {
                            System.out.println("Cuentas de " + clienteRetiro.name + ":");
                            for (int i = 0; i < clienteRetiro.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteRetiro.bankAccounts.get(i).toString());
                            }
                            System.out.println("Seleccione el número de cuenta:");
                            int indexRetiro = sc.nextInt() - 1;
                            if (indexRetiro >= 0 && indexRetiro < clienteRetiro.bankAccounts.size()) {
                                BankAccount cuentaElegida = clienteRetiro.bankAccounts.get(indexRetiro);
                                System.out.println("Introduzca la cantidad a retirar: ");
                                double cantidad = sc.nextDouble();
                                if (cuentaElegida.getBalance() >= cantidad) {
                                    cuentaElegida.setBalance(cuentaElegida.getBalance() - cantidad);
                                    db.updateSaldo(cuentaElegida.getAccNumber(), cuentaElegida.getBalance());
                                    db.registrarTransaccion(clienteRetiro.DNI, "RETIRO ", cantidad, "Retiro realizado correctamente");
                                    System.out.println("¡Retiro de " + cantidad + "€ realizado con éxito!");
                                } else {
                                    System.out.println("Operación denegada: El cliente no tiene saldo suficiente.");
                                }
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.println("--- Transferencia ---");
                    System.out.println("Indique el DNI del cliente que envía el dinero: ");
                    sc.nextLine();
                    String dniOrigen = sc.nextLine();
                    User clienteOrigen = db.getUser(dniOrigen);
                    if (clienteOrigen == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteOrigen.bankAccounts = db.getUserAccounts(clienteOrigen.DNI);
                        if (clienteOrigen.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas bancarias para enviar dinero.");
                        } else {
                            System.out.println("Seleccione la cuenta de origen de " + clienteOrigen.name + ":");
                            for (int i = 0; i < clienteOrigen.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteOrigen.bankAccounts.get(i).toString());
                            }
                            int indexOrigen = sc.nextInt() - 1;
                            sc.nextLine();
                            if (indexOrigen >= 0 && indexOrigen < clienteOrigen.bankAccounts.size()) {
                                BankAccount cuentaOrigen = clienteOrigen.bankAccounts.get(indexOrigen);
                                System.out.println("Introduzca el número de cuenta de destino:");
                                String cuentaDestino = sc.nextLine();
                                if (cuentaOrigen.getAccNumber().equals(cuentaDestino)) {
                                    System.out.println("No se puede transferir dinero a la misma cuenta.");
                                    break;
                                }
                                System.out.println("Introduzca la cantidad a transferir: ");
                                double cantidadTrans = sc.nextDouble();

                                if (cantidadTrans > 0 && cuentaOrigen.getBalance() >= cantidadTrans) {
                                    boolean exito = db.realizarTransferencia(cuentaOrigen.getAccNumber(), cuentaDestino, cantidadTrans);
                                    if (exito) {
                                        db.registrarTransaccion(clienteOrigen.DNI, "TRANSFERENCIA ", cantidadTrans, "Transferencia ordenada por personal a " + cuentaDestino);
                                        System.out.println("¡Transferencia de " + cantidadTrans + "€ realizada con éxito en nombre de " + clienteOrigen.name + "!");
                                    } else {
                                        System.out.println("Error: La cuenta de destino no existe en la base de datos.");
                                    }
                                } else {
                                    System.out.println("Operación denegada: Saldo insuficiente o cantidad no válida.");
                                }
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("--- Recargar Móvil (SIM) por Ventanilla ---");
                    System.out.println("Indique el DNI del cliente que va a pagar la recarga: ");
                    sc.nextLine();
                    String dniSim = sc.nextLine();
                    User clienteSim = db.getUser(dniSim);
                    if (clienteSim == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteSim.bankAccounts = db.getUserAccounts(clienteSim.DNI);
                        if (clienteSim.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas para pagar la recarga.");
                        } else {
                            System.out.println("Seleccione la cuenta de " + clienteSim.name + " para pagar:");
                            for (int i = 0; i < clienteSim.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteSim.bankAccounts.get(i).toString());
                            }
                            int indexCuentaVentanilla = sc.nextInt() - 1;
                            sc.nextLine();
                            if (indexCuentaVentanilla >= 0 && indexCuentaVentanilla < clienteSim.bankAccounts.size()) {
                                BankAccount cuentaPagoVentanilla = clienteSim.bankAccounts.get(indexCuentaVentanilla);
                                System.out.println("Introduzca el número de teléfono a recargar:");
                                String numeroTelefonoVentanilla = sc.nextLine();
                                System.out.println("Introduzca la cantidad a recargar: ");
                                double importeRecargaVentanilla = sc.nextDouble();
                                if (importeRecargaVentanilla > 0 && cuentaPagoVentanilla.getBalance() >= importeRecargaVentanilla) {
                                    cuentaPagoVentanilla.setBalance(cuentaPagoVentanilla.getBalance() - importeRecargaVentanilla);
                                    db.updateSaldo(cuentaPagoVentanilla.getAccNumber(), cuentaPagoVentanilla.getBalance());
                                    db.registrarTransaccion(clienteSim.DNI, "RECARGA SIM", importeRecargaVentanilla, "Recarga al número " + numeroTelefonoVentanilla);
                                    System.out.println("Recarga de " + importeRecargaVentanilla + "€ al número " + numeroTelefonoVentanilla + " realizada con éxito");
                                } else {
                                    System.out.println("Operación denegada: Saldo insuficiente o cantidad no válida.");
                                }
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("--- Usuarios Bloqueados ---");
                    ArrayList<User> usuariosBloqueados = db.getLockedUsers();
                    if (usuariosBloqueados.isEmpty()) {
                        System.out.println("No hay ningún usuario bloqueado en este momento.");
                    } else {
                        for(User u : usuariosBloqueados) {
                            System.out.println("DNI: " + u.DNI + " | Nombre: " + u.name);
                        }
                        System.out.println("\nSeleccione el DNI del usuario que quiera desbloquear: ");
                        sc.nextLine();
                        String dniDesbloqueo = sc.nextLine();
                        boolean exito = db.unlockUser(dniDesbloqueo);
                        if (exito) {
                            System.out.println("¡Usuario " + dniDesbloqueo + " activado con éxito en la base de datos!");
                        } else {
                            System.out.println("No se pudo desbloquear. Compruebe que el DNI está bien escrito.");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Ingrese el DNI del usuario del que desea ver el historial: ");
                    sc.nextLine(); // Limpiar buffer
                    String dniHistorial = sc.nextLine();

                    System.out.println("--- HISTORIAL DE TRANSACCIONES ---");
                    db.verHistorial(dniHistorial);
                    break;
                case 8:
                    System.out.println("--- Lista de todas las cuentas del Banco ---");
                    db.mostrarTodasLasCuentas();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Elija una opción del 1-9");
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
            System.out.println("7. Ver todas las cuentas del banco");
            System.out.println("8. Salir");
            System.out.println("Elija una opción del 1-8");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Indique el DNI del usuario:");
                    sc.nextLine();
                    DNI = sc.nextLine();
                    User currentUser = db.getUser(DNI);
                    if (currentUser == null) {
                        System.out.println("El DNI no existe en la base de datos.");
                    } else {
                        System.out.println("Usuario encontrado: " + currentUser.name + " | DNI: " + currentUser.DNI);
                        System.out.println("¿Es este el cliente correcto? Sí (S) / No (N)");
                        String confirmacion = sc.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                            System.out.println("Seleccione tipo de cuenta a crear:");
                            System.out.println("1. Débito\n2. Crédito\n3. Cancelar");
                            int opcionTarjeta = sc.nextInt();
                            if (opcionTarjeta == 1) {
                                DebitAccount nuevaCuenta = new DebitAccount("", "", "", "", currentUser.DNI);
                                DebitAccount cuentaCreada = nuevaCuenta.createDebitAccount(currentUser);
                                db.saveAccount(cuentaCreada, "DEBITO");
                                System.out.println("Cuenta de Débito creada y guardada en la Nube.");
                            } else if (opcionTarjeta == 2) {
                                CreditAccount nuevaCuenta = new CreditAccount("", "", "", 0.0, 0.0, "", currentUser.DNI);
                                CreditAccount cuentaCreada = nuevaCuenta.createCreditAccount(currentUser);
                                db.saveAccount(cuentaCreada, "CREDITO");
                                System.out.println("Cuenta de Crédito creada y guardada en la Nube.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("--- Ingresar dinero a Cliente ---");
                    System.out.println("Indique el DNI del cliente: ");
                    sc.nextLine();
                    String dniIngreso = sc.nextLine();
                    User clienteIngreso = db.getUser(dniIngreso);
                    if (clienteIngreso == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteIngreso.bankAccounts = db.getUserAccounts(clienteIngreso.DNI);
                        if (clienteIngreso.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas bancarias.");
                        } else {
                            System.out.println("Cuentas de " + clienteIngreso.name + ":");
                            for (int i = 0; i < clienteIngreso.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteIngreso.bankAccounts.get(i).toString());
                            }
                            System.out.println("Seleccione el número de cuenta:");
                            int indexCuenta = sc.nextInt() - 1;
                            if (indexCuenta >= 0 && indexCuenta < clienteIngreso.bankAccounts.size()) {
                                BankAccount cuentaElegida = clienteIngreso.bankAccounts.get(indexCuenta);
                                System.out.println("Introduzca la cantidad a ingresar: ");
                                int cantidad = sc.nextInt();
                                cuentaElegida.deposit(cantidad, cuentaElegida);
                                db.updateSaldo(cuentaElegida.getAccNumber(), cuentaElegida.getBalance());
                                db.registrarTransaccion(clienteIngreso.DNI, "INGRESO", cantidad, "Ingreso realizado por personal del banco");
                                System.out.println("Ingreso realizado con éxito en la cuenta de " + clienteIngreso.name + "");
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("--- Retirar dinero ---");
                    System.out.println("Indique el DNI del cliente: ");
                    sc.nextLine();
                    String dniRetiro = sc.nextLine();
                    User clienteRetiro = db.getUser(dniRetiro);

                    if (clienteRetiro == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteRetiro.bankAccounts = db.getUserAccounts(clienteRetiro.DNI);
                        if (clienteRetiro.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas bancarias.");
                        } else {
                            System.out.println("Cuentas de " + clienteRetiro.name + ":");
                            for (int i = 0; i < clienteRetiro.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteRetiro.bankAccounts.get(i).toString());
                            }
                            System.out.println("Seleccione el número de cuenta:");
                            int indexRetiro = sc.nextInt() - 1;
                            if (indexRetiro >= 0 && indexRetiro < clienteRetiro.bankAccounts.size()) {
                                BankAccount cuentaElegida = clienteRetiro.bankAccounts.get(indexRetiro);
                                System.out.println("Introduzca la cantidad a retirar: ");
                                double cantidad = sc.nextDouble();
                                if (cuentaElegida.getBalance() >= cantidad) {
                                    cuentaElegida.setBalance(cuentaElegida.getBalance() - cantidad);
                                    db.updateSaldo(cuentaElegida.getAccNumber(), cuentaElegida.getBalance());
                                    db.registrarTransaccion(clienteRetiro.DNI, "RETIRO ", cantidad, "Retiro realizado correctamente");
                                    System.out.println("¡Retiro de " + cantidad + "€ realizado con éxito!");
                                } else {
                                    System.out.println("Operación denegada: El cliente no tiene saldo suficiente.");
                                }
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("--- Transferencia ---");
                    System.out.println("Indique el DNI del cliente que envía el dinero: ");
                    sc.nextLine();
                    String dniOrigen = sc.nextLine();
                    User clienteOrigen = db.getUser(dniOrigen);
                    if (clienteOrigen == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteOrigen.bankAccounts = db.getUserAccounts(clienteOrigen.DNI);
                        if (clienteOrigen.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas bancarias para enviar dinero.");
                        } else {
                            System.out.println("Seleccione la cuenta de origen de " + clienteOrigen.name + ":");
                            for (int i = 0; i < clienteOrigen.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteOrigen.bankAccounts.get(i).toString());
                            }
                            int indexOrigen = sc.nextInt() - 1;
                            sc.nextLine();
                            if (indexOrigen >= 0 && indexOrigen < clienteOrigen.bankAccounts.size()) {
                                BankAccount cuentaOrigen = clienteOrigen.bankAccounts.get(indexOrigen);
                                System.out.println("Introduzca el número de cuenta de destino:");
                                String cuentaDestino = sc.nextLine();
                                if (cuentaOrigen.getAccNumber().equals(cuentaDestino)) {
                                    System.out.println("No se puede transferir dinero a la misma cuenta.");
                                    break;
                                }
                                System.out.println("Introduzca la cantidad a transferir: ");
                                double cantidadTrans = sc.nextDouble();

                                if (cantidadTrans > 0 && cuentaOrigen.getBalance() >= cantidadTrans) {
                                    boolean exito = db.realizarTransferencia(cuentaOrigen.getAccNumber(), cuentaDestino, cantidadTrans);
                                    if (exito) {
                                        db.registrarTransaccion(clienteOrigen.DNI, "TRANSFERENCIA ", cantidadTrans, "Transferencia ordenada por personal a " + cuentaDestino);
                                        System.out.println("¡Transferencia de " + cantidadTrans + "€ realizada con éxito en nombre de " + clienteOrigen.name + "!");
                                    } else {
                                        System.out.println("Error: La cuenta de destino no existe en la base de datos.");
                                    }
                                } else {
                                    System.out.println("Operación denegada: Saldo insuficiente o cantidad no válida.");
                                }
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("--- Recargar Móvil ---");
                    System.out.println("Indique el DNI del cliente que va a pagar la recarga: ");
                    sc.nextLine();
                    String dniSim = sc.nextLine();
                    User clienteSim = db.getUser(dniSim);
                    if (clienteSim == null) {
                        System.out.println("No se ha encontrado a ningún cliente con ese DNI.");
                    } else {
                        clienteSim.bankAccounts = db.getUserAccounts(clienteSim.DNI);
                        if (clienteSim.bankAccounts.isEmpty()) {
                            System.out.println("Este cliente no tiene cuentas para pagar la recarga.");
                        } else {
                            System.out.println("Seleccione la cuenta de " + clienteSim.name + " para pagar:");
                            for (int i = 0; i < clienteSim.bankAccounts.size(); i++) {
                                System.out.println((i + 1) + "- " + clienteSim.bankAccounts.get(i).toString());
                            }
                            int indexCuentaVentanilla = sc.nextInt() - 1;
                            sc.nextLine();
                            if (indexCuentaVentanilla >= 0 && indexCuentaVentanilla < clienteSim.bankAccounts.size()) {
                                BankAccount cuentaPagoVentanilla = clienteSim.bankAccounts.get(indexCuentaVentanilla);

                                System.out.println("Introduzca el número de teléfono a recargar:");
                                String numeroTelefonoVentanilla = sc.nextLine();
                                System.out.println("Introduzca la cantidad a recargar: ");
                                double importeRecargaVentanilla = sc.nextDouble();
                                if (importeRecargaVentanilla > 0 && cuentaPagoVentanilla.getBalance() >= importeRecargaVentanilla) {
                                    cuentaPagoVentanilla.setBalance(cuentaPagoVentanilla.getBalance() - importeRecargaVentanilla);
                                    db.updateSaldo(cuentaPagoVentanilla.getAccNumber(), cuentaPagoVentanilla.getBalance());
                                    db.registrarTransaccion(clienteSim.DNI, "RECARGA SIM ", importeRecargaVentanilla, "Recarga al número " + numeroTelefonoVentanilla);
                                    System.out.println("¡Recarga de " + importeRecargaVentanilla + "€ al número " + numeroTelefonoVentanilla + " realizada con éxito!");
                                } else {
                                    System.out.println("Operación denegada: Saldo insuficiente o cantidad no válida.");
                                }
                            } else {
                                System.out.println("Número de cuenta incorrecto.");
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("--- Usuarios Bloqueados ---");
                    ArrayList<User> usuariosBloqueados = db.getLockedUsers();
                    if (usuariosBloqueados.isEmpty()) {
                        System.out.println("No hay ningún usuario bloqueado en este momento.");
                    } else {
                        for(User u : usuariosBloqueados) {
                            System.out.println("DNI: " + u.DNI + " | Nombre: " + u.name);
                        }
                        System.out.println("\nSeleccione el DNI del usuario que quiera desbloquear: ");
                        sc.nextLine();
                        String dniDesbloqueo = sc.nextLine();
                        boolean exito = db.unlockUser(dniDesbloqueo);
                        if (exito) {
                            System.out.println("¡Usuario " + dniDesbloqueo + " activado con éxito en la base de datos!");
                        } else {
                            System.out.println("No se pudo desbloquear. Compruebe que el DNI está bien escrito.");
                        }
                    }
                    break;
                case 7:
                    System.out.println("--- Lista de TODAS las cuentas del Banco ---");
                    db.mostrarTodasLasCuentas();
                    break;
                case 8:
                    return;
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
                    User currentUser = db.getUser(DNI);
                    if (currentUser == null) {
                        System.out.println("No se ha encontrado el ID. Por favor, ingrese un ID válido.");
                    } else if (!currentUser.active) {
                        System.out.println("La cuenta asociada a este ID está bloqueada.\nContacte con el personal del banco.");
                    } else {
                        int tries = 0;
                        boolean loginCorrecto = false;
                        while (tries < 3 && !loginCorrecto) {
                            System.out.println("Por favor, ingrese su contraseña: ");
                            String pass = sc.nextLine();
                            if (pass.equals(currentUser.password)) {
                                System.out.println("Inicio sesión completado. ¡Bienvenido " + currentUser.name + "!");
                                currentUser.bankAccounts = db.getUserAccounts(currentUser.DNI);
                                MenuUser(currentUser);
                                loginCorrecto = true;
                            } else {
                                tries++;
                                System.out.println("Contraseña incorrecta. Intento " + tries + " de 3.");
                                if (tries == 3) {
                                    System.out.println("Has agotado los intentos. Tu cuenta ha sido bloqueada por seguridad.");
                                    db.lockUser(DNI);
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Por favor, ingrese su ID de Manager (DNI): ");
                    DNI = sc.nextLine();
                    Manager currentManager = db.getManager(DNI);
                    if (currentManager == null) {
                        System.out.println("No se ha encontrado el ID. Por favor, ingrese un ID válido.");
                    } else if (!currentManager.active) {
                        System.out.println("La cuenta asociada a este ID está bloqueada.\nContacte con el personal del banco.");
                    } else {
                        int triesManager = 0;
                        boolean loginManagerOk = false;
                        while (triesManager < 3 && !loginManagerOk) {
                            System.out.println("Por favor, ingrese su contraseña: ");
                            String pass = sc.nextLine();
                            if (pass.equals(currentManager.password)) {
                                System.out.println("Inicio de sesión correcto. ¡Bienvenido " + currentManager.name + "!");
                                menuManager(currentManager);
                                loginManagerOk = true;
                            } else {
                                triesManager++;
                                System.out.println("Contraseña incorrecta. Intento " + triesManager + " de 3.");
                                if (triesManager == 3) {
                                    System.out.println("Has agotado los intentos. Tu cuenta ha sido bloqueada por seguridad.");
                                    db.lockManager(DNI);
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Por favor, ingrese su ID de empleado (DNI): ");
                    DNI = sc.nextLine();
                    Employee currentEmployee = db.getEmployee(DNI);
                    if (currentEmployee == null) {
                        System.out.println("No se ha encontrado el ID. Por favor, ingrese un ID válido.");
                    } else if (!currentEmployee.active) {
                        System.out.println("La cuenta asociada a este ID está bloqueada.\nContacte con el personal del banco.");
                    } else {
                        int triesEmp = 0;
                        boolean loginEmpOk = false;
                        while (triesEmp < 3 && !loginEmpOk) {
                            System.out.println("Por favor, ingrese su contraseña: ");
                            String pass = sc.nextLine();

                            if (pass.equals(currentEmployee.password)) {
                                System.out.println("Inicio de sesión correcto. ¡Bienvenido " + currentEmployee.name + "!");
                                menuEmployee(currentEmployee);
                                loginEmpOk = true;
                            } else {
                                triesEmp++;
                                System.out.println("Contraseña incorrecta. Intento " + triesEmp + " de 3.");
                                if (triesEmp == 3) {
                                    System.out.println("Has agotado los intentos. Tu cuenta ha sido bloqueada por seguridad.");
                                    db.lockEmployee(DNI);
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