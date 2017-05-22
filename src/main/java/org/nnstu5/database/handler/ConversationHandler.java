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
 *         ConversationHandler инкапсулирует методы-шаблоны запросов работы с беседами
 *         Методы, как правило состоят из вызова нужного метода-шаблона с добавлением sql-запроса, аргументов и маски результатов
 */
public class ConversationHandler extends DatabasePartHandler {
    private static final String SQL_INSERT_CONV = "insert into conversation (name, creator_id) values (?, ?);";
    private static final String SQL_INSERT_USER = "insert into convers_member (convers_id, member_id) values (?, ?);";
    private static final String SQL_DELETE_MEMBER = "delete from convers_member where (convers_id = ?) & (member_id = ?);";
    private static final String SQL_SELECT_USERS = "select c.convers_id, c.name, c.creator_id\n" +
            "                from conversation c inner join convers_member cm\n" +
            "                    on c.convers_id == cm.convers_id\n" +
            "                where cm.member_id = ?;";
    private static final String SQL_CHECK_CONV = "select convers_id from conversation where convers_id = ?";
    private static final String SQL_DELETE_ALL_MEMBERS = "delete from convers_member where (convers_id = ?);";
    private static final String SQL_DELETE_CONV = "delete from conversation where (convers_id = ?);";
    private static final String SQL_CHECK_CREATOR = "select convers_id from conversation where (convers_id = ?) & (creator_id = ?);";
    private static final String SQL_CHECK_USER = "select convers_id from convers_member where (convers_id = ?) & (member_id = ?);";

    public ConversationHandler(Preparatory statementCreator) {
        super(statementCreator);
    }

    /**
     * Выполняет sql-запрос вставки новой беседы
     *
     * @param name      название беседы
     * @param creatorId пользователь, создавщий беседу
     * @return сгенерированный ключ новой беседы
     * @throws SQLException
     */
    public int insertConversation(String name, int creatorId) throws SQLException {
        return insertAndReturnId(SQL_INSERT_CONV, new ArgHolder(name), new ArgHolder(creatorId));
    }

    /**
     * Выполняет sql-запрос вставки нового пользователя в беседу
     *
     * @param conversId беседа
     * @param userId    новый пользователь
     * @throws SQLException
     */
    public void insertUserToConversation(int conversId, int userId) throws SQLException {
        insert(SQL_INSERT_USER, new ArgHolder(conversId), new ArgHolder(userId));
    }

    /**
     * Выполняет sql-запрос удаления пользователя-участника из беседы
     *
     * @param conversId беседа
     * @param userId    исключаемый участник
     * @throws SQLException
     */
    public void deleteMemberFromConversation(int conversId, int userId) throws SQLException {
        delete(SQL_DELETE_MEMBER, new ArgHolder(conversId), new ArgHolder(userId));
    }

    /**
     * Выполняет sql-запрос выбора всех бесед пользователя
     *
     * @param userId пользователь
     * @return все беседы пользователя, упакованные в List<ArgLine>
     * @throws SQLException
     */
    public List<ArgLine> selectUserConversations(int userId) throws SQLException {
        ArgHolder[] argHolders = {new ArgHolder(userId)};
        ArgMask[] resultMasks = {new ArgMask(ArgType.INTEGER, "convers_id"),
                new ArgMask(ArgType.STRING, "name"),
                new ArgMask(ArgType.INTEGER, "creator_id")};
        return select(SQL_SELECT_USERS, argHolders, resultMasks);
    }

    /**
     * Выполняет sql-запрос проверки существования беседы
     *
     * @param conversId беседа
     * @return true - беседа существует, false - беседа не найдена
     * @throws SQLException
     */
    public boolean checkConversation(int conversId) throws SQLException {
        return checkBySelect(SQL_CHECK_CONV, new ArgHolder(conversId));
    }

    /**
     * Выполняет sql-запрос удаления всех участников беседы из этой беседы
     *
     * @param conversId беседа
     * @throws SQLException
     */
    public void deleteAllMembersFromConversation(int conversId) throws SQLException {
        delete(SQL_DELETE_ALL_MEMBERS, new ArgHolder(conversId));
    }

    /**
     * Выполняет sql-запрос удаления беседы
     *
     * @param conversId
     * @throws SQLException
     */
    public void deleteConversation(int conversId) throws SQLException {
        delete(SQL_DELETE_CONV, new ArgHolder(conversId));
    }

    /**
     * Выполняет sql-запрос проверки принадлежности пользователя к беседе
     *
     * @param userId         пользователь
     * @param conversationId беседа
     * @return true - если пользователь участвует в беседе, false - не участвует
     * @throws SQLException
     */
    public boolean isUserCreateConversation(int userId, int conversationId) throws SQLException {
        return checkBySelect(SQL_CHECK_CREATOR, new ArgHolder(conversationId), new ArgHolder(userId));
    }

    /**
     * Выполняет sql-запрос проверки, является ли пользователь создателем этой беседы
     *
     * @param userId         пользователь
     * @param conversationId беседа
     * @return true - если пользователь является создателембеседы, false - не является создателем
     * @throws SQLException
     */
    public boolean isUserInConversation(int userId, int conversationId) throws SQLException {
        return checkBySelect(SQL_CHECK_USER, new ArgHolder(conversationId), new ArgHolder(userId));
    }
}
