//Interfaz de tipo remota, cabe resaltar que
// solo tiene el metodo echo ( no hay instrucciones) 
public interface EchoInterface extends java.rmi.Remote 
{
    public int pago(int cantidad_a_pagar, String tarjeta, String cvv)throws java.rmi.RemoteException;
}
