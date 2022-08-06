package com.legends.api;

import com.legends.api.exceptions.NoSuchPlatformException;
import com.legends.data.player.PlayerData;
import com.legends.data.player.PlayerURIParams;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

public class ApexLegendsAPIAccess {

    public static final String URL = "https://api.mozambiquehe.re/bridge?version=5&";

    public static PlayerData getPlayer(@NotNull String name, @NotNull String platform, @NotNull String key) {
        String url = assembleURL(name, platform, key);

        JSONObject playerData;

        try {
            playerData = new JSONObject(Jsoup.connect(url).get().body().text());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (playerData.has("Error"))
            return null;
        return new PlayerData(playerData, new PlayerURIParams(name, platform, key));
    }

    public static PlayerData getPlayer(PlayerURIParams playerURIParams) {
        return getPlayer(playerURIParams.getName(), playerURIParams.getPlatform(), playerURIParams.getAPIKey());
    }

    public static String assembleURL(@NotNull String name, @NotNull String platform, @NotNull String key) {
        if (!(platform.equals("PC") || platform.equals("PS4") || platform.equals("X1")))
            throw new NoSuchPlatformException(platform);
        return URL + "player=" + name + "&" + "platform=" + platform + "&" + "auth=" + key;
    }
}
