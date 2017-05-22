package org.nnstu5.container;

import org.nnstu5.ChatRules;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.StringJoiner;

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

    private static final String DEFAULT_NICKNAME = "";
    private static final String HASH_GENERATOR = "MD5";

    /**
     * Конструктор авторизации (никнейм не обязателен)
     * @param email
     * @param password
     */
    public CurrentUser(String email,  String password) {
        this(DEFAULT_NICKNAME , email, password);
    }

    /**
     * Конструктор регистрации (требуется ввести все данные, включая никнейм)
     * @param nickname
     * @param email
     * @param password
     */
    public CurrentUser(String nickname, String email, String password) {
        if(!ChatRules.isValidUserNickname(nickname) && !nickname.equals(DEFAULT_NICKNAME)) {
            throw new IllegalArgumentException("Nickname is invalid");
        }
        if(!ChatRules.isValidUserEmail(email)) {
            throw new IllegalArgumentException("Email is invalid");
        }
        if(!ChatRules.isValidPassword(password)) {
            throw new IllegalArgumentException("Password is invalid");
        }

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
            MessageDigest md = MessageDigest.getInstance(HASH_GENERATOR);
            md.update(password.getBytes());
           return new BigInteger(1, md.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Wrong algorythm. Passhash will set empty.");
        return "";
        }
    }

    @Override
    public String toString() {
        return (nickname.equals(DEFAULT_NICKNAME) ? "" : "nickname: " + nickname + "; ") + "email: " + email + "; passHash: " + passHash;
    }
}
