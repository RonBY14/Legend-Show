package com.legends.ui;

import com.legends.data.DataAccess;
import com.legends.ui.components.ExtendedJPanel;

public abstract class UI extends ExtendedJPanel implements UISetup {

    protected PanelContainer panelContainer;
    protected DataAccess dataAccess;

    public UI(PanelContainer panelContainer, DataAccess dataAccess) {
        this.panelContainer = panelContainer;
        this.dataAccess = dataAccess;
    }
    
    public void update() {}
}
