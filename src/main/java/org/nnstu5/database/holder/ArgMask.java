package org.nnstu5.database.holder;

/**
 * @author Trubkin Nikita
 *
 * ArgMask определяет маску для создания ArgHolder, то есть определяет все кроме хранимого значения
 * Это может быть полезно для формирования результатов select sql-запроса из ResultSet
 */
public class ArgMask {
    private final ArgType argType;
    private final String argLabel;

    public ArgMask(ArgType argType, String argLabel) {
        this.argType = argType;
        this.argLabel = argLabel;
    }

    public ArgHolder createArgHolder(int arg) {
        if(argType != ArgType.INTEGER) {
            throw new IllegalStateException("This argMask requires " + argType + " type");
        }
        return new ArgHolder(arg, argLabel);
    }

    public ArgHolder createArgHolder(String arg) {
        if(argType != ArgType.STRING) {
            throw new IllegalStateException("This argMask requires " + argType + " type");
        }
        return new ArgHolder(arg, argLabel);
    }

    public ArgType getArgType() {
        return argType;
    }

    public String getArgLabel() {
        return argLabel;
    }
}
