package org.nnstu5.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.nnstu5.client.ClientLauncher;

import java.io.IOException;

/**
 * Created by TrubkinN on 09.05.2017.
 */
public class AuthAndRegController {
    private static final String CHAT_FXML = "/chat.fxml";
    private AuthAndRegModel model;

    @FXML
    public TextField authEmail;
    @FXML
    public TextField authPass;

    @FXML
    public TextField regEmail;
    @FXML
    public TextField regNickname;
    @FXML
    public TextField regPassword;
    @FXML
    public TextField regPassConfirmation;

    @FXML
    public AnchorPane authPane;
    @FXML
    public AnchorPane regPane;

    @FXML
    public void initialize() {
        model = new AuthAndRegModel();
        showAuth();
    }

    public void processAuthButton(ActionEvent event) {
        System.out.println("auth");
        model.authorizeAndLoadChat(authEmail.getText(), authPass.getText());
        loadChatScene(event);
    }


    public void showAuth() {
        regPane.setVisible(false);
        authPane.setVisible(true);
    }

    public void processRegButton() {
        model.registerUser(regEmail.getText(),regNickname.getText(), regPassword.getText(), regPassConfirmation.getText());
        System.out.println("reg");
    }

    public void showReg() {
        authPane.setVisible(false);
        regPane.setVisible(true);
    }

    private void loadChatScene(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(CHAT_FXML));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException exc) {
            System.out.println(exc);
        }
    }
}
