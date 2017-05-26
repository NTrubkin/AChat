package org.nnstu5.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * ControllerChat - реализует контроллерную часть mvc-паттерна визуального интерфейса чата.
 * Содержит методы-обработчики событий. Без бизнес-логики.
 */
public class ControllerChat {
    private Model model;

    @FXML
    private TextArea area;
    @FXML
    private TextField field;
    @FXML
    private VBox vBox;
    int count = 0;
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

    public void addMes() {


        Label userName = new Label();
        //userName.setLayoutX(70.0);
        userName.setText("USERNAME");
        userName.setStyle("-fx-background-color: blue");

        Label message = new Label();
        message.setMaxWidth(400.0);
        message.setLayoutX(70.0);
        message.setLayoutY(20.0);
        message.setWrapText(true);
        message.setText("Меня все заебало.. сильно, очень.. хочу уже каникулы, чтобы ни очем не думать, гулять, купаться и кататься на велике ");
        message.setStyle("-fx-background-color: orange");

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: red");
        pane.setPrefSize(100,200);

        pane.getChildren().addAll(userName, message);
        vBox.getChildren().add(pane);
       // System.out.println(vBox.getBoundsInParent().getHeight());
    }
}