package org.nnstu5.client;

import org.nnstu5.server.ServerRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Elizarova Julia
 *         <p>
 *         ClientLauncher управляет клиентской частью rmi соединения
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
     *
     * @return Client
     * @throws Exception RemoteException при ошибке соединения или NotBoundException
     */
    public static Client start() throws Exception {
        if (registry != null || client != null) {
            throw new IllegalStateException("Client already started");
        } else {
            registry = LocateRegistry.getRegistry("127.0.0.1", 1097);
            ServerRemote serverRemote = (ServerRemote) registry.lookup("Server");
            client = new Client(serverRemote);
            return client;
        }
    }

    /**
     * Геттер для доступа к клиенту из модели.
     *
     * @return клиент
     */
    public static Client getClient() {
        return client;
    }

    /**
     * Проверяет запущен ли клиент.
     *
     * @return true – запущен клиент, false – не запущен.
     */
    public static boolean isClientStarted() {
        return (client == null ? false : true);
    }
}

