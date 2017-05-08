package org.nnstu5.client;

import org.nnstu5.container.Message;
import org.nnstu5.server.ServerRemote;
import org.nnstu5.ui.Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Client extends UnicastRemoteObject implements ClientRemote {

    private ServerRemote server;
    private Model model;

    protected Client(ServerRemote server) throws RemoteException {
        this.server = server;
        server.registerClient(this);
    }

    /**
     * Отправляет серверу сообщение
     *
     * @param message текст сообщения
     */
    public void sendMessageToServer(Message message) throws RemoteException {
        server.recieveMessage(message);
    }

    /**
     * Отображает на стороне этого клиента сообщение. Переопределено из ClientRemote
     *
     * @param messages текст сообщения
     * @throws RemoteException
     */
    public void showMessage(Message messages) throws RemoteException {
        model.showMessage(messages);
    }


    /**
     * Устанавливает модель визуального интерфейса для дальнейшего использования
     *
     * @param model модель ui (mvc паттерн)
     */
    public void setModel(Model model) {
        this.model = model;
    }


    public List<Message> getHistory() {
        try {
            return server.getHistory();
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }
}