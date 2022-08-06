package com.legends.ui.panels;

import com.legends.data.DataAccess;
import com.legends.ui.PanelContainer;
import com.legends.ui.UI;
import com.legends.ui.utils.ResourceRepository;
import net.miginfocom.swing.MigLayout;

public class HomePanel extends UI {

    public HomePanel(PanelContainer panelContainer, DataAccess dataAccess) {
        super(panelContainer, dataAccess);
    }

    @Override
    public void setup() {
        setImageBackground(ResourceRepository.getImage("home_panel"));
        setLayout(new MigLayout());
    }

    @Override
    public void teardown() {

    }
}
