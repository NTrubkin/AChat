package org.nnstu5.client;

import org.nnstu5.container.CurrentUser;
import org.nnstu5.container.Message;
import org.nnstu5.container.User;
import org.nnstu5.server.ServerRemote;
import org.nnstu5.ui.Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Client extends UnicastRemoteObject implements ClientRemote {

    private ServerRemote server;
    private Model model;
    private User authorizedUser;

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
        server.recieveMessage(new Message(text,authorizedUser.getId()));
    }

    /**
     * Отображает на стороне этого клиента сообщение. Переопределено из ClientRemote
     *
     * @param message текст сообщения
     * @throws RemoteException
     */
    public void showMessage(Message message) throws RemoteException {
        model.showMessage(message);
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
            return server.getHistory(authorizedUser.getId());
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }

    public void registerUser(CurrentUser currentUser) {
        try {
            server.registerUser(currentUser);
        } catch (RemoteException e) {
            System.out.println("Remote error");
            e.printStackTrace();
        }
    }

    public User authUser(CurrentUser user) {
        try {
            authorizedUser = server.authUser(user);
            return authorizedUser;
        } catch (RemoteException e) {
            System.out.println("Remote error");
            e.printStackTrace();
            return null;
        }
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }
}