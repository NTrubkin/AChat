package org.nnstu5.container;

import java.io.Serializable;

/**
 * @author Trubkin Nikita
 *         User - контейнер для хранения и передачи публичной информации об одном пользователе
 *         Как правило, это краткие публичные сведения о другом пользователе.
 *         Запрещено хранение и передача конфиденциальных данных, таких как пароль.
 */
public class User implements Serializable {
    private final int id;                   // id пользователя в бд
    private final String nickname;          // публичный никнейм пользователя
    private final String email;             // email, привязанный к аккаунту пользователя

    public User(int id, String nickname, String email) {

        this.id = id;
        if (nickname == null) {
            this.nickname = "";
        } else {
            this.nickname = nickname;
        }
        if (email == null) {
            this.email = "";
        } else {
            this.email = email;
        }
    }

/*    public User(ArgLine argLine, String idLabel, String nicknameLabel, String emailLabel) {
        this(argLine.getArgHolder(idLabel).getInt(),
                argLine.getArgHolder(nicknameLabel).getString(),
                argLine.getArgHolder(emailLabel).getString()
        );
    }*/

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "user #" + id + "; " + nickname + "; email: " + email;
    }
}
