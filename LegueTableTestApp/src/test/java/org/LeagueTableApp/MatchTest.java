package org.LeagueTableApp;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class MatchTest {

    @Test(expected = NullPointerException.class)
    public void checkMatchCreationWithAwayTeamIsNull(){
        new Match("Arsenal", null, 0,0);
    }

    @Test(expected = NullPointerException.class)
    public void checkMatchCreationWithHomeTeamIsNull(){
        new Match(null, "Arsenal",0,0);
    }

    @Test(expected = NullPointerException.class)
    public void checkInvalidTeamNameInGoalsForMethod(){
        Match match = new Match("Arsenal", "Liverpool", 0, 0);
        match.getTeamGoalsFor("Chelsey");
    }

    @Test(expected = NullPointerException.class)
    public void checkTeamNameIsNullInGoalsForMethod(){
        Match match = new Match("Arsenal", "Liverpool", 0, 0);
        match.getTeamGoalsFor(null);
    }

    @Test(expected = NullPointerException.class)
    public void checkInvalidTeamNameInGoalsAgainstMethod(){
        Match match = new Match("Arsenal", "Liverpool", 0, 0);
        match.getTeamGoalsAgainst("Chelsey");
    }

    @Test(expected = NullPointerException.class)
    public void checkTeamNameIsNullInGoalsAgainstMethod(){
        Match match = new Match("Arsenal", "Liverpool", 0, 0);
        match.getTeamGoalsAgainst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkHomeScoreIsNegative(){
        new Match("Arsenal", "Liverpool", -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAwayScoreIsNegative(){
        new Match("Arsenal", "Liverpool", 0, -1);
    }

    @Test
    public void checkConcurrency(){

        int matchesPerSeason = 38;
        for (int i = 0; i < 1000; i++) {
            List<Match> matches = parseCSV();
            List<LeagueTableEntry> tableEntries = new LeagueTable(matches).getTableEntries();
            tableEntries.forEach(tableEntry -> assertEquals(matchesPerSeason, tableEntry.getPlayed()));
        }
    }

    private List<Match> parseCSV() {
        List<Match> list = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = "PremierLeague2015-2016.csv";
        File file = new File(classLoader.getResource(fileName).getFile());
        try (Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                Match match = new Match(scanner.next(), scanner.next(), Integer.valueOf(scanner.next()), Integer.valueOf(scanner.next()));
                list.add(match);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}