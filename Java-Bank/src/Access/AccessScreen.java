package Access;
import Person.Employee;
import Person.Manager;
import Person.User;

import java.util.Scanner;

import java.util.ArrayList;
/*Comentario de prueba*/
/*Comentario de Pau*/
public class AccessScreen {
    Scanner sc = new Scanner(System.in);

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Employee> employees = new ArrayList<Employee>();
    ArrayList<Manager> managers = new ArrayList<Manager>();

    String id="";
    User dummyUser = new User(null, null, null, null, true);
    Employee dummyEmployee = new Employee (null, null, null, null, 0);
    Manager dummyManager = new Manager (null, null, null, null, 0);

    public void menu(){

        int option=0;
        while(option!=2){
            /*De momento he dejado el menu de Incio sin variaciones, lo suyo es modificarlo para solo dejar hacerl login, y cuando se corrobore
            * que se trata de un employee o manager, deja hacer cuentas de banco */
            /*Es por lo que los menus son un copia y pega del menu users*/
            System.out.println("Welcome to JavaBank ");
            System.out.println("1. Log In");
            System.out.println("2. Close Application");
            System.out.println("Please enter your numbered choice (1 or 2)");
            option = sc.nextInt();
            switch (option){
                case 1:
                    /*Esto queda eliminado de momento*/
                    /*User newUser = dummyUser.register();
                    users.add(newUser);*/
                    login();
                    break;
                case 2:
                    break;
            }
        }

    }

    public void MenuUser(User currentUser){
        int option=0;
        System.out.println("Menu User");
        System.out.println("Welcome " + currentUser.name);
        System.out.println("1. Make a deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer Money");
        System.out.println("4. Recharge SIM card");
        System.out.println("5. Log Out");
        System.out.println("Please enter your numbered choice (1, 2, 3, 4 or 5)");
        while(option!=5){
            switch (option){
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
                    return;
            }
        }
    }
    public void menuManager (Manager currentManager){
        int option=0;
        System.out.println("Menu Manager");
        System.out.println("Welcome " + currentManager.name);
        System.out.println("1. Create BankAccount");
        System.out.println("2. Make a deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Money");
        System.out.println("5. Recharge SIM card");
        System.out.println("6. Log Out");
        System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5 or 6)");
        while(option!=6){
            switch (option){
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
                    return;
                case 6:
                    return;
            }
        }
    }

    public void menuEmployee (Employee currentEmployee){
        int option=0;
        System.out.println("Menu Employee");
        System.out.println("Welcome " + currentEmployee.name);
        System.out.println("1. Create BankAccount");
        System.out.println("2. Make a deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Money");
        System.out.println("5. Recharge SIM card");
        System.out.println("6. Log Out");
        System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5 or 6)");
        while(option!=6){
            switch (option){
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
                    return;
                case 6:
                    return;
            }
        }
    }

    public void login(){
        int option=0;
        while(option!=4) {
            System.out.println("Please select your profile:");
            System.out.println("1. User");
            System.out.println("2. Manager");
            System.out.println("3. Employee");
            System.out.println("4. Log Out");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Please enter user id: ");
                    sc.nextLine();
                    id = sc.nextLine();
                    User currentUser =  null;
                    for (int i = 0; i < users.size(); i++) {
                        if(users.get(i).id.equals(id)){
                            currentUser =  users.get(i);
                        }
                    }
                    if (currentUser == null){
                        System.out.println("Stated id is not found, please enter a valid id");
                        return;
                    }
                    else{
                        if(!currentUser.active){
                            System.out.println("The account associated with this id is blocked.\n Contact a system admin for more information.");
                        }
                        else{
                            int tries = 0;
                            while (tries != 3){
                                System.out.println("Please enter password: ");
                                String pass = sc.nextLine();
                                if(pass.equals(currentUser.password)){
                                    System.out.println("You have successfully logged in");
                                    MenuUser(currentUser);
                                }
                                else{
                                    System.out.println("Wrong password, please try again");
                                    tries++;
                                    if(tries == 3){
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
                        sc.nextLine();
                        id = sc.nextLine();
                        Manager currentManager =  null;
                        for (int i = 0; i < managers.size(); i++) {
                            if(managers.get(i).ID.equals(id)){
                                currentManager =  managers.get(i);
                            }
                        }
                        if (currentManager == null){
                            System.out.println("Stated id is not found, please enter a valid id");
                            return;
                        }
                        else{
                            if(!currentManager.active){
                                System.out.println("The account associated with this id is blocked.\n Contact a system admin for more information.");
                            }
                            else{
                                int tries = 0;
                                while (tries != 3){
                                    System.out.println("Please enter password: ");
                                    String pass = sc.nextLine();
                                    if(pass.equals(currentManager.password)){
                                        System.out.println("You have successfully logged in");
                                        menuManager(currentManager);
                                    }
                                    else{
                                        System.out.println("Wrong password, please try again");
                                        tries++;
                                        if(tries == 3){
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
                            sc.nextLine();
                            id = sc.nextLine();
                            Employee currentEmployee =  null;
                            for (int i = 0; i < employees.size(); i++) {
                                if(employees.get(i).ID.equals(id)){
                                    currentEmployee =  employees.get(i);
                                }
                            }
                            if (currentEmployee == null){
                                System.out.println("Stated id is not found, please enter a valid id");
                                return;
                            }
                            else{
                                if(!currentEmployee.active){
                                    System.out.println("The account associated with this id is blocked.\n Contact a system admin for more information.");
                                }
                                else{
                                    int tries = 0;
                                    while (tries != 3){
                                        System.out.println("Please enter password: ");
                                        String pass = sc.nextLine();
                                        if(pass.equals(currentEmployee.password)){
                                            System.out.println("You have successfully logged in");
                                            menuEmployee(currentEmployee);
                                        }
                                        else{
                                            System.out.println("Wrong password, please try again");
                                            tries++;
                                            if(tries == 3){
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
