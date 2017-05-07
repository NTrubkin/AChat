package org.nnstu5.client;

import org.nnstu5.container.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Имя интерфейса ClientInter изменено в соответствии с java naming conventions
public interface ClientRemote extends Remote {


    void showMessage(Message messages) throws RemoteException;
}
