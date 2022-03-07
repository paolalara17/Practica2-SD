import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class EchoObjectSkeleton implements EchoInterface {
    String myURL="localhost";
    //Constructor de la clase EchoObjectSkeleton
    public EchoObjectSkeleton()
    {
        try {
            // obtengo el nombre del equipo donde estoy ejecutando y lo guardo en la propiedad MyURL
            myURL=InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){
            // si no pude conocer el nombre del equipo, mantengo el nombre localhost para MyURL
            myURL="localhost";
        }
    }
    
    public int pago(int cantidad_a_pagar, String tarjeta, String cvv)throws java.rmi.RemoteException{

        Random r = new Random();
        int saldotar = r.nextInt(1000001);

        String numtars = "";
        int digitos;
        int cartar[] = new int[16];
        String tarjest="";
        String tiptar="";

        int revtar=0;
        
        try{
            
            numtars = tarjeta;
            
            if(numtars.equals("0000000000000000") || numtars.equals("000000000000")){
                
                tarjest ="Tarjeta Inválida";
                tiptar = "Tarjeta Inválida";
                revtar = 0;

            }else{ 
            
                for(int i=0; i<numtars.length();i++){
                    cartar[i] = Integer.parseInt(numtars.substring(i,i+1));
                }

                int sum = 0;
                int length = cartar.length;
                int digit;

                for(int i = 0; i < length; i++) {
                    // sacar los digitos en orden inverso
                    digit= cartar[length - i - 1];

                    // cada segundo número se multiplica por 2
                    if(i % 2 == 1) {
                        digit = digit * 2;
                    }
                    if(digit > 9){
                        digit = digit - 9;
                    }
                    sum = sum + digit;
                }
                
                int res = sum %10;
                
                if(res == 0){

                    tarjest ="Tarjeta Válida";
                    String numtarsaux1 = numtars.substring(0,2);
                    String numtarsaux2 = numtars.substring(0,1);

                    if(numtarsaux1.equals("51") || numtarsaux1.equals("52") || numtarsaux1.equals("53") || numtarsaux1.equals("54") || numtarsaux1.equals("55")){

                        tiptar = "Mastercard";

                    }else if(numtarsaux2.equals("4")){

                        tiptar = "Visa";

                    }else{

                        tiptar = "--------------";

                    }

                    System.out.println(tarjest + "\n" + "Tipo de tajeta: " + tiptar);

                    if(cvv.length()==3){
                        System.out.println("CVV: "+cvv+" valido");
                        if(saldotar>=cantidad_a_pagar){
                            System.out.println("Pago exitoso, saldo restante en tarjeta: "+ (saldotar - cantidad_a_pagar));
                            revtar = 1;
                        }else{
                            System.out.println("Pago fallido, saldo insuficiente :(");
                        }
                    }else{
                        System.out.println("CVV: "+cvv+" invalido");
                    }


                }else{

                    tarjest ="Tarjeta Inválida";
                    tiptar = "Tarjeta Inválida";
                    System.out.println(tarjest);

                }
            }

        }catch(Exception e){
            
            System.out.println(e.getMessage());
            
            
        }

        return revtar;

    }
    
}