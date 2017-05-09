package org.nnstu5.ui;

import org.nnstu5.ChatRules;
import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.container.CurrentUser;
import org.nnstu5.container.User;

/**
 * Created by TrubkinN on 09.05.2017.
 */
public class AuthAndRegModel {
    private static final String PAS_HASH = "PASSHASHPASSHASHPASSHASHPASSHASH";
    private Client client;

    public void registerUser(String email, String nickname, String password, String passСonfirmation) {
        if (!ChatRules.isValidUserEmail(email)) {
            System.out.println("Некорректный email, регистрация прервана");
            return;
        }
        if (!ChatRules.isValidUserNickname(nickname)) {
            System.out.println("Некорректный логин, регистрация прервана");
            return;
        }
        if (!ChatRules.isValidPassword(password)) {
            System.out.println("Некорректный пароль, регистрация прервана");
            return;
        }
        if (!password.equals(passСonfirmation)) {
            System.out.println("Пароли не совпадают");
            return;
        }

        try {
            if (!ClientLauncher.isClientStarted()) {
                client = ClientLauncher.start();
            } else {
                client=ClientLauncher.getClient();
            }
        } catch (Exception exc) {
            System.out.println("Cannot start client");
            exc.printStackTrace();
        }

        client.registerUser(new CurrentUser(nickname, email, PAS_HASH));
    }

    public void authorizeAndLoadChat(String email, String pass) {
        if (!ChatRules.isValidUserEmail(email)) {
            System.out.println("Некорректный email, авторизация прервана");
            return;
        }
        if (!ChatRules.isValidPassword(pass)) {
            System.out.println("Некорректный пароль, авторизация прервана");
            return;
        }
        try {
            if (!ClientLauncher.isClientStarted()) {
                client = ClientLauncher.start();
            } else {
                client=ClientLauncher.getClient();
            }
        } catch (Exception exc) {
            System.out.println("Cannot start client");
            exc.printStackTrace();
        }
        System.out.println(client.authUser(new CurrentUser(email, PAS_HASH)));
    }
}
