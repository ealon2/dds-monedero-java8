package dds.monedero.model;

import java.time.LocalDate;

public class Depositar extends Movimiento{

    public Depositar(LocalDate fecha, double monto) {
        super(fecha, monto);
    }

    public double calcularValor(Cuenta cuenta) {
            return cuenta.getSaldo() + getMonto();
    }
}
