package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static final int PORT = 8189;
    private DataInputStream in;
    private DataOutputStream out;

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен и ожидает новые подключения");
            Socket clientSocket = serverSocket.accept();
            in  = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("Клиент подключен");

            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readUTF();
                        if (message.equalsIgnoreCase("/end")) {
                            break;
                        }
                        System.out.println("Сообщение от клиента: " + message);
                        System.out.println("Введите сообщение");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            System.err.println("Ошибка при подключении к порту " + PORT);
        }
    }

    public void sendMessage(String message) {
        if (!message.trim().isEmpty()) {
            try {
                out.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Ошибка отправки сообщения");
            }
        }
    }

    public static void main(String[] args) {
        Server server;
        try {
            server = new Server();
            server.start();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите сообщение");
                server.sendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
