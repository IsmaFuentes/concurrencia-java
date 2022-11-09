package almacenes.classes;

public class Cliente implements Runnable {
  private Almacen almacen;
  private int intentos;
  private int clientId;

  public Cliente(int clientId, Almacen almacen){
    this.almacen = almacen;
    this.intentos = 0;
    this.clientId = clientId;
  }

  @Override
  public void run(){
    try{
      while(intentos < 10) {
        if(almacen.ComprobarApuertura()){
          almacen.EntradaCliente();
          if(!almacen.AdquirirProducto(clientId)){
            System.out.println("El cliente " + clientId + " ha conseguido entrar pero ya no quedaban productos.");
          }
          break;
        }

        intentos ++;
        if(intentos == 10){
          System.out.println("El cliente " + clientId + " ha fallado demasiados intentos.");
        }
        Thread.sleep(1000);
      }
    }catch (Exception ex){
      System.out.println("ERROR: " + ex.getMessage());
    }
  }
}
