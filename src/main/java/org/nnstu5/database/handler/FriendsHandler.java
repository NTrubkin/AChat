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
 *         FriendsHandler инкапсулирует методы-шаблоны запросов работы с друзьями
 *         Методы, как правило состоят из вызова нужного метода-шаблона с добавлением sql-запроса, аргументов и маски результатов
 */
public class FriendsHandler extends DatabasePartHandler {
    private static final String SQL_INSERT_FRIENDS = "insert into friends_pair (first_friend_id, second_friend_id) values (?, ?);";
    private static final String SQL_DELETE_FRIENDS = "delete from friends_pair where ((first_friend_id = ?) & (second_friend_id = ?)) | ((first_friend_id = ?) & (second_friend_id = ?));";
    private static final String SQL_SELECT_FRIENDS = "select friend_id, nickname, email\n" +
            "from (select (first_friend_id + second_friend_id - ?) friend_id\n" +
            "    from friends_pair\n" +
            "    where (first_friend_id = ?) | (second_friend_id = ?)) f\n" +
            "inner join account a\n" +
            "    on f.friend_id == a.account_id;";
    private static final String SQL_CHECK_FRIENDS = "select first_friend_id from friends_pair where ((first_friend_id = ?) & (second_friend_id = ?)) | ((first_friend_id = ?) & (second_friend_id = ?));";
    private static final String SQL_SELECT_NON_MEMBER_FRIENDS = "select friend_id, nickname, email\n" +
            "from (select (first_friend_id + second_friend_id - ?) friend_id\n" +
            "from friends_pair\n" +
            "where (first_friend_id = ?) | (second_friend_id = ?)\n" +
            "    EXCEPT\n" +
            "select member_id from convers_member where convers_id = ?) f\n" +
            "inner join account a\n" +
            "on f.friend_id == a.account_id;";

    public FriendsHandler(Preparatory statementCreator) {
        super(statementCreator);
    }

    /**
     * Выполняет sql-запрос вставки новой пары друзей
     *
     * @param firstFriendId  первый друг
     * @param secondFriendId второй друг
     * @throws SQLException
     */
    public void insertFriendPair(int firstFriendId, int secondFriendId) throws SQLException {
        insert(SQL_INSERT_FRIENDS, new ArgHolder(firstFriendId), new ArgHolder(secondFriendId));
    }

    /**
     * Выполняет sql-запрос удаления пары друзей
     *
     * @param firstFriendId  первый друг
     * @param secondFriendId второй друг
     * @throws SQLException
     */
    public void deleteFriendPair(int firstFriendId, int secondFriendId) throws SQLException {
        delete(SQL_DELETE_FRIENDS, new ArgHolder(firstFriendId), new ArgHolder(secondFriendId), new ArgHolder(secondFriendId), new ArgHolder(firstFriendId));
    }

    /**
     * Выполняет sql-запрос выбора всех друзей пользователя
     *
     * @param userId пользователь
     * @return всех друзей, упакованных в List<ArgLine>
     * @throws SQLException
     */
    public List<ArgLine> selectFriends(int userId) throws SQLException {
        // складывать и вычитать id это ненормально. если есть другой способ, дай мне знать об этом.

        ArgHolder[] argHolders = {new ArgHolder(userId), new ArgHolder(userId), new ArgHolder(userId)};
        ArgMask[] resultMasks = {new ArgMask(ArgType.INTEGER, "friend_id"),
                new ArgMask(ArgType.STRING, "nickname"),
                new ArgMask(ArgType.STRING, "email")};
        return select(SQL_SELECT_FRIENDS, argHolders, resultMasks);
    }

    /**
     * Выполняет sql-запрос проверки, являются ли пользователи друзьями
     *
     * @param firstUserId  первый пользователь
     * @param secondUserId второй пользователь
     * @return true - если пользователи являются друзьями, false - не являются
     * @throws SQLException
     */
    public boolean areUsersFrends(int firstUserId, int secondUserId) throws SQLException {
        return checkBySelect(SQL_CHECK_FRIENDS, new ArgHolder(firstUserId), new ArgHolder(secondUserId), new ArgHolder(secondUserId), new ArgHolder(firstUserId));
    }


    /**
     * Формирует список друзей пользователя userId, которые не являются членами conversId
     *
     * @param userId пользователь, чьих друзей нужно найти
     * @param conversId беседа, в которую не входят друзья
     * @return List<User> список друзей, не являющихся членами conversId
     */

    /**
     * Выполняет sql-запрос выбора всех друзей пользователя userId, которые не являются членами беседы conversId
     *
     * @param userId пользователь, чьих друзей нужно найти
     * @param conversId беседа, в которую не входят друзья
     * @return List<ArgLine> список друзей, не являющихся членами conversId
     * @throws SQLException
     */
    public List<ArgLine> selectNonMemberFriends(int userId, int conversId) throws SQLException {
        ArgHolder[] argHolders = {new ArgHolder(userId), new ArgHolder(userId), new ArgHolder(userId), new ArgHolder(conversId)};
        ArgMask[] resultMasks = {new ArgMask(ArgType.INTEGER, "friend_id"),
                new ArgMask(ArgType.STRING, "nickname"),
                new ArgMask(ArgType.STRING, "email")};
        return select(SQL_SELECT_NON_MEMBER_FRIENDS, argHolders, resultMasks);
    }
}
