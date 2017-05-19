package org.nnstu5.ui;

import org.nnstu5.ChatRules;
import org.nnstu5.client.Client;
import org.nnstu5.client.ClientLauncher;
import org.nnstu5.container.CurrentUser;
import org.nnstu5.container.User;

/**
 * Created by TrubkinN on 09.05.2017.
 * Содержит методы бизнес-логики. Не работает непосредственно с разметкой view и визуальными элементами
 * Запускает клиент при регистрации или авторизации.
 * Отправляет клиенту данные при регистрации и авторизации в контейнере CurrentUser.
 */

public class AuthAndRegModel {
    private Client client;

    /**
     * Проверка корректности введеных регистрационных данных через ChatRules.
     * Запуск клиента, если не был запущен.
     * Запаковка данных в контейнер CurrentUser и отправка клиенту.
     *
     * @param email
     * @param nickname
     * @param password
     * @param passСonfirmation
     */
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
                client = ClientLauncher.getClient();
            }
        } catch (Exception exc) {
            System.out.println("Cannot start client");
            exc.printStackTrace();
        }

        client.registerUser(new CurrentUser(nickname, email, password));
    }

    /**
     * Проверка корректности введеных авторизационных данных через ChatRules.
     * Запуск клиента, если не был запущен.
     * Запаковка данных в контейнер CurrentUser и отправка клиенту.
     *
     * @param email
     * @param password
     */
    public void authorizeAndLoadChat(String email, String password) {
        if (!ChatRules.isValidUserEmail(email)) {
            System.out.println("Некорректный email, авторизация прервана");
            return;
        }
        if (!ChatRules.isValidPassword(password)) {
            System.out.println("Некорректный пароль, авторизация прервана");
            return;
        }
        try {
            if (!ClientLauncher.isClientStarted()) {
                client = ClientLauncher.start();
            } else {
                client = ClientLauncher.getClient();
            }
        } catch (Exception exc) {
            System.out.println("Cannot start client");
            exc.printStackTrace();
        }
        System.out.println(client.authUser(new CurrentUser(email, password)));
    }
}
