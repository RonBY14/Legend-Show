package com.legends.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.legends.data.DataAccess;
import com.legends.ui.panels.BasePanel;
import com.legends.ui.utils.ResourceRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class PanelContainer extends JFrame implements UISetup {

    public static final Font GLOBAL_FONT = new Font("Return of Ganon Regular", Font.PLAIN, 22);

    private final DataAccess dataAccess;

    public PanelContainer() {
        dataAccess = new DataAccess();

        setup();
    }

    @Override
    public void setup() {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("ui/fonts/app_font.ttf"));
            //Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/ui/fonts/app_font.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

            FlatLightLaf.setup();
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("LegendShow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(new BasePanel(this, dataAccess));
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                getContentPane().setPreferredSize(getSize());
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void teardown() {}

    public static void main(String[] args) {
        try {
            Class.forName("com.legends.ui.utils.ResourceRepository");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(PanelContainer::new);
    }
}
