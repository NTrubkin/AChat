package org.nnstu5.container;

/**
 * @author Trubkin Nikita
 *         CurrentUser - контейнер для хранения и передачи приватной информации об одном пользователе
 *         Как правило, это авторизационные или регистрационные приватные сведения о текущем пользователе.
 *         Предназначен для хранения и передачи конфиденциальных данных, таких как пароль.
 */
public class CurrentUser {
    private final String nickname;          // публичный никнейм пользователя
    private final String email;             // email, привязанный к аккаунту пользователя
    private final String passHash;          // хэш пароля текущего пользователя

    /**
     * Конструктор авторизации (никнейм не обязателен)
     * @param email
     * @param passHash
     */
    public CurrentUser(String email, String passHash) {
        this("", email, passHash);
    }

    /**
     * Конструктор регистрации (требуется ввести все данные, включая никнейм)
     * @param nickname
     * @param email
     * @param passHash
     */
    public CurrentUser(String nickname, String email, String passHash) {
        this.nickname = nickname;
        this.email = email;
        this.passHash = passHash;
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

    @Override
    public String toString() {
        return (nickname.equals("") ? "" : "nickname: " + nickname + "; ") + "email: " + email + "; passHash: " + passHash;
    }
}
