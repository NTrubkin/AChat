package client;

import java.io.Serializable;

/**
 * Created by Kuznetsov on 01.05.2017.
 */
public class User implements Serializable  {

    private int id;                   // id пользователя в бд
    private String nickname;          // публичный никнейм пользователя
    private String email;             // email, привязанный к аккаунту пользователя

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

//    public User(ArgLine argLine, String idLabel, String nicknameLabel, String emailLabel) {
//        id = argLine.getArgHolder(idLabel).getInt();
//        nickname = argLine.getArgHolder(nicknameLabel).getString();
//        email = argLine.getArgHolder(emailLabel).getString();
//    }

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
        return "user #" + id + " " + nickname + " email: " + email;
    }
}



