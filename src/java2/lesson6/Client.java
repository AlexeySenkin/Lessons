package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private DataInputStream in;
    private DataOutputStream out;

    public void openConnection() throws IOException {
        String SERVER_ADR = "localhost";
        int SERVER_PORT = 8189;
        Socket socket = new Socket(SERVER_ADR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = in.readUTF();
                    if (messageFromServer.equalsIgnoreCase("/end")) {
                        break;
                    }
                    System.out.println("Сообщение от сервера: " + messageFromServer);
                    System.out.println("Введите сообщение");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
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
        Client client;
        try {
            System.out.println("Клиент запущен! Можно отправить сообщение.");
            client = new Client();
            client.openConnection();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите сообщение");
                client.sendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
