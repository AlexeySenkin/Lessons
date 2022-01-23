package ru.senkinay.server.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    public static final String AUTH_OK_COMMAND = "/authOk";
    public static final String AUTH_COMMAND = "/auth";
    public static final String PRIVATE_MESSAGE_COMMAND = "/w";

    private MyServer server;
    private final Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String login;
    private String password;
    private String userName;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.server = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        inputStream  = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authenticate();
                readMessage();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to process message from client");
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to close connection");
                }
            }
        }).start();
    }

    private void authenticate() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if(message.startsWith(AUTH_COMMAND)) {
                String[] parts = message.split(" ");
                String login =  parts[1];
                String password =  parts[2];
                String userName = server.getAuthService().getUserNameByLoginAndPassword(login,password);
                if(userName==null) {
                    sendMessage("Неверный логин и пароль");
                } else {
                    sendMessage(String.format("%s %s",AUTH_OK_COMMAND,userName));
                    server.subscribe(this);
                    this.login = login;
                    this.password = password;
                    this.userName = userName;
                    return;
                }
            }
        }
    }

    private void readMessage() throws IOException {
      while (true) {
          String message = inputStream.readUTF().trim();
          System.out.println("message=" + message);
          if(message.startsWith("/end")) {
              return;
          } else {
              processMessage(message);
          }

      }
    }

    private void processMessage(String message) throws IOException {
        if(message.startsWith(PRIVATE_MESSAGE_COMMAND)) {
            this.server.messageToUser(message, this);
        } else {
            this.server.broadcastMessage(message, this);
        }


    }

    public void sendMessage(String message) throws IOException {
        this.outputStream.writeUTF(message);
    }

    private void closeConnection() throws IOException {
        server.unsubscribe(this);
        clientSocket.close();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

}
