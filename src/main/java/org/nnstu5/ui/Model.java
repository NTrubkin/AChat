package org.nnstu5.ui;

import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.container.Message;

import java.rmi.RemoteException;
import java.util.List;

/**
 * ControllerChat - реализует модельную часть mvc-паттерна визуального интерфейса чата.
 * Содержит методы бизнес-логики. Не работает непосредственно с разметкой view и визуальными элементами
 */
public class Model {
    private Client client;
    private final ControllerChat controller;

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
            Message message = new Message(text, 1);
            client.sendMessageToServer(message);
        } catch (RemoteException exc) {
            // sending message failed
        }
    }


    private void showHistory() {
        showMessages(client.getHistory());
    }

    /**
     * Запрашивает у контроллера отрисовку нового сообщения
     *
     * @param message
     */
    public void showMessage(Message message) {
        controller.appendMessage(message.getText());
    }

    private void showMessages(List<Message> messages) {
        for (Message message : messages) {
            controller.appendMessage(message.getText());
        }
    }
}