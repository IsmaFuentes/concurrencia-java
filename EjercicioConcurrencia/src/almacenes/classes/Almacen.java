package almacenes.classes;

public class Almacen {
  private int productos;
  private boolean abierto;

  public Almacen(int productos){
    this.productos = productos;
    this.abierto = true;
  }

  public void Apertura(boolean abierto){
    this.abierto = abierto;
  }

  public boolean ComprobarApuertura(){
    return this.abierto;
  }

  public synchronized void EntradaCliente() throws InterruptedException {
      Apertura(false);
      Thread.sleep(2000);
      Apertura(true);
  }

  public synchronized boolean AdquirirProducto(int clientId){
    if(productos > 0){
      productos--;
      System.out.println("[ALMACEN]: cliente " + clientId + " ha recogido un producto.");
      System.out.println("[ALMACEN]: productos restantes - " + productos);
      return  true;
    }else{
      return false;
    }
  }
}
