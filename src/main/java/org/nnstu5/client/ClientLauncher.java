package org.nnstu5.client;

import org.nnstu5.server.ServerRemote;

import javax.naming.OperationNotSupportedException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ClientLauncher управляет клиентской частью rmi соединения
 */
public class ClientLauncher {
    private static Registry registry;
    private static Client client;

    /**
     * Приватный конструктор. Предотвращает создание объектов класса
     */
    private ClientLauncher() {

    }

    /**
     * Запускает клиентскую часть, пытается соединиться с сервером
     * @return Client
     * @throws Exception RemoteException при ошибке соединения или NotBoundException
     */
    public static Client start() throws Exception {
        if(registry != null) {
            throw new IllegalStateException("Client already started");
        }
        else {
            registry = LocateRegistry.getRegistry("127.0.0.1", 1097);
            ServerRemote serverRemote = (ServerRemote) registry.lookup("Server");
            client = new Client(serverRemote);
            return client;
        }
    }
}
