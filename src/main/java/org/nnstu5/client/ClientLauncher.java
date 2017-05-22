package org.nnstu5.client;

import org.nnstu5.ChatRules;
import org.nnstu5.database.ChatDatabase;
import org.nnstu5.server.Server;
import org.nnstu5.server.ServerLauncher;
import org.nnstu5.server.ServerRemote;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
            try {

                registry = LocateRegistry.getRegistry("localhost", ChatRules.RMI_PORT);
                ServerRemote serverRemote = (ServerRemote) registry.lookup(ChatRules.RMI_BIND_KEY);
                client = new Client(serverRemote);

            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();

            }
        }
        return client;
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
        return (client != null);
    }
}

