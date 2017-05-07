package org.nnstu5.server;

import org.nnstu5.client.ClientRemote;
import org.nnstu5.container.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerRemote extends Remote {



    void registerClient(ClientRemote client) throws RemoteException;

    void recieveMessage(Message message) throws RemoteException;

    List<Message> getHistory() throws RemoteException;
}

