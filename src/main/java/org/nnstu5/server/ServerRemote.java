package org.nnstu5.server;

import org.nnstu5.client.ClientRemote;
import org.nnstu5.container.CurrentUser;
import org.nnstu5.container.Message;
import org.nnstu5.container.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Andrey Kuznetsov
 *         <p>
 *         ServerRemote – интерфейс сервера, который определяет методы для удаленного использования клиентом.
 */
public interface ServerRemote extends Remote {

    void registerClient(ClientRemote client) throws RemoteException;

    void recieveMessage(Message message) throws RemoteException;

    List<Message> getHistory(int initiatorId) throws RemoteException;

    void registerUser(CurrentUser currentUser) throws RemoteException;

    User authUser(CurrentUser currentUser) throws RemoteException;
}

