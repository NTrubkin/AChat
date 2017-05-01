package ui;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextArea area;
    @FXML
    private TextField field;


   public Controller (Controller cnt) {

        Client clnt =  new Client();
        clnt.Client(cnt);

    }

    void setClient (Client clientinterimp){

    }



    @FXML
    void data() /* throws RemoteException */{



       /* ClientInter clientInterImpl = new ClientInterImpl();*/

        String text = field.getText();

        area.appendText(text);
        area.appendText("\n");
        field.setText("");

        /*clientInterImpl.sendMessage(text);*/

    }

    @FXML
    void send() /*throws RemoteException */{
    }

}