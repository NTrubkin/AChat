package server;

import client.ClientInter;
import javafx.scene.Parent;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInter extends Remote {

    void viewNewMessage(Parent model) throws RemoteException;

    void registerClient(ClientInter clientInterImpl) throws RemoteException;

}

