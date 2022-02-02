package ru.senkinay.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.senkinay.client.controllers.AuthController;
import ru.senkinay.client.controllers.ClientController;
import ru.senkinay.client.model.Network;

import java.io.IOException;

public class ClientChat extends Application {

    public static ClientChat INSTANCE;

    private FXMLLoader chatWindowLoader;
    private FXMLLoader authLoader;

    private Stage primaryStage;
    private Stage authStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        initViews();
        getChatStage().show();
        getAuthStage().show();
        getAuthController().initializeMessageHandler();
    }

    @Override
    public void init() throws Exception {
        INSTANCE = this;
    }

    public void initViews() throws IOException {
        initChatWindow();
        initAuthDialogs();
    }

    private void initAuthDialogs() throws IOException {
        authLoader = new FXMLLoader();
        authLoader.setLocation(getClass().getResource("authDialog.fxml"));
        Parent authDialogPanel = authLoader.load();

        authStage = new Stage();
        authStage.initOwner(primaryStage);
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.setScene(new Scene(authDialogPanel));

    }

    private void initChatWindow() throws IOException {
        chatWindowLoader = new FXMLLoader();
        chatWindowLoader.setLocation(getClass().getResource("chat-template.fxml"));
        Parent root = chatWindowLoader.load();
        this.primaryStage.setScene(new Scene(root));
    }


    private AuthController getAuthController() {
        return authLoader.getController();
    }

    private ClientController getChatController() {
        return chatWindowLoader.getController();
    }




//    private void connectToServer(ClientController clientController) {
//        boolean result = Network.getInstance().connect();
//
//        if (!result) {
//            String errorMessage = CONNECTION_ERROR_MESSAGE;
//            System.err.println(errorMessage);
//            showErrorDialog(errorMessage);
//            return;
//        }
//
//
//        clientController.setApplication(this);
//
//        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent windowEvent) {
//                Network.getInstance().close();
//            }
//        });
//
//    }

    public void switchToMainCharWindow(String userName) {
        getChatStage().setTitle(userName);
        getChatController().initializeMessageHandler();
        getAuthController().close();
        getAuthStage().close();
    }


    public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getChatStage() {
        return this.primaryStage;
    }

    public Stage getAuthStage() {
        return authStage;
    }


}