package dds.monedero.model;

import java.time.LocalDate;

public class Extraer extends Movimiento {
    public Extraer(LocalDate fecha, double monto) {
        super(fecha, monto);
    }
    public double calcularValor(Cuenta cuenta) {
        return cuenta.getSaldo() - getMonto();
    }
}
