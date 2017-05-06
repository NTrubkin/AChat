package org.nnstu5.container;

import java.io.Serializable;

/**
 * @author Trubkin Nikita
 *         Conversation - контейнер для хранения и передачи информации об одной беседе
 */
public class Conversation implements Serializable {
    private final int id;                  // id беседы в бд
    private final String name;             // публичное имя беседы
    private final int creatorId;            // создатель беседы

    public Conversation(int id, String name, int creatorId) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
    }

    /*public Conversation(ArgLine argLine, String idLabel, String nameLabel, String creatorIdLabel) {
        this(argLine.getArgHolder(idLabel).getInt(),
                argLine.getArgHolder(nameLabel).getString(),
                argLine.getArgHolder(creatorIdLabel).getInt());
    }*/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCreatorId() {
        return creatorId;
    }

    @Override
    public String toString() {
        return "conversation #" + id + "; " + name + "; creator #" + creatorId;
    }
}
