package ru.senkinay.server.chat;

import ru.senkinay.clientserver.Command;
import ru.senkinay.clientserver.CommandType;
import ru.senkinay.clientserver.commands.AuthCommandData;
import ru.senkinay.clientserver.commands.PrivateMessageCommandData;
import ru.senkinay.clientserver.commands.PublicMessageCommandData;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;


public class ClientHandler {
//    public static final String PRIVATE_MESSAGE_COMMAND = "/w";

    private final MyServer server;
    private final Socket clientSocket;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private String login;
    private String password;
    private String userName;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.server = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        inputStream  = new ObjectInputStream(clientSocket.getInputStream());
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        authenticate();
        readMessage();
        closeConnection();
//        new Thread(() -> {
//            try {
//                authenticate();
//                readMessage();
//            } catch (IOException e) {
//                System.err.println("Failed to process message from client");
//                e.printStackTrace();
//            } finally {
//                try {
//                    closeConnection();
//                } catch (IOException e) {
//                    System.err.println("Failed to close connection");
//                }
//            }
//        }).start();
    }

    private void authenticate() throws IOException {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String userName = server.getAuthService().getUserNameByLoginAndPassword(login,password);
                if(userName == null) {
                    try {
                        System.err.println("?????????? ?????????????????????? ??????????????");
                        closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(timerTask, 120000);
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }
            if (command.getType() == CommandType.AUTH) {
                AuthCommandData data = (AuthCommandData) command.getData();
                String login = data.getLogin();
                String password = data.getPassword();
                String userName = server.getAuthService().getUserNameByLoginAndPassword(login,password);
                if(userName == null) {
                    sendCommand(Command.errorCommand("???????????????? ?????????? ?? ????????????"));
                } else if (server.isUserNameBusy(userName)) {
                    sendCommand(Command.errorCommand("???????????????????????? ?? ?????????? ?????????????? ?? ?????????????? ?????? ????????????????????"));
                } else {
                    sendCommand(Command.authOkCommand(userName));
                    this.login = login;
                    this.password = password;
                    this.userName = userName;
                    server.subscribe(this);
                    return;
                }
            }
        }
    }

    public void sendCommand(Command command) throws IOException {
        outputStream.writeObject(command);
    }

    private Command readCommand() throws IOException {
        Command command = null;
        try {
            command = (Command) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to read class command");
            e.printStackTrace();
        }
        return command;
    }

    private void readMessage() throws IOException {
      while (true) {
          Command command = readCommand();

          if (command == null) {
              continue;
          }

          switch (command.getType()) {
              case END:
                  return;
              case PRIVATE_MESSAGE: {
                  PrivateMessageCommandData data = (PrivateMessageCommandData) command.getData();
                  String recipient = data.getReceiver();
                  String privateMessage = data.getMessage();
                  this.server.sendPrivateMessage(this,recipient,privateMessage);
                  System.err.println("PRIVATE_MESSAGE");
                  System.err.println("username=" + recipient);
                  System.err.println("message=" + data.getMessage());
                  break;
              }
              case PUBLIC_MESSAGE: {
                  PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                  this.server.broadcastMessage(data.getMessage(), this);
                  System.err.println("PUBLIC_MESSAGE");
                  System.out.println("message=" + data.getMessage());
                  break;
              }
          }
      }
    }

//    private void processMessage(String message) throws IOException {
//        this.server.broadcastMessage(message, this);
//        if(message.startsWith(PRIVATE_MESSAGE_COMMAND)) {
//            this.server.messageToUser(message, this);
//        } else {
//            this.server.broadcastMessage(message, this);
//        }
//    }



    public void closeConnection() throws IOException {
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
