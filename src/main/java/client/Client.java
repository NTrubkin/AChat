package client;

import server.RemoteInter;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception, RemoteException {


        Registry myReg = LocateRegistry.getRegistry("127.0.0.1", 1098);
        RemoteInter ri = (RemoteInter) myReg.lookup("RemoteInterImpl");


        System.out.println("Введите имя");
        Scanner scanner = new Scanner(System.in);
        String name;
        name = scanner.nextLine();
        System.out.println("Ок!");

        new ClientInterImpl(name, ri);

    }
}
