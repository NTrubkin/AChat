package org.nnstu5.container;

import org.nnstu5.database.holder.ArgLine;

import java.io.Serializable;

/**
 * @author Trubkin Nikita
 *         Conversation - контейнер для хранения и передачи информации об одном сообщении
 */
public class Message implements Serializable {
    private final int id;               // id сбщ в бд
    private final int senderId;          // отправитель сбщ
    private final String text;          // текст сообщения

    public Message(int id, int senderId, String text) {
        this.id = id;
        this.senderId = senderId;
        this.text = text;
    }

    public Message(ArgLine argLine, String idLabel, String textLabel, String senderIdLabel) {
        this(argLine.getArgHolder(idLabel).getInt(),
                argLine.getArgHolder(senderIdLabel).getInt(),
                argLine.getArgHolder(textLabel).getString()
        );
    }

    public int getId() {
        return id;
    }

    public int getSender() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "message #" + id + "; from user #" + senderId + ": " + text;
    }
}
