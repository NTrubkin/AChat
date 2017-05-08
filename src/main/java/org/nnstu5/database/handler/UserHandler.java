package org.nnstu5.database.handler;

import org.nnstu5.database.DatabaseController;
import org.nnstu5.database.holder.ArgHolder;

import java.sql.SQLException;

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

    public UserHandler(DatabaseController dbController) {
        super(dbController);
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
    public int insertUser(String nickname, String email, String pass_hash) throws SQLException {
        return insertAndReturnId(SQL_INSERT_USER, new ArgHolder(nickname), new ArgHolder(email), new ArgHolder(pass_hash));
    }

    /**
     * Выполняет sql-запрос поиска пользователя по email
     *
     * @param email адрес эл почты
     * @return id найденого пользователя или -1
     * @throws SQLException
     */
    public int findUser(String email) throws SQLException {
        return findBySelect(SQL_FIND_USER_BY_EMAIL, "email", new ArgHolder(email));
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
}
