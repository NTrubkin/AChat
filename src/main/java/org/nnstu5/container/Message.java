package org.nnstu5.container;

import org.nnstu5.database.holder.ArgLine;

import java.io.Serializable;

/**
 * @author Trubkin Nikita
 *         Conversation - контейнер для хранения и передачи информации об одном сообщении
 */
public class Message implements Serializable {
    private final String text;          // текст сообщения
    private final int senderId;         // отправитель сбщ

    public Message(String text, int senderId) {
        this.senderId = senderId;
        this.text = text;
    }

    /**
     * Конструктор, извлекающий данные сообщения из холдера ArgLine
     * @param argLine
     * @param textLabel
     * @param senderIdLabel
     */
    public Message(ArgLine argLine, String textLabel, String senderIdLabel) {
        this(argLine.getArgHolder(textLabel).getString(),
                argLine.getArgHolder(senderIdLabel).getInt()
        );
    }

    public String getText() {
        return text;
    }

    public int getSender() {
        return senderId;
    }

    @Override
    public String toString() {
        return "message from user #" + senderId + ": " + text;
    }
}
