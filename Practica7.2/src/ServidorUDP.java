import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            // Se crea un socket UDP en el puerto 9876 para recibir datagramas.
            DatagramSocket socket = new DatagramSocket(9876);
            System.out.println("Servidor UDP esperando datagramas...");

            while (true) {
                // Se prepara un buffer para almacenar los datos del datagrama entrante.
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // El servidor espera pacientemente la llegada de un datagrama desde un cliente.
                socket.receive(packet);

                // Se obtiene el número enviado por el cliente desde los datos del datagrama.
                double numero = Double.parseDouble(new String(packet.getData(), 0, packet.getLength()));

                // Se realiza el cálculo del cuadrado del número recibido.
                double resultado = Math.pow(numero, 2);

                // Se convierte el resultado a bytes para enviarlo de vuelta al cliente.
                byte[] resultadoBytes = String.valueOf(resultado).getBytes();

                // Se prepara un nuevo datagrama con el resultado y se envía al cliente.
                DatagramPacket resultadoPacket = new DatagramPacket(resultadoBytes, resultadoBytes.length,
                        packet.getAddress(), packet.getPort());
                socket.send(resultadoPacket);

                // Se registran en la consola los detalles de la transacción.
                System.out.println("Número recibido: " + numero);
                System.out.println("Resultado enviado: " + resultado);
            }
        } catch (Exception e) {
            // En caso de excepción, se imprime la traza de la excepción para el diagnóstico.
            e.printStackTrace();
        }
    }
}
