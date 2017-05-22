package org.nnstu5.container;

import org.nnstu5.ChatRules;
import org.nnstu5.database.holder.ArgLine;

import java.io.Serializable;
import java.util.Objects;

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

    /**
     * Стандартный конструктор
     * @param id
     * @param nickname
     * @param email
     */
    public User(int id, String nickname, String email) {
        if(!ChatRules.isValidUserNickname(nickname)) {
            throw new IllegalArgumentException("User nickname is invalid");
        }
        if(!ChatRules.isValidUserEmail(email)) {
            throw new IllegalArgumentException("Email is invalid");
        }
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    /**
     * Конструктор, извлекающий данные пользователя из холдера ArgLine
     * @param argLine
     * @param idLabel
     * @param nicknameLabel
     * @param emailLabel
     */
    public User(ArgLine argLine, String idLabel, String nicknameLabel, String emailLabel) {
        this(argLine.getArgHolder(idLabel).getInt(),
                argLine.getArgHolder(nicknameLabel).getString(),
                argLine.getArgHolder(emailLabel).getString()
        );
    }

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
