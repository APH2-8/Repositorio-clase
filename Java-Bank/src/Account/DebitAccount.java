package Account;

import Access.AccessScreen;
import Person.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Representa una cuenta de débito en el sistema bancario.
 * Este tipo de cuenta permite realizar operaciones con el saldo disponible sin
 * posibilidad de sobregiro.
 * Implementa todas las operaciones bancarias definidas en {@code Accounting}.
 * 
 * @version 1.0
 * @see BankAccount
 * @see Accounting
 */
public class DebitAccount extends BankAccount implements Serializable {

    AccessScreen acc = new AccessScreen();

    Scanner sc = new Scanner(System.in);

    @Override
    public String toString() {
        return "ID Asociado: "+ this.idPropietario + ", IBAN: " + this.IBAN + ", Alias: " + this.accountAlias + ", Balance: " + this.balance;
    }

    /**
     * Crea una nueva cuenta de débito con alias personalizado.
     *
     * @param accNumber    Número de cuenta.
     * @param dc           Dígito de control.
     * @param IBAN         Código IBAN completo.
     * @param accountAlias Alias personalizado para la cuenta.
     */
    public DebitAccount(String accNumber, String dc, String IBAN, String accountAlias, User user) {
        super(accNumber, dc, IBAN, accountAlias, user);
    }

    /**
     * Deposita una cantidad de dinero en la cuenta especificada.
     * Incrementa el saldo y muestra el resultado de la operación.
     * @param amount  Cantidad a depositar (debe ser positiva).
     * @param account Cuenta bancaria destino del depósito.
     */

    @Override
    public void deposit(int amount, BankAccount account) {

        account.balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + account.balance);
        account.addTransaction("Deposit: ", amount);
    }

    /**
     * Retira una cantidad de dinero de la cuenta especificada.
     * Valida que haya saldo suficiente antes de realizar la operación.
     * @param amount  Cantidad a retirar.
     * @param account Cuenta bancaria de la que se retirará el dinero.
     */
    @Override
    public void withdraw(int amount, BankAccount account) {

        if (account.balance <= 0 || account.balance - amount < 0) {
            System.out.println("Insufficient funds");
        } else {
            account.balance -= amount;
            System.out.println("Operation successful");
            System.out.println("New balance in " + account.accNumber + " is: " + account.balance);
            account.addTransaction("Retirada: ", -amount);
        }
    }
    /**
     * Transfiere dinero desde la cuenta origen a una cuenta destino.
     * Solicita por consola el número de cuenta destino y el importe.
     * Valida que haya saldo suficiente y busca la cuenta destino entre las cuentas
     * registradas.
     * @param amount  Parámetro sin usar (la cantidad se solicita por consola).
     * @param account Cuenta bancaria origen de la transferencia.
     */
    public void transfer(double amount, BankAccount account, ArrayList<BankAccount> bankAccounts) {

        try {
            String sourceAcc = account.accNumber;
            System.out.println("Please enter the destination account number\n");
            String destinationAcc = sc.nextLine();
            System.out.println("Please enter the amount to be transferred (With decimals)\n");
            double ammount = sc.nextDouble();

            if (ammount > account.balance) {
                System.out.println("Insufficient funds");
            } else {
                account.balance -= ammount;
                BankAccount destAcc = null;
                for (int i = 0; i < bankAccounts.size(); i++) {
                    if (bankAccounts.get(i).accNumber.equals(destinationAcc)) {
                        bankAccounts.get(i).balance += ammount;
                        destAcc = bankAccounts.get(i);
                    }
                }
                System.out.println("Operation successful");
                System.out.println("New balance in " + sourceAcc + " is: " + account.balance);
                System.out.println("New balance in " + destinationAcc + " is: " + destAcc.balance);
                account.addTransaction("Transferencia enviada a " + destAcc.accNumber, -ammount);
                destAcc.addTransaction("Transferencia recibida de " + account.accNumber, ammount);
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Realiza una recarga de saldo en una tarjeta SIM móvil.
     * Solicita el número de teléfono destino y valida que tenga 9 dígitos.
     * @param amount  Cantidad a recargar.
     * @param account Cuenta bancaria desde la que se realizará la recarga.
     */
    @Override
    public void rechargeSIM(int amount, BankAccount account) {
        System.out.println("Input the destination phone number\n");
        try {
            String number = sc.nextLine();
            while (number.length() != 9) {
                System.out.println("Please enter a valid phone number (9 digits)\n");
                number = sc.nextLine();
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permite al usuario seleccionar una cuenta bancaria de su lista.
     * Muestra todas las cuentas disponibles con sus alias y solicita la selección
     * por número.
     * @param user Usuario propietario de las cuentas a seleccionar.
     */
    @Override
    public void selectAccount(User user) {

        BankAccount foundBankAccount = null;
        System.out.println("Select the account you want to use by typing the number of the option");
        for (int i = 0; i < user.bankAccounts.size(); i++) {
            String aliasBA = user.bankAccounts.get(i).accountAlias;
            System.out.println("Option " + (i + 1) + ": " + aliasBA);
        }
        try {
            int option = sc.nextInt();
            sc.nextLine();
            foundBankAccount = user.bankAccounts.get(option - 1);
            System.out.println(
                    "Selected account: " + foundBankAccount.accNumber + " Balance: " + foundBankAccount.balance);

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }

    }
    public DebitAccount createDebitAccount(User currentUser) {
        String entity = "9999", office = "8888", dc = "", accNumber = "", IBAN = "", alias = "";

        entity = getEntity();
        office = getOffice();
        accNumber = String.valueOf((int) (Math.random() * (99999999 - 10000000) + 10000000));
        dc = calcDC(entity, office, accNumber);
        IBAN = calcIBAN(entity, office, accNumber);
        alias = changeAccountAlias();
        System.out.println("Your account has been created");
        return new DebitAccount( accNumber,  dc,  IBAN,  accountAlias,  currentUser); //limite de credito falta.
    }
}
