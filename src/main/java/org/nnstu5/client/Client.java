package org.nnstu5.client;

import org.nnstu5.server.ServerRemote;
import org.nnstu5.ui.Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
     * @param text текст сообщения
     */
    public void sendMessageToServer(String text) throws RemoteException {
        server.recieveMessage(text);
    }

    /**
     * Отображает на стороне этого клиента сообщение. Переопределено из ClientRemote
     *
     * @param text текст сообщения
     * @throws RemoteException
     */
    public void showMessage(String text) throws RemoteException {
        model.showMessage(text);
        System.out.println(text);
    }

    /**
     * Устанавливает модель визуального интерфейса для дальнейшего использования
     *
     * @param model модель ui (mvc паттерн)
     */
    public void setModel(Model model) {
        this.model = model;
    }
}