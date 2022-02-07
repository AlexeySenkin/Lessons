package ru.senkinay.client.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.senkinay.client.ClientChat;
import ru.senkinay.client.dialogs.Dialogs;
import ru.senkinay.client.model.Network;
import ru.senkinay.client.model.ReadCommandListener;
import ru.senkinay.clientserver.Command;
import ru.senkinay.clientserver.CommandType;
import ru.senkinay.clientserver.commands.AuthOkCommandData;
import ru.senkinay.clientserver.commands.ErrorCommandData;

import java.io.IOException;

public class AuthController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button authButton;

    private ReadCommandListener readCommandListener;

    @FXML
    public void executeAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password =  passwordField.getText();

        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            //clientChat.showErrorDialog("Логин и пароль должны быть указаны");
            Dialogs.AuthError.EMPTY_CREDENTIAL.show();
            return;
        }

        if (!connectToServer()) {
            Dialogs.NetworkError.SERVER_CONNECT.show();
        }

        try {
            Network.getInstance().sendAuthMessage(login, password);
        } catch (IOException e) {
            //clientChat.showErrorDialog("Ошибка передачи данных по сети");
            Dialogs.NetworkError.SEND_MESSAGE.show();
            e.printStackTrace();
        }
    }

    private boolean connectToServer() {
        Network network = Network.getInstance();
        return network.isConnected() || network.connect();
    }

    public void initializeMessageHandler() {
        readCommandListener = getNetwork().addReadMessageListener(new ReadCommandListener() {
            @Override
            public void processReceivedCommand(Command command) {
                if (command.getType() == CommandType.AUTH_OK) {
                    AuthOkCommandData data = (AuthOkCommandData) command.getData();
                    String userName = data.getUsername();
                    Network.getInstance().setUserName(userName);
                    Platform.runLater(()-> {
                        ClientChat.INSTANCE.switchToMainCharWindow(userName);
                    });
                } else if (command.getType() == CommandType.ERROR)   {
                    ErrorCommandData data = (ErrorCommandData) command.getData();
                    String errorMessage = data.getErrorMessage();
                    Platform.runLater(() -> {
                        Dialogs.AuthError.INVALID_CREDENTIAL.show(errorMessage);
                    });
                }
            }
        });
    }

    public void close() {
        getNetwork().removeReadMessageListener(readCommandListener);
    }

    private Network getNetwork() {
        return Network.getInstance();
    }

}
