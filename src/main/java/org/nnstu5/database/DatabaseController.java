package org.nnstu5.database;

//import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @author Trubkin Nikita
 *         <p>
 *         DatabaseController подготавливает одну базу данных к работе, определяет правила работы с ней
 *         Работает только с одной бд типа sqlite.
 *         Открывает соединение в конструкторе
 *         Закрывается вручную вызовом метода close()
 *         По умолчанию autocommit false. Каждая комманда должна заверщаться методом commitTransaction()
 */
public class DatabaseController implements AutoCloseable, Preparatory {
    private Connection connection = null;
    //private static final Logger log = Logger.getLogger(DatabaseController.class);

    private static final String DRIVER_CLASS = "org.sqlite.JDBC";
    private static final String EXC_HEADER = "Exception: ";
    private final String DB_URL;

    /**
     * Подготавливает бд к работе
     *
     * @param db_url путь к файлу с базой данных
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    DatabaseController(String db_url) throws SQLException, ClassNotFoundException {
        DB_URL = db_url;
        open();
    }

    /**
     * Открывает соединение с бд
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void open() throws SQLException, ClassNotFoundException {
        // load the JDBC driver using the current class loader
        Class.forName(DRIVER_CLASS);

        // create a database connection
        connection = DriverManager.getConnection(DB_URL);
        connection.setAutoCommit(false);
        //log.info("DatabaseController opened.");
        System.out.println("DatabaseController opened.");
    }

    /**
     * Закрывает соединение с бд
     *
     * @throws SQLException
     */
    @Override
    public void close() throws SQLException {
        connection.close();
        //log.info("ChatDatabase closed.");
        System.out.println("DatabaseController closed.");
    }

    /**
     * Откатывает транзакцию, включает автокоммит
     *
     * @throws SQLException
     */
    void rollbackTransaction() throws SQLException {
        connection.rollback();
    }

    /**
     * Применяет транзакцию, включает автокоммит
     *
     * @throws SQLException
     */
    void commitTransaction() throws SQLException {
        connection.commit();                // сначала коммит
    }

    /**
     * Позволяет создать PreparedStatement c возможностью возврата генерируемых значений
     *
     * @param sql запрос PreparedStatement
     * @return созданный PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement createPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }
}
