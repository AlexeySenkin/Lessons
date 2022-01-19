package java2.lesson6_part2.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientChat extends Application {

    public static final int SERVER_PORT = 8189;
    public static final String SERVER_HOST = "localhost";

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("chat-template.fxml"));
        Parent load = fxmlLoader.load();

        Scene scene = new Scene(load);

        stage.setTitle("Онлайн чат GeegBrains");
        stage.setScene(scene);

        ClientController controller = fxmlLoader.getController();
        controller.userList.getItems().addAll("Пользователь1","Пользователь2","Пользователь3","Пользователь4");
        stage.show();
        
        connectToServer();
    }

    private void connectToServer() {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {

        } catch (UnknownHostException e) {

        } catch (IOException e){

        }

    }

    public static void main(String[] args) {
        launch();
    }
}