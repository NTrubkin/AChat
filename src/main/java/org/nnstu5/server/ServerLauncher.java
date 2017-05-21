package org.nnstu5.server;

import org.nnstu5.database.ChatDatabase;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.ServerException;
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
    private static ChatDatabase db = ChatDatabase.getInstance();  // доступ к базе данных.
    static Registry registry;

    /**
     * регистрация сервера в реестре RMI.
     * Остановка сервера и закрытие базы данных через консольный ввод "stop".
     *
     * @param args
     */
    public static void main(String[] args) {
            try {
                Server obj = new Server();
                registry = LocateRegistry.createRegistry(1090);
                ServerRemote stub = (ServerRemote) UnicastRemoteObject.exportObject(obj, 0);

                registry.bind("Server", stub);
                System.out.println("Server is ready");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            while (true){}
            /*Scanner sc = new Scanner(System.in);
            String string = sc.nextLine();
            if (string.equals("stop")) {
                try {
                    registry.unbind("Server");
                    System.out.println("Server disconnected");
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
                try {
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

