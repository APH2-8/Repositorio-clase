package Account;

import Person.User;

/**
 * Interfaz que define las operaciones bancarias disponibles en el sistema.
 * Todas las cuentas bancarias deben implementar estas operaciones
 * fundamentales.
 * 
 * @version 1.0
 */
interface Accounting {
    /**
     * Realiza un depósito de dinero en una cuenta bancaria.
     * @param amount  Cantidad a depositar.
     * @param account Cuenta bancaria destino del depósito.
     */
    void deposit(int amount, BankAccount account);

    /**
     * Realiza una retirada de dinero de una cuenta bancaria.
     * @param amount  Cantidad a retirar.
     * @param account Cuenta bancaria de la que se retirará el dinero.
     */
    void withdraw(int amount, BankAccount account);

    /**
     * Realiza una transferencia de dinero entre cuentas bancarias.
     * @param amount  Cantidad a transferir.
     * @param account Cuenta bancaria origen de la transferencia.
     */
    void transfer(double amount, BankAccount account);

    /**
     * Realiza una recarga de saldo en una tarjeta SIM móvil.
     * @param amount  Cantidad a recargar.
     * @param account Cuenta bancaria desde la que se realizará la recarga.
     */
    void rechargeSIM(int amount, BankAccount account);

    /**
     * Permite al usuario seleccionar una cuenta bancaria de su lista de cuentas.
     * @param user Usuario propietario de las cuentas.
     */
    void selectAccount(User user);
}