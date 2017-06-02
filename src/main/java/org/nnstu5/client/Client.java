package org.nnstu5.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.nnstu5.container.Conversation;
import org.nnstu5.container.CurrentUser;
import org.nnstu5.container.Message;
import org.nnstu5.container.User;
import org.nnstu5.server.ServerRemote;
import org.nnstu5.ui.Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elizarova Julia
 *         <p>
 *         Client обеспечивает взаимосвязь между моделью и сервером.
 */

public class Client extends UnicastRemoteObject implements ClientRemote {

    private ServerRemote server;
    private Model model;
    private User authorizedUser;    // контейнер с публичной информацией о пользователе.
    private ObservableList<Conversation> conversations = FXCollections.observableArrayList();

    private Conversation currentConvers;

    /**
     * Получает ссылку на сервер и сохраняет в переменные экземпляра. Регистрирует клиент на сервере.
     *
     * @param server
     * @throws RemoteException
     */
    public Client(ServerRemote server) throws RemoteException {
        this.server = server;
        server.registerClient(this);
    }

    public void loadConversations() {
        try {
            conversations.clear();
            conversations.addAll((ArrayList) server.getConversations(authorizedUser.getId()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправляет серверу сообщение
     *
     * @param text текст сообщения
     * @throws RemoteException
     */
    public void sendMessageToServer(String text) throws RemoteException {
        server.recieveMessage(new Message(text, authorizedUser.getId()), currentConvers.getId());
    }

    /**
     * Отображает на стороне этого клиента сообщение. Переопределено из ClientRemote
     *
     * @param message текст сообщения
     * @throws RemoteException
     */

    public void showMessage(Message message) throws RemoteException {
        try {
            model.showMessage(message);
        } catch (NullPointerException exc) {
            System.out.println("model is null");
            exc.printStackTrace();
        }
    }


    /**
     * Устанавливает модель визуального интерфейса для дальнейшего использования
     *
     * @param model модель ui (mvc паттерн)
     */
    public void setModel(Model model) {
        this.model = model;
        model.showCurrentUser(authorizedUser);
    }

    /**
     * Запрашивает у сервера историю сообщений.
     *
     * @return коллекция сообщений беседы в случае успешного получения от сервера, в случае ошибки – пустой список.
     */

    public List<Message> loadCurrentConversHistory() {
        try {
            return server.getHistory(currentConvers.getId(), authorizedUser.getId());
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Отправляет серверу контейнер CurrentUser с регистрационными данными.
     *
     * @param currentUser контейнер для хранения и передачи приватной информации об одном пользователе.
     */
    public void registerUser(CurrentUser currentUser) {
        try {
            server.registerUser(currentUser);
        } catch (RemoteException e) {
            System.out.println("Remote error");
            e.printStackTrace();
        }
    }

    /**
     * Отправляет серверу контейнер CurrentUser с авторизационными данными.
     * Получает контейнер User и сохраняет в переменные экземпляра.
     *
     * @param user – контейнер currentUser для хранения и передачи приватной информации об одном пользователе
     * @return autorizedUser – контейнер User, сохраненный в переменной экземпляра. Возращается в AuthAndRegModel.
     */
    public User authorizeUser(CurrentUser user) {
        try {
            authorizedUser = server.authUser(user);
            loadConversations();
            return authorizedUser;
        } catch (RemoteException e) {
            System.out.println("Remote error");
            e.printStackTrace();
            return null;
        }
    }

    public void setCurrentConvers(int id) {
        for (Conversation conversation : conversations) {
            if (conversation.getId() == id) {
                currentConvers = conversation;
                model.showCurrentConversation(conversation.getName());
                return;
            }
        }
    }

    public int getCurrentConversationId() {
      return currentConvers.getId();
    }

    public ObservableList<Conversation> getConversations() {
        return conversations;
    }
    public void createConversation(String name) {
        try {
            server.createConversation(name, authorizedUser.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        loadConversations();
    }

    public void addFriend(String email) {
        try {
            server.addFriend(email,authorizedUser.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException exc) {
            System.out.println("Illegal argument by adding new friend");
        }
    }
}


