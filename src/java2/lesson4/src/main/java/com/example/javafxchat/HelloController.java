package com.example.javafxchat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {


    @FXML public TextArea textArea;
    @FXML public Button sendButton;
    @FXML public ListView<String> userList;
    @FXML public TextField textField;

    public void sendMessage(ActionEvent actionEvent) {
        if (!textField.getText().isEmpty()) {
            textArea.appendText(textField.getText());
            textArea.appendText(System.lineSeparator());
            textField.clear();
        }
    }
}