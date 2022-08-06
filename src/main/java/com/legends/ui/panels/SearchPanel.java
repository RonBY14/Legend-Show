package com.legends.ui.panels;

import com.legends.api.ApexLegendsAPIAccess;
import com.legends.data.DataAccess;
import com.legends.data.player.PlayerData;
import com.legends.ui.PanelContainer;
import com.legends.ui.UI;
import com.legends.ui.utils.ProgressBarAnimator;
import com.legends.ui.utils.ResourceRepository;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchPanel extends UI implements ActionListener {

    private final BasePanel basePanel;

    private JComboBox<String> searchBox;
    private JComboBox<String> platformBox;
    private JButton searchButton;

    private JProgressBar searchProgress;

    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;

    private boolean searchRunning;

    public SearchPanel(PanelContainer panelContainer, DataAccess dataAccess, @NotNull BasePanel basePanel) {
        super(panelContainer, dataAccess);

        this.basePanel = basePanel;
    }

    @Override
    public void setup() {
        mouseHandler = new MouseHandler();
        keyHandler = new KeyHandler();

        setPreferredSize(new Dimension(600, 50));
        setLayout(new MigLayout("", "fill, grow"));
        addMouseListener(mouseHandler);
        addKeyListener(keyHandler);

        searchUISetup();

        searchRunning = false;
    }

    private void searchUISetup() {
        searchBox = new JComboBox<>();
        searchBox.setPreferredSize(new Dimension(getPreferredSize().width, 25));

        searchBox.setEditable(true);
        searchBox.setSelectedItem("");
        searchBox.getEditor().getEditorComponent().addMouseListener(mouseHandler);
        searchBox.getEditor().getEditorComponent().addKeyListener(keyHandler);
        add(searchBox);

        platformBox = new JComboBox<>();
        platformBox.addItem("PC");
        platformBox.addItem("PS4");
        platformBox.addItem("X1");
        platformBox.setSelectedIndex(0);
        add(platformBox);

        Image searchButtonIcon = ResourceRepository
                .getImage("search_button")
                .getScaledInstance(25, searchBox.getPreferredSize().height, Image.SCALE_SMOOTH);

        searchButton = new JButton(new ImageIcon(searchButtonIcon));
        searchButton.setMaximumSize(new Dimension(25, searchBox.getPreferredSize().height));
        searchButton.setMargin(new Insets(0, 0, 0, 0));
        searchButton.setOpaque(false);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setBorder(null);
        searchButton.addActionListener(this);
        add(searchButton, "wrap");

        searchProgress = new JProgressBar(0, 100);
        searchProgress.setForeground(Color.green);
        add(searchProgress, "wrap, align left");
    }

    @Override
    public void teardown() {

    }

    public void searchPlayer() {
        searchRunning = true;
        SwingWorker searchAnimation = ProgressBarAnimator.getAnimationThread(searchProgress, 40, 20);
        searchAnimation.execute();

        new SwingWorker<>() {

            @Override
            protected Object doInBackground() {
                String name = searchBox.getSelectedItem().toString();
                String platform = platformBox.getSelectedItem().toString();
                String key = "RkXTtZwNa2wEqKMl9ndA";

                PlayerData playerData = ApexLegendsAPIAccess.getPlayer(name, platform, key);
                searchProgress.setValue(0);

                if (playerData == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Player " + name + " Not Found! Try to check name spelling " +
                                    "and make sure the platform is correct or search again, else the player does not " +
                                    "exist.",
                            "Error - Player Not Found",
                            JOptionPane.ERROR_MESSAGE);
                    searchProgress.setValue(0);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    searchRunning = false;
                    return null;
                } else if (!ResourceRepository.isLegendSupported(playerData.getSelectedLegend())) {
                    JOptionPane.showMessageDialog(
                            null,
                            "[FATAL] The player is playing an unsupported legend -> " + playerData.getSelectedLegend() +
                                    ".. Sorry, this is the last version of this app...",
                            "Error - Unsupported Legend",
                            JOptionPane.ERROR_MESSAGE);
                    searchProgress.setValue(0);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    searchRunning = false;
                    return 0;
                }
                dataAccess.setUnregisteredPlayerData(playerData);
                searchProgress.setValue(80);
                basePanel.setView(new ProfilePanel(panelContainer, dataAccess));
                searchProgress.setValue(100);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                searchRunning = false;
                return null;
            }
        }.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            if (!searchRunning)
                searchPlayer();
        }
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource().equals(searchBox.getEditor().getEditorComponent())) {
                searchBox.showPopup();
            }
        }
    }

    private class KeyHandler extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getSource().equals(searchBox.getEditor().getEditorComponent())) {
                searchBox.showPopup();
            }
        }
    }
}
