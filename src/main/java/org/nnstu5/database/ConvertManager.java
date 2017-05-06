package org.nnstu5.database;

import org.nnstu5.container.Conversation;
import org.nnstu5.container.Message;
import org.nnstu5.container.User;
import org.nnstu5.database.holder.ArgLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Trubkin Nikita
 *
 * ConvertManager оборачивает результат sql select запроса в удобную форму
 * Исходные данные - коллекция ArgLine, которая, как правило, сохраняет исходную табличную форму
 * Методы ConvertManager преобразуют коллекция ArgLine в коллекции контейнеров нужного типа в соответствии с заложенным шаблоном
 */
class ConvertManager {


    /**
     * Оборачивает сообщения в контейнеры Message по определенным лейблам
     * @param argLines результат запроса
     * @return коллекция Message
     */
    List<Message> wrapMessages(List<ArgLine> argLines) {
        ArrayList<Message> messages = new ArrayList<>();
        for (ArgLine argLine : argLines) {
            messages.add(wrapMessage(argLine));
        }
        return messages;
    }

    /**
     * Оборачивает одно сообщение в один контейнер Message по определенным лейблам
     * @param argLine строка результата запроса
     * @return Message
     */
    private Message wrapMessage(ArgLine argLine) {
        return new Message(argLine, "message_id", "msg_text", "from_id");
    }

    /**
     * Оборачивает беседы в контейнеры Conversation по определенным лейблам
     * @param argLines результат запроса
     * @return коллекция Conversation
     */
    List<Conversation> wrapConversations(List<ArgLine> argLines) {
        ArrayList<Conversation> conversations = new ArrayList<>();
        for (ArgLine argLine : argLines) {
            conversations.add(wrapConversation(argLine));
        }
        return conversations;
    }

    /**
     * Оборачивает одну беседу в контейнер Conversation по определенным лейблам
     * @param argLine строка результата запроса
     * @return Conversation
     */
    private Conversation wrapConversation(ArgLine argLine) {
        return new Conversation(argLine, "convers_id", "name", "creator_id");
    }

    /**
     * Оборачивает беседы в контейнеры User по определенным лейблам
     * @param argLines результат запроса
     * @return коллекция User
     */
    List<User> wrapFriends(List<ArgLine> argLines) {
        ArrayList<User> friends = new ArrayList<>();
        for (ArgLine argLine : argLines) {
            friends.add(wrapFriend(argLine));
        }
        return friends;
    }

    /**
     * Оборачивает одного пользователя в контейнер User по определенным лейблам
     * @param argLine строка результата запроса
     * @return User
     */
    private User wrapFriend(ArgLine argLine) {
        return new User(argLine, "friend_id", "nickname", "email");
    }
}
