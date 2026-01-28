package Account; // O el paquete que decidas

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; // "Ingreso", "Retirada", "Transferencia enviada", etc.
    private double amount;
    private LocalDateTime date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now(); // Toma la fecha/hora actual
    }

    @Override
    public String toString() {
        // Formato solicitado: HH:MM:SS dd/mm/aaaa
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        return "Op: " + type + " | Cantidad: " + amount + " | Fecha: " + date.format(formatter);
    }

    // Getters si son necesarios...
}