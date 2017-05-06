package org.nnstu5.client;

import org.nnstu5.server.ServerRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ClientLauncher управляет клиентской частью rmi соединения
 */
public class ClientLauncher {
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
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1098);
        ServerRemote serverRemote = (ServerRemote) registry.lookup("Server");
        return new Client(serverRemote);
    }

    /**
     * Останавливает клиентскую часть
     * @throws Exception
     */
    public static void stop() throws Exception {
        // write stop client here
        System.out.println("Client thread is still running. Fix it");
    }
}
