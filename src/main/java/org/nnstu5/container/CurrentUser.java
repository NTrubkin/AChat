package org.nnstu5.container;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Trubkin Nikita
 *         CurrentUser - контейнер для хранения и передачи приватной информации об одном пользователе
 *         Как правило, это авторизационные или регистрационные приватные сведения о текущем пользователе.
 *         Предназначен для хранения и передачи конфиденциальных данных, таких как пароль.
 */
public class CurrentUser implements Serializable {
    private final String nickname;          // публичный никнейм пользователя
    private final String email;             // email, привязанный к аккаунту пользователя
    private final String passHash;          // хэш пароля текущего пользователя

    /**
     * Конструктор авторизации (никнейм не обязателен)
     * @param email
     * @param password
     */
    public CurrentUser(String email,  String password) {
        this("", email, password);
        generatePassHash(password);
    }

    /**
     * Конструктор регистрации (требуется ввести все данные, включая никнейм)
     * @param nickname
     * @param email
     * @param password
     */
    public CurrentUser(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.passHash = generatePassHash(password);
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassHash() {
        return passHash;
    }

    private String generatePassHash (String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
           return new BigInteger(1, md.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Wrong algorythm. Passhash will set empty.");
        return new String("");
        }
    }

    @Override
    public String toString() {
        return (nickname.equals("") ? "" : "nickname: " + nickname + "; ") + "email: " + email + "; passHash: " + passHash;
    }
}
