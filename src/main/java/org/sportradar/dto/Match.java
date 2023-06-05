package org.sportradar.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private Date startTime;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(homeTeam.getName()).append(" ").append(homeScore)
                .append(" - ")
                .append(awayScore).append(" ").append(awayTeam.getName());
        return sb.toString();
    }
}