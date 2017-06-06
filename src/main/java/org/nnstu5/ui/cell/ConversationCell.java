package org.nnstu5.ui.cell;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import org.nnstu5.container.Conversation;

import java.io.IOException;

/**
 * @author Trubkin Nikita
 */
public class ConversationCell extends ListCell<Conversation> {
    private static final String FXML = "/cell/conversationCell.fxml";
    private static final String LABEL_ID = "label";

    @Override
    public void updateItem(Conversation item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            try {
                Parent cell = FXMLLoader.load(getClass().getResource(FXML));
                for(Node node : cell.getChildrenUnmodifiable()) {
                    if(node.getId() != null && node.getId().equals(LABEL_ID)) {
                        ((Label)node).setText(item.getName());
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