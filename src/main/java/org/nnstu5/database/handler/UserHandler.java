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
 *         UserHandler инкапсулирует методы-шаблоны запросов работы с пользователями
 *         Методы, как правило состоят из вызова нужного метода-шаблона с добавлением sql-запроса, аргументов и маски результатов
 */
public class UserHandler extends DatabasePartHandler {
    private static final String SQL_INSERT_USER = "insert into account(nickname, email, pass_hash) values (?, ?, ?);";
    private static final String SQL_FIND_USER_BY_EMAIL = "select account_id from account where email = ?";
    private static final String SQL_FIND_USER_BY_PASSHASH = "select account_id from account where (email = ?) & (pass_hash = ?)";
    private static final String SQL_CHECK_USER = "select account_id from account where account_id = ?";
    private static final String SQL_SELECT_USER = "select account_id, nickname, email from account where (email = ?) & (pass_hash = ?)";

    public UserHandler(Preparatory statementCreator) {
        super(statementCreator);
    }

    // returns generated key

    /**
     * Выполняет sql-запрос вставки нового пользователя
     *
     * @param nickname  имя пользователя
     * @param email     уникальный адрес эл почты
     * @param pass_hash md5 хэш пароля пользователя
     * @return
     * @throws SQLException
     */
    public void insertUser(String nickname, String email, String pass_hash) throws SQLException {
        insert(SQL_INSERT_USER, new ArgHolder(nickname), new ArgHolder(email), new ArgHolder(pass_hash));
    }

    /**
     * Выполняет sql-запрос поиска пользователя по email
     *
     * @param email адрес эл почты
     * @return id найденого пользователя или -1
     * @throws SQLException
     */
    public int findUser(String email) throws SQLException {
        return findBySelect(SQL_FIND_USER_BY_EMAIL, "account_id", new ArgHolder(email));
    }

    /**
     * Выполняет sql-запрос поиска пользователя по email и passHash. Может использоваться для авторизации
     *
     * @param email    адрес эл почты
     * @param passHash md5 хэш пароля пользователя
     * @return id найденого пользователя или -1
     * @throws SQLException
     */
    public int findUser(String email, String passHash) throws SQLException {
        return findBySelect(SQL_FIND_USER_BY_PASSHASH, "account_id", new ArgHolder(email), new ArgHolder(passHash));
    }

    /**
     * Выполняет sql-запрос проверки существования пользователя
     *
     * @param userId пользователь
     * @return true - пользователь существует, false - пользователь не найден
     * @throws SQLException
     */
    public boolean checkUser(int userId) throws SQLException {
        return checkBySelect(SQL_CHECK_USER, new ArgHolder(userId));
    }

    public List<ArgLine> selectUser(String email, String passHash) throws SQLException{
        ArgHolder[] argHolders = {new ArgHolder(email), new ArgHolder(passHash)};
        ArgMask[] resultMasks = {new ArgMask(ArgType.INTEGER, "account_id"),
                new ArgMask(ArgType.STRING, "nickname"),
                new ArgMask(ArgType.STRING, "email") };
        return select(SQL_SELECT_USER, argHolders, resultMasks);
    }
}
