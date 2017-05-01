package client;

import javafx.scene.Parent;
import ui.Model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInter extends Remote {

    void returnMessage(Parent model) throws RemoteException;
    void setUi(Parent model) throws RemoteException;
}
