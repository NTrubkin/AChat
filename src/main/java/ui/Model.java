package ui;

import client.Client;
import client.ClientInter;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class Model extends Application {
    ClientInter clientInterimpl;


    public void modelSet (ClientInter clientInterimpl) throws Exception {

        this.clientInterimpl = clientInterimpl;

    }




    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
       try {
           clientInterimpl.setUi(root) ;
       } catch (Exception ex){};

        primaryStage.setTitle("AChat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);



    }

}