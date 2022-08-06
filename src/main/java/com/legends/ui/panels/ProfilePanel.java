package com.legends.ui.panels;

import com.legends.data.DataAccess;
import com.legends.data.player.MatchData;
import com.legends.data.player.PlayerData;
import com.legends.ui.PanelContainer;
import com.legends.ui.UI;
import com.legends.ui.components.ExtendedJPanel;
import com.legends.ui.components.MatchDataPrefab;
import com.legends.ui.factories.JLabelFactory;
import com.legends.ui.utils.ResourceRepository;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ProfilePanel extends UI implements ActionListener {

    private ExtendedJPanel profileDataPane;
    private JLabel selectedLegend;
    private JLabel playerName;
    private JLabel playerUID;

    private ExtendedJPanel rankedDataPane;
    private JLabel playerRank;
    private JLabel playerRankScore;
    private JLabel seasonSplit;

    private ExtendedJPanel matchHistoryPane;
    private JLabel matchHistoryIndicationLabel;

    public ProfilePanel(PanelContainer panelContainer, DataAccess dataAccess) {
        super(panelContainer, dataAccess);
    }

    @Override
    public void setup() {
        setLayout(new MigLayout("insets 5 5 5 5, fill", "[center][right][left][l]"));
        setImageBackground(ResourceRepository.getImage("profile_panel"));

        profileDataSetup();
        rankedDataSetup();
        matchHistorySetup();

        update();
    }

    private void profileDataSetup() {
        Font font = PanelContainer.GLOBAL_FONT;

        profileDataPane = new ExtendedJPanel(new MigLayout("insets 5 5 5 5", "[center][right][left][l]"));
        profileDataPane.setPreferredSize(new Dimension(200, 200));
        profileDataPane.setBackground(new Color(0, 0, 0, 100));
        selectedLegend = new JLabel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                PlayerData playerData = dataAccess.getUnregisteredPlayerData();

                if (playerData == null)
                    playerData = dataAccess.getRegisteredPlayerData();

                if (playerData.isOnline())
                    g.drawImage(ResourceRepository.getImage("connected_icon"), 0, 0, 32, 32, null);
                else
                    g.drawImage(ResourceRepository.getImage("disconnected_icon"), 0, 0, 32, 32, null);
            }
        };
        add(profileDataPane, "align left, split");

        profileDataPane.add(selectedLegend, "align left, wrap 5px");

        playerName = JLabelFactory.getNoSurfaceLabel();
        playerName.setFont(font);
        profileDataPane.add(playerName, "align left, wrap");

        playerUID = JLabelFactory.getNoSurfaceLabel();
        playerUID.setFont(font.deriveFont(18f));
        profileDataPane.add(playerUID, "align left");
    }

    private void rankedDataSetup() {
        Font font = PanelContainer.GLOBAL_FONT;

        rankedDataPane = new ExtendedJPanel(new MigLayout("insets 5 5 5 5", "[center][right][left][l]"));
        rankedDataPane.setPreferredSize(new Dimension(200, 200));
        rankedDataPane.setBackground(new Color(0, 0, 0, 100));
        add(rankedDataPane, "align left, split, wrap");

        playerRank = new JLabel();
        playerRank.setFont(font);
        rankedDataPane.add(playerRank, "align left, wrap");

        playerRankScore = new JLabel();
        playerRankScore.setFont(font);
        rankedDataPane.add(playerRankScore, "align left, wrap");

        seasonSplit = new JLabel();
        seasonSplit.setFont(font);
        rankedDataPane.add(seasonSplit, "align left");
    }

    private void matchHistorySetup() {
        Font font = PanelContainer.GLOBAL_FONT;

        matchHistoryPane = new ExtendedJPanel();
        matchHistoryPane.setLayout(new GridLayout(12, 1, 0, 1));
        matchHistoryPane.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height / 2));
        matchHistoryPane.setBackground(new Color(0, 0, 0, 100));

        PlayerData playerData = dataAccess.getUnregisteredPlayerData();

        if (playerData == null)
            playerData = dataAccess.getRegisteredPlayerData();
        Stack<MatchData> matchHistory = playerData.getMatchHistory();

        if (matchHistory.isEmpty()) {
            matchHistoryPane.setLayout(new MigLayout("insets 5 5 5 5", "[center][right][left][c]"));

            matchHistoryIndicationLabel = new JLabel("No Match Data Available");
            matchHistoryIndicationLabel.setFont(font.deriveFont(28f));
            matchHistoryPane.add(matchHistoryIndicationLabel, "align center, push");
        } else {
            if (playerData.isInMatch()) {
             //   MatchData matchData = new MatchData()
            }

            for (MatchData matchData : matchHistory)
                matchHistoryPane.add(new MatchDataPrefab(panelContainer, dataAccess, matchData));
        }
        add(matchHistoryPane, "dock south, grow");
    }

    @Override
    public void teardown() {}

    @Override
    public void update() {
        PlayerData playerData = dataAccess.getUnregisteredPlayerData();

        if (playerData == null)
            playerData = dataAccess.getRegisteredPlayerData();

        ImageIcon selectedLegendIcon = new ImageIcon(ResourceRepository
                .getImage(playerData.getSelectedLegend().toUpperCase())
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        selectedLegend.setIcon(selectedLegendIcon);
        selectedLegend.repaint();

        playerName.setText(playerData.getName() + " [" + playerData.getLevel() + "lvl]");
        playerUID.setText("UID: " + playerData.getUid());

        playerRank.setText("Rank: " + playerData.getRankName());
        playerRankScore.setText("Rank Score: " + playerData.getRankScore());
        String[] temp = playerData.getSeasonSplit().split("_");
        seasonSplit.setText("Season Split: Season " + temp[0].substring(6) + " Split " + temp[2]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
