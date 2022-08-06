package com.legends.data.player;

import java.text.SimpleDateFormat;

public class MatchData {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    private String startTime;
    private String endTime;

    private String kills;
    private String damage;

    public MatchData(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public MatchData() {}


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKills() {
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}
