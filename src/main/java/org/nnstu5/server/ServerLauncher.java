package org.nnstu5.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ServerLauncher управляет серверной частью rmi соединения
 * Временная точка входа серверного приложения
 */
public class ServerLauncher {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1097);
            ServerRemote serverRemote = new Server();

            registry.rebind("Server", serverRemote);
            System.out.println("Server is ready");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
