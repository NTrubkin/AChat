package org.nnstu5.ui.customElement;

import javafx.scene.control.Button;

/**
 * Created by Kuznetsov on 21.05.2017.
 */
public class ContainerButton extends Button {
    private int info;

    public ContainerButton(String text, int info) {
        super(text);
        this.info = info;
    }

    public int getInfo() {
        return info;
    }
}
