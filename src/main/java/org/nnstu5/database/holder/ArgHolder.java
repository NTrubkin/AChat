package org.nnstu5.database.holder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Trubkin Nikita
 *
 * ArgHolder выполняет функцию контейнера аргументов вне зависимости от его типа.
 * Это удобно при передаче аргументов в sql-запрос PreparedStatement и при формировании результатов
 * На данный момент может хранить только типы: int, String
 * Следует помнить, что контейнер содержит только одно значение определенного типа.
 * Попытка воспользоваться геттером типа, отличающегося от хранимого, вызовет исключение IllegalStateException
 */

public class ArgHolder {
    private final Object arg;
    private final ArgType argType;
    private final String argLabel;

    private static final String DEFAULT_ARGLABEL = "";

    public ArgHolder(Integer arg, String argLabel) {
        this.arg = arg;
        argType = ArgType.INTEGER;
        if(argLabel == null) {
            this.argLabel = DEFAULT_ARGLABEL;
        }
        else {
            this.argLabel = argLabel;
        }
    }

    public ArgHolder(Integer arg) {
        this(arg, DEFAULT_ARGLABEL);
    }

    public ArgHolder(String arg, String argLabel) {
        this.arg = arg;
        argType = ArgType.STRING;
        if(argLabel == null) {
            this.argLabel = DEFAULT_ARGLABEL;
        }
        else {
            this.argLabel = argLabel;
        }
    }

    public ArgHolder(String arg) {
        this(arg, DEFAULT_ARGLABEL);
    }

    public ArgHolder(ResultSet resultSet, ArgMask resultMask) throws SQLException {
        switch (resultMask.getArgType()) {
            case INTEGER:
                this.arg = resultSet.getInt(resultMask.getArgLabel());
                break;
            case STRING:
                this.arg = resultSet.getString(resultMask.getArgLabel());
                break;
            default: throw new UnsupportedOperationException();
        }
        this.argType = resultMask.getArgType();
        this.argLabel = resultMask.getArgLabel();
    }

    public ArgType getArgType() {
        return argType;
    }

    public int getInt() {
        if(argType != ArgType.INTEGER) {
            throw new IllegalStateException("This argumentContainer contains " + argType + " type");
        }
        return (Integer) arg;
    }

    public String getString() {
        if(argType != ArgType.STRING) {
            throw new IllegalStateException("This argumentContainer contains " + argType + " type");
        }
        return (String) arg;
    }

    public String getArgLabel(){
        return argLabel;
    }

    @Override
    public String toString() {
        return (argLabel.equals(DEFAULT_ARGLABEL) ? "" : argLabel + " = ") + arg.toString();
    }
}
