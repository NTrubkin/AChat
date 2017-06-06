package org.nnstu5.ui;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.nnstu5.container.Conversation;

import java.util.ArrayList;

import org.nnstu5.container.Message;
import org.nnstu5.container.User;
import org.nnstu5.ui.cell.ConversationCell;
import org.nnstu5.ui.cell.MessageCell;
import org.nnstu5.ui.cell.UserCell;

/**
 * @author Vermenik Maxim
 *         <p>
 *         ChatController - реализует контроллерную часть mvc-паттерна визуального интерфейса чата.
 *         Содержит методы-обработчики событий. Без бизнес-логики.
 */
public class ChatController {

    private Model model;

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
    private ListView<User> friendsListView;
    @FXML
    private ListView<User> nonMemberConversListView;
    @FXML
    private ListView<Message> messagesListView;

    @FXML
    private TextField newConversName;
    @FXML
    public TextField newFriendEmail;

    @FXML
    public Button conversPaneButton;
    @FXML
    public Button friendsPaneButton;
    @FXML
    private Button nonMembersConversPaneButton;

    @FXML
    public AnchorPane friendsPane;
    @FXML
    public AnchorPane conversPane;
    @FXML
    private AnchorPane nonMembersConvers;

    @FXML
    public Label navLabel;

    @FXML
    public AnchorPane navPane;

    @FXML
    public void initialize() {
        // модель необходимо конструировать после того, как будут инициализированы поля разметки
        // иначе модель не сможет работать с полями
        model = new Model(this);

        conversListView.setItems(model.getConversations());
        conversListView.setCellFactory(lcv -> {
            ConversationCell cell = new ConversationCell();
            cell.setOnMouseClicked(event -> {
                Conversation item = cell.getItem();
                if (item != null) {
                    model.setConvers(item.getId());
                }
            });
            return cell;
        });

        friendsListView.setItems(model.getFriends());
        friendsListView.setCellFactory((ListView<User> l) -> new UserCell());

        nonMemberConversListView.setItems(model.getNonMembersConversation());
        nonMemberConversListView.setCellFactory(lcv -> {
            UserCell cell = new UserCell();
            cell.setOnMouseClicked(event -> {
                User item = cell.getItem();
                if (item != null) {
                    model.addUserToCurrentConvers(item.getId());
                    nonMemberConversListView.getSelectionModel().clearSelection();
                }
            });
            return cell;
        });

        messagesListView.setItems(model.getMessages());
        messagesListView.setCellFactory((ListView<Message> l) -> new MessageCell());

        showConversationsPane();
        navPane.setVisible(false);
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

    public void processAddFriendButton() {
        model.addFriend(newFriendEmail.getText());
        newFriendEmail.clear();
    }

    public void showFriendsPane() {
        friendsPane.setVisible(true);
        conversPane.setVisible(false);
        nonMembersConvers.setVisible(false);
        navLabel.setText("Друзья");
    }

    public void showConversationsPane() {
        friendsPane.setVisible(false);
        conversPane.setVisible(true);
        nonMembersConvers.setVisible(false);
        navLabel.setText("Беседы");
    }

    public void showNonMembersConversPane() {
        friendsPane.setVisible(false);
        conversPane.setVisible(false);
        nonMembersConvers.setVisible(true);
        navLabel.setText("Добавить в беседу");
    }

    public void processConversPaneButton() {
        showConversationsPane();
        navPane.setVisible(false);
    }

    public void processFriendsPaneButton() {
        showFriendsPane();
        navPane.setVisible(false);
    }

    @FXML
    public void processNavPaneButton() {
        navPane.setVisible(!navPane.isVisible());
    }

    public void processNonMembersConversPaneButton() {
        model.loadNonMembersConverastion();
        showNonMembersConversPane();
        navPane.setVisible(false);
    }

    public void setNonMembersConversPaneButtonState(boolean disabled) {
        nonMembersConversPaneButton.setDisable(disabled);
    }
}