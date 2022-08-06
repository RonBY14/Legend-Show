package com.legends.data.player;

import org.jetbrains.annotations.NotNull;

public class PlayerURIParams {

    private final String name;
    private final String platform;
    private final String APIKey;

    public PlayerURIParams(@NotNull String name, @NotNull String platform, @NotNull String APIKey) {
        this.name = name;
        this.platform = platform;
        this.APIKey = APIKey;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getAPIKey() {
        return APIKey;
    }
}
