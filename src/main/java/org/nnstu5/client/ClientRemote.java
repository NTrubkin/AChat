package org.nnstu5.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Имя интерфейса ClientInter изменено в соответствии с java naming conventions
public interface ClientRemote extends Remote {

    void showMessage(String text) throws RemoteException;
}
