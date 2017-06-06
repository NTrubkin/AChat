package org.nnstu5.container;

import org.nnstu5.ChatRules;

import java.util.Objects;

/**
 * Created by TrubkinN on 06.06.2017.
 */
public class ExtendedMessage extends Message {
    private final User sender;         // отправитель сбщ

    public ExtendedMessage(Message message, User sender) {
        super(message.getText(), message.getSenderId());
        Objects.requireNonNull(sender, "sender cannot be null");
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }
}
