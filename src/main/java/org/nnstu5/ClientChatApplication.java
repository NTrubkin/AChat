package org.nnstu5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.ui.Model;

/**
 * @author Vermenik Maxim
 *         <p>
 *         ClientChatApplication - точка входа клиентского приложения AChat
 */
public class ClientChatApplication extends Application {
    private static Model model;

    /**
     * Запускает главный метод start().
     * Реализует закрытие клиента после завершения приложения.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
        System.exit(0);
    }

    /**
     * Запускает отрисовку визуального интерфейса
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/authorizationAndRegistration.fxml"));
        primaryStage.setTitle("AChat");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Осуществляет закрытие графического интерфеса после нажатия на системную кнопку "закрыть".
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
