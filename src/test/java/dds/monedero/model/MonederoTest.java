package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonederoTest {
  private Cuenta cuenta;

  @BeforeEach
  void init() {
    cuenta = new Cuenta();
  }

  @Test
  void PonerTest() {
    cuenta.poner(1500);
    assertEquals(1500,(cuenta.getSaldo()));
  }

  @Test
  void PonerMontoNegativoTest() {
    assertThrows(MontoNegativoException.class, () -> cuenta.poner(-1500));
  }

  @Test
  void TresDepositosTest() {
    cuenta.poner(1500);
    assertEquals(1500,(cuenta.getSaldo()));
    cuenta.poner(456);
    assertEquals(1956,(cuenta.getSaldo()));
    cuenta.poner(1900);
    assertEquals(3856,(cuenta.getSaldo()));
  }

  @Test
  void MasDeTresDepositosTest() {
    assertThrows(MaximaCantidadDepositosException.class, () -> {
          cuenta.poner(1500);
          cuenta.poner(456);
          cuenta.poner(1900);
          cuenta.poner(245);
    });
  }

  @Test
  void ExtraerMasQueElSaldoTest() {
    assertThrows(SaldoMenorException.class, () -> {
          cuenta.setSaldo(90);
          cuenta.sacar(1001);
    });
  }

  @Test
  public void ExtraerMasDe1000Test() {
    assertThrows(MaximoExtraccionDiarioException.class, () -> {
      cuenta.setSaldo(5000);
      cuenta.sacar(1001);
    });
  }

  @Test
  public void ExtraerMontoNegativoTest() {
    assertThrows(MontoNegativoException.class, () -> cuenta.sacar(-500));
  }

}