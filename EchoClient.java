import java.io.*;
import java.net.*;

public class EchoClient {
    //definimos el Stub del cliente
    private static EchoObjectStub ss;
    
    public static void main(String[] args){
        //revisamos que los argumentos para ejecutar el programa son correctos
        int cant_a_pag;
        String num_tarj="";
        String cvv="";

        if (args.length<2) {
            System.out.println("Para ejecutar , hazlo en este formato: Echo <nombre o IP del Equipo> <numero de puerto>");
            System.exit(1);
        }
        //instanciamos el STUB
        ss = new EchoObjectStub();
        //le asignamos al STUB el puerto y nombre del equipo HOST (el nombre del servidor) 
        ss.setHostAndPort(args[0],Integer.parseInt(args[1]));
        String input,output;
        try{  
        //construyo un bucle infinito:
            //while(true){
                //preparo "apuntador" que es el lector de flujo para el teclado
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                //asigno a una variable y leo una linea del lector de flujo que leyo del teclado
                System.out.println("Ingrese la cantidad a pagar: ");
                cant_a_pag=Integer.parseInt(in.readLine());
                System.out.println("Ingrese su número de tarjeta: ");
                num_tarj=in.readLine();
                System.out.println("Ingrese su cvv: ");
                cvv=in.readLine();
                //Invocar el stub con el metodo remoto echo e Imprimir .. 
                //por pantalla lo que regreso el metodo remoto echo
                if(ss.pago(cant_a_pag,num_tarj,cvv)== 1)
                    System.out.println("El pago se ha realizado con éxito!!");
                else
                    System.out.println("Pago rechazado, compruebe su saldo o su tarjeta");
            //}
        }catch (IOException e){
            System.err.println("Falla conexion de E/S con el host:"+args[0]);
        }
    }
}
