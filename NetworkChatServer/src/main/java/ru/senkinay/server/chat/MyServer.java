package ru.senkinay.server.chat;

import ru.senkinay.clientserver.Command;
import ru.senkinay.server.chat.auth.AuthService;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {


    private final List<ClientHandler> clients = new ArrayList<>();

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private AuthService authService;

    public void start(int port) {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server has been started");
            authService = new AuthService();
            while (true) {
                waitAndProcessClientConnection(serverSocket);
            }
        } catch (IOException e) {
            System.err.println("Failed to bind port");
        }
    }

    public  void stop() {
      executorService.shutdown();
    }

    private void waitAndProcessClientConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for new client connection");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client has been connected");
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        executorService.execute(()->{
            try {
                clientHandler.handle();
            } catch (IOException e) {
                System.err.println("Failed client thread");
                e.printStackTrace();
            } finally {
                try {
                    clientHandler.closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to close connection");
                    e.printStackTrace();
                }
            }
        });
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if(client != sender) {
                client.sendCommand(Command.clientMessageCommand(sender.getUserName(),message));
            }
      }
    }

    public synchronized void sendPrivateMessage(ClientHandler sender, String recipient, String privateMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender && client.getUserName().equals(recipient)) {
                System.err.println("sender=" + sender.getUserName());
                System.err.println("recipient=" + recipient);
                client.sendCommand(Command.clientMessageCommand(sender.getUserName(),privateMessage));
                break;
            }
        }
    }

    public synchronized boolean isUserNameBusy(String userName) {
        for (ClientHandler client : clients) {
            if (client.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

//    public void messageToUser(String message, ClientHandler sender) throws IOException {
//        String[] parts = message.split(" ");
//        String userName = parts[1];
//        String messageText = message.substring(parts[0].length() + parts[1].length() + 2);
//        for (ClientHandler client : clients) {
//            if(client.getUserName().equals(userName)) {
//                client.sendMessage(messageText);
//                break;
//            }
//        }
//    }

    public synchronized void subscribe(ClientHandler client) throws IOException {
        this.clients.add(client);
        notifyClientUserListUpdated();
    }

    public synchronized void unsubscribe(ClientHandler client) throws IOException {
        this.clients.remove(client);
        notifyClientUserListUpdated();
    }

    public AuthService getAuthService() {
        return authService;
    }

    private void notifyClientUserListUpdated() throws IOException {
        List<String> userListOnLine = new ArrayList<>();
        for (ClientHandler client : clients) {
            userListOnLine.add(client.getUserName());
        }

        for (ClientHandler client : clients) {
            client.sendCommand(Command.updateUserListCommand(userListOnLine));
        }
    }

}
