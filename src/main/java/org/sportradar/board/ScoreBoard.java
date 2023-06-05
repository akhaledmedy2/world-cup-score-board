package org.sportradar.board;

import org.sportradar.dto.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {
    private List<Match> matches;

    public ScoreBoard(List<Match> matches) {
        this.matches = matches;
    }

    public Match startGame(int matchIndex) {
        Match match = getMatch(matchIndex);
        match.setStartTime(new Date());
        return match;
    }

    public Match updateScore(int matchIndex, int homeScore, int awayScore) {
        Match match = getMatch(matchIndex);
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
        matches.set(matchIndex, match);
        return match;
    }

    public Match endGame(int matchIndex) {
        return matches.remove(matchIndex);
    }

    public List<Match> getBoardSummery() {
        List<Match> sortedMatches = new ArrayList<>(matches);

        sortedMatches = sortedMatches.stream().sorted(Comparator.comparing(Match::getTotalScore).reversed()).collect(Collectors.toList());

        return sortedMatches;

    }

    private Match getMatch(int matchIndex) {
        try {
            return matches.get(matchIndex);
        } catch (IndexOutOfBoundsException ex) {
            throw new NullPointerException("match not found");
        }
    }
}