package org.nnstu5.ui;

import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.container.Conversation;
import org.nnstu5.container.Message;

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
        client = ClientLauncher.getClient();
        client.setModel(this);
        this.controller = controller;

        client.loadConversations();
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

    /**
     * Распаковывает список конференций. Передает их в контроллер (по одной).
     * Очищает старые конференции из списка.
     *
     * @param conversations
     */
    public void showConversations(ArrayList<Conversation> conversations) {
        controller.clearMessages();
        for (Conversation conversation : conversations) {
            controller.showConversation(conversation.getName(), conversation.getId());
        }
    }
}