package org.nnstu5.ui;

import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.container.Message;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Controller - реализует модельную часть mvc-паттерна визуального интерфейса чата.
 * Содержит методы бизнес-логики. Не работает непосредственно с разметкой view и визуальными элементами
 */
public class Model {
    private Client client;
    private final Controller controller;

    Model(Controller controller) {
        try {
            client = ClientLauncher.start();
        } catch (Exception exc) {
            //start client failed
        }
        client.setModel(this);
        this.controller = controller;

        showHistory();

        // подгрузить историю сбщ здесь!
        // теперь модель конструируется после инициализации разметки
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


    private void  showHistory () {
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