package org.nnstu5.server;

import org.nnstu5.ChatRules;
import org.nnstu5.database.ChatDatabase;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Andrey Kuznetsov
 *         <p>
 *         ServerLauncher управляет серверной частью rmi соединения
 *         Временная точка входа серверного приложения
 */
public class ServerLauncher {
    private static final String EXIT_CMD = "stop";

    private static Registry registry;
    private static ChatDatabase db = ChatDatabase.getInstance();
    /**
     * регистрация сервера в реестре RMI.
     * Остановка сервера и закрытие базы данных через консольный ввод "stop".
     *
     * @param args
     */
    public static void main(String[] args) {
            try {
                open();
                System.out.println("Server is ready.");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            while (true) {
                Scanner sc = new Scanner(System.in);
                String cmd = sc.nextLine();
                if(cmd.equals(EXIT_CMD)) { break; }
            }

            close();
        }

        private static void open() throws RemoteException, AlreadyBoundException {
            ServerRemote obj = new Server();
            registry = LocateRegistry.createRegistry(ChatRules.RMI_PORT);
            ServerRemote stub = (ServerRemote) UnicastRemoteObject.exportObject(obj, 0);

            registry.bind(ChatRules.RMI_BIND_KEY, stub);
        }

        private static void close() {
            try {
                registry.unbind(ChatRules.RMI_BIND_KEY);
                System.out.println("Server disconnected.");
            } catch (RemoteException | NotBoundException e) {
                System.out.println("Server disconnecting failed.");
                e.printStackTrace();
            }
            try {
                db.close();
            } catch (SQLException e) {
                System.out.println("Database closing failed.");
                e.printStackTrace();
            }

            System.exit(0);
        }
    }

