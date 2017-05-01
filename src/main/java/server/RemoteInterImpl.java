package server;

import client.ClientInter;
import javafx.scene.Parent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteInterImpl extends UnicastRemoteObject implements RemoteInter {

    private volatile List<ClientInter> listClients;

    protected RemoteInterImpl() throws RemoteException {

        listClients = new ArrayList<ClientInter>();

    }

    public void viewNewMessage(Parent model) throws RemoteException {
        for (ClientInter clientInter : listClients) {
            clientInter.returnMessage(model);   // как сервис знает, что у него есть клиент? Знаееет.
        }

        // 5 возвращаем сообщение всем клиентам!
        // каждый клиент будет отправлять сообщ через returnMessage
    }


    public synchronized void registerClient(ClientInter clientInterImplement) throws RemoteException {

        this.listClients.add(clientInterImplement);

    }

}












