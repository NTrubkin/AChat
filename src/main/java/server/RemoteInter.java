package server;

import client.ClientInter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInter extends Remote {

    void viewNewMessage(String message) throws RemoteException;

    void registerClient(ClientInter clientInterImpl) throws RemoteException;

}

