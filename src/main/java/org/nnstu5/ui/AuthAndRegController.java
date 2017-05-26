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
 * Контроллер авторизации и регистрации разметки "/chat.fxml"
 */
public class AuthAndRegController {
    private static final String CHAT_FXML = "/chat.fxml";
    private AuthAndRegModel model;

    @FXML
    public TextField authEmail;             // поле ввода электронной почты для авторизации
    @FXML
    public TextField authPass;              // поле ввода пароля для авторизации

    @FXML
    public TextField regEmail;              // поле ввода электронной почты для регистрации
    @FXML
    public TextField regNickname;           // поле ввода никнейма для регистрации
    @FXML
    public TextField regPassword;           // поле ввода пароля для регистрации
    @FXML
    public TextField regPassConfirmation;   // поле ввода подтверждения пароля для регистрации

    @FXML
    public AnchorPane authPane;             // окно авторизации
    @FXML
    public AnchorPane regPane;              // окно регистрации


    @FXML
    public void initialize() {
        // Инициализация авторизационной и регистрационной модели сразу после создания этого контроллера
        // и полей разметки

        model = new AuthAndRegModel();
        showAuth();
    }

    /**
     * Реагирует на нажатие кнопки авторизации
     *
     * @param event
     */
    public void processAuthButton(ActionEvent event) {
        model.authorizeAndLoadChat(authEmail.getText(), authPass.getText());
        loadChatScene(event);
    }

    /**
     * Отображает панель авторизации. Скрывает панель регистрации.
     */
    public void showAuth() {
        regPane.setVisible(false);
        authPane.setVisible(true);
    }

    /**
     * Отправляет модели регистрационные данные.
     */
    public void processRegButton() {
        model.registerUser(regEmail.getText(), regNickname.getText(), regPassword.getText(), regPassConfirmation.getText());
    }

    /**
     * Отображает панель регистрации. Скрывает панель авторизации.
     */
    public void showReg() {
        authPane.setVisible(false);
        regPane.setVisible(true);
    }

    /**
     * Загружает разметку для чата.
     *
     * @param event
     */
    private void loadChatScene(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(CHAT_FXML));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }
}
