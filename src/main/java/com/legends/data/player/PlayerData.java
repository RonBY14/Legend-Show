package com.legends.data.player;

import com.legends.data.exceptions.MatchStackException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Stack;

public class PlayerData {

    public static final int MAX_MATCHES = 12;

    private final PlayerURIParams playerURIParams;

    private final JSONObject playerData;

    private String selectedLegend;
    private boolean isOnline;
    private String name;
    private short level;
    private long uid;

    private String rankName;
    private int rankScore;
    private String seasonSplit;

    private boolean isInMatch;

    private Stack<MatchData> matchHistory;

    private int totalKills;

    public PlayerData(JSONObject playerData, PlayerURIParams playerURIParams) {
        this.playerURIParams = playerURIParams;

        this.playerData = playerData;
        init();
    }

    public PlayerURIParams getPlayerURIParams() {
        return playerURIParams;
    }

    public JSONObject getPlayerData() {
        return playerData;
    }

    public String getSelectedLegend() {
        return selectedLegend;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getName() {
        return name;
    }

    public short getLevel() {
        return level;
    }

    public long getUid() {
        return uid;
    }

    public String getRankName() {
        return rankName;
    }

    public int getRankScore() {
        return rankScore;
    }

    public String getSeasonSplit() {
        return seasonSplit;
    }

    public boolean isInMatch() {
        return isInMatch;
    }

    public Stack<MatchData> getMatchHistory() {
        return matchHistory;
    }

    public void setMatchHistory(Stack<MatchData> matchHistory) {
        if (matchHistory.size() > MAX_MATCHES)
            throw new MatchStackException("Stack exceeded the maximum size!");
        this.matchHistory = matchHistory;
    }

    private void init() {
        matchHistory = new Stack<>();
        matchHistory.add(new MatchData(MatchData.DATE_FORMAT.format(new Date()), MatchData.DATE_FORMAT.format(new Date(31556926000l * 53))));

        retrieveFromDocument();
    }

    private void retrieveFromDocument() {
        JSONObject global = playerData.getJSONObject("global");
        JSONObject rank = global.getJSONObject("rank");

        JSONObject realtime = playerData.getJSONObject("realtime");

        selectedLegend = realtime.getString("selectedLegend");
        isOnline = realtime.getLong("isOnline") != 0;
        name = global.getString("name");
        level = (short) global.getLong("level");
        uid = global.getLong("uid");

        rankName = rank.getString("rankName");
        rankScore = (int) rank.getLong("rankScore");
        seasonSplit = rank.getString("rankedSeason");

        isInMatch = realtime.getLong("isInGame") != 0;

        System.out.println(playerData.toString(4));
    }

    public void addMatch(MatchData matchData) {
        if (matchHistory.size() > MAX_MATCHES)
            matchHistory.remove(matchHistory.lastElement());
        matchHistory.add(matchData);
    }

    public String toString() {
        return  "uid: " + uid +
                "\nName: " + name +
                "\nLevel: " + level +
                "\n######" +
                "\nRank Name: " + rankName +
                "\nRank Score: " + rankScore +
                "\nSeason Split: " + seasonSplit +
                "\n######" +
                "\nIs Online: " + isOnline +
                "\nIs In Match: " + isInMatch +
                "\nSelected Legend: " + selectedLegend;
    }
}
