import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            // Se crea un objeto Scanner para obtener la entrada del usuario.
            Scanner scanner = new Scanner(System.in);

            // Se inicializa un socket UDP para la comunicación con el servidor.
            DatagramSocket socket = new DatagramSocket();

            // Se especifica la dirección IP del servidor al que se enviará el mensaje.
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // Se solicita al usuario que ingrese un número para ser enviado al servidor.
            System.out.print("Ingrese un número: ");
            double numero = scanner.nextDouble();

            // Se convierte el número en bytes para ser transmitido a través del socket.
            byte[] numeroBytes = String.valueOf(numero).getBytes();

            // Se crea un paquete que contiene el número y se envía al servidor.
            DatagramPacket numeroPacket = new DatagramPacket(numeroBytes, numeroBytes.length, serverAddress, 9876);
            socket.send(numeroPacket);

            // Se prepara un paquete para recibir la respuesta del servidor.
            byte[] buffer = new byte[1024];
            DatagramPacket resultadoPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(resultadoPacket);

            // Se convierte la respuesta del servidor de bytes a un número.
            double resultado = Double.parseDouble(new String(resultadoPacket.getData(), 0, resultadoPacket.getLength()));

            // Se muestra el resultado al usuario.
            System.out.println("El cuadrado del número es: " + resultado);

            // Se cierra el socket después de completar la comunicación.
            socket.close();
        } catch (Exception e) {
            // Se manejan las excepciones imprevistas mostrando información de error.
            e.printStackTrace();
        }
    }
}
