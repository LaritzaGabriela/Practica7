import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Estos comandos nos ayudan a conectarnos al servidor
            Socket socket = new Socket("localhost", 12345);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // pedimos el numero
            System.out.print("Ingrese un número: ");
            double numero = scanner.nextDouble();

            // enviamos el numero
            outputStream.writeDouble(numero);

            // Recibimos resultado
            long resultado = inputStream.readLong();
            System.out.println("El cuadrado del número es: " + resultado);
            // cerramos la coexion
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
