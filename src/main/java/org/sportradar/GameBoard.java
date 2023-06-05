package org.sportradar;

import org.sportradar.board.ScoreBoard;
import org.sportradar.dto.Match;
import org.sportradar.dto.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameBoard {
    public static void main(String[] args) {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match(new Team("Mexico"), new Team("Canada")));
        matches.add(new Match(new Team("Spain"), new Team("Brazil")));
        matches.add(new Match(new Team("Germany"), new Team("France")));
        matches.add(new Match(new Team("Uruguay"), new Team("Italy")));
        matches.add(new Match(new Team("Argentina"), new Team("Portugal")));
        matches.add(new Match(new Team("Egypt"), new Team("Senegal")));

        ScoreBoard scoreBoard = new ScoreBoard(matches);

        Match match = scoreBoard.startGame(0);
        System.out.println(match + " started at " + match.getStartTime());
        match = scoreBoard.startGame(2);
        System.out.println(match + " started at " + match.getStartTime());
        match = scoreBoard.startGame(4);
        System.out.println(match + " started at " + match.getStartTime());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        match = scoreBoard.startGame(1);
        System.out.println(match + " started at " + match.getStartTime());
        match = scoreBoard.startGame(3);
        System.out.println(match + " started at " + match.getStartTime());
        match = scoreBoard.startGame(5);
        System.out.println(match + " started at " + match.getStartTime());


        match =  scoreBoard.updateScore(0, 1, 3);
        System.out.println(match + " score updated at " + new Date());
        match = scoreBoard.updateScore(1, 4, 4);
        System.out.println(match + " score updated at " + new Date());
        match =  scoreBoard.updateScore(2, 3, 2);
        System.out.println(match + " score updated at " + new Date());
        match = scoreBoard.updateScore(3, 6, 4);
        System.out.println(match + " score updated at " + new Date());
        match = scoreBoard.updateScore(4, 6, 5);
        System.out.println(match + " score updated at " + new Date());
        match = scoreBoard.updateScore(5, 3, 0);
        System.out.println(match + " score updated at " + new Date());

        List<Match> matchesSummery = scoreBoard.getBoardSummery();
        matchesSummery.forEach(System.out::println);

        match = scoreBoard.endGame(3);
        System.out.println(match + " ended at " + new Date());

        matchesSummery = scoreBoard.getBoardSummery();
        matchesSummery.forEach(System.out::println);
    }
}