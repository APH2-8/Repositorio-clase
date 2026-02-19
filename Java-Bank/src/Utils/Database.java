package Utils;

import java.sql.*;
import Account.CreditAccount;
import Account.DebitAccount;
import Features.Store;
import Features.Tarjeta;
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
    // --- MEJORA 1: DESBLOQUEAR USUARIOS ---

    // 1. Método para obtener solo los usuarios bloqueados
    public ArrayList<User> getLockedUsers() {
        ArrayList<User> lockedUsers = new ArrayList<>();
        // Buscamos los que tengan activo = false (0 en MySQL)
        String sql = "SELECT * FROM usuarios WHERE activo = false";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User u = new User(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("fecha_nacimiento")
                );
                u.active = false;
                lockedUsers.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lockedUsers;
    }
    // BLOQUEOS

    public Manager getManager(String dni) {
        Manager manager = null;
        String sql = "SELECT * FROM managers WHERE dni = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                manager = new Manager(rs.getString("dni"), rs.getString("nombre"),
                        rs.getString("password"), rs.getString("fecha_nacimiento"), rs.getInt("id_manager"));
                if (!rs.getBoolean("activo")) manager.active = false;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return manager;
    }
    public void lockManager(String dni) {
        String sql = "UPDATE managers SET activo = false WHERE dni = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            pst.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public Employee getEmployee(String dni) {
        Employee employee = null;
        String sql = "SELECT * FROM empleados WHERE dni = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                employee = new Employee(rs.getString("dni"), rs.getString("nombre"),
                        rs.getString("password"), rs.getString("fecha_nacimiento"), rs.getInt("id_empleado"));
                if (!rs.getBoolean("activo")) employee.active = false;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return employee;
    }

    public void lockEmployee(String dni) {
        String sql = "UPDATE empleados SET activo = false WHERE dni = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            pst.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void lockUser(String dni) {
        String sql = "UPDATE usuarios SET activo = false WHERE dni = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al bloquear: " + e.getMessage());
        }
    }
    public boolean unlockUser(String dni) {
        String sql = "UPDATE usuarios SET activo = true WHERE dni = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);

            int filasModificadas = pst.executeUpdate();
            return filasModificadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al desbloquear: " + e.getMessage());
            return false;
        }
    }
    // --- ACTUALIZAR SALDO ---
    public void updateSaldo(String numeroCuenta, double nuevoSaldo) {
        String sql = "UPDATE cuentas SET saldo = ? WHERE numero_cuenta = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setDouble(1, nuevoSaldo);
            pst.setString(2, numeroCuenta);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar saldo: " + e.getMessage());
        }
    }
    // ---  TRANSFERENCIAS ---
    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, double cantidad) {
        try {
            PreparedStatement check = connection.prepareStatement("SELECT * FROM cuentas WHERE numero_cuenta = ?");
            check.setString(1, cuentaDestino);
            ResultSet rs = check.executeQuery();
            if (!rs.next()) {
                return false;
            }
            PreparedStatement restar = connection.prepareStatement("UPDATE cuentas SET saldo = saldo - ? WHERE numero_cuenta = ?");
            restar.setDouble(1, cantidad);
            restar.setString(2, cuentaOrigen);
            restar.executeUpdate();
            PreparedStatement sumar = connection.prepareStatement("UPDATE cuentas SET saldo = saldo + ? WHERE numero_cuenta = ?");
            sumar.setDouble(1, cantidad);
            sumar.setString(2, cuentaDestino);
            sumar.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error en la transferencia: " + e.getMessage());
            return false;
        }
    }
    // --- VER TODAS LAS CUENTAS (Para Empleados/Managers) ---
    public void mostrarTodasLasCuentas() {
        String sql = "SELECT * FROM cuentas";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            boolean hayCuentas = false;
            while(rs.next()) {
                hayCuentas = true;
                System.out.println("Cuenta: " + rs.getString("numero_cuenta") +
                        " | Propietario (DNI): " + rs.getString("dni_propietario") +
                        " | Saldo: " + rs.getDouble("saldo") + "€" +
                        " | Tipo: " + rs.getString("tipo_cuenta"));
            }
            if(!hayCuentas) System.out.println("No hay ninguna cuenta en el banco.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // --- GUARDAR EN EL HISTORIAL ---
    public void registrarTransaccion(String dni, String tipo, double cantidad, String detalles) {
        String sql = "INSERT INTO transacciones (dni_cliente, tipo_operacion, cantidad, detalles) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            pst.setString(2, tipo);
            pst.setDouble(3, cantidad);
            pst.setString(4, detalles);
            pst.executeUpdate();
        } catch (SQLException e) { System.err.println("Error al guardar historial: " + e.getMessage()); }
    }

    // --- LEER EL HISTORIAL ---
    public void verHistorial(String dni) {
        String sql = "SELECT * FROM transacciones WHERE dni_cliente = ? ORDER BY fecha DESC";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            boolean hayDatos = false;
            while(rs.next()) {
                hayDatos = true;
                System.out.println("[" + rs.getTimestamp("fecha") + "] " +
                        rs.getString("tipo_operacion") + " | " +
                        rs.getDouble("cantidad") + "€ | " + rs.getString("detalles"));
            }
            if(!hayDatos) System.out.println("No hay transacciones registradas para este usuario.");
        } catch (SQLException e) { e.printStackTrace(); }
    }
    // --- GESTIÓN DE TIENDA ---
    public ArrayList<Store> getProductosTienda() {
        ArrayList<Store> lista = new ArrayList<>();
        String sql = "SELECT * FROM tienda_productos";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new Store(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("descripcion"), rs.getDouble("precio"),
                        rs.getString("tipo"), rs.getBoolean("outlet")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // --- GESTIÓN DE TARJETAS ---
    public void guardarTarjeta(Tarjeta t) {
        String sql = "INSERT INTO tarjetas VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, t.numTarjeta);
            pst.setInt(2, t.cvv);
            pst.setInt(3, t.numSecreto);
            pst.setTimestamp(4, java.sql.Timestamp.valueOf(t.fechaCaducidad));
            pst.setTimestamp(5, java.sql.Timestamp.valueOf(t.fechaCreacion));
            pst.setString(6, t.cuentaAsociada.getAccNumber());
            pst.setBoolean(7, t.activo);
            pst.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public ArrayList<Tarjeta> getTarjetasUsuario(String dni) {
        ArrayList<Tarjeta> lista = new ArrayList<>();
        String sql = "SELECT t.* FROM tarjetas t JOIN cuentas c ON t.num_cuenta = c.numero_cuenta WHERE c.dni_propietario = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            ArrayList<BankAccount> cuentas = getUserAccounts(dni);
            while (rs.next()) {
                String numCuentaAsociada = rs.getString("num_cuenta");
                BankAccount cuentaEncontrada = null;
                for (BankAccount acc : cuentas) {
                    if (acc.getAccNumber().equals(numCuentaAsociada)) {
                        cuentaEncontrada = acc;
                        break;
                    }
                }

                if (cuentaEncontrada != null) {
                    Tarjeta t = new Tarjeta(rs.getInt("num_secreto"), cuentaEncontrada);
                    t.numTarjeta = rs.getString("num_tarjeta");
                    t.cvv = rs.getInt("cvv");
                    t.fechaCaducidad = rs.getTimestamp("fecha_caducidad").toLocalDateTime();
                    t.fechaCreacion = rs.getTimestamp("fecha_creacion").toLocalDateTime();
                    t.activo = rs.getBoolean("activo");
                    lista.add(t);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    }

