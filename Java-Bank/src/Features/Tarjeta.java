package Features;
import Account.BankAccount;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Tarjeta {

    public int codigoJavaBank = 428165;
    public String numTarjeta;
    public int cvv;
    public int numSecreto;
    public LocalDateTime fechaCaducidad;
    public LocalDateTime fechaCreacion;
    public BankAccount cuentaAsociada;
    public boolean activo;

    ArrayList<Tarjeta> tarjetas = new ArrayList <>();

    public Tarjeta (int numeroSecreto, BankAccount cuentaAsociada) {
        this.numTarjeta = (String.valueOf(codigoJavaBank) + (int) (Math.random() * (999999999 - 100000000) + 100000000) + (int) (Math.random() * (9 - 1) + 1));
        this.cvv = (int) (Math.random() * (999 - 100) + 100);
        this.numSecreto = numeroSecreto;
        this.fechaCaducidad = LocalDateTime.now().plusYears(5);
        this.fechaCreacion = LocalDateTime.now();
        this.cuentaAsociada = cuentaAsociada;
        this.activo = true;
    }

    public void TarjetasInicio() {
        tarjetas.clear();
        /*try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Java-Bank/data/storeProducts.dat"));
            int longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                tarjetas.add((Tarjeta) input.readObject());
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            System.err.println(e.getMessage());
        }*/
    }

    @Override
    public String toString() {
        String tarjetanueva = this.numTarjeta.replaceAll(".{4}(?!$)", "$0 ");
        return " Número de tarjeta: " + tarjetanueva + "\n CVV: " + this.cvv + "\n Fecha de expedición: " + this.fechaCreacion + "\n Fecha de caducidad: " + this.fechaCaducidad;
    }

    public void crearTarjeta(BankAccount cuentaAsociada) {

        Scanner sc = new Scanner(System.in);
        String dato = "";
        System.out.println("Introduce el número secreto de la tarjeta: ");
        dato = sc.nextLine();
        while (!dato.matches("\\d{4}") || !dato.matches("salir")) {
            System.out.println(" Si deseas salir de la creación, escribe 'salir' \n Ingresa un número secreto válido (4 dígitos): ");
            dato = sc.nextLine();
        }
        if (dato.equals("salir")) {
            System.out.println("Creación de tarjeta cancelada");
        } else  {
            System.out.println("Creación de tarjeta exitosa");
            Tarjeta nuevaTarjeta = new Tarjeta(Integer.parseInt(dato), cuentaAsociada);
        }
    }

    public void apagarTarjeta(Tarjeta tarjetaAsociada) {
        Scanner sc = new Scanner(System.in);
        String dato = "";
        if (!tarjetaAsociada.activo) {
            System.out.println("Esta tarjeta ya está inactiva");
        } else {
            System.out.println("¿Estás segur@? (S / N): ");
            dato = sc.nextLine();
            if (dato.equalsIgnoreCase("S")) {
                setEstadoTarjeta(tarjetaAsociada, false);
            } else if (dato.equalsIgnoreCase("N")) {
                System.out.println("Operación cancelada");
            } else {
                System.out.println("Opción no válida. \n Operación cancelada");
            }
        }
    }

    public void encenderTarjeta(Tarjeta tarjetaAsociada) {
        Scanner sc = new Scanner(System.in);
        String dato = "";
        if (tarjetaAsociada.activo) {
            System.out.println("Esta tarjeta ya está activa");
        } else {
            System.out.println("¿Estás segur@? (S / N): ");
            dato = sc.nextLine();
            if (dato.equalsIgnoreCase("S")) {
                setEstadoTarjeta(tarjetaAsociada, true);
            } else if (dato.equalsIgnoreCase("N")) {
                System.out.println("Operación cancelada");
            } else {
                System.out.println("Opción no válida. \n Operación cancelada");
            }
        }
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setCVV(int CVV) {
        this.cvv = CVV;
    }

    public void setEstadoTarjeta(Tarjeta tarjeta, boolean estado) {
        tarjeta.activo = estado;
    }
}

