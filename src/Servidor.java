import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + socket.getInetAddress());

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                // recibimos el numero
                double numero = inputStream.readDouble();
                System.out.println("Número recibido: " + numero);

                // hacemos la raiz cuadrada
                long resultado = Math.round(Math.pow(numero, 2));

                // Enviar el resultado al cliente
                outputStream.writeDouble(resultado);
                System.out.println("Resultado enviado: " + resultado);

                // Cerrar conexiones
                socket.close();
                inputStream.close();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
