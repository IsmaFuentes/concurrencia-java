package semaforos.classes;

import java.util.concurrent.Semaphore;

public class Almacen {
  private final int MAX_LIMITE = 20;
  private int producto = 0;
  private Semaphore productor = new Semaphore(MAX_LIMITE);
  private Semaphore consumidor = new Semaphore(0);
  private Semaphore mutex = new Semaphore(1);

  public void producir(String nombreProductor) {
    System.out.println(nombreProductor + " intentando almacenar un producto");
    try {
      productor.acquire();
      mutex.acquire();
      producto++;
      System.out.println(nombreProductor + " almacena un producto. " + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
      mutex.release();

      Thread.sleep(1000);

    } catch (InterruptedException ex) {
      System.out.println(ex.getMessage());
    } finally {
      consumidor.release();
    }

  }

  public void consumir(String nombreConsumidor) {
    System.out.println(nombreConsumidor + " intentando retirar un producto");
    try {
      consumidor.acquire();
      mutex.acquire();

      producto--;
      System.out.println(nombreConsumidor + " retira un producto. "
              + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
      mutex.release();

      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      System.out.println(ex.getMessage());
    } finally {
      productor.release();
    }
  }
}
