package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("!");
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        System.out.println("!!");
        primaryStage.setTitle("Hello World");
        System.out.println("!!!");
        primaryStage.setScene(new Scene(root, 800, 600));
        System.out.println("!!!!");
        primaryStage.show();
        System.out.println("!!!!!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
