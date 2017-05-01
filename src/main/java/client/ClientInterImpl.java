package client;

import server.RemoteInter;
import ui.Controller;
import ui.Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientInterImpl extends UnicastRemoteObject implements ClientInter {

    private RemoteInter remoteInterImpl;
    private String name = null;

    public ClientInterImpl(Model cnt, RemoteInter remoteInterImpl) throws RemoteException {

        //this.name = name;
        this.remoteInterImpl = remoteInterImpl;
        remoteInterImpl.registerClient(this);
        cnt.setClient(this);

//      Scanner scanner = new Scanner(System.in);    // 1 ввод
//        String message;
//        int j = 0;
//        while (true) {
//
//
//            message = scanner.nextLine();           //  2 собственно, ввод сообщения
//            try {
//                remoteInterImpl.viewNewMessage(name + ":" + message); // 3 это мы отправляем серваку, в метод viewNewMessage
//
//
//            } catch (RemoteException ex) {
//                ex.printStackTrace();
//            }
//
//            j++;
//            if (j == Integer.MIN_VALUE) {
//                break;
//            }
//        }*/

    }


    public void sendMessage(String msg) {
        try {
            remoteInterImpl.viewNewMessage(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void returnMessage(String message) throws RemoteException { // 6 получили сообщение от сервака. Показываем всем.
        System.out.println(message);

    }
}