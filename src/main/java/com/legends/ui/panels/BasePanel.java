package com.legends.ui.panels;

import com.legends.data.DataAccess;
import com.legends.data.player.PlayerData;
import com.legends.ui.PanelContainer;
import com.legends.ui.UI;
import com.legends.ui.components.ExtendedJPanel;
import com.legends.ui.factories.JButtonFactory;
import com.legends.ui.utils.ResourceRepository;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class BasePanel extends UI implements ActionListener, WindowFocusListener {

    private ExtendedJPanel controlBar;
    private JButton profileButton;
    private JButton usernameButton;
    private JButton homeButton;
    private JButton searchButton;

    private UI view;

    private JDialog searchBox;

    public BasePanel(PanelContainer panelContainer, DataAccess dataAccess) {
        super(panelContainer, dataAccess);

        setup();
    }

    @Override
    public void setup() {
        setBackground(Color.yellow);
        setLayout(new MigLayout("fill"));
        controlBarSetup();
        searchBoxSetup();
        setView(new HomePanel(panelContainer, dataAccess));

        update();
    }

    private void controlBarSetup() {
        controlBar = new ExtendedJPanel();
        controlBar.setLayout(new MigLayout("insets 5 5 5 5", "[right][center][left][l]"));
        controlBar.setImageBackground(ResourceRepository.getImage("control_bar"));
        controlBar.setBackground(Color.GREEN);
        add(controlBar, "dock north");

        profileButton = JButtonFactory.getNoSurfaceButton();
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.addActionListener(this);
        controlBar.add(profileButton, "align left, push");

        usernameButton = JButtonFactory.getNoSurfaceButton();
        usernameButton.setForeground(Color.black);
        usernameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        usernameButton.addActionListener(this);
        controlBar.add(usernameButton, "align left, cell 0 0");

        homeButton = JButtonFactory.getNoSurfaceButton();
        ImageIcon homeButtonIcon = new ImageIcon(ResourceRepository
                .getImage("home_button")
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        homeButton.setIcon(homeButtonIcon);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.addActionListener(this);
        controlBar.add(homeButton, "align right");

        searchButton = JButtonFactory.getNoSurfaceButton();
        ImageIcon searchButtonIcon = new ImageIcon(ResourceRepository
                .getImage("search_button")
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        searchButton.setIcon(searchButtonIcon);
        searchButton.addActionListener(this);
        controlBar.add(searchButton, "align right");
    }

    private void searchBoxSetup() {
        searchBox = new JDialog(panelContainer);
        searchBox.addWindowFocusListener(this);
        searchBox.setResizable(false);
        searchBox.setUndecorated(true);

        SearchPanel searchPanel = new SearchPanel(panelContainer, dataAccess, this);
        searchPanel.setup();
        searchBox.setContentPane(searchPanel);
        searchBox.pack();
    }

    @Override
    public void teardown() {}

    public void setView(@NotNull UI ui) {
        if (view != null)
            remove(view);
        view = ui;

        view.setup();
        add(view, "dock center");

        revalidate();
    }

    @Override
    public void update() {
        PlayerData playerData = dataAccess.getRegisteredPlayerData();

        ImageIcon profilePicture;

        if (playerData == null) {
            profilePicture = new ImageIcon(ResourceRepository
                    .getImage("default_profile_picture")
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            usernameButton.setText("register");
        } else {
            profilePicture = new ImageIcon(ResourceRepository
                    .getImage(playerData.getSelectedLegend().toUpperCase())
                    .getScaledInstance(30, 30, BufferedImage.SCALE_SMOOTH));
            usernameButton.setText(dataAccess.getRegisteredPlayerData().getName());
        }
        profileButton.setIcon(profilePicture);

        if (view != null && view instanceof ProfilePanel)
            setView(new ProfilePanel(panelContainer, dataAccess));
        else
            view.update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(profileButton) || e.getSource().equals(usernameButton)) {
            PlayerData playerData = dataAccess.getUnregisteredPlayerData();

            if (playerData != null) {
                dataAccess.setRegisteredPlayerData(playerData);
                update();
            } else if (dataAccess.getRegisteredPlayerData() != null) {
                setView(new ProfilePanel(panelContainer, dataAccess));
            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "No player was chosen. Can't register...",
                        "Can't Register",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource().equals(homeButton)) {
            if (!(view instanceof HomePanel)) {
                setView(new HomePanel(panelContainer, dataAccess));
                dataAccess.setUnregisteredPlayerData(null);
            }
        } else if (e.getSource().equals(searchButton)) {
            Point point = new Point(searchButton.getLocationOnScreen());
            Point thisPoint = getLocationOnScreen();

            if (thisPoint.x + getWidth() + 220 >= Toolkit.getDefaultToolkit().getScreenSize().getWidth()) {
                searchBox.setLocation(searchButton.getLocationOnScreen().x - searchBox.getWidth() + searchButton.getWidth(),
                                    point.y + searchButton.getHeight() + 20);
            } else {
                searchBox.setLocation(point.x - searchBox.getWidth() / 2 - 50, point.y + searchButton.getHeight() + 20);
            }
            searchBox.setVisible(!searchBox.isVisible());
        }
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {}

    @Override
    public void windowLostFocus(WindowEvent e) {
        if (e.getSource().equals(searchBox)) {
            searchBox.setVisible(false);
        }
    }
}
