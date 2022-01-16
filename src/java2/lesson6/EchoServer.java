package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static final int PORT = 8189;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен и ожидает новые подключения");
            Socket clientSocket = serverSocket.accept();
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            while (true) {
                String message = input.readUTF();
                if (message.equals("/end")) {
                    break;
                }
                output.writeUTF("Echo " + message);
            }

            System.out.println("Клиент подключен");
        } catch (IOException e) {
            System.err.println("Ошибка при подключении к порту " + PORT);
        }

    }
}
