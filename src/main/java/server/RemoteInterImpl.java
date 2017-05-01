package server;

import client.ClientInter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteInterImpl extends UnicastRemoteObject implements RemoteInter {

    private volatile List<ClientInter> listClients;

    protected RemoteInterImpl() throws RemoteException {

        listClients = new ArrayList<ClientInter>();

    }

    public synchronized void registerClient(ClientInter clientInterImplement) throws RemoteException {

        this.listClients.add(clientInterImplement);

    }

    public void viewNewMessage(String message) throws RemoteException { // 4 сервер получает в параметрах метода сообщение
        //от клиента

        for (ClientInter clientInter : listClients) {
            clientInter.returnMessage(message);   // как сервис знает, что у него есть клиент? Знаееет.
        }

        // 5 возвращаем сообщение всем клиентам!
        // каждый клиент будет отправлять сообщ через returnMessage
    }
}












