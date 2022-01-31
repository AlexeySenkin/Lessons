package java2.lesson6_part2.Client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.function.Consumer;

public class ClientController {


    @FXML public TextArea textArea;
    @FXML public Button sendButton;
    @FXML public ListView<String> userList;
    @FXML public TextField textField;

    private Network network;
    private ClientChat application;



    public void sendMessage(ActionEvent actionEvent) {
        String message = textField.getText();
        appendMessageToChar(message);
        try {
            network.sendMessage(message);
        } catch (IOException e) {
            application.showErrorDialog("Ошибка передачи данных по сети");
        }
    }

    private void appendMessageToChar(String message) {
        if (!textField.getText().isEmpty()) {
            textArea.appendText(textField.getText());
            textArea.appendText(System.lineSeparator());
            textField.clear();
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
        network.waitMessages(new Consumer<String>() {
            @Override
            public void accept(String message) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        appendMessageToChar(message);
                    }
                });

            }
        });

    }

    public void setApplication(ClientChat application) {
        this.application = application;
    }
}