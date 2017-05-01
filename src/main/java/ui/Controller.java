package ui;

import client.Client;
import client.ClientInter;
import client.ClientInterImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextArea area;
    @FXML
    private TextField field;


    @FXML
    void data() /* throws RemoteException */ {



       /* ClientInter clientInterImpl = new ClientInterImpl();*/

        String text = field.getText();
        area.appendText(text);
        area.appendText("\n");
        field.setText("");

        /*clientInterImpl.sendMessage(text);*/

    }
    @FXML
    void send() {
        data();
    }


}