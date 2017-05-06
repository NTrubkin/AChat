package org.nnstu5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.ui.Model;

/**
 * ClientChatApplication - точка входа клиентского приложения AChat
 */
public class ClientChatApplication extends Application {
    private static Model model;

    public static void main(String[] args) {
        launch();
    }

    /**
     * Запускает отрисовку визуального интерфейса
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("AChat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ClientLauncher.stop();
    }
}
