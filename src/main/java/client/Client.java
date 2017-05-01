package client;

import server.RemoteInter;
import ui.Controller;
import ui.Model;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {



    public static void main(String[] args) throws Exception, RemoteException {

        Registry myReg = LocateRegistry.getRegistry("127.0.0.1", 1097);
        RemoteInter ri = (RemoteInter) myReg.lookup("RemoteInterImpl");


        new ClientInterImpl(ri);


    }
}




