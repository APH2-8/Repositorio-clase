package Utils;

import java.sql.*;
import Account.CreditAccount;
import Account.DebitAccount;
import Person.Employee;
import Person.Manager;
import Person.User;
import Account.BankAccount;
import java.util.ArrayList;

public class Database {
    private static final String USER = "avnadmin";
    private static final String URL = "jdbc:mysql://mysql-381243cd-alepenalva2-740b.b.aivencloud.com:13138/defaultdb?sslMode=REQUIRED";
    private static final String PASS = "AVNS_7HpVw-ZLOo96OkUycpH";
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión establecida.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la BBDD: " + e.getMessage());
        }
    }

    //Empleados
    public Employee loginEmployee(String dni, String password) {
        Employee employee = null;
        String sql = "SELECT * FROM empleados WHERE dni = ? AND password = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                employee = new Employee(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("fecha_nacimiento"),
                        rs.getInt("id_empleado")
                );
                if (!rs.getBoolean("activo")) {
                    employee.active = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    //MANAGER
    public Manager loginManager(String dni, String password) {
        Manager managers = null;
        String sql = "SELECT * FROM managers WHERE dni = ? AND password = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                managers = new Manager(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("fecha_nacimiento"),
                        rs.getInt("id_manager")
                );
                if (!rs.getBoolean("activo")) {
                    managers.active = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }

    //USUARIOS
    public User loginUser(String dni, String password) {
        User user = null;
        String sql = "SELECT * FROM usuarios WHERE dni = ? AND password = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("fecha_nacimiento")
                );
                if (!rs.getBoolean("activo")) {
                    user.active = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO usuarios (dni, nombre, password, fecha_nacimiento, activo) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.DNI);
            pst.setString(2, user.name);
            pst.setString(3, user.password);
            pst.setString(4, user.birthDate);
            pst.setBoolean(5, true);

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // CUENTAS
    public void saveAccount(BankAccount account, String type) {
        String sql = "INSERT INTO cuentas (numero_cuenta, iban, alias, saldo, tipo_cuenta, limite_credito, dni_propietario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, account.getAccNumber());
            pst.setString(2, account.getIBAN());
            pst.setString(3, account.accountAlias);
            pst.setDouble(4, account.getBalance());
            pst.setString(5, type);

            if (account instanceof CreditAccount) {
                CreditAccount ca = (CreditAccount) account;
                pst.setDouble(6, ca.getCreditLimit());
            } else {
                pst.setDouble(6, 0.0);
            }

            pst.setString(7, account.getIdPropietario());

            pst.executeUpdate();
            System.out.println("Cuenta guardada en BD.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //CARGAR CUENTAS
    public ArrayList<BankAccount> getUserAccounts(String dni) {
        ArrayList<BankAccount> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuentas WHERE dni_propietario = ?";

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo_cuenta");
                if (tipo.equalsIgnoreCase("DEBITO")) {
                    DebitAccount da = new DebitAccount(
                            rs.getString("numero_cuenta"),
                            "",
                            rs.getString("iban"),
                            rs.getString("alias"),
                            dni
                    );
                    da.setBalance(rs.getDouble("saldo"));
                    lista.add(da);
                }
                else if (tipo.equalsIgnoreCase("CREDITO")) {
                    CreditAccount ca = new CreditAccount(
                            rs.getString("numero_cuenta"),
                            "",
                            rs.getString("iban"),
                            rs.getDouble("limite_credito"),
                            0.0, // Interés
                            rs.getString("alias"),
                            dni
                    );
                    ca.setBalance(rs.getDouble("saldo"));
                    lista.add(ca);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    // Método para buscar usuario SOLO por DNI (Para Empleados/Managers)
    public User getUser(String dni) {
        User user = null;
        String sql = "SELECT * FROM usuarios WHERE dni = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("fecha_nacimiento")
                );
                if (!rs.getBoolean("activo")) {
                    user.active = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
