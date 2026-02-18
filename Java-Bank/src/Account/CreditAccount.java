package Account;

import Person.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Representa una cuenta de crédito en el sistema bancario.
 * Este tipo de cuenta permite al usuario utilizar crédito con un límite establecido
 * y está sujeta a un porcentaje de interés sobre el crédito utilizado.
 * g
 * @version 1.0
 * @see BankAccount
 */
public class CreditAccount extends BankAccount {
    private static final long serialVersionUID = 11L;

    /**
     * Límite de crédito disponible para la cuenta.
     */
    double creditLimit = 0.0;

    /**
     * Porcentaje de interés aplicado sobre el crédito utilizado.
     */
    double creditPercentage = 0.0;

    /**
     * Crea una nueva cuenta de crédito.
     *
     * @param accNumber        Número de cuenta.
     * @param dc               Dígito de control.
     * @param IBAN             Código IBAN completo.
     * @param creditLimit      Límite de crédito disponible.
     * @param creditPercentage Porcentaje de interés aplicado.
     */
    public CreditAccount(String accNumber, String dc, String IBAN, double creditLimit, double creditPercentage, String accountAlias, String DNI) {
        super(accNumber, dc, IBAN, accountAlias, DNI);
        this.creditLimit = creditLimit;
        this.creditPercentage = creditPercentage;

    }

    @Override
    public String toString() {
        return "ID Asociado: "+ this.idPropietario + ", IBAN: " + this.IBAN + ", Alias: " + this.accountAlias + ", Balance: " + this.balance; // Añadir limite de credito
    }

    @Override
    public void deposit(int amount, BankAccount account) {

        account.balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + account.balance);
        account.addTransaction("Deposit: ", amount, account.idPropietario);

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
            account.addTransaction("Retirada: ", -amount, account.idPropietario);
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
    @Override
    public void transfer(double amount, BankAccount account, ArrayList<BankAccount> bankAccounts) {
        Scanner sc = new Scanner(System.in);
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
                account.addTransaction("Transferencia enviada a " + destAcc.accNumber, -ammount, account.idPropietario);
                destAcc.addTransaction("Transferencia recibida de " + account.accNumber, ammount, account.idPropietario);
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {

    }

    @Override
    public void selectAccount(User user) {

    }
    public CreditAccount createCreditAccount(User currentUser) {
        BankAccount newBankAccount;
        String entity = "9999", office = "8888", dc = "", accNumber = "", IBAN = "", alias = "";

        entity = getEntity();
        office = getOffice();
        accNumber = String.valueOf((int) (Math.random() * (99999999 - 10000000) + 10000000));
        dc = calcDC(entity, office, accNumber);
        IBAN = calcIBAN(entity, office, accNumber);
        alias = changeAccountAlias();
        double credito = asignarLimiteCredito();
        System.out.println("Your account has been created");
        return new CreditAccount(accNumber, dc, IBAN, credito, 0.0, alias, currentUser.DNI); //limite de credito falta.
    }
}
