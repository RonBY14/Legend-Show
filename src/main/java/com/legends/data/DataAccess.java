package com.legends.data;

import com.legends.data.player.PlayerData;

public class DataAccess {

    private PlayerData registeredPlayerData;
    private PlayerData unregisteredPlayerData;

    public DataAccess() {}

    public PlayerData getRegisteredPlayerData() {
        return registeredPlayerData;
    }

    public void setRegisteredPlayerData(PlayerData registeredPlayerData) {
        this.registeredPlayerData = registeredPlayerData;
    }

    public PlayerData getUnregisteredPlayerData() {
        return unregisteredPlayerData;
    }

    public void setUnregisteredPlayerData(PlayerData unregisteredPlayerData) {
        this.unregisteredPlayerData = unregisteredPlayerData;
    }
}
