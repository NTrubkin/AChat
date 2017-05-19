package org.nnstu5.ui;

import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.container.Message;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Vermenik Maxim
 *         <p>
 *         ControllerChat - реализует модельную часть mvc-паттерна визуального интерфейса чата.
 *         Содержит методы бизнес-логики. Не работает непосредственно с разметкой view и визуальными элементами
 */
public class Model {
    private Client client;
    private final ControllerChat controller;

    /**
     * Модель получает клиент через getClient().
     * Отпрвляет клиенту ссылку на себя (модель) через setModel()
     * Модель сохраняет ссылку на контроллер.
     * Вызов showHistory в этой же модели для показа истории сообщений.
     *
     * @param controller ControllerChat
     */
    Model(ControllerChat controller) {
        client = ClientLauncher.getClient();
        client.setModel(this);
        this.controller = controller;

        showHistory();
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
     * Запрос истории сообщений у клиента
     * получает от клиента List<Message> и отправляет в showMessages().
     */
    private void showHistory() {
        showMessages(client.getHistory());
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
        for (Message message : messages) {
            showMessage(message);
        }
    }
}