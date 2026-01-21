package Account;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase abstracta que representa una cuenta bancaria genérica.
 * Define la estructura base y operaciones comunes para todos los tipos de
 * cuentas.
 * Implementa la interfaz {@code Accounting} delegando las operaciones
 * específicas a las subclases.
 * 
 * @version 1.0
 * @see Accounting
 * @see DebitAccount
 * @see CreditAccount
 */
public abstract class BankAccount implements Accounting {

    /**
     * Código de la entidad bancaria (por defecto "9999").
     */
    public String entity = "9999";

    /**
     * Código de la oficina bancaria (por defecto "8888").
     */
    public String office = "8888";

    /**
     * Dígito de control de la cuenta.
     */
    public String dc = "";

    /**
     * Número de cuenta.
     */
    public String accNumber = "";

    /**
     * Código IBAN completo de la cuenta.
     */
    public String IBAN = "";

    /**
     * Alias o nombre personalizado de la cuenta.
     */
    public String accountAlias = "";

    /**
     * Saldo actual de la cuenta.
     */
    public double balance = 0.0;

    /**
     * Contador interno para nuevas cuentas.
     */
    int numNewAccount = 0;

    /**
     * Lista de cuentas bancarias asociadas.
     */
    ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    /**
     * Scanner para lectura de entrada por consola.
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Crea una nueva cuenta bancaria con alias personalizado.
     * 
     * @param entity       Código de la entidad bancaria.
     * @param office       Código de la oficina.
     * @param accNumber    Número de cuenta.
     * @param dc           Dígito de control.
     * @param IBAN         Código IBAN completo.
     * @param accountAlias Alias personalizado para la cuenta.
     */
    public BankAccount(String entity, String office, String accNumber, String dc, String IBAN, String accountAlias) {
        this.entity = entity;
        this.office = office;
        this.accNumber = accNumber;
        this.dc = dc;
        this.IBAN = IBAN;
        this.accountAlias = accountAlias;
        this.balance = 0.0;
    }

    /**
     * Crea una nueva cuenta bancaria con alias automático.
     * El alias se genera automáticamente como "Account" seguido del número de
     * cuenta.
     * 
     * @param entity    Código de la entidad bancaria.
     * @param office    Código de la oficina.
     * @param accNumber Número de cuenta.
     * @param dc        Dígito de control.
     * @param IBAN      Código IBAN completo.
     */
    public BankAccount(String entity, String office, String accNumber, String dc, String IBAN) {
        this.entity = entity;
        this.office = office;
        this.accNumber = accNumber;
        this.dc = dc;
        this.IBAN = IBAN;
        this.accountAlias = "Account " + accNumber;
        this.balance = 0.0;
    }

    /**
     * Calcula el dígito de control (DC) de una cuenta bancaria española.
     * Aplica el algoritmo oficial del sistema bancario español que utiliza
     * pesos específicos para entidad/oficina y número de cuenta.
     * @param entidad Código de la entidad (se formatea a 4 dígitos).
     * @param oficina Código de la oficina (se formatea a 4 dígitos).
     * @param cuenta  Número de cuenta (se formatea a 10 dígitos).
     * @return Dígito de control de 2 caracteres.
     */
    public static String calcDC(String entidad, String oficina, String cuenta) {
        entidad = String.format("%04d", Integer.parseInt(entidad));
        oficina = String.format("%04d", Integer.parseInt(oficina));
        cuenta = String.format("%010d", Long.parseLong(cuenta));

        int[] w1 = { 4, 8, 5, 10, 9, 7, 3, 6 };
        int[] w2 = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };

        String bloque1 = entidad + oficina;
        int suma1 = 0;
        for (int i = 0; i < 8; i++)
            suma1 += (bloque1.charAt(i) - '0') * w1[i];
        int r1 = suma1 % 11;
        int d1 = 11 - r1;
        if (d1 == 11)
            d1 = 0;
        else if (d1 == 10)
            d1 = 1;

        String bloque2 = cuenta;
        int suma2 = 0;
        for (int i = 0; i < 10; i++)
            suma2 += (bloque2.charAt(i) - '0') * w2[i];
        int r2 = suma2 % 11;
        int d2 = 11 - r2;
        if (d2 == 11)
            d2 = 0;
        else if (d2 == 10)
            d2 = 1;

        return "" + d1 + d2;
    }

    /**
     * Calcula el IBAN completo de una cuenta bancaria española.
     * Genera el código IBAN según el estándar internacional (ISO 13616).
     * El IBAN español tiene el formato: ES + 2 dígitos de control + BBAN (20
     * dígitos).
     * 
     * @param entity    Código de la entidad bancaria.
     * @param office    Código de la oficina.
     * @param accNumber Número de cuenta.
     * @return IBAN completo con formato "ESxx" seguido del BBAN.
     */
    public static String calcIBAN(String entity, String office, String accNumber) {
        String dc = calcDC(entity, office, accNumber);

        entity = String.format("%04d", Integer.parseInt(entity));
        office = String.format("%04d", Integer.parseInt(office));
        accNumber = String.format("%010d", Long.parseLong(accNumber));

        String bban = entity + office + dc + accNumber;

        // ES -> 14 28, más "00"
        String numeric = bban + "142800";

        BigInteger num = new BigInteger(numeric);
        int resto = num.mod(BigInteger.valueOf(97)).intValue();
        int cd = 98 - resto;
        String cdStr = String.format("%02d", cd);

        return "ES" + cdStr + bban;
    }

    /**
     * Inicia el proceso de creación de una nueva cuenta bancaria.
     * Solicita datos por consola y genera automáticamente el DC, IBAN y alias.
     */
    public void createBankAccount() {
        BankAccount newBankAccount;
        String entity = "", office = "", dc = "", accNumber = "", IBAN = "", alias = "";

        entity = getEntity();
        office = getOffice();

        dc = calcDC(entity, office, accNumber);
        IBAN = calcIBAN(entity, office, accNumber);
        alias = changeAccountAlias();
        System.out.println("Your account has been created");

    }

    /**
     * Permite cambiar o asignar un alias personalizado a la cuenta.
     * Solicita confirmación al usuario por consola.
     * Si no se proporciona alias, se genera uno automático.
     * @return El alias asignado a la cuenta.
     */
    public String changeAccountAlias() {
        String alias = "";
        System.out.println("Do you want to give an alias to your account?");
        String check = sc.nextLine();
        if (check.equalsIgnoreCase("yes") || check.equalsIgnoreCase("si")) {
            System.out.println("Introduce the account alias: ");
            alias = sc.nextLine();
        }
        if (alias.isEmpty()) {
            System.out.println("You have not entered an alias. The account name will default to its number.");
            alias = "Account " + IBAN;
        } else {
            alias = check;
        }
        return alias;
    }

    /// Getters y Setters

    public String getEntity() {
        return this.entity;

    }

    public String getOffice() {
        return this.office;
    }

    public String getDc() {
        return dc;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
