package org.nnstu5.ui;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.nnstu5.container.Conversation;

import java.util.ArrayList;

import org.nnstu5.ui.customElement.ContainerButton;

/**
 * @author Vermenik Maxim
 *         <p>
 *         ChatController - реализует контроллерную часть mvc-паттерна визуального интерфейса чата.
 *         Содержит методы-обработчики событий. Без бизнес-логики.
 */
public class ChatController {

    private Model model;

    @FXML
    private TextArea area;   // поле вывода сообщений
    @FXML
    private TextField field; // поле ввода сообщений
    @FXML
    private Label conversName;
    @FXML
    private Label nickname;
    @FXML
    private Label email;
    @FXML
    private ListView<Conversation> conversListView;
    @FXML
    private TextField newConversName;


    @FXML
    public void initialize() {
        // модель необходимо конструировать после того, как будут инициализированы поля разметки
        // иначе модель не сможет работать с полями
        model = new Model(this);
        conversListView.setItems(model.getConversations());

/*        conversListView.setCellFactory((ListView<Conversation> l) -> new ConversationCell());*/

        conversListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Conversation> ov, Conversation oldVal,
                 Conversation newVal) -> {
                    model.setConvers(newVal.getId());
                });

    }

    /**
     * Обрабатывает нажатие на кнопку отправить
     */
    @FXML
    void processSendButton() {
        String text = field.getText();
        model.sendMessage(text);
        field.clear();

    }

    /**
     * Выводит на экран новое сообщение
     *
     * @param text текст нового сообщения
     */
    void appendMessage(String text) {
        area.appendText(text);
        area.appendText("\n");
    }

    public void clearMessages() {
        area.clear();
    }

    public void setConversName(String name) {
        conversName.setText(name);
    }

    public void setNickname(String name) {
        nickname.setText(name);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    @FXML
    public void processCreateConversationButton() {
        model.createConversation(newConversName.getText());
        newConversName.clear();
    }
}