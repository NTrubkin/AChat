package org.nnstu5.database.handler;


//import org.apache.log4j.Logger;
import org.nnstu5.database.DatabaseController;
import org.nnstu5.database.holder.ArgHolder;
import org.nnstu5.database.holder.ArgLine;
import org.nnstu5.database.holder.ArgMask;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Trubkin Nikita
 *         <p>
 *         DatabasePartHandler инкапсулирует простые sql-запросы
 *         Каждый метод выполняет только один сколь угодно сложный запрос
 *         Также содержит методы-шаблоны запросов insert, delete, select, на основе которого строятся остальные методы
 *         Работает только с одним DatabaseController
 *         Использование транзакций в методах DatabasePartHandler и дочерних крайне нежелательно, поскольку должен выполняется всего один запрос
 *         Транзакции используются на более высоком уровне в компоновщике этих простейших запросов
 */
public abstract class DatabasePartHandler {
    protected DatabaseController dbController;

    //private static final Logger log = Logger.getLogger(DatabasePartHandler.class);
    private static final String SQL_EXC_MSG = "Something wrong with SQL";

    /**
     * Стандартный конструктор
     *
     * @param dbController открытый контроллер доступа к бд
     */
    public DatabasePartHandler(DatabaseController dbController) {
        this.dbController = dbController;
    }

    /**
     * Метод-шаблон sql-запроса insert, delete
     * Выполняет insert или delete sql-запрос в зависимости от sqlQuery с использованием PreparedStatement
     *
     * @param sqlQuery исполняемый sql-запрос
     * @param sqlArgs  аргументы запроса для PreparedStatement
     * @throws SQLException
     */
    private void insertOrDelete(String sqlQuery, ArgHolder... sqlArgs) throws SQLException {
        // реализация insert() ничем не отчичается от delete().
        PreparedStatement statement = dbController.createPreparedStatement(sqlQuery);
        setStatementArgs(statement, sqlArgs);
        statement.executeUpdate();

        statement.close();
    }

    /**
     * Метод-шаблон sql-запроса insert
     * Выполняет insert sql-запрос с использованием PreparedStatement
     *
     * @param sqlInsert исполняемый sql-запрос
     * @param sqlArgs   аргументы запроса для PreparedStatement
     * @throws SQLException
     */
    protected void insert(String sqlInsert, ArgHolder... sqlArgs) throws SQLException {
        // Вызывает insertOrDelete(), поскольку insert, delete шаблоны не отличаются
        insertOrDelete(sqlInsert, sqlArgs);
    }

    /**
     * Метод-шаблон sql-запроса insert
     * Выполняет insert sql-запрос с использованием PreparedStatement с возвратом сгенерированного id элемента (например, автоинкрементом)
     *
     * @param sqlInsert исполняемый sql-запрос
     * @param sqlArgs   аргументы запроса для PreparedStatement
     * @return сгенерированный id элемента (например, автоинкрементом)
     * @throws SQLException
     */
    protected int insertAndReturnId(String sqlInsert, ArgHolder... sqlArgs) throws SQLException {
        PreparedStatement statement = dbController.createPreparedStatement(sqlInsert);
        setStatementArgs(statement, sqlArgs);
        statement.executeUpdate();

        int result = statement.getGeneratedKeys().getInt(1);
        statement.close();
        return result;
    }

    /**
     * Метод-шаблон sql-запроса delete
     * Выполняет insert sql-запрос с использованием PreparedStatement
     *
     * @param sqlDelete исполняемый sql-запрос
     * @param sqlArgs   аргументы запроса для PreparedStatement
     * @throws SQLException
     */
    protected void delete(String sqlDelete, ArgHolder... sqlArgs) throws SQLException {
        // Вызывает insertOrDelete(), поскольку insert, delete шаблоны не отличаются
        insertOrDelete(sqlDelete, sqlArgs);
    }

    /**
     * Метод-шаблон sql-запроса select
     * Выполняет поисковый select sql-запрос с использованием PreparedStatement
     *
     * @param sqlSelect исполняемый sql-запрос
     * @param sqlArgs   аргументы запроса для PreparedStatement
     * @return true - была найдена хотя бы одна строчка, false - результат не содержит ни одной строчки
     * @throws SQLException
     */
    protected boolean checkBySelect(String sqlSelect, ArgHolder... sqlArgs) throws SQLException {
        PreparedStatement statement = dbController.createPreparedStatement(sqlSelect);
        setStatementArgs(statement, sqlArgs);
        ResultSet resultSet = statement.executeQuery();

        boolean result = resultSet.next();
        statement.close();
        return result;
    }

    /**
     * Метод-шаблон sql-запроса select
     * Выполняет поисковый select sql-запрос с использованием PreparedStatement с возвратом id найденого элемента или -1
     *
     * @param sqlSelect         исполняемый sql-запрос
     * @param resultColumnLabel название столбца с результатом
     * @param sqlArgs           аргументы запроса для PreparedStatement
     * @return id искомого элемента или -1
     * @throws SQLException
     */
    protected int findBySelect(String sqlSelect, String resultColumnLabel, ArgHolder... sqlArgs) throws SQLException {
        PreparedStatement statement = dbController.createPreparedStatement(sqlSelect);
        setStatementArgs(statement, sqlArgs);
        ResultSet result = statement.executeQuery();

        int result_id;
        if (result.next()) {
            result_id = result.getInt(resultColumnLabel);
        } else {
            result_id = -1;
        }
        statement.close();
        return result_id;
    }

    /**
     * Метод-шаблон sql-запроса select
     * Выполняет select sql-запрос с использованием PreparedStatement с возвратом результатов
     *
     * @param sqlSelect   исполняемый sql-запрос
     * @param sqlArgs     аргументы запроса для PreparedStatement
     * @param resultMasks маска результатов запроса для упаковки
     * @return результаты запроса, упакованные в List<ArgLine>
     * @throws SQLException
     */
    protected List<ArgLine> select(String sqlSelect, ArgHolder[] sqlArgs, ArgMask[] resultMasks) throws SQLException {
        PreparedStatement statement = dbController.createPreparedStatement(sqlSelect);
        setStatementArgs(statement, sqlArgs);
        ResultSet resultSet = statement.executeQuery();

        // упаковка результатов
        ArrayList<ArgLine> argLines = new ArrayList<>();
        while (resultSet.next()) {
            argLines.add(new ArgLine(resultSet, resultMasks));
        }

        statement.close();
        return argLines;
    }

    /**
     * Встраивает аргумент в sql-запрос в PreparedStatement, помеченый символом ?
     *
     * @param statement готовый PreparedStatement без аргумента
     * @param argIndex  индекс встраиваемого аргумента
     * @param arg       аргумент
     * @throws SQLException
     */
    private void setStatementArg(PreparedStatement statement, int argIndex, ArgHolder arg) throws SQLException {
        switch (arg.getArgType()) {
            case INTEGER:
                statement.setInt(argIndex, arg.getInt());
                break;
            case STRING:
                statement.setString(argIndex, arg.getString());
                break;
            default:
                throw new UnsupportedOperationException("Arg " + arg.getArgType() + " type is not implemented");
        }

    }

    /**
     * Встраивает аргументы в sql-запрос в PreparedStatement, помеченые символом ? с соблюдением порядка, начиная с 1(особенность sql-запросов с аргументами в jdbc)
     *
     * @param statement готовый PreparedStatement без аргументов
     * @param args      аргументы по порядку
     * @throws SQLException
     */
    private void setStatementArgs(PreparedStatement statement, ArgHolder... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            setStatementArg(statement, (i + 1), args[i]);     // (i+1), поскольку индексация sql аргументов начинается с 1, а не 0
        }
    }
}
