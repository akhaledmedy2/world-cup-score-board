package org.sportradar.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sportradar.dto.Match;
import org.sportradar.dto.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    List<Match> matches;

    @BeforeEach
    public void setup() {
        matches = new ArrayList<>();
        matches.add(new Match(new Team("Mexico"), new Team("Canada")));
        matches.add(new Match(new Team("Spain"), new Team("Brazil")));
        matches.add(new Match(new Team("Germany"), new Team("France")));

        scoreBoard = new ScoreBoard(matches);
    }

    @Test
    public void startGame_WhenGameFound_MatchWillStart() {
        Match match = scoreBoard.startGame(2);
        assertNotNull(match.getStartTime());
    }

    @Test
    public void startGame_WhenGameNotFound_WillThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            scoreBoard.startGame(5);
        });
    }

    @Test
    public void updateScore_WhenGameFound_MatchScoreWillBeUpdated() {
        Match match = scoreBoard.updateScore(0, 1, 2);
        assertEquals(match.getTotalScore(), 3);
        assertEquals(matches.get(0).getHomeScore(), 1);
        assertEquals(matches.get(0).getAwayScore(), 2);
    }

    @Test
    public void endGame_WhenGameFound_MatchWillRemoved() {
        scoreBoard.endGame(1);
        assertEquals(matches.size(), 2);
    }

    @Test
    public void getBoardSummery_WhenGamesListIsNotEmpty_MatchesWillBeSorted() {
        scoreBoard.startGame(0);
        scoreBoard.startGame(1);
        scoreBoard.startGame(2);
        scoreBoard.updateScore(0, 3, 0);
        scoreBoard.updateScore(1, 4, 6);
        scoreBoard.updateScore(2, 7, 8);

        List<Match> sortedMatches = scoreBoard.getBoardSummery();
        assertEquals(sortedMatches.size(), 3);
        assertTrue(sortedMatches.get(0).getTotalScore() > sortedMatches.get(1).getTotalScore());
        assertTrue(sortedMatches.get(1).getTotalScore() > sortedMatches.get(2).getTotalScore());
    }

    @Test
    public void getBoardSummery_WhenGamesListIsEmpty_SortedListSizeWillBeZero() {
        scoreBoard = new ScoreBoard(new ArrayList<>());
        List<Match> sortedMatches = scoreBoard.getBoardSummery();
        assertEquals(sortedMatches.size(), 0);
    }

    @Test
    public void getBoardSummery_WhenTwoMatchesScoreAreEqual_MatchesWillBeSortedByStartTime() throws InterruptedException {
        scoreBoard.startGame(0);
        Thread.sleep(5000);
        scoreBoard.startGame(1);
        Thread.sleep(5000);
        scoreBoard.startGame(2);

        scoreBoard.updateScore(2, 3, 3);
        scoreBoard.updateScore(0, 3, 3);
        scoreBoard.updateScore(1, 3, 3);

        List<Match> sortedMatches = scoreBoard.getBoardSummery();
        assertTrue(sortedMatches.get(1).getStartTime().after(sortedMatches.get(0).getStartTime()));
        assertTrue(sortedMatches.get(2).getStartTime().after(sortedMatches.get(1).getStartTime()));
    }
}