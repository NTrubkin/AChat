package org.nnstu5.server;

import org.nnstu5.client.ClientRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements ServerRemote {

    private volatile List<ClientRemote> clients;

    protected Server() throws RemoteException {
        clients = new ArrayList<>();
    }

    public synchronized void registerClient(ClientRemote client) throws RemoteException {
        this.clients.add(client);
    }

    /**
     * Принимает и обрабатывает сообщение от клиента
     *
     * @param text текст сообщения
     * @throws RemoteException
     */
    public void recieveMessage(String text) throws RemoteException {
        for (ClientRemote client : clients) {
            client.showMessage(text);
        }
    }
}












