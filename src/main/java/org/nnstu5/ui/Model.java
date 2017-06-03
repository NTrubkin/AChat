package org.nnstu5.ui;

import javafx.collections.ObservableList;
import org.nnstu5.ChatRules;
import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.container.Conversation;
import org.nnstu5.container.Message;
import org.nnstu5.container.User;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vermenik Maxim
 *         <p>
 *         ChatController - реализует модельную часть mvc-паттерна визуального интерфейса чата.
 *         Содержит методы бизнес-логики. Не работает непосредственно с разметкой view и визуальными элементами
 */
public class Model {
    private Client client;
    private final ChatController controller;

    /**
     * Модель получает клиент через getClient().
     * Отпрвляет клиенту ссылку на себя (модель) через setModel()
     * Модель сохраняет ссылку на контроллер.
     * Вызов showHistory в этой же модели для показа истории сообщений.
     *
     * @param controller ChatController
     */
    Model(ChatController controller) {
        this.controller = controller;
        client = ClientLauncher.getClient();
        client.setModel(this);

        if (client.getConversations().size() > 0) {
            setConvers((client.getConversations()).get(0).getId());
        }

    }

    public void setConvers(int id) {
        client.setCurrentConvers(id);
        showMessages(client.loadCurrentConversHistory());
    }

    /**
     * Делегирует клиенту отправку сбщ
     *
     * @param text текст сбщ
     */
    void sendMessage(String text) {
        try {
            client.sendMessageToServer(text);
        } catch (RemoteException exc) {
            // sending message failed
        }
    }

    /**
     * Запрашивает у контроллера отрисовку нового сообщения.
     *
     * @param message контейнер для одного сообщения
     */
    public void showMessage(Message message) {
        controller.appendMessage("#" + message.getSenderId() + ": " + message.getText());
    }

    /**
     * Принимает от showHistory() коллекцию сообщений беседы List<Message> и каждое сообщение выводит через showMessage().
     *
     * @param messages список со множеством сообщений.
     */
    private void showMessages(List<Message> messages) {
        controller.clearMessages();
        for (Message message : messages) {
            showMessage(message);
        }
    }

    public void showCurrentConversation(String conversName) {
        controller.setConversName(conversName);
        updateNonMembersConversPaneButton();
    }

    public void showCurrentUser(User user) {
        String nickname = user.getNickname();
        String email = user.getEmail();

        controller.setNickname(nickname);
        controller.setEmail(email);
    }

    public ObservableList<Conversation> getConversations() {
        return client.getConversations();
    }

    public ObservableList<User> getFriends() {
        return client.getFriends();
    }

    public ObservableList<User> getNonMembersConversation() {
        return client.getNonMembersConversation();
    }

    public void createConversation(String name) {
        if (ChatRules.isValidConversationName(name)) {
            client.createConversation(name);
        }
    }

    public void addFriend(String email) {
        if (ChatRules.isValidUserEmail(email)) {
            client.addFriend(email);
        }
    }

    public void loadNonMembersConverastion() {
        client.loadNonMembersConverastion();
    }

    public void updateNonMembersConversPaneButton() {
        if (client.isAuthorizedUserCreatorOfCurrentConvers()) {
            controller.setNonMembersConversPaneButtonState(false);
        } else {
            controller.setNonMembersConversPaneButtonState(true);
        }
    }

    public void addUserToCurrentConvers(int userId) {
        client.addUserToCurrentConvers(userId);
    }
}