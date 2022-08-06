package com.legends.ui.factories;

import javax.swing.*;
import java.awt.*;

public class JButtonFactory {

    public static JButton getNoSurfaceButton() {
        JButton button = new JButton();

        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(null);
        button.setIconTextGap(0);
        return button;
    }
}
