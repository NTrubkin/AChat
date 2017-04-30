package ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private TextArea area;
    @FXML
    private TextField field;

    @FXML
    void data() {
        String text = field.getText();
        area.appendText(text);
        area.appendText("\n");
        System.out.println("!!!");
      //field.setFocusTraversable(false);
        field.setText("");

    }

    @FXML
    void send() {
        data();
    }

}