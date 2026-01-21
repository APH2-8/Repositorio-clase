package Account;

import Person.User;

/**
 * Representa una cuenta de crédito en el sistema bancario.
 * Este tipo de cuenta permite al usuario utilizar crédito con un límite establecido
 * y está sujeta a un porcentaje de interés sobre el crédito utilizado.
 * g
 * @version 1.0
 * @see BankAccount
 */
public class CreditAccount extends BankAccount {
    /**
     * Límite de crédito disponible para la cuenta.
     */
    double creditLimit = 0.0;

    /**
     * Porcentaje de interés aplicado sobre el crédito utilizado.
     */
    double creditPercentage = 0.0;

    /**
     * Crea una nueva cuenta de crédito con alias personalizado.
     * 
     * @param entity           Código de la entidad bancaria.
     * @param office           Código de la oficina.
     * @param accNumber        Número de cuenta.
     * @param dc               Dígito de control.
     * @param IBAN             Código IBAN completo.
     * @param accountAlias     Alias personalizado para la cuenta.
     * @param creditLimit      Límite de crédito disponible.
     * @param creditPercentage Porcentaje de interés aplicado.
     */
    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN, String accountAlias,
            double creditLimit, double creditPercentage) {
        super(entity, office, accNumber, dc, IBAN, accountAlias);
        this.creditLimit = creditLimit;
        this.creditPercentage = creditPercentage;
    }

    /**
     * Crea una nueva cuenta de crédito con alias automático.
     * 
     * @param entity           Código de la entidad bancaria.
     * @param office           Código de la oficina.
     * @param accNumber        Número de cuenta.
     * @param dc               Dígito de control.
     * @param IBAN             Código IBAN completo.
     * @param creditLimit      Límite de crédito disponible.
     * @param creditPercentage Porcentaje de interés aplicado.
     */
    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN, double creditLimit,
            double creditPercentage) {
        super(entity, office, accNumber, dc, IBAN);
        this.creditLimit = creditLimit;
        this.creditPercentage = creditPercentage;
    }

    @Override
    public void deposit(int amount, BankAccount account) {

    }

    @Override
    public void withdraw(int amount, BankAccount account) {

    }

    @Override
    public void transfer(double amount, BankAccount account) {

    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {

    }

    @Override
    public void selectAccount(User user) {

    }
}
