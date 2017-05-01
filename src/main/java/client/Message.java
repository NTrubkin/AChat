package client;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Kuznetsov on 01.05.2017.
 */
public class Message implements Serializable {
        private int id;               // id сбщ в бд
        private User sender;
        private int senderId;         // id отправителя в бд
        private String text;          // текст сообщения
        private Timestamp time;

        public Message(int id, int senderId, String text) {
            this.id = id;
            this.senderId = senderId;
            if (text == null) {
                this.text = "";
            } else {
                this.text = text;
            }
        }

        //    public Message(ArgLine argLine, String idLabel, String textLabel, String senderIdLabel, String senderNicknameLabel, String senderEmailLabel) {
//        id = argLine.getArgHolder(idLabel).getInt();
//        text = argLine.getArgHolder(textLabel).getString();
//        sender = new User(argLine, senderIdLabel, senderNicknameLabel, senderEmailLabel);
//    }
        public void setTime(Timestamp time){ this.time = time; }

        public int getId() {
            return id;
        }

        public int getSenderId() {
            return senderId;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return time + " message #" + id + " from #" + senderId + ": " + text;
        }
    }

