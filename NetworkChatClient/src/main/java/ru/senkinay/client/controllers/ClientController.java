package ru.senkinay.client.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.senkinay.client.ClientChat;
import ru.senkinay.client.messagehistory.MessageHistory;
import ru.senkinay.client.model.Network;
import ru.senkinay.client.model.ReadCommandListener;
import ru.senkinay.clientserver.Command;
import ru.senkinay.clientserver.CommandType;
import ru.senkinay.clientserver.commands.ClientMessageCommandData;
import ru.senkinay.clientserver.commands.UpdateUserListCommandData;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class ClientController {

    public static final int MESSAGE_HISTORY_ROW_COUNT = 100;

//    private static final List<String> USER_TEST_DATA = List.of(
//            "username1",
//            "username2",
//            "username3",
//            "username4"
//    );

    @FXML
    public TextArea textArea;
    @FXML
    public Button sendButton;
    @FXML
    public ListView<String> userList;
    @FXML
    public TextField textField;

    private ClientChat application;

    private MessageHistory messageHistory;



    public void sendMessage() {
        String message = textField.getText().trim();

        if(message.isEmpty()) {
            textField.clear();
            return;
        }

        String sender = null;
        if(!userList.getSelectionModel().isEmpty()) {
            sender = userList.getSelectionModel().getSelectedItem();
        }

        try {
            if (sender != null) {
                Network.getInstance().sendPrivateMessage(sender, message);
            } else {
                Network.getInstance().sendMessage(message);
            }


        } catch (IOException e) {
            application.showErrorDialog("Ошибка передачи данных по сети");
        }
        appendMessageToChar("Я", message);
    }

    private void appendMessageToChar(String sender, String message) {

        int textLengthBeforeAddMessage = textArea.getText().length();

        textArea.appendText(DateFormat.getDateTimeInstance().format(new Date()));
        textArea.appendText(System.lineSeparator());

        if (sender != null) {
            textArea.appendText(sender + ":");
            textArea.appendText(System.lineSeparator());
        }
        textArea.appendText(message);
        textArea.appendText(System.lineSeparator());
        textArea.appendText(System.lineSeparator());

        textField.setFocusTraversable(true);
        textField.clear();

        messageHistory.addMessageToHistoryFile(textArea.getText(textLengthBeforeAddMessage,textArea.getLength()));

    }

    public void setApplication(ClientChat application) {
        this.application = application;
    }

    private void createMessageHistory() {
        this.messageHistory = new MessageHistory(Network.getInstance().getUserName());
        messageHistory.createHistoryFile();
    }

    private void loadMessageHistory(){
        textArea.clear();
        textArea.setText(messageHistory.loadMessagesFromHistoryFile(MESSAGE_HISTORY_ROW_COUNT));
    }

    public void initializeMessageHandler() {
        Network.getInstance().addReadMessageListener(new ReadCommandListener() {
            @Override
            public void processReceivedCommand(Command command) {
                if (messageHistory == null) {
                    createMessageHistory();
                    loadMessageHistory();
                }
                if (command.getType() == CommandType.CLIENT_MESSAGE) {
                    ClientMessageCommandData data = (ClientMessageCommandData) command.getData();
                    appendMessageToChar(data.getSender(), data.getMessage());

                } else if (command.getType() == CommandType.UPDATE_USER_LIST) {
                    UpdateUserListCommandData data = (UpdateUserListCommandData) command.getData();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            userList.setItems(FXCollections.observableList(data.getUsers()));
                        }
                    });

                }

            }
        });
    }

}