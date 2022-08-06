package com.legends.api;

import com.legends.data.DataAccess;
import com.legends.data.player.PlayerData;
import com.legends.ui.PanelContainer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ScheduledDataUpdater implements Runnable {

    public static final int SCHEDULE_EVERY = 10000;

    private final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private Future<?> future;

    private final PanelContainer panelContainer;
    private final DataAccess dataAccess;

    public ScheduledDataUpdater(PanelContainer panelContainer, DataAccess dataAccess) {
        this.panelContainer = panelContainer;
        this.dataAccess = dataAccess;
    }

    public synchronized void start() {
        if (future != null && !future.isDone())
            return;
        future = EXECUTOR.submit(this);
    }

    public PlayerData requestPlayerData(PlayerData requestedPlayer) {
        PlayerData playerData = null;

        while (requestedPlayer != null && playerData == null) {
            playerData = ApexLegendsAPIAccess.getPlayer(requestedPlayer.getPlayerURIParams());

            if (playerData != null) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return playerData;
    }

    @Override
    public void run() {
        while (!future.isCancelled()) {
            PlayerData registeredCurrentData = dataAccess.getRegisteredPlayerData();
            PlayerData unregisteredCurrentData = dataAccess.getUnregisteredPlayerData();

            PlayerData registeredNewData = dataAccess.getRegisteredPlayerData();
            PlayerData unregisteredNewData = dataAccess.getUnregisteredPlayerData();

            if (registeredNewData != null) {
                registeredNewData.setMatchHistory(registeredCurrentData.getMatchHistory());
                dataAccess.setRegisteredPlayerData(registeredNewData);

                System.out.println("Player's Data Updated.");
                //panelContainer.update();
            }

            if (unregisteredNewData != null) {
                unregisteredNewData.setMatchHistory(unregisteredCurrentData.getMatchHistory());
                dataAccess.setUnregisteredPlayerData(unregisteredNewData);

                System.out.println("Player's Data Updated.");
                //panelContainer.update();
            }

            try {
                Thread.sleep(SCHEDULE_EVERY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
