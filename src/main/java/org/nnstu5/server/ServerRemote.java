package org.nnstu5.server;

import org.nnstu5.client.ClientRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRemote extends Remote {

    void recieveMessage(String text) throws RemoteException;

    void registerClient(ClientRemote client) throws RemoteException;

}

