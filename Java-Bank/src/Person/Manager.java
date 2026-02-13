package Person;

import Account.BankAccount;

import java.io.Serializable;
import java.time.Year;
import java.util.Scanner;

public class Manager extends Person {
    private static final long serialVersionUID = 23L;
    int managerID;
    public int id;
    public boolean active;

    public Manager(String DNI, String name, String password, String birthDate, int managerID) {
        super(DNI, name, password, birthDate);
        this.managerID = managerID;
        this.active=true;
    }

    @Override
    public String toString() {
        return " DNI: " + this.DNI + ", Nombre: " + this.name + ", Contraseña: " + this.password + ", Fecha de cumpleaños: " + this.birthDate + ", ID Administrativo: " + this.managerID + ", Cuenta activa: " + this.active;
    }

    @Override
    public User register(){
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP=false, checkD=false;
        System.out.println("Introduzca nombre y apellidos: ");
        name = sc.nextLine();

        System.out.println("Introduzca su contraseña: ");
        password = sc.nextLine();
        checkP=checkPassword(password);
        while (!checkP){
            System.out.println("Contraseña con formato incorrecto.");
            System.out.println("La contraseña debe contener:");
            System.out.println("* 8 dígitos");
            System.out.println("* 1 mayúscula");
            System.out.println("* 1 minúscula");
            System.out.println("* 1 número");
            System.out.println("* 1 caracter especial");
            password = sc.nextLine();
            checkP=checkPassword(password);
        }

        System.out.println("Introduzca su fecha de nacimiento (dd/mm/yyyy): ");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);
        while(!checkD){
            System.out.println("Fecha incorrecta, inténtelo de nuevo");
            System.out.println("Recuente usar el siguiente formato: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }
        id += 1;
        String newId = createId(id);
        User newUser = new User(DNI, name, password, birthdate);
        System.out.println("El resgistro se ha completado");
        System.out.println("Tus datos: ");
        System.out.println("Nombre: " + name);
        System.out.println("Fecha de nacimiento: " + birthdate);
        System.out.println("Contraseña: " + password);
        System.out.println("ID: " + newId);
        return newUser;
    }
    @Override
    public boolean checkPassword(String password){ //regex password
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(password.matches(pattern)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkDate(String date){
        String regex = "[,\\.\\s]";
        String[] myArray = date.split(regex);
        int element1 = Integer.parseInt(myArray[0]);
        int element2 = Integer.parseInt(myArray[1]);
        int element3 = Integer.parseInt(myArray[2]);
        int year = Year.now().getValue();

        if (element1 > 32 || element1 <0){ //check if the day is between 1 and 31
            return false;
        }
        if(  element2 == 4 || element2 == 6 || element2 == 9 ||  element2 == 11 ){//check if it is a 30-day month
            if (element1 >30){
                return false;
            }
        }
        if (element2 == 2  ) { //check if february
            if (element3 % 4 == 0) {
                if (element1 > 29) {//leap year
                    return false;
                }
            } else {
                if (element1 > 28) {//normal year
                    return false;
                }
            }
        }
        if (element3 < 1900 || element3 > year) {
            return false;
        }
        return true;
    }

    public String createId(int id){
        String newId ="";
        for (int i= String.valueOf(id).length(); i < 8; i++){
            newId = "0" + newId;
        }
        return newId;
    }

    public BankAccount createBankAccount(){
        return null;
    }
}
