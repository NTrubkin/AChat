package org.nnstu5.ui.cell;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import org.nnstu5.container.Message;

import java.io.IOException;

/**
 * @author Trubkin Nikita
 */
public class MessageCell extends ListCell<Message> {
    private static final String FXML = "/cell/messageCell.fxml";
    private static final String TEXT_ID = "text";
    private static final String SENDER_ID = "sender";

    @Override
    public void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            try {
                Parent cell = FXMLLoader.load(getClass().getResource(FXML));
                for(Node node : cell.getChildrenUnmodifiable()) {
                    if(node.getId() != null) {
                        if(node.getId().equals(TEXT_ID)) {
                            ((Label)node).setText(item.getText());
                        }
                        if(node.getId().equals(SENDER_ID)) {
                            ((Label)node).setText(item.getSenderId() + "");
                        }
                    }
                }
                setGraphic(cell);
            }
            catch (IOException exc) {
                System.out.println("Cannot load fxml cell");
                setGraphic(null);
            }
        } else {
            setGraphic(null);
        }
    }
}