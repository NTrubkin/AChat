package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInter extends Remote {

    void returnMessage(String message) throws RemoteException;
}
