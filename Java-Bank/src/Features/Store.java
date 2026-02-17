package Features;

import Account.CreditAccount;
import Account.DebitAccount;
import Account.Transaction;
import Person.Employee;
import Person.Manager;
import Person.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Store implements Serializable {
    private static final long serialVersionUID = 3L;

    int idProducto;
    String nombreProducto;
    String descripcionProducto;
    double precioProducto;
    String tipoProducto;
    boolean outlet;

    ArrayList<Store> productos = new ArrayList<Store>();

    public Store(int idProducto, String nombreProducto, String descripcionProducto, double precioProducto, String tipoProducto, boolean outlet) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.tipoProducto = tipoProducto;
        this.outlet = outlet;

    }

    @Override
    public String toString() {
        return " ID: " + this.idProducto + ", Producto: " + this.nombreProducto + ", Resumen: " + this.descripcionProducto + ", Precio: " + this.precioProducto;
    }

    public void StoreInicio() {
        productos.clear();
       /* try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Java-Bank/data/storeProducts.dat"));
            int longitud = input.readInt();
            for (int i = 0; i < longitud; i++) {
                productos.add((Store) input.readObject());
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            System.err.println(e.getMessage());
        } */
        StoreMenu();
    }

    public void StoreMenu() {

        productos.add(new Store(0, "pPhone 16 Pro", "Inserte su descripción", 1800.85, "Tecnología y Electrónica", false));
        productos.add(new Store(1, "Jugueis 2X max", "Inserte su descripción", 650.99, "Movilidad y Ocio", true));
        productos.add(new Store(2, "pPhone 12 pro", "Inserte su descripción", 1049.99, "Tecnología y Electrónica", false));
        productos.add(new Store(3, "pPhone 7 SE", "Inserte su descripción", 849.99, "Tecnología y Electrónica", false));
        productos.add(new Store(4, "Bailais TurboClean 23", "Inserte su descripción", 234.99, "Hogar", true));
        productos.add(new Store(5, "Jugueis CicloTurbo 3", "Inserte su descripción", 299.99, "Movilidad y Ocio", true));
        productos.sort(Comparator.comparing(Store::getTipoProducto));

        int option = 0;
        Scanner sc = new Scanner(System.in);
        while (option != 2) {
            System.out.println("¡Bienvenido a JavaStore!");
            System.out.println("1. Ver productos");
            System.out.println("2. Ver ofertas");
            System.out.println("3. Salir de la tienda");
            System.out.println("Por favor, selecciona una opción (1, 2 o 3)");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    Escaparate();
                    break;
                case 2:
                    outletCompra();
                case 3:
                    try {

                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Java-Bank/data/storeProducts.dat"));
                        output.writeInt(productos.size());
                        for (int i = 0; i < productos.size(); i++) {
                            output.writeObject(productos.get(i));
                        }
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return;
        }

    }

    public void Escaparate() {

        int option = 0;
        Scanner sc = new Scanner(System.in);
        String tipo = "Default";
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).tipoProducto.equals(tipo)) {
                System.out.println(productos.get(i).toString());
            } else {
                System.out.println("--V--" + productos.get(i).tipoProducto + "--V--");
                System.out.println(productos.get(i).toString());
            }
            tipo = productos.get(i).tipoProducto;
        }
        System.out.println("-------^------^------- \n -------v------v-------");
        System.out.println("1- Comprar productos");
        System.out.println("2- Volver");
        option = sc.nextInt();
        switch (option) {
            case 1:
                cesta(productos);
                break;
            case 2:
                break;
        }
    }

    public ArrayList<Store> outletCompra() {

        ArrayList<Store> compraFinal = new ArrayList<Store>();
        int option = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("---V--- Productos en oferta ---V---");
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).outlet) {
                System.out.println(productos.get(i).toString());
            }
        }
        System.out.println("-------^------^------- \n -------v------v-------");
        System.out.println("1- Comprar productos");
        System.out.println("2- Volver");
        option = sc.nextInt();
        switch (option) {
            case 1:
                cesta(productos);
                break;
            case 2:
                break;
        }
        return compraFinal;
    }

    public ArrayList<Store> cesta(ArrayList<Store> productosDisponibles) {

        ArrayList<Store> compraFinal = new ArrayList<Store>();
        int option = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecciona la ID del producto que deseas meter en la cesta:");
        option = sc.nextInt();
        while (true) {
            for (Store producto : productosDisponibles) {
                if (producto.idProducto == option) {
                    compraFinal.add(producto);
                }
            }
            System.out.println("Producto en la cesta \n 1- Ver cesta de la compra \n 2- Seguir comprando \n 3- Finalizar la compra \n 4- Volver");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    for (int i = 0; i < compraFinal.size(); i++) {
                        System.out.println(compraFinal.get(i).toString());
                    }
                case 2:
                    System.out.println("Selecciona la ID del producto que deseas meter en la cesta:");
                    option = sc.nextInt();
                case 3:
                    confirmarCompra();
                    break;
                case 4:
                    break;
            }
            break;
        }
        return productos;
    }

    public void confirmarCompra() {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione el método de pago:");
        System.out.println("Número de targeta: ");
        option = sc.nextInt();
        // no están hechas las targetas, hasta que no estén hechas no se puede usar la tienda
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

}