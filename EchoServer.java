import java.net.*;
import java.io.*;
public class EchoServer {
	// Objeto remoto ( es el Stub del servidor, conocido como Skeleton)
    private static EchoObjectSkeleton eo = new EchoObjectSkeleton();
    private static String myURL="localhost";
    private static ServerSocket serverSocket =null;
    private static Socket clientSocket = null;
    private static BufferedReader is = null;
    private static PrintWriter os = null;
    private static String cant_pag = new String();
    private static String num_tar = new String();
    private static String cvv = new String();

    
    public static void main(String[] args) {
        try {
            // obtengo el nombre de mi computadora
            myURL=InetAddress.getLocalHost().getHostName(); 
        } catch (UnknownHostException e) {
            System.out.println("Host Desconocido  :" + e.toString());
            System.exit(1);
        }
        try {
            // abro el socket para el servidor ( para que este a la escucha) en el puerto 1007
            serverSocket = new ServerSocket(1007);
        } catch (IOException e) {
            System.out.println(myURL + ": no puedo escuchar en el puerto: 1007, " +e.toString());
            System.exit(1);
        }
        System.out.println(myURL + ": EchoServer esta escuchando en el  puerto: 1007");
        // Servidor esta ahora a la escucha ( esperando conexiones)
        try {
            boolean listening = true;
            while(listening){   // bucle infinito
                try{
                    clientSocket = serverSocket.accept();// aceptamos conexion de un client
                    System.out.println("\nSe ha detectado una solicitud de pago");
                    // preparamos para leer  del socket
                    is = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                    //preparamos para escribir en el socket
                    os = new PrintWriter(clientSocket.getOutputStream());
                    // revisamos que en el socket el cliente ha enviado algun texto
                    cant_pag = is.readLine();
                    num_tar=is.readLine();
                    cvv=is.readLine();
                    os.println(eo.pago(Integer.parseInt(cant_pag),num_tar,cvv));//escribimos en el socket lo recibido mas mensaje
                    os.flush();//limpiamos el socket
                    os.close();
                    is.close();
                    clientSocket.close();
                }catch(IOException e){
                    System.err.println("Error aceptando al cliente" + e.getMessage());
                    e.printStackTrace();
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error enviando/recibiendo" + e.getMessage());
            e.printStackTrace();
        }
    }
}
