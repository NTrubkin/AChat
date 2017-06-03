package org.nnstu5.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Trubkin Nikita
 *
 * Preparatory - интерфейс, позволяющий получить новый jdbc PreparedStatement для работы с базой данных
 * Возможное применение - DatabaseController для сокрытия методов упровления соединением, транзакцией и прочее.
 */
public interface Preparatory {
    PreparedStatement createPreparedStatement(String sql) throws SQLException;
}
