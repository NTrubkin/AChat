package org.nnstu5.client;

import org.nnstu5.container.Message;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Elizarova Julia
 *         <p>
 *         ClientRemote – интерфейс клиента, который определяет методы для удаленного использования сервером.
 */

public interface ClientRemote extends Remote {

    void showMessage(Message messages) throws RemoteException;
}
