package org.nnstu5.ui;

import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;

import java.rmi.RemoteException;

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
     * Запрашивает у контроллера отрисовку нового сообщения
     *
     * @param text
     */
    public void showMessage(String text) {
        controller.appendMessage(text);
    }
}