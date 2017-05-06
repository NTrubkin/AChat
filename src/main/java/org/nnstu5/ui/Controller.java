package org.nnstu5.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller - реализует контроллерную часть mvc-паттерна визуального интерфейса чата.
 * Содержит методы-обработчики событий. Без бизнес-логики.
 */
public class Controller {
    private final Model model = new Model(this);

    @FXML
    private TextArea area;
    @FXML
    private TextField field;

    @FXML
    /**
     * Обрабатывает нажатие на кнопку отправить
     */
    void processSendButton() {
        String text = field.getText();
        model.sendMessage(text);
        field.clear();
    }

    /**
     * Добавляет в историю сообщений новое
     *
     * @param text текст нового сообщения
     */
    void appendMessage(String text) {
        area.appendText(text);
        area.appendText("\n");
    }
}