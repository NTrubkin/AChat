package org.nnstu5.database.handler;

import org.nnstu5.database.DatabaseController;
import org.nnstu5.database.Preparatory;
import org.nnstu5.database.holder.ArgHolder;
import org.nnstu5.database.holder.ArgLine;
import org.nnstu5.database.holder.ArgMask;
import org.nnstu5.database.holder.ArgType;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Trubkin Nikita
 *         <p>
 *         MessageHandler инкапсулирует методы-шаблоны запросов работы с сообщениями
 *         Методы, как правило состоят из вызова нужного метода-шаблона с добавлением sql-запроса, аргументов и маски результатов
 */
public class MessageHandler extends DatabasePartHandler {
    private static final String SQL_INSERT_MSG = "insert into message (msg_text, from_id, convers_id) values (?, ?, ?);";
    private static final String SQL_SELECT_MSGS = "select msg_text, from_id from message where convers_id = ?";
    private static final String SQL_DELETE_ALL_MSGS = "delete from message where (convers_id = ?);";

    public MessageHandler(Preparatory statementCreator) {
        super(statementCreator);
    }

    /**
     * Выполняет sql-запрос вставки нового сообщения
     *
     * @param text      текст сообщения
     * @param conversId беседа
     * @param senderId  отправитель
     * @throws SQLException
     */
    public void insertMessage(String text, int conversId, int senderId) throws SQLException {
        insert(SQL_INSERT_MSG, new ArgHolder(text), new ArgHolder(senderId), new ArgHolder(conversId));
    }

    /**
     * Выполняет sql-запрос выбора всех сообщений беседы
     *
     * @param conversId беседа
     * @return все сообщения беседы, упакованные в List<ArgLine>
     * @throws SQLException
     */
    public List<ArgLine> selectMessages(int conversId) throws SQLException {
        ArgHolder[] argHolders = {new ArgHolder(conversId)};
        ArgMask[] resultMasks = {new ArgMask(ArgType.STRING, "msg_text"),
                new ArgMask(ArgType.INTEGER, "from_id")};
        return select(SQL_SELECT_MSGS, argHolders, resultMasks);
    }

    /**
     * Выполняет sql-запрос удаления всех сообщений беседы
     *
     * @param conversId беседа
     * @throws SQLException
     */
    public void deleteAllConversationMessages(int conversId) throws SQLException {
        delete(SQL_DELETE_ALL_MSGS, new ArgHolder(conversId));
    }
}
