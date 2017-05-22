package org.nnstu5;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ChatRules определяет глобальные правила приложения и его элементов.
 * Также выступает в роли конфиг-файла, поскольку содержит параметры, влияющее на все приложение
 * Управляет всеми слоями приложения.
 * Рекомендуется все свои конфиг-параметры переносить сюда
 */
public class ChatRules {
    private static final int MAX_NICK_LENGTH = 30;
    private static final int MIN_NICK_LENGTH = 3;
    private static final int MAX_CONVERS_NAME_LENGTH = MAX_NICK_LENGTH;
    private static final int MIN_CONVERS_NAME_LENGTH = MIN_NICK_LENGTH;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final int MAX_EMAIL_LENGTH = 254;
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static final int PASS_HASH_LENGTH = 32;
    private static final int MAX_MESSAGE_TEXT_LENGTH = 200;
    private static final int MIN_MESSAGE_TEXT_LENGTH = 1;
    private static final int MSG_LIMIT = 1000;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 30;

    /**
     * Приватный конструктор, поскольку нет смысла создавать объект
     */
    private ChatRules() {
    }

    /**
     * Проверяет корректность никнейма по:
     * максимальному MAX_NICK_LENGTH и минимальному MIN_NICK_LENGTH количеству символов
     * @param nickname никнейм пользователя в системе
     * @return true - если корректен, false - если есть проблемы
     */
    public static boolean isValidUserNickname(String nickname) {
        return (StringUtils.isNotEmpty(nickname)) && (nickname.length() <= MAX_NICK_LENGTH) && (nickname.length() >= MIN_NICK_LENGTH);
    }

    /**
     * Проверяет корректность названия беседы по:
     * максимальному MAX_CONVERS_NAME_LENGTH и минимальному MIN_CONVERS_NAME_LENGTH количеству символов
     * @param name название беседы
     * @return true - если корректен, false - если есть проблемы
     */
    public static boolean isValidConversationName(String name) {
        return (StringUtils.isNotEmpty(name)) &&(name.length() <= MAX_CONVERS_NAME_LENGTH) && (name.length() >= MIN_CONVERS_NAME_LENGTH);
    }

    /**
     * Проверяет корректность email адреса по:
     * по regex шаблону EMAIL_PATTERN
     * максимальному MAX_EMAIL_LENGTH количеству символов
     * @param email адрес эл почты пользователя
     * @return true - если корректен, false - если есть проблемы
     */
    public static boolean isValidUserEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return (StringUtils.isNotEmpty(email)) &&(email.length() <= MAX_EMAIL_LENGTH) && (matcher.matches());
    }

    /**
     * Проверяет корректность passHash по:
     * максимальному PASS_HASH_LENGTH количеству символов
     * @param passHash md5-хэш пароля пользователя
     * @return true - если корректен, false - если есть проблемы
     */
    public static boolean isValidUserPassHash(String passHash) {
        return (StringUtils.isNotEmpty(passHash)) &&(passHash.length() == PASS_HASH_LENGTH);
    }

    /**
     * Проверяет корректность текста сообщения по:
     * максимальной MAX_MESSAGE_TEXT_LENGTH и минимальной MIN_MESSAGE_TEXT_LENGTH длине сообщения
     * @param text сообщение пользователя
     * @return true - если корректен, false - если есть проблемы
     */
    public static boolean isValidMessageText(String text) {
        return (StringUtils.isNotEmpty(text)) &&(text.length() >= MIN_MESSAGE_TEXT_LENGTH) && (text.length() <= MAX_MESSAGE_TEXT_LENGTH);
    }

    public static boolean isValidPassword(String password) {
        return (StringUtils.isNotEmpty(password)) &&(password.length() >= MIN_PASSWORD_LENGTH) && (password.length() <= MAX_PASSWORD_LENGTH);
    }

    /**
     * Возвращает предел количества сообщений в выборке из базы
     * @return MSG_LIMIT
     */
    public static int getMsgLimit() {
        return MSG_LIMIT;
    }
}
