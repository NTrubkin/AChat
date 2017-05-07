package org.nnstu5.server;

import org.nnstu5.client.Client;
import org.nnstu5.client.ClientRemote;
import org.nnstu5.container.Message;
import org.nnstu5.database.ChatDatabase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements ServerRemote {

    private volatile List<ClientRemote> clients;
    private ChatDatabase db = ChatDatabase.getInstance();

    protected Server() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override

    public synchronized void registerClient(ClientRemote client) throws RemoteException {
        this.clients.add(client);
    }

    /**
     * Принимает и обрабатывает сообщение от клиента
     *
     * @throws RemoteException
     */

    public void recieveMessage(Message message) throws RemoteException {
        try {
            db.sendMessage(message.getText(), 1, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(ClientRemote client: clients) {
            client.showMessage(message);
        }
    }


    public List<Message> getHistory() throws RemoteException {
        try {
            return db.getMessages(1, 1);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}












