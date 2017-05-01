package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {


    public static void main(String[] args) {
        try {

            Registry reg = LocateRegistry.createRegistry(1098);
            RemoteInter ri = new RemoteInterImpl();

            reg.rebind("RemoteInterImpl", ri);
            System.out.println("Server is ready");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
