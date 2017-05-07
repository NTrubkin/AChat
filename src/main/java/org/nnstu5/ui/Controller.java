package org.nnstu5.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller - реализует контроллерную часть mvc-паттерна визуального интерфейса чата.
 * Содержит методы-обработчики событий. Без бизнес-логики.
 */
public class Controller {
    private Model model;

    @FXML
    private TextArea area;
    @FXML
    private TextField field;

    @FXML
    public void initialize() {
        // модель необходимо конструировать после того, как будут инициализированы поля разметки
        // иначе модель не сможет работать с полями
         model = new Model(this);
    }

    /**
     * Обрабатывает нажатие на кнопку отправить
     */
    @FXML
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