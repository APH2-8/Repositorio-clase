package Account;

import Person.User;

import java.util.ArrayList;

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

    }

    @Override
    public void withdraw(int amount, BankAccount account) {

    }


    @Override
    public void transfer(double amount, BankAccount account, ArrayList<BankAccount> accounts) {

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
        System.out.println("Cuenta creada correctamente");
        return new CreditAccount(accNumber, dc, IBAN, credito, 0.0, alias, currentUser.DNI); //limite de credito falta.
    }
}
