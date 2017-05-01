package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Model extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("AChat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void main(String[] args) {
        launch(args);
        new Controller (this);



    }

}