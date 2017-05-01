package client;

import javafx.scene.Parent;
import server.RemoteInter;
import ui.Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientInterImpl extends UnicastRemoteObject implements ClientInter {

    private RemoteInter remoteInterImpl;
    private String name = null;

    public ClientInterImpl(RemoteInter remoteInterImpl) throws Exception {

        Model model = new Model();
        model.modelSet(this);


        this.remoteInterImpl = remoteInterImpl;
        remoteInterImpl.registerClient(this);

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


    public void returnMessage(Parent model) throws RemoteException {
        System.out.println(model);
    }

    public void setUi(Parent model) {
        try {
            remoteInterImpl.viewNewMessage(model);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}