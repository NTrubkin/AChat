package ui;

import client.Client;
import client.ClientInter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Model extends Application {



    ClientInter clientInterimpll;
//



    public void setClient (ClientInter clientinterimpl) {
       this.clientInterimpll = clientinterimpl;


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("AChat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        Controller p =new Controller();
        p.send(clientInterimpll);
        new Client(this);

    }

    public static void main(String[] args) {

        launch(args);


    }

}