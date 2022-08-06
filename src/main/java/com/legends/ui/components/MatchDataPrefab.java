package com.legends.ui.components;

import com.legends.data.DataAccess;
import com.legends.data.player.MatchData;
import com.legends.ui.PanelContainer;
import com.legends.ui.UI;
import com.legends.ui.factories.JLabelFactory;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MatchDataPrefab extends UI {

    private MatchData matchData;

    private JLabel startTime;

    public MatchDataPrefab(PanelContainer panelContainer, DataAccess dataAccess, MatchData matchData) {
        super(panelContainer, dataAccess);

        this.matchData = matchData;

        setup();
    }

    @Override
    public void setup() {
        setBackground(new Color(0, 0, 0, 100));
        setLayout(new MigLayout("insets 5 5 5 5", "[left][center][right][l]"));

        Font font = PanelContainer.GLOBAL_FONT.deriveFont(18f);

        startTime = JLabelFactory.getNoSurfaceLabel();
        startTime.setFont(font);

        if (matchData.getEndTime() != null) {
 //           startTime.setText(matchData.getStartTime() + " - " + matchData.getEndTime() + " Length: " + matchData.getFormattedLength());
        } else {
            //startTime.setText(matchData.getStartTime() + " Length: " + matchData.getFormattedLength());
        }
        add(startTime, "align left");
    }

    @Override
    public void teardown() {}
}
