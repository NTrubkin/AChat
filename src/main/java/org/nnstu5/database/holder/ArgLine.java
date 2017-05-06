package org.nnstu5.database.holder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Trubkin Nikita
 * ArgLine хранит и обрабатывает коллекцию ArgHolder
 * Важной особенностью ArgLine является возмножность упаковки результатов ResultSet(которые удаляются по закрытии Statement)
 *
 * неоптимально, так как каждый контейнер хранит лейбл
 */
public class ArgLine {
    private final ArrayList<ArgHolder> args = new ArrayList<ArgHolder>();

    public ArgLine(ResultSet resultSet, ArgMask[] resultMasks) throws SQLException {
        for (int i = 0; i < resultMasks.length; i++) {
            args.add(new ArgHolder(resultSet, resultMasks[i]));
        }
    }

    public List<ArgHolder> getArgs() {
        return args;
    }

    public ArgHolder getArgHolder(String label) {
        for (ArgHolder arg : args) {
            if (arg.getArgLabel().equals(label)) {
                return arg;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return args.toString();
    }
}
