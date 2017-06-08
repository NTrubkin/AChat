package org.nnstu5.server;

import org.nnstu5.client.Client;
import org.nnstu5.client.ClientRemote;
import org.nnstu5.container.Conversation;
import org.nnstu5.container.CurrentUser;
import org.nnstu5.container.Message;
import org.nnstu5.container.User;
import org.nnstu5.database.ChatDatabase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Kuznetsov
 *         <p>
 *         Обеспечивает взаимосвязь между клиентом и базой данных.
 */

public class Server implements ServerRemote {

    private volatile List<ClientRemote> clients;
    private ChatDatabase db = ChatDatabase.getInstance();

    /**
     * конструктор для создания коллекции, которая будет хранить ссылки на клиенты.
     */
    protected Server() throws RemoteException {
        clients = new ArrayList<>();
    }

    /**
     * регистрирует клиентов: добавляет в List<ClientRemote> ссылки на клиенты.
     *
     * @throws RemoteException
     */
    @Override

    public synchronized void registerClient(ClientRemote client) throws RemoteException {
        clients.add(client);
    }

    public synchronized void unregisterClient(ClientRemote client) throws RemoteException {
        clients.remove(client);
    }

    /**
     * Принимает и обрабатывает сообщение от клиента
     *
     * @throws RemoteException
     */

    public void recieveMessage(Message message, int conversId) throws RemoteException {
        try {
            db.sendMessage(message.getText(), conversId, message.getSenderId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (ClientRemote client : clients) {
            if (client.getCurrentConversationId() == conversId) {
                client.showMessage(message);
            }
        }
    }


    /**
     * Получает из базы данных коллецию сообщений беседы.
     *
     * @param initiatorId участники беседы
     * @return List<Message> коллецию сообщений беседы в случае успеха, иначе – пустой список.
     * @throws RemoteException
     */

    public List<Message> getHistory(int conversId, int initiatorId) throws RemoteException {
        try {
            return db.getMessages(conversId, initiatorId);
        } catch (SQLException e) {
            System.out.println("sql error");
            return new ArrayList<>();
        }
    }

    /**
     * Добавляет в базу данных нового пользователя
     *
     * @param currentUser контейнер с регистрационными приватными данными пользователя.
     */
    public void registerUser(CurrentUser currentUser) {
        try {
            db.registerUser(currentUser);
        } catch (SQLException e) {
            System.out.println("Database error");
            e.printStackTrace();
        }
    }

    /**
     * Запрашивает у базы данных информацию об авторизованном пользователе
     *
     * @return контейнер User с публичной информацией в случае успеха, иначе – пустую ссылку.
     */
    public User authUser(CurrentUser currentUser) {
        try {
            return db.authorizeUser(currentUser);
        } catch (SQLException e) {
            System.out.println("Database error");
            e.printStackTrace();
            return null;
        }
    }

    public List<Conversation> getConversations(int userId) {
        try {
            return db.getUserConversations(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void createConversation(String name, int creatorId) {
        try {
            db.createConversation(name, creatorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriend(String email, int initiatorId) {
        try {
            db.addFriend(initiatorId, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getFriends(int initiatorId) {
        try {
            return db.getUserFriends(initiatorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public List<User> getNonMembersConversation(int userId, int conversId) {
        try {
            return db.getNonMemberFriends(userId, conversId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addUserToConvers(int conversId, int userId, int initiatorId) {
        try {
            db.addUserToConversation(conversId, userId, initiatorId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getConversationMembers(int conversId) {
        try {
            return db.getConversationMembers(conversId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}












