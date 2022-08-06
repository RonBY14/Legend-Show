package com.legends.ui.factories;

import javax.swing.*;

public class JLabelFactory {

    public static JLabel getNoSurfaceLabel() {
        JLabel label = new JLabel();

        label.setOpaque(false);
        label.setBorder(null);
        label.setIconTextGap(0);
        return label;
    }
}
