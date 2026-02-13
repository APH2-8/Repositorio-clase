package Account; // O el paquete que decidas

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 3L;
    private String type; // "Ingreso", "Retirada", "Transferencia enviada", etc.
    private double amount;
    private LocalDateTime date;
    public String idAsociado;

    public Transaction(String type, double amount, String idAsociado) {
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now(); // Toma la fecha/hora actual
        this.idAsociado = idAsociado;
    }

    @Override
    public String toString() {
        // Formato solicitado: HH:MM:SS dd/mm/aaaa
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        return "Op: " + type + " | Cantidad: " + amount + " | Fecha: " + date.format(formatter);
    }

    // Getters si son necesarios...
}